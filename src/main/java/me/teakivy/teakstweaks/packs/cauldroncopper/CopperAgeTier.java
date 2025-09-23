package me.teakivy.teakstweaks.packs.cauldroncopper;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class CopperAgeTier {
    private final Material base;
    private final Material exposed;
    private final Material weathered;
    private final Material oxidized;

    public CopperAgeTier(Material base, Material exposed, Material weathered, Material oxidized) {
        this.base = base;
        this.exposed = exposed;
        this.weathered = weathered;
        this.oxidized = oxidized;
    }

    public boolean contains(Material material) {
        return material == base || material == exposed || material == weathered || material == oxidized;
    }

    public Material getNextStage(Material material) {
        if (material == base) return exposed;
        if (material == exposed) return weathered;
        if (material == weathered) return oxidized;
        return null;
    }

    public boolean hasNextStage(Material material) {
        return material == base || material == exposed || material == weathered;
    }

    public CopperAgeTier getWaxedVariant() {
        return new CopperAgeTier(getWaxed(base), getWaxed(exposed), getWaxed(weathered), getWaxed(oxidized));
    }

    private Material getWaxed(Material type) {
        if (type == null) return null;
        return Material.getMaterial("WAXED_" + type.name());
    }

    public static List<CopperAgeTier> getTiers() {
        List<CopperAgeTier> tiers = new ArrayList<>();
        tiers.add(new CopperAgeTier(Material.COPPER_BLOCK, Material.EXPOSED_COPPER, Material.WEATHERED_COPPER, Material.OXIDIZED_COPPER));
        tiers.add(new CopperAgeTier(Material.CHISELED_COPPER, Material.EXPOSED_CHISELED_COPPER, Material.WEATHERED_CHISELED_COPPER, Material.OXIDIZED_CHISELED_COPPER));
        tiers.add(new CopperAgeTier(Material.COPPER_GRATE, Material.EXPOSED_COPPER_GRATE, Material.WEATHERED_COPPER_GRATE, Material.OXIDIZED_COPPER_GRATE));
        tiers.add(new CopperAgeTier(Material.CUT_COPPER, Material.EXPOSED_CUT_COPPER, Material.WEATHERED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER));
        tiers.add(new CopperAgeTier(Material.CUT_COPPER_STAIRS, Material.EXPOSED_CUT_COPPER_STAIRS, Material.WEATHERED_CUT_COPPER_STAIRS, Material.OXIDIZED_CUT_COPPER_STAIRS));
        tiers.add(new CopperAgeTier(Material.CUT_COPPER_SLAB, Material.EXPOSED_CUT_COPPER_SLAB, Material.WEATHERED_CUT_COPPER_SLAB, Material.OXIDIZED_CUT_COPPER_SLAB));
        tiers.add(new CopperAgeTier(Material.COPPER_DOOR, Material.EXPOSED_COPPER_DOOR, Material.WEATHERED_COPPER_DOOR, Material.OXIDIZED_COPPER_DOOR));
        tiers.add(new CopperAgeTier(Material.COPPER_TRAPDOOR, Material.EXPOSED_COPPER_TRAPDOOR, Material.WEATHERED_COPPER_TRAPDOOR, Material.OXIDIZED_COPPER_TRAPDOOR));
        tiers.add(new CopperAgeTier(Material.COPPER_BULB, Material.EXPOSED_COPPER_BULB, Material.WEATHERED_COPPER_BULB, Material.OXIDIZED_COPPER_BULB));

        // waxed
        int size = tiers.size();
        for (int i = 0; i < size; i++) {
            tiers.add(tiers.get(i).getWaxedVariant());
        }

        return tiers;
    }
}
