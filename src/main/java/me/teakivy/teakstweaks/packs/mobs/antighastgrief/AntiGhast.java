package me.teakivy.teakstweaks.packs.mobs.antighastgrief;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

public class AntiGhast extends BasePack {

    public AntiGhast() {
        super("Anti Ghast Grief", "anti-ghast-grief");
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.FIREBALL)) {
            event.blockList().clear();
        }
    }
}
