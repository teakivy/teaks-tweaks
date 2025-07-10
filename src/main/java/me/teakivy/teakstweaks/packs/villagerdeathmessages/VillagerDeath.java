package me.teakivy.teakstweaks.packs.villagerdeathmessages;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.log.Logger;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class VillagerDeath extends BasePack {

    public VillagerDeath() {
        super("villager-death-messages", Material.TORCH);
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.VILLAGER) {
            Location loc = event.getEntity().getLocation();
            Component deathMessage = getText("death_message",
                    insert("x", loc.getBlockX()),
                    insert("y", loc.getBlockY()),
                    insert("z", loc.getBlockZ()),
                    insert("world", getWorldName(loc)));

            if (getConfig().getBoolean("show-in-chat")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(deathMessage);
                }
                return;
            }
            Logger.info(deathMessage);
        }
    }

    private Component getWorldName(Location loc) {
        World world = loc.getWorld();
        if (world == null) return newText("<yellow>Unknown World");

        return switch (world.getName()) {
            case "world" -> newText("<yellow>The Overworld");
            case "world_nether" -> newText("<red>The Nether");
            case "world_the_end" -> newText("<light_purple>The End");
            default -> newText("<yellow>" + world.getName());
        };
    }


}
