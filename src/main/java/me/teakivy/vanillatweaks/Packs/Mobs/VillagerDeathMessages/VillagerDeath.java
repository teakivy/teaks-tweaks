package me.teakivy.vanillatweaks.Packs.Mobs.VillagerDeathMessages;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.Logger.Log;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
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
            String deathMessage = MessageHandler.getMessage("pack.villager-death-messages.death-message")
                    .replace("%x%", Math.floor(loc.getX()) + "")
                    .replace("%y%", Math.floor(loc.getY()) + "")
                    .replace("%z%", Math.floor(loc.getZ()) + "")
                    .replace("world", getWorldName(loc));

            if (main.getConfig().getBoolean("packs.villager-death-messages.show-in-chat"))
                Bukkit.broadcastMessage(deathMessage);
            else Log.message(deathMessage);
        }
    }

    private String getWorldName(Location loc) {
        World world = loc.getWorld();
        if (world == null) return "";
        if (world.getName().equalsIgnoreCase("world")) return ChatColor.YELLOW + " in " + ChatColor.GREEN + "Overworld";
        if (world.getName().equalsIgnoreCase("world_nether"))
            return ChatColor.YELLOW + " in " + ChatColor.RED + "The Nether";
        if (world.getName().equalsIgnoreCase("world_the_end"))
            return ChatColor.YELLOW + " in " + ChatColor.LIGHT_PURPLE + "The End";
        return "";
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }


}
