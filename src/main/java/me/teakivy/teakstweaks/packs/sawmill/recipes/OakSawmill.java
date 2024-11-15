package me.teakivy.teakstweaks.packs.sawmill.recipes;

import me.teakivy.teakstweaks.packs.sawmill.BaseSawmillRecipe;
import org.bukkit.Material;

public class OakSawmill extends BaseSawmillRecipe {

    public OakSawmill() {
        super(
                Material.OAK_LOG,
                Material.OAK_WOOD,
                Material.STRIPPED_OAK_LOG,
                Material.STRIPPED_OAK_WOOD,
                Material.OAK_PLANKS,
                Material.OAK_STAIRS,
                Material.OAK_SLAB,
                Material.OAK_FENCE,
                Material.OAK_FENCE_GATE,
                Material.OAK_DOOR,
                Material.OAK_TRAPDOOR,
                Material.OAK_PRESSURE_PLATE,
                Material.OAK_BUTTON,
                Material.OAK_SIGN
        );
    }
}
