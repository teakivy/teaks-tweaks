package me.teakivy.teakstweaks.packs.antiendermangrief;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class AntiEndermanGrief extends BasePack {

    public AntiEndermanGrief() {
        super(TTPack.ANTI_ENDERMAN_GRIEF, Material.ENDERMAN_SPAWN_EGG);
    }

    @EventHandler
    public void onEndermanPickup(EntityChangeBlockEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ENDERMAN) {
            event.setCancelled(true);
        }
    }

}
