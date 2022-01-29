package me.teakivy.teakstweaks.Packs.Mobs.AntiEndermanGrief;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class AntiEnderman extends BasePack {

    public AntiEnderman() {
        super("Anti Enderman Grief", "anti-enderman-grief");
    }

    @EventHandler
    public void onEndermanPickup(EntityChangeBlockEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ENDERMAN) {
            event.setCancelled(true);
        }
    }

}
