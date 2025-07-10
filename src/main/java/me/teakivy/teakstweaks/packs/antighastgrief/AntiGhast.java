package me.teakivy.teakstweaks.packs.antighastgrief;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

public class AntiGhast extends BasePack {

    public AntiGhast() {
        super("anti-ghast-grief", Material.GHAST_SPAWN_EGG);
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.FIREBALL)) {
            event.blockList().clear();
        }
    }
}
