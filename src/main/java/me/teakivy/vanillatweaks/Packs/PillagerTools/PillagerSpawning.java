package me.teakivy.vanillatweaks.Packs.PillagerTools;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;

public class PillagerSpawning implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (main.getConfig().getBoolean("packs.pillager-tools.disable-patrols")) {
            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.PATROL) {
                event.setCancelled(true);
            }
        }

        if (main.getConfig().getBoolean("packs.pillager-tools.disable-leaders")) {
            if (event.getEntity().getType() != EntityType.PILLAGER) {
                Pillager pillager = (Pillager) event.getEntity();
                if (pillager.isPatrolLeader()) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPotion(EntityPotionEffectEvent event) {
        if (main.getConfig().getBoolean("packs.pillager-tools.disable-bad-omen")) {
            if (event.getCause() == EntityPotionEffectEvent.Cause.PATROL_CAPTAIN) {
                event.setCancelled(true);
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
