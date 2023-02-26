package me.teakivy.teakstweaks.packs.teakstweaks.chatcolors;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChatColors extends BasePack {

    public ChatColors() {
        super("Chat Colors", "chat-colors", PackType.TEAKSTWEAKS, Material.PURPLE_WOOL);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!getConfig().getBoolean("chat")) return;
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }

    @EventHandler
    private void onAnvil(PrepareAnvilEvent event) {
        if (!getConfig().getBoolean("items")) return;
        ItemStack result = event.getResult();
        if (result == null) return;
        if (!result.hasItemMeta()) return;

        ItemMeta meta = result.getItemMeta();
        if (meta == null) return;
        if (!meta.hasDisplayName()) return;

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', meta.getDisplayName()));
        result.setItemMeta(meta);
        event.setResult(result);
    }

    @EventHandler
    public void onSign(SignChangeEvent event) {
        if (!getConfig().getBoolean("signs")) return;
        for (int i = 0; i < event.getLines().length; i++) {
            if (event.getLine(i) == null) continue;
            event.setLine(i, ChatColor.translateAlternateColorCodes('&', event.getLine(i)));
        }
    }

}
