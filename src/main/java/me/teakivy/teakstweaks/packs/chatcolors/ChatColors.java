package me.teakivy.teakstweaks.packs.chatcolors;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.teakivy.teakstweaks.packs.BasePack;
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
        event.message(mm.deserialize(mm.serialize(legacyToMiniMessage(event.message())).replaceAll("\\\\<", "<")));
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
        meta.displayName(mm.deserialize(mm.serialize(legacyToMiniMessage(meta.displayName())).replaceAll("\\\\<", "<")));
        result.setItemMeta(meta);
        event.setResult(result);
    }

    @EventHandler
    public void onSign(SignChangeEvent event) {
        if (!Permission.CHAT_COLORS_SIGNS.check(event.getPlayer())) return;
        MiniMessage mm = MiniMessage.miniMessage();

        for (int i = 0; i < event.lines().size(); i++) {
            if (event.line(i) == null) continue;
            event.line(i, mm.deserialize(mm.serialize(legacyToMiniMessage(event.line(i))).replaceAll("\\\\<", "<")));
        }
    }

    private Component legacyToMiniMessage(Component legacy) {
        legacy = legacy.replaceText(b -> b.matchLiteral("&a").replacement("<green>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&b").replacement("<aqua>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&c").replacement("<red>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&d").replacement("<light_purple>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&e").replacement("<yellow>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&f").replacement("<white>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&0").replacement("<black>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&1").replacement("<dark_blue>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&2").replacement("<dark_green>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&3").replacement("<dark_aqua>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&4").replacement("<dark_red>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&5").replacement("<dark_purple>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&6").replacement("<gold>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&7").replacement("<gray>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&8").replacement("<dark_gray>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&9").replacement("<blue>"));

        legacy = legacy.replaceText(b -> b.matchLiteral("&k").replacement("<obfuscated>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&l").replacement("<bold>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&m").replacement("<strikethrough>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&n").replacement("<underlined>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&o").replacement("<italic>"));
        legacy = legacy.replaceText(b -> b.matchLiteral("&r").replacement("<reset>"));

        return legacy;
    }

}
