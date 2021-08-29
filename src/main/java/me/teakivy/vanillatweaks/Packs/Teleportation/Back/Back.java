package me.teakivy.vanillatweaks.Packs.Teleportation.Back;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.ErrorType;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class Back implements Listener {

    Main main = Main.getPlugin(Main.class);

    public static HashMap<UUID, Location> backLoc = new HashMap<>();

    public static void tpBack(Player player) {
        if (!player.hasPermission("vanillatweaks.back.teleport")) {
            player.sendMessage(ErrorType.MISSING_PERMISSION.m());
            return;
        }
        if (backLoc.containsKey(player.getUniqueId())) {
            player.teleport(backLoc.get(player.getUniqueId()));
        } else {
            player.sendMessage(MessageHandler.getMessage("pack.back.no-spot"));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!main.getConfig().getBoolean("packs.back.save-death-location")) return;

        backLoc.put(player.getUniqueId(), player.getLocation());
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
