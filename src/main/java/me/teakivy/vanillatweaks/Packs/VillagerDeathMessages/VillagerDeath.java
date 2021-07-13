package me.teakivy.vanillatweaks.Packs.VillagerDeathMessages;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class VillagerDeath implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (!main.getConfig().getBoolean("packs.villager-death-messages.enabled")) return;
        if (event.getEntity().getType() == EntityType.VILLAGER) {
            Location loc = event.getEntity().getLocation();
            String deathMessage = ChatColor.YELLOW + "A villager has died! " + ChatColor.RESET + "(" + ChatColor.GOLD + "XYZ: " + Math.floor(loc.getX()) + " " + Math.floor(loc.getY()) + " " + Math.floor(loc.getZ()) + ChatColor.YELLOW + getWorldName(loc) + ChatColor.RESET + ")";

            if (main.getConfig().getBoolean("packs.villager-death-messages.show-in-chat")) Bukkit.broadcastMessage(deathMessage);
            else System.out.println(deathMessage);
        }
    }

    private String getWorldName(Location loc) {
        World world = loc.getWorld();
        if (world == null) return "";
        if (world.getName().equalsIgnoreCase("world")) return ChatColor.YELLOW + " in " + ChatColor.GREEN + "Overworld";
        if (world.getName().equalsIgnoreCase("world_nether")) return ChatColor.YELLOW + " in " + ChatColor.RED + "The Nether";
        if (world.getName().equalsIgnoreCase("world_the_end")) return ChatColor.YELLOW + " in " + ChatColor.LIGHT_PURPLE + "The End";
        return "";
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }


}
