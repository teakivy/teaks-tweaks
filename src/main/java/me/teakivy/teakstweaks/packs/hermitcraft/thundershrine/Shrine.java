package me.teakivy.teakstweaks.packs.hermitcraft.thundershrine;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Shrine extends BasePack {

    int particleTask = -1;

    public Shrine() {
        super("thunder-shrine", PackType.HERMITCRAFT, Material.NETHER_STAR);
    }

    @Override
    public void init() {
        super.init();
        register();
    }

    public void register() {
        if (getConfig().getBoolean("idle-particles")) {
            particleTask = new BukkitRunnable() {
                @Override
                public void run() {
                    runParticles();
                }
            }.runTaskTimer(Main.getInstance(), 0, 3L).getTaskId();
        } else {
            particleTask = -1;
        }
    }

    public static void runParticles() {
        List<Entity> shrines = getShrines();
        if (shrines == null) return;
        for (Entity shrine : getShrines()) {
            if (shrine == null) {
                removeShrine(shrine);
            } else if (shrine.isDead()) {
                removeShrine(shrine);
            } else {
                shrine.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, shrine.getLocation().add(0, 1, 0), 1, 0.1, 0.1, 0.1, 1);
            }
        }

    }

    public static void removeShrine(Entity shrine) {
        getDataConfig().set("thunder-shrines." + shrine.getUniqueId(), null);
        try {
            getData().saveConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getData().reloadConfig();
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (!hasPermission(event.getPlayer())) {
            return;
        }
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.getType() == Material.getMaterial(getConfig().getString("summoning.summoning-item"))) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (isShrine(event.getItemDrop())) {
                        event.getItemDrop().remove();
                        startThunder(event.getPlayer(), event.getItemDrop().getLocation());
                    }
                    if (event.getItemDrop().isDead() || event.getItemDrop().getItemStack().getAmount() != 1) this.cancel();
                }
            }.runTaskTimer(Main.getInstance(), 0, 20L);
        }
    }

    public static void startThunder(Player player, Location loc) {
        ConfigurationSection config = Main.getInstance().getConfig().getConfigurationSection("packs.thunder-shrine");
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
        if (config.getBoolean("summoning.brodcast-message")) {
            Bukkit.broadcastMessage(Translatable.get("storm_initialize"));
        }
    }

    public static List<Entity> getShrines() {
        if (!getDataConfig().contains("thunder-shrines")) return null;
        List<Entity> shrines = new ArrayList<>();
        for (String shrine : Objects.requireNonNull(getDataConfig().getConfigurationSection("thunder-shrines")).getKeys(false)) {
            if (Bukkit.getEntity(UUID.fromString(Objects.requireNonNull(getDataConfig().getString("thunder-shrines." + shrine + ".id")))) == null) {
                getDataConfig().set("thunder-shrines." + shrine, null);
                try {
                    getData().saveConfig();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getData().reloadConfig();
            } else {
                shrines.add(Bukkit.getEntity(UUID.fromString(Objects.requireNonNull(getDataConfig().getString("thunder-shrines." + shrine + ".id")))));
            }
        }
        return shrines;
    }

    public static boolean isShrine(Entity entity) {
        for (Entity shrine : Objects.requireNonNull(getShrines())) {
            if (shrine.getNearbyEntities(.5, .5, .5).contains(entity)) return true;
        }
        return false;
    }

    public static void createShrine(Location loc) throws IOException {
        Marker shrine = (Marker) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.MARKER);
        getDataConfig().set("thunder-shrines." + shrine.getUniqueId() + ".id", shrine.getUniqueId().toString());
        getData().saveConfig();
    }

    @Override
    public void unregister() {
        super.unregister();
        if (particleTask != -1) {
            Bukkit.getScheduler().cancelTask(particleTask);
        }
    }

}
