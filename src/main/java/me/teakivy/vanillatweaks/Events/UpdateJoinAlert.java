package me.teakivy.vanillatweaks.Events;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoinAlert implements Listener {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            if (main.getConfig().getBoolean("config.alert-op-on-new-version")) {
                if (main.newVersionAvaliable) {
                    player.sendMessage(vt + ChatColor.YELLOW + "There is a new Version of Vanilla Tweaks avaliable! Please update to the latest version: " + ChatColor.GOLD + main.latestVTVersion);
                }
            }
        }
    }

}
