package me.teakivy.teakstweaks.Events;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.MessageHandler;
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
                    player.sendMessage(vt + MessageHandler.getMessage("plugin.update-join-alert").replace("%latest_version%", main.latestVTVersion));
                }
            }
        }
    }

}
