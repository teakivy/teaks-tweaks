package me.teakivy.teakstweaks.packs.spawningspheres;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.LinkedHashMap;

public class SpawningSphere {
    private static final int INNER_RADIUS = 24;
    private static final int OUTER_RADIUS = 128;
    private boolean inUse;
    private Location center;
    protected SphereType type;

    public SpawningSphere(LinkedHashMap<String, Object> map, SphereType type) {
        this.type = type;
        deserialize(map);
    }

    public void createSphere() {
        if (inUse) return;
        createSphere(SphereIO.INNER);
        createSphere(SphereIO.OUTER);

        summonStand(center, SphereIO.INNER, true);

        inUse = true;
    }

    public void setCenter(Location location) {
        location.setYaw(0);
        location.setPitch(0);
        center = location;
    }

    public Location getCenter() {
        return center;
    }

    private void createSphere(SphereIO sphereIO) {
        int radius = sphereIO == SphereIO.INNER ? INNER_RADIUS : OUTER_RADIUS;
        for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
            double curRadius = Math.sin(i);
            double y = Math.cos(i) * radius;
            for (double a = 0; a < Math.PI * 2; a+= Math.PI / 10) {

                double x = Math.cos(a) * curRadius * radius;
                double z = Math.sin(a) * curRadius * radius;
                Location loc = center.clone().add(x, y, z);
                summonStand(loc, sphereIO);
            }
        }
    }

    private void summonStand(Location location, SphereIO sphereIO) {
        summonStand(location, sphereIO, false);
    }

    private void summonStand(Location location, SphereIO sphereIO, boolean isCenter) {
        ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        stand.setGravity(false);
        stand.setVisible(false);
        stand.setSmall(true);
        stand.setMarker(true);

        stand.addScoreboardTag(type.getColorName() + "_sphere");
        stand.addScoreboardTag("spawning_sphere");

        stand.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 999999, 0));

        if (isCenter) {
            stand.customName(Component.translatable("spawning_spheres.center_stand"));
            stand.setCustomNameVisible(true);
        }

        stand.getEquipment().setHelmet(new ItemStack(sphereIO.getMaterial(type)));

        type.getTeam().addEntry(stand.getUniqueId().toString());
    }

    public void removeSphere() {
        for (Entity entity : center.getWorld().getEntities()) {
            if (entity.getScoreboardTags().contains(type.getColorName() + "_sphere")) {
                entity.remove();
            }
        }

        inUse = false;
    }

    public void teleport(Player player) {
        player.teleportAsync(center);
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public boolean isInUse() {
        return inUse;
    }

    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put(type.getColorName() + ".in_use", inUse);

        if (!inUse) return map;

        map.put(type.getColorName() + ".center.x", center.getX());
        map.put(type.getColorName() + ".center.y", center.getY());
        map.put(type.getColorName() + ".center.z", center.getZ());
        map.put(type.getColorName() + ".center.world", center.getWorld().getName());

        return map;
    }

    public void deserialize(LinkedHashMap<String, Object> map) {
        Boolean inUse = (Boolean) map.get(type.getColorName() + ".in_use");
        this.inUse = inUse != null && inUse;

        if (!this.inUse) return;

        double x = (double) map.get(type.getColorName() + ".center.x");
        double y = (double) map.get(type.getColorName() + ".center.y");
        double z = (double) map.get(type.getColorName() + ".center.z");
        String world = (String) map.get(type.getColorName() + ".center.world");

        center = new Location(Bukkit.getWorld(world), x, y, z);
    }

    public enum SphereIO {
        INNER,
        OUTER;

        public Material getMaterial(SphereType type) {
            return switch (this) {
                case INNER -> type.getMaterial();
                case OUTER -> type.getSubMaterial();
            };
        }
    }
}

