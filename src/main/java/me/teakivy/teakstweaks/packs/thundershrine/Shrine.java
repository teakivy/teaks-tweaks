package me.teakivy.teakstweaks.packs.thundershrine;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shrine extends BasePack {
    private int particleTask = -1;

    public Shrine() {
        super("thunder-shrine", Material.NETHER_STAR);
    }

    @Override
    public void init() {
        super.init();
        register();
    }

    public void register() {
        if (!getConfig().getBoolean("idle-particles")) particleTask = -1;

        particleTask = new BukkitRunnable() {
            @Override
            public void run() {
                runParticles();
            }
        }.runTaskTimer(TeaksTweaks.getInstance(), 0, 3L).getTaskId();
    }

    public static void runParticles() {
        for (Entity shrine : getShrines()) {
            shrine.getWorld().spawnParticle(Particle.ENCHANT, shrine.getLocation().add(0, 1, 0), 1, 0.1, 0.1, 0.1, 1);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (!Permission.THUNDER_SHRINE_USE.check(event.getPlayer())) return;
        if (item.getType() != Material.getMaterial(getConfig().getString("summoning.summoning-item"))) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (isNearShrine(event.getItemDrop().getLocation())) {
                    event.getItemDrop().remove();
                    startThunder(event.getPlayer(), event.getItemDrop().getLocation());
                }
                if (event.getItemDrop().isDead() || event.getItemDrop().getItemStack().getAmount() != 1) this.cancel();
            }
        }.runTaskTimer(TeaksTweaks.getInstance(), 0, 20L);
    }

    public static void startThunder(Player player, Location loc) {
        ConfigurationSection config = Config.get().getConfigurationSection("packs.thunder-shrine");
        World world = player.getWorld();
        if (config.getBoolean("summoning.strike-lightning")) {
            world.strikeLightning(loc.add(0, +1, 0));
        }
        if (config.getBoolean("summoning.show-particles")) {
            world.spawnParticle(Particle.FLAME, loc, 100, 0, 0, 0, .5);
        }
        world.setWeatherDuration(6000);
        world.setThunderDuration(6000);
        world.setStorm(true);
        world.setThundering(true);
        if (!config.getBoolean("summoning.broadcast-message")) return;

        for (Player oPlayer : Bukkit.getOnlinePlayers()) {
            oPlayer.sendMessage(Translatable.get("storm_initialize"));
        }
    }

    public static List<Entity> getShrines() {
        List<Entity> shrines = new ArrayList<>();
        Bukkit.getWorlds().forEach(world -> world.getEntities().forEach(entity -> {
            if (isShrine(entity)) {
                shrines.add(entity);
            }
        }));
        return shrines;
    }

    public static boolean isShrine(Entity entity) {
        if (!(entity instanceof Marker)) return false;
        return entity.getPersistentDataContainer().has(Key.get("thunder-shrine"), PersistentDataType.BOOLEAN);
    }

    public static boolean isNearShrine(Location loc) {
        for (Entity entity : Objects.requireNonNull(loc.getWorld()).getEntities()) {
            if (isShrine(entity)) {
                if (entity.getLocation().distance(loc) < 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void createShrine(Location loc) throws IOException {
        Marker shrine = (Marker) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc.add(0.5, 0, 0.5), EntityType.MARKER);

        PersistentDataContainer container = shrine.getPersistentDataContainer();
        container.set(Key.get("thunder-shrine"), PersistentDataType.BOOLEAN, true);
    }

    @Override
    public void unregister() {
        super.unregister();
        if (particleTask != -1) {
            Bukkit.getScheduler().cancelTask(particleTask);
        }
    }

}
