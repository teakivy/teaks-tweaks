package me.teakivy.teakstweaks.packs.sawmill.recipes;

import me.teakivy.teakstweaks.packs.sawmill.BaseSawmillRecipe;
import org.bukkit.Material;

public class BirchSawmill extends BaseSawmillRecipe {

    public BirchSawmill() {
        super(
                Material.BIRCH_LOG,
                Material.BIRCH_WOOD,
                Material.STRIPPED_BIRCH_LOG,
                Material.STRIPPED_BIRCH_WOOD,
                Material.BIRCH_PLANKS,
                Material.BIRCH_STAIRS,
                Material.BIRCH_SLAB,
                Material.BIRCH_FENCE,
                Material.BIRCH_FENCE_GATE,
                Material.BIRCH_DOOR,
                Material.BIRCH_TRAPDOOR,
                Material.BIRCH_PRESSURE_PLATE,
                Material.BIRCH_BUTTON,
                Material.BIRCH_SIGN
        );
    }
}
