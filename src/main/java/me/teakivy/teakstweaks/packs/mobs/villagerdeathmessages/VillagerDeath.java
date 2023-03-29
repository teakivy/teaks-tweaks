package me.teakivy.teakstweaks.packs.mobs.villagerdeathmessages;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class VillagerDeath extends BasePack {

    public VillagerDeath() {
        super("Villager Death Messages", "villager-death-messages", PackType.MOBS, Material.TORCH, "Notifies the server in chat when a villager dies. Displayes Coordinates and Dimension that it died in.");
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.VILLAGER) {
            Location loc = event.getEntity().getLocation();
            String deathMessage = MessageHandler.getMessage("pack.villager-death-messages.death-message")
                    .replace("%x%", Math.floor(loc.getX()) + "")
                    .replace("%y%", Math.floor(loc.getY()) + "")
                    .replace("%z%", Math.floor(loc.getZ()) + "")
                    .replace("world", getWorldName(loc));

            if (getConfig().getBoolean("show-in-chat")) {
                Bukkit.broadcastMessage(deathMessage);
            } else {
                Logger.log(Logger.LogLevel.INFO, deathMessage);
            }
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


}
