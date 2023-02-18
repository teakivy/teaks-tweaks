package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoinAlert implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.isOp()) return;
        if (!main.getConfig().getBoolean("config.alert-op-on-new-version")) return;
        if (!main.newVersionAvailable) return;

        player.sendMessage(ChatColor.YELLOW + "There is a new Version of Teaks Tweaks available! " +
            "Please update to the latest version: " +
            ChatColor.BOLD + ChatColor.GOLD +  main.latestVersion);
    }

}
