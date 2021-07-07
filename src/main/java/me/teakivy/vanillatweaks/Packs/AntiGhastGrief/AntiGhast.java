package me.teakivy.vanillatweaks.Packs.AntiGhastGrief;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.material.MaterialData;

import java.util.Objects;
import java.util.Set;

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
}
