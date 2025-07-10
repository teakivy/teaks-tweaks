package me.teakivy.teakstweaks.packs.anticreepergrief;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class AntiCreeper extends BasePack {

    public AntiCreeper() {
        super("anti-creeper-grief", Material.CREEPER_SPAWN_EGG);
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.CREEPER) && !getConfig().getBoolean("do-block-damage")) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageByEntityEvent event) {
        EntityDamageEvent.DamageCause damageCause = event.getCause();

        if (damageCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION && !getConfig().getBoolean("do-entity-damage")) {
            if (event.getDamager().getType().equals(EntityType.CREEPER)) {
                event.setCancelled(true);
            }
        }
    }

}
