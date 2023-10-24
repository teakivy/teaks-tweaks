package me.teakivy.teakstweaks.packs.utilities.spawningspheres;

import me.teakivy.teakstweaks.utils.lang.Translatable;
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

public class SpawningSphere {
    private static final int INNER_RADIUS = 24;
    private static final int OUTER_RADIUS = 128;
    private boolean inUse;
    private Location center;
    protected SphereType type;

    public SpawningSphere(Location center, SphereType type) {
        this.center = center;
        this.type = type;

        center.setYaw(0);
        center.setPitch(0);
    }

    public void createSphere() {
        if (inUse) return;
        createSphere(SphereIO.INNER);
        createSphere(SphereIO.OUTER);

        summonStand(center, SphereIO.INNER, true);

        inUse = true;
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
            stand.setCustomName(Translatable.get("spawning_spheres.center_stand"));
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
    }

    public void teleport(Player player) {
        player.teleport(center);
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public boolean isInUse() {
        return inUse;
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

