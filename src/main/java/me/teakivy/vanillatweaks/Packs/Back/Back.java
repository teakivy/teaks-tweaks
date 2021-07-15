package me.teakivy.vanillatweaks.Packs.Back;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
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
    static String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public static void tpBack(Player player) {
        if (backLoc.containsKey(player.getUniqueId())) {
            player.teleport(backLoc.get(player.getUniqueId()));
        } else {
            player.sendMessage(vt + ChatColor.RED + "You have nowhere to go back to!");
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
