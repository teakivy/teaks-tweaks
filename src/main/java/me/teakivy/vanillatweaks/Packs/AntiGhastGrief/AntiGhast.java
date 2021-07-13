package me.teakivy.vanillatweaks.Packs.AntiGhastGrief;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class AntiGhast implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        if (!main.getConfig().getBoolean("packs.anti-ghast-grief.enabled")) return;
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.FIREBALL)) {
            event.blockList().clear();
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }
}
