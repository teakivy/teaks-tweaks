package me.teakivy.teakstweaks.packs.cauldronpotions;

import me.teakivy.teakstweaks.utils.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Marker;
import org.bukkit.entity.TextDisplay;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import java.util.UUID;

public class PotionCauldron {
    private Location location;
    private int level;
    private PotionType type;
    private UUID markerId;
    private UUID overlayId;

    public PotionCauldron(Location location, int level, PotionType type) {
        this.location = location.getBlock().getLocation();
        this.level = level;
        this.type = type;

        Marker marker = location.getWorld().spawn(location.getBlock().getLocation(), Marker.class);
        this.markerId = marker.getUniqueId();
        PersistentDataContainer markerData = marker.getPersistentDataContainer();
        markerData.set(Key.get("potion_cauldron"), PersistentDataType.BOOLEAN, true);
        markerData.set(Key.get("potion_cauldron_level"), PersistentDataType.INTEGER, level);
        markerData.set(Key.get("potion_cauldron_type"), PersistentDataType.STRING, type.name());

        TextDisplay overlay = location.getWorld().spawn(location.getBlock().getLocation().add(getOffset(level)), TextDisplay.class, display -> {
            display.text(Component.text(" "));
            display.setTextOpacity((byte) 0);
            display.setBackgroundColor(type.getPotionEffects().getFirst().getType().getColor().setAlpha(150));
            display.setDefaultBackground(false);
            display.setRotation(0, -90);
            Transformation transformation = display.getTransformation();
            transformation.getScale().set(6.8, 3.9, 1);
            display.setTransformation(transformation);
        });

        this.overlayId = overlay.getUniqueId();
        markerData.set(Key.get("potion_cauldron_overlay"), PersistentDataType.STRING, overlayId.toString());
    }

    public PotionCauldron(Location location, int level, PotionType type, UUID markerId, UUID overlayId) {
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
            TextDisplay overlay = (TextDisplay) location.getWorld().getEntity(overlayId);
            if (overlay != null) overlay.remove();
            return true;
        }
        this.level = level;
        Marker marker = (Marker) location.getWorld().getEntity(markerId);
        if (marker == null) return false;
        PersistentDataContainer markerData = marker.getPersistentDataContainer();
        markerData.set(Key.get("potion_cauldron_level"), PersistentDataType.INTEGER, level);

        TextDisplay overlay = (TextDisplay) location.getWorld().getEntity(overlayId);
        if (overlay == null) return false;
        overlay.teleport(location.getBlock().getLocation().add(getOffset(level)));
        overlay.setRotation(0, -90);
        return true;
    }

    public boolean remove() {
        return this.setLevel(0);
    }

    public static PotionCauldron fromMarker(Marker marker) {
        PersistentDataContainer data = marker.getPersistentDataContainer();
        if (!data.has(Key.get("potion_cauldron"), PersistentDataType.BOOLEAN)) return null;
        int level = data.get(Key.get("potion_cauldron_level"), PersistentDataType.INTEGER);
        String typeString = data.get(Key.get("potion_cauldron_type"), PersistentDataType.STRING);
        if (typeString == null) return null;
        PotionType type = PotionType.valueOf(typeString);
        return new PotionCauldron(marker.getLocation(), level, type, marker.getUniqueId(), UUID.fromString(data.get(Key.get("potion_cauldron_overlay"), PersistentDataType.STRING)));
    }

    public static PotionCauldron getCauldronAt(Location location) {
        for (Marker marker : location.getWorld().getEntitiesByClass(Marker.class)) {
            PersistentDataContainer data = marker.getPersistentDataContainer();
            if (!data.has(Key.get("potion_cauldron"), PersistentDataType.BOOLEAN)) continue;
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

        TextDisplay overlay = (TextDisplay) location.getWorld().getEntity(overlayId);
        if (overlay == null) return false;
        overlay.teleport(location.getBlock().getLocation().add(getOffset(level)));
        overlay.setRotation(0, -90);
        return true;
    }

    public Location getLocation() {
        return location;
    }

    public int getLevel() {
        return level;
    }

    public PotionType getType() {
        return type;
    }

    public UUID getMarkerId() {
        return markerId;
    }

    private Vector getOffset(int level) {
        System.out.println("Level: " + level);
        return new Vector(0.4, ((level * 3) + 6)/16f, .99);
    }
}
