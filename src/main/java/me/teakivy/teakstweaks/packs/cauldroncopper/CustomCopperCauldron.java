package me.teakivy.teakstweaks.packs.cauldroncopper;

import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Marker;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;

import java.util.UUID;

public class CustomCopperCauldron {
    private Location location;
    private int level;
    private Material type;
    private UUID markerId;
    private UUID overlayId;

    public CustomCopperCauldron(Location location, int level, Material type) {
        this.location = location.getBlock().getLocation();
        this.level = level;
        this.type = type;

        Marker marker = location.getWorld().spawn(location.getBlock().getLocation(), Marker.class);
        this.markerId = marker.getUniqueId();
        PersistentDataContainer markerData = marker.getPersistentDataContainer();
        markerData.set(Key.get("custom_cauldron"), PersistentDataType.BOOLEAN, true);
        markerData.set(Key.get("cauldron_level"), PersistentDataType.INTEGER, level);
        markerData.set(Key.get("cauldron_type"), PersistentDataType.STRING, type.name());

        BlockDisplay overlay = location.getWorld().spawn(location.getBlock().getLocation().add(2/16f, 4/16f, 2/16f), BlockDisplay.class, display -> {
            display.setBlock(type.createBlockData());
            Transformation transformation = display.getTransformation();
            transformation.getScale().set(12/16f, (2+(level*3))/16f, 12/16f);
            display.setTransformation(transformation);
        });

        this.overlayId = overlay.getUniqueId();
        markerData.set(Key.get("cauldron_overlay"), PersistentDataType.STRING, overlayId.toString());
    }

    public CustomCopperCauldron(Location location, int level, Material type, UUID markerId, UUID overlayId) {
        this.location = location.getBlock().getLocation();
        this.level = level;
        this.type = type;
        this.markerId = markerId;
        this.overlayId = overlayId;
    }

    public boolean setLevel(int level) {
        if (level > 3) return false;
        if (level <= 0) {
            Marker marker = (Marker) location.getWorld().getEntity(markerId);
            if (marker != null) marker.remove();
            BlockDisplay overlay = (BlockDisplay) location.getWorld().getEntity(overlayId);
            if (overlay != null) overlay.remove();
            return true;
        }
        this.level = level;
        Marker marker = (Marker) location.getWorld().getEntity(markerId);
        if (marker == null) return false;
        PersistentDataContainer markerData = marker.getPersistentDataContainer();
        markerData.set(Key.get("cauldron_level"), PersistentDataType.INTEGER, level);

        BlockDisplay overlay = (BlockDisplay) location.getWorld().getEntity(overlayId);
        if (overlay == null) return false;
        Transformation transformation = overlay.getTransformation();
        transformation.getScale().set(12/16f, (2+(level*3))/16f, 12/16f);
        overlay.setTransformation(transformation);
        return true;
    }

    public boolean remove() {
        return this.setLevel(0);
    }

    public static CustomCopperCauldron fromMarker(Marker marker) {
        PersistentDataContainer data = marker.getPersistentDataContainer();
        if (!data.has(Key.get("custom_cauldron"), PersistentDataType.BOOLEAN)) return null;
        int level = data.get(Key.get("cauldron_level"), PersistentDataType.INTEGER);
        String typeString = data.get(Key.get("cauldron_type"), PersistentDataType.STRING);
        if (typeString == null) return null;
        Material type = Material.getMaterial(typeString);
        return new CustomCopperCauldron(marker.getLocation(), level, type, marker.getUniqueId(), UUID.fromString(data.get(Key.get("cauldron_overlay"), PersistentDataType.STRING)));
    }

    public static CustomCopperCauldron getCauldronAt(Location location) {
        for (Marker marker : location.getWorld().getEntitiesByClass(Marker.class)) {
            PersistentDataContainer data = marker.getPersistentDataContainer();
            if (!data.has(Key.get("custom_cauldron"), PersistentDataType.BOOLEAN)) continue;
            if (marker.getLocation().getBlock().equals(location.getBlock())) {
                return fromMarker(marker);
            }
        }
        return null;
    }

    public boolean move(Location location) {
        this.location = location.getBlock().getLocation();
        Marker marker = (Marker) location.getWorld().getEntity(markerId);
        if (marker == null) return false;
        marker.teleport(location.getBlock().getLocation());

        BlockDisplay overlay = (BlockDisplay) location.getWorld().getEntity(overlayId);
        if (overlay == null) return false;
        overlay.teleport(location.getBlock().getLocation().add(2/16f, 4/16f, 2/16f));
        return true;
    }

    public Location getLocation() {
        return location;
    }

    public int getLevel() {
        return level;
    }

    public Material getType() {
        return type;
    }

    public UUID getMarkerId() {
        return markerId;
    }

    public boolean isFull() {
        return level >= 3;
    }

    public void addLevel() {
        setLevel(level + 1);
    }

    public void removeLevel() {
        setLevel(level - 1);
    }
}
