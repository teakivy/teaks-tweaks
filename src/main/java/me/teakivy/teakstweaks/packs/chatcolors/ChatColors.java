package me.teakivy.teakstweaks.packs.chatcolors;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.StringUtils;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChatColors extends BasePack {

    public ChatColors() {
        super(TTPack.CHAT_COLORS, Material.PURPLE_WOOL);
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        if (!Permission.CHAT_COLORS_CHAT.check(event.getPlayer())) return;

        MiniMessage mm = MiniMessage.miniMessage();
        event.message(mm.deserialize(mm.serialize(StringUtils.parseLegacyChatColors(event.message())).replaceAll("\\\\<", "<")));
    }

    @EventHandler
    private void onAnvil(PrepareAnvilEvent event) {
        if (!Permission.CHAT_COLORS_ANVIL.check(event.getInventory().getViewers().getFirst())) return;
        ItemStack result = event.getResult();
        if (result == null) return;
        if (!result.hasItemMeta()) return;

        ItemMeta meta = result.getItemMeta();
        if (meta == null) return;
        if (!meta.hasDisplayName()) return;

        MiniMessage mm = MiniMessage.miniMessage();
        meta.displayName(mm.deserialize(mm.serialize(StringUtils.parseLegacyChatColors(meta.displayName())).replaceAll("\\\\<", "<")));
        result.setItemMeta(meta);
        event.setResult(result);
    }

    @EventHandler
    public void onSign(SignChangeEvent event) {
        if (!Permission.CHAT_COLORS_SIGNS.check(event.getPlayer())) return;
        MiniMessage mm = MiniMessage.miniMessage();

        for (int i = 0; i < event.lines().size(); i++) {
            if (event.line(i) == null) continue;
            event.line(i, mm.deserialize(mm.serialize(StringUtils.parseLegacyChatColors(event.line(i))).replaceAll("\\\\<", "<")));
        }
    }

}
