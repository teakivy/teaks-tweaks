package me.teakivy.vanillatweaks.Packs.AntiCreeperGreif;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class AntiCreeper implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        if (!main.getConfig().getBoolean("packs.anti-creeper-grief.enabled")) return;
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.CREEPER)) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageByEntityEvent event) {
        if (!main.getConfig().getBoolean("packs.anti-creeper-grief.enabled")) return;
        EntityDamageEvent.DamageCause damageCause = event.getCause();

        if (damageCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            if (event.getDamager().getType().equals(EntityType.CREEPER)) {
                event.setCancelled(true);
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
