package me.teakivy.teakstweaks.Packs.TeaksTweaks.ChatColors;

import me.teakivy.teakstweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChatColors implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!main.getConfig().getBoolean("packs.chat-colors.chat")) return;
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }

    @EventHandler
    private void onAnvil(PrepareAnvilEvent event) {
        if (!main.getConfig().getBoolean("packs.chat-colors.items")) return;
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
        if (!main.getConfig().getBoolean("packs.chat-colors.signs")) return;
        for (int i = 0; i < event.getLines().length; i++) {
            if (event.getLine(i) == null) continue;
            event.setLine(i, ChatColor.translateAlternateColorCodes('&', event.getLine(i)));
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
