package me.teakivy.teakstweaks.packs.mobs.batmembranes;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

public class BatMembranes extends BasePack {

    public BatMembranes() {
        super("Bat Membranes", "bat-membranes", PackType.MOBS, Material.PHANTOM_MEMBRANE, "Disable Phantoms to get membranes from Bats instead.");
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
