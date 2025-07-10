package me.teakivy.teakstweaks.packs.largerphantoms;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Objects;

public class Phantoms extends BasePack {

    public Phantoms() {
        super("larger-phantoms", Material.PHANTOM_SPAWN_EGG);
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.PHANTOM || (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL && !Config.isDevMode())) return;
        Phantom phantom = (Phantom) entity;
        Player player = null;
        double distance = Integer.MAX_VALUE;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getUID() != Objects.requireNonNull(event.getLocation().getWorld()).getUID()) continue;
            if (p.getLocation().distanceSquared(event.getLocation()) < distance) {
                player = p;
                distance = p.getLocation().distanceSquared(event.getLocation());
            }
        }
        if (player == null || distance > 1000) return;
        int lastSleepTicks = player.getStatistic(Statistic.TIME_SINCE_REST);
        if (lastSleepTicks < 144000) return; // 2 Hours
        if (lastSleepTicks < 216000) { // 3 Hours
            setPhantomAttributes(phantom, 3, 25, 1, 20, 15);
            return;
        }
        if (lastSleepTicks < 288000) { // 4 Hours
            setPhantomAttributes(phantom, 5, 30, 1.3, 24, 17);
            return;
        }
        if (lastSleepTicks < 1728000) { // 24 Hours
            setPhantomAttributes(phantom, 7, 35, 1.6, 28, 20);
            return;
        }
        setPhantomAttributes(phantom, 20, 100, 2, 50, 30);

    }

    public void setPhantomAttributes(Phantom phantom, int size, int maxHeath, double movementSpeed, int followRange, int attackDamage) {
        phantom.setSize(size);
        Objects.requireNonNull(phantom.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(maxHeath);
        Objects.requireNonNull(phantom.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(movementSpeed);
        Objects.requireNonNull(phantom.getAttribute(Attribute.FOLLOW_RANGE)).setBaseValue(followRange);
        Objects.requireNonNull(phantom.getAttribute(Attribute.ATTACK_DAMAGE)).setBaseValue(attackDamage);
        phantom.setHealth(maxHeath);
    }

}
