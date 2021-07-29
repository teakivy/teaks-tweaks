package me.teakivy.vanillatweaks.Packs.Mobs.LargerPhantoms;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class Phantoms implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.PHANTOM || (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL && !main.getConfig().getBoolean("config.dev-mode"))) return;
        Phantom phantom = (Phantom) entity;
        Player player = null;
        double distance = Integer.MAX_VALUE;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getLocation().distanceSquared(event.getLocation()) < distance) {
                player = p;
                distance = p.getLocation().distanceSquared(event.getLocation());
            }
        }
        if (player == null || distance > 1000) return;
        int lastSleepTicks = player.getStatistic(Statistic.TIME_SINCE_REST);
        if (lastSleepTicks < 144000) return;
        if (lastSleepTicks < 216000) {
            setPhantomAttributes(phantom, 3, 25, 1, 20, 15);
            return;
        }
        if (lastSleepTicks < 288000) {
            setPhantomAttributes(phantom, 5, 30, 1.3, 24, 17);
            return;
        }
        if (lastSleepTicks < 2400000) {
            setPhantomAttributes(phantom, 7, 35, 1.6, 28, 20);
            return;
        }
        setPhantomAttributes(phantom, 20, 100, 2, 50, 30);

    }

    public void setPhantomAttributes(Phantom phantom, int size, int maxHeath, double movementSpeed, int followRange, int attackDamage) {
        phantom.setSize(size);
        phantom.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHeath);
        phantom.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(movementSpeed);
        phantom.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        phantom.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        phantom.setHealth(maxHeath);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
