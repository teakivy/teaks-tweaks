package me.teakivy.teakstweaks.Packs.Mobs.BatMembranes;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

public class BatMembranes extends BasePack {

    public BatMembranes() {
        super("Bat Membranes", "bat-membranes");
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.PHANTOM) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if (event.getEntityType() != EntityType.BAT) return;

        event.getDrops().add(new ItemStack(Material.PHANTOM_MEMBRANE));
    }
}
