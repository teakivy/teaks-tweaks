package me.teakivy.vanillatweaks.Packs.AntiEndermanGrief;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class AntiEnderman implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onEndermanPickup(EntityChangeBlockEvent event) {
        if (!main.getConfig().getBoolean("packs.anti-enderman-grief.enabled")) return;
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ENDERMAN) {
            event.setCancelled(true);
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
