package me.teakivy.teakstweaks.packs.teakstweaks.chatcolors;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChatColors extends BasePack {

    public ChatColors() {
        super("chat-colors", PackType.TEAKSTWEAKS, Material.PURPLE_WOOL);
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        if (!getConfig().getBoolean("chat")) return;
        if (!hasPermission(event.getPlayer())) return;

        event.message(legacyToMiniMessage(event.message()));
    }

    @EventHandler
    private void onAnvil(PrepareAnvilEvent event) {
        if (!getConfig().getBoolean("items")) return;
        if (!hasPermission((Player) event.getInventory().getViewers().get(0))) return;
        ItemStack result = event.getResult();
        if (result == null) return;
        if (!result.hasItemMeta()) return;

        ItemMeta meta = result.getItemMeta();
        if (meta == null) return;
        if (!meta.hasDisplayName()) return;

        meta.displayName(legacyToMiniMessage(meta.displayName()));
        result.setItemMeta(meta);
        event.setResult(result);
    }

    @EventHandler
    public void onSign(SignChangeEvent event) {
        if (!getConfig().getBoolean("signs")) return;
        if (!hasPermission(event.getPlayer())) return;

        for (int i = 0; i < event.lines().size(); i++) {
            if (event.line(i) == null) continue;
            event.line(i, legacyToMiniMessage(event.line(i)));
        }
    }

    private Component legacyToMiniMessage(Component legacy) {
        String text = MiniMessage.miniMessage().serialize(legacy);
        text = text.replaceAll("&a", "<green>");
        text = text.replaceAll("&b", "<aqua>");
        text = text.replaceAll("&c", "<red>");
        text = text.replaceAll("&d", "<light_purple>");
        text = text.replaceAll("&e", "<yellow>");
        text = text.replaceAll("&f", "<white>");
        text = text.replaceAll("&0", "<black>");
        text = text.replaceAll("&1", "<dark_blue>");
        text = text.replaceAll("&2", "<dark_green>");
        text = text.replaceAll("&3", "<dark_aqua>");
        text = text.replaceAll("&4", "<dark_red>");
        text = text.replaceAll("&5", "<dark_purple>");
        text = text.replaceAll("&6", "<gold>");
        text = text.replaceAll("&7", "<gray>");
        text = text.replaceAll("&8", "<dark_gray>");
        text = text.replaceAll("&9", "<blue>");
        text = text.replaceAll("&k", "<obfuscated>");
        text = text.replaceAll("&l", "<bold>");
        text = text.replaceAll("&m", "<strikethrough>");
        text = text.replaceAll("&n", "<underlined>");
        text = text.replaceAll("&o", "<italic>");
        text = text.replaceAll("&r", "<reset>");
        return newText(text);
    }

}
