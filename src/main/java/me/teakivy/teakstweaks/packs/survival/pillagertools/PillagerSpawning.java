package me.teakivy.teakstweaks.packs.survival.pillagertools;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;

public class PillagerSpawning extends BasePack {

    public PillagerSpawning() {
        super("Pillager Tools", "pillager-tools", PackType.SURVIVAL, Material.PILLAGER_SPAWN_EGG, "Allows you do disable bad omen, patrols, or patrol leaders separately");
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (getConfig().getBoolean("disable-patrols")) {
            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.PATROL) {
                event.setCancelled(true);
            }
        }

        if (getConfig().getBoolean("disable-leaders")) {
            if (event.getEntity().getType() == EntityType.PILLAGER) {
                Pillager pillager = (Pillager) event.getEntity();
                if (pillager.isPatrolLeader()) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPotion(EntityPotionEffectEvent event) {
        if (getConfig().getBoolean("disable-bad-omen")) {
            if (event.getCause() == EntityPotionEffectEvent.Cause.PATROL_CAPTAIN) {
                event.setCancelled(true);
            }
        }
    }

}
