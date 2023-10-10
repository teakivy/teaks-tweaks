package me.teakivy.teakstweaks.packs.mobs.antiendermangrief;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class AntiEnderman extends BasePack {

    public AntiEnderman() {
        super("anti-enderman-grief", PackType.MOBS, Material.ENDERMAN_SPAWN_EGG);
    }

    @EventHandler
    public void onEndermanPickup(EntityChangeBlockEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ENDERMAN) {
            event.setCancelled(true);
        }
    }

}
