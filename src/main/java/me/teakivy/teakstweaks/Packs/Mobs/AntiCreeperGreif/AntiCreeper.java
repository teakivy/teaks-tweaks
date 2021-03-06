package me.teakivy.teakstweaks.Packs.Mobs.AntiCreeperGreif;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class AntiCreeper extends BasePack {

    public AntiCreeper() {
        super("Anti Creeper Grief", "anti-creeper-grief");
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.CREEPER)) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageByEntityEvent event) {
        EntityDamageEvent.DamageCause damageCause = event.getCause();

        if (damageCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            if (event.getDamager().getType().equals(EntityType.CREEPER)) {
                event.setCancelled(true);
            }
        }
    }

}
