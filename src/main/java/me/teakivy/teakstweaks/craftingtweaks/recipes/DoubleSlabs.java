package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class DoubleSlabs extends AbstractRecipe {

    public DoubleSlabs() {
        super("Double Slabs", "double-slabs");
    }

    @Override
    public void registerRecipes() {
        newShapelessRecipe("blackstone", Material.BLACKSTONE, Material.BLACKSTONE_SLAB);
        newShapelessRecipe("bricks", Material.BRICKS, Material.BRICK_SLAB);
        newShapelessRecipe("cobblestone", Material.COBBLESTONE, Material.COBBLESTONE_SLAB);
        newShapelessRecipe("dark_prismarine", Material.DARK_PRISMARINE, Material.DARK_PRISMARINE_SLAB);
        newShapelessRecipe("diorite", Material.DIORITE, Material.DIORITE_SLAB);
        newShapelessRecipe("end_stone_bricks", Material.END_STONE_BRICKS, Material.END_STONE_BRICK_SLAB);
        newShapelessRecipe("granite", Material.GRANITE, Material.GRANITE_SLAB);
        newShapelessRecipe("mossy_cobblestone", Material.MOSSY_COBBLESTONE, Material.MOSSY_COBBLESTONE_SLAB);
        newShapelessRecipe("mossy_stone_bricks", Material.MOSSY_STONE_BRICKS, Material.MOSSY_STONE_BRICK_SLAB);
        newShapelessRecipe("nether_bricks", Material.NETHER_BRICKS, Material.NETHER_BRICK_SLAB);
        newShapelessRecipe("polished_andesite", Material.POLISHED_ANDESITE, Material.POLISHED_ANDESITE_SLAB);
        newShapelessRecipe("andesite", Material.ANDESITE, Material.ANDESITE_SLAB);
        newShapelessRecipe("polished_blackstone_bricks", Material.POLISHED_BLACKSTONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICK_SLAB);
        newShapelessRecipe("prismarine_bricks", Material.PRISMARINE_BRICKS, Material.PRISMARINE_BRICK_SLAB);
        newShapelessRecipe("prismarine", Material.PRISMARINE, Material.PRISMARINE_SLAB);
        newShapelessRecipe("purpur_block", Material.PURPUR_BLOCK, Material.PURPUR_SLAB);
        newShapelessRecipe("quartz_block", Material.QUARTZ_BLOCK, Material.QUARTZ_SLAB);
        newShapelessRecipe("red_nether_bricks", Material.RED_NETHER_BRICKS, Material.RED_NETHER_BRICK_SLAB);
        newShapelessRecipe("red_sandstone", Material.RED_SANDSTONE, Material.RED_SANDSTONE_SLAB);
        newShapelessRecipe("sandstone", Material.SANDSTONE, Material.SANDSTONE_SLAB);
        newShapelessRecipe("smooth_quartz", Material.SMOOTH_QUARTZ, Material.SMOOTH_QUARTZ_SLAB);
        newShapelessRecipe("smooth_red_sandstone", Material.SMOOTH_RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE_SLAB);
        newShapelessRecipe("smooth_sandstone", Material.SMOOTH_SANDSTONE, Material.SMOOTH_SANDSTONE_SLAB);
        newShapelessRecipe("stone_bricks", Material.STONE_BRICKS, Material.STONE_BRICK_SLAB);

        newShapelessRecipe("cut_copper", Material.CUT_COPPER, Material.CUT_COPPER_SLAB);
        newShapelessRecipe("exposed_cut_copper", Material.EXPOSED_CUT_COPPER, Material.EXPOSED_CUT_COPPER_SLAB);
        newShapelessRecipe("weathered_cut_copper", Material.WEATHERED_CUT_COPPER, Material.WEATHERED_CUT_COPPER_SLAB);
        newShapelessRecipe("oxidized_cut_copper", Material.OXIDIZED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER_SLAB);
        newShapelessRecipe("waxed_cut_copper", Material.WAXED_CUT_COPPER, Material.WAXED_CUT_COPPER_SLAB);
        newShapelessRecipe("waxed_exposed_cut_copper", Material.WAXED_EXPOSED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER_SLAB);
        newShapelessRecipe("waxed_weathered_cut_copper", Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_WEATHERED_CUT_COPPER_SLAB);
        newShapelessRecipe("waxed_oxidized_cut_copper", Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        newShapelessRecipe("cobbled_deepslate", Material.COBBLED_DEEPSLATE, Material.COBBLED_DEEPSLATE_SLAB);
        newShapelessRecipe("polished_deepslate", Material.POLISHED_DEEPSLATE, Material.POLISHED_DEEPSLATE_SLAB);
        newShapelessRecipe("deepslate_bricks", Material.DEEPSLATE_BRICKS, Material.DEEPSLATE_BRICK_SLAB);
        newShapelessRecipe("deepslate_tiles", Material.DEEPSLATE_TILES, Material.DEEPSLATE_TILE_SLAB);

        newShapelessRecipe("mud_bricks", Material.MUD_BRICKS, Material.MUD_BRICK_SLAB);
    }

    public static void newShapelessRecipe(String keyName, Material ingredient, Material result) {
        NamespacedKey key = new NamespacedKey(main, keyName + "_double_slab");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(result, 2));

        recipe.addIngredient(ingredient);

        Bukkit.addRecipe(recipe);
    }

}
