package me.teakivy.teakstweaks.packs.cauldroncopper;

import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Marker;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.UUID;

public class SlimeCauldron extends CustomCopperCauldron {
    private static List<CopperAgeTier> ageTiers;

    public SlimeCauldron(Location location, int level) {
        super(location, level, Material.SLIME_BLOCK);
    }

    public SlimeCauldron(Location location, int level, Material type, UUID markerId, UUID overlayId) {
        super(location, level, type, markerId, overlayId);
    }

    public static void init() {
        ageTiers = CopperAgeTier.getTiers();
    }

    public static SlimeCauldron getCauldronAt(Location location) {
        for (Marker marker : location.getWorld().getEntitiesByClass(Marker.class)) {
            PersistentDataContainer data = marker.getPersistentDataContainer();
            if (!data.has(Key.get("custom_cauldron"), PersistentDataType.BOOLEAN)) continue;
            if (!data.has(Key.get("cauldron_type"), PersistentDataType.STRING)) continue;
            if (!data.get(Key.get("cauldron_type"), PersistentDataType.STRING).equals(Material.SLIME_BLOCK.name())) continue;
            if (marker.getLocation().getBlock().equals(location.getBlock())) {
                return fromMarker(marker);
            }
        }
        return null;
    }

    public static SlimeCauldron fromMarker(Marker marker) {
        PersistentDataContainer data = marker.getPersistentDataContainer();
        if (!data.has(Key.get("custom_cauldron"), PersistentDataType.BOOLEAN)) return null;
        int level = data.get(Key.get("cauldron_level"), PersistentDataType.INTEGER);
        String typeString = data.get(Key.get("cauldron_type"), PersistentDataType.STRING);
        if (typeString == null) return null;
        Material type = Material.getMaterial(typeString);
        return new SlimeCauldron(marker.getLocation(), level, type, marker.getUniqueId(), UUID.fromString(data.get(Key.get("cauldron_overlay"), PersistentDataType.STRING)));
    }

    public void clickCopper(Material type, int amount, PlayerInteractEvent event) {
        if (ageTiers == null) init();
        for (CopperAgeTier tier : ageTiers) {
            if (tier.contains(type)) {
                if (!tier.hasNextStage(type)) return;
                Material next = tier.getNextStage(type);
                if (next == null) return;

                this.removeLevel();
                event.setCancelled(true);
                event.getPlayer().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.BLOCK_COPPER_HIT, 1, 1);

                if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                    event.getPlayer().getInventory().addItem(new ItemStack(next, amount));
                    return;
                }
                event.getPlayer().getInventory().setItem(event.getHand(), new ItemStack(next, amount));
                return;
            }
        }
    }
}
