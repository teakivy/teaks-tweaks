package me.teakivy.teakstweaks.packs.spawningspheres;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.JsonManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class SpheresPack extends BasePack {
    private static SpawningSphere redSphere;
    private static SpawningSphere blueSphere;
    private static SpawningSphere greenSphere;

    public SpheresPack() {
        super("spawning-spheres", Material.WARDEN_SPAWN_EGG);
    }

    @Override
    public void init() {
        super.init();

        LinkedHashMap<String, Object> json = getJson();

        redSphere = new SpawningSphere(json, SphereType.RED);
        blueSphere = new SpawningSphere(json, SphereType.BLUE);
        greenSphere = new SpawningSphere(json, SphereType.GREEN);
    }

    public static void save() {
        LinkedHashMap<String, Object> json = new LinkedHashMap<>();
        json.putAll(redSphere.serialize());
        json.putAll(blueSphere.serialize());
        json.putAll(greenSphere.serialize());

        JsonManager.saveToFile(json, "data/spawning_spheres.json");
    }

    public static boolean teleport(SphereType type, Player player) {
        SpawningSphere sphere = getSphere(type);

        if (sphere == null) return false;
        if (!sphere.isInUse()) return false;

        sphere.teleport(player);
        return true;
    }

    public static boolean summonSphere(SphereType type, Location location) {
        SpawningSphere sphere = getSphere(type);

        if (sphere == null) return false;
        if (sphere.isInUse()) return false;

        sphere.setCenter(location);
        sphere.createSphere();
        save();
        return true;
    }

    public static boolean removeSphere(SphereType type, Player executor) {
        SpawningSphere sphere = getSphere(type);

        if (sphere == null) return false;
        if (!sphere.isInUse()) return false;

        Location location = executor.getLocation();

        executor.teleportAsync(sphere.getCenter());

        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), sphere::removeSphere, 20L);
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
            executor.teleportAsync(location);
            save();
        }, 20L * 3);
        return true;
    }

    public static SpawningSphere getSphere(SphereType type) {
        return switch (type) {
            case RED -> redSphere;
            case BLUE -> blueSphere;
            case GREEN -> greenSphere;
        };
    }

    private static LinkedHashMap<String, Object> getJson() {
        return JsonManager.get("data/spawning_spheres.json");
    }
}
