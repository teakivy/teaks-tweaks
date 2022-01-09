package me.teakivy.teakstweaks;

import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoinAlert implements Listener {

    Main main = Main.getPlugin(Main.class);
    String vt = MessageHandler.prefix;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            if (main.getConfig().getBoolean("config.alert-op-on-new-version")) {
                if (main.newVersionAvaliable) {
                    player.sendMessage(ChatColor.GRAY.toString() + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "TT"  + ChatColor.RESET.toString() + ChatColor.GRAY.toString() + "]"  + ChatColor.RESET.toString() + ChatColor.YELLOW + " There is a new Version of Teaks Tweaks avaliable! Please update to the latest version: " + ChatColor.BOLD + ChatColor.GOLD +  main.latestVTVersion);
                }
            }
        }
    }

}
