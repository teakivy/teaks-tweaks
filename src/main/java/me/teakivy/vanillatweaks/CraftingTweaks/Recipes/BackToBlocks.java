package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class BackToBlocks {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        newShapelessStairRecipe("acacia_planks", new ItemStack(Material.ACACIA_PLANKS, 3), Material.ACACIA_STAIRS, 4);
        newShapelessStairRecipe("birch_planks", new ItemStack(Material.BIRCH_PLANKS, 3), Material.BIRCH_STAIRS, 4);
        newShapelessStairRecipe("blackstone", new ItemStack(Material.BLACKSTONE, 3), Material.BLACKSTONE_STAIRS, 4);
        newShapelessStairRecipe("bricks", new ItemStack(Material.BRICKS, 3), Material.BRICK_STAIRS, 4);
        newShapelessStairRecipe("cobblestone", new ItemStack(Material.COBBLESTONE, 3), Material.COBBLESTONE_STAIRS, 4);
        newShapelessStairRecipe("crimson_planks", new ItemStack(Material.CRIMSON_PLANKS, 3), Material.CRIMSON_STAIRS, 4);
        newShapelessStairRecipe("dark_oak_planks", new ItemStack(Material.DARK_OAK_PLANKS, 3), Material.DARK_OAK_STAIRS, 4);
        newShapelessStairRecipe("dark_prismarine", new ItemStack(Material.DARK_PRISMARINE, 3), Material.DARK_PRISMARINE_STAIRS, 4);
        newShapelessStairRecipe("diorite", new ItemStack(Material.DIORITE, 3), Material.DIORITE_STAIRS, 4);
        newShapelessStairRecipe("end_stone_bricks", new ItemStack(Material.END_STONE_BRICKS, 3), Material.END_STONE_BRICK_STAIRS, 4);
        newShapelessStairRecipe("granite", new ItemStack(Material.GRANITE, 3), Material.GRANITE_STAIRS, 4);
        newShapelessStairRecipe("jungle_planks", new ItemStack(Material.JUNGLE_PLANKS, 3), Material.JUNGLE_STAIRS, 4);
        newShapelessStairRecipe("mossy_cobblestone", new ItemStack(Material.MOSSY_COBBLESTONE, 3), Material.MOSSY_COBBLESTONE_STAIRS, 4);
        newShapelessStairRecipe("mossy_stone_bricks", new ItemStack(Material.MOSSY_STONE_BRICKS, 3), Material.MOSSY_STONE_BRICK_STAIRS, 4);
        newShapelessStairRecipe("nether_bricks", new ItemStack(Material.NETHER_BRICKS, 3), Material.NETHER_BRICK_STAIRS, 4);
        newShapelessStairRecipe("oak_planks", new ItemStack(Material.OAK_PLANKS, 3), Material.OAK_STAIRS, 4);
        newShapelessStairRecipe("polished_andesite", new ItemStack(Material.POLISHED_ANDESITE, 3), Material.POLISHED_ANDESITE_STAIRS, 4);
        newShapelessStairRecipe("polished_blackstone", new ItemStack(Material.POLISHED_BLACKSTONE, 3), Material.POLISHED_BLACKSTONE_STAIRS, 4);
        newShapelessStairRecipe("polished_blackstone_bricks", new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS, 3), Material.POLISHED_BLACKSTONE_BRICK_STAIRS, 4);
        newShapelessStairRecipe("prismarine_bricks", new ItemStack(Material.PRISMARINE_BRICKS, 3), Material.PRISMARINE_BRICK_STAIRS, 4);
        newShapelessStairRecipe("prismarine", new ItemStack(Material.PRISMARINE, 3), Material.PRISMARINE_STAIRS, 4);
        newShapelessStairRecipe("purpur_block", new ItemStack(Material.PURPUR_BLOCK, 3), Material.PURPUR_STAIRS, 4);
        newShapelessStairRecipe("quartz_block", new ItemStack(Material.QUARTZ_BLOCK, 3), Material.QUARTZ_STAIRS, 4);
        newShapelessStairRecipe("red_nether_bricks", new ItemStack(Material.RED_NETHER_BRICKS, 3), Material.RED_NETHER_BRICK_STAIRS, 4);
        newShapelessStairRecipe("red_sandstone", new ItemStack(Material.RED_SANDSTONE, 3), Material.RED_SANDSTONE_STAIRS, 4);
        newShapelessStairRecipe("sandstone", new ItemStack(Material.SANDSTONE, 3), Material.SANDSTONE_STAIRS, 4);
        newShapelessStairRecipe("smooth_quartz", new ItemStack(Material.SMOOTH_QUARTZ, 3), Material.SMOOTH_QUARTZ_STAIRS, 4);
        newShapelessStairRecipe("smooth_red_sandstone", new ItemStack(Material.SMOOTH_RED_SANDSTONE, 3), Material.SMOOTH_RED_SANDSTONE_STAIRS, 4);
        newShapelessStairRecipe("smooth_sandstone", new ItemStack(Material.SMOOTH_SANDSTONE, 3), Material.SMOOTH_SANDSTONE_STAIRS, 4);
        newShapelessStairRecipe("spruce_planks", new ItemStack(Material.SPRUCE_PLANKS, 3), Material.SPRUCE_STAIRS, 4);
        newShapelessStairRecipe("stone_bricks", new ItemStack(Material.STONE_BRICKS, 3), Material.STONE_BRICK_STAIRS, 4);
        newShapelessStairRecipe("stone", new ItemStack(Material.STONE, 3), Material.STONE_STAIRS, 4);
        newShapelessStairRecipe("warped_planks", new ItemStack(Material.WARPED_PLANKS, 3), Material.WARPED_STAIRS, 4);
        newShapelessStairRecipe("cut_copper", new ItemStack(Material.CUT_COPPER, 3), Material.CUT_COPPER_STAIRS, 4);
        newShapelessStairRecipe("exposed_cut_copper", new ItemStack(Material.EXPOSED_CUT_COPPER, 3), Material.EXPOSED_CUT_COPPER_STAIRS, 4);
        newShapelessStairRecipe("weathered_cut_copper", new ItemStack(Material.WEATHERED_CUT_COPPER, 3), Material.WEATHERED_CUT_COPPER_STAIRS, 4);
        newShapelessStairRecipe("oxidized_cut_copper", new ItemStack(Material.OXIDIZED_CUT_COPPER, 3), Material.OXIDIZED_CUT_COPPER_STAIRS, 4);
        newShapelessStairRecipe("waxed_cut_copper", new ItemStack(Material.WAXED_CUT_COPPER, 3), Material.WAXED_CUT_COPPER_STAIRS, 4);
        newShapelessStairRecipe("waxed_exposed_cut_copper", new ItemStack(Material.WAXED_EXPOSED_CUT_COPPER, 3), Material.WAXED_EXPOSED_CUT_COPPER_STAIRS, 4);
        newShapelessStairRecipe("waxed_weathered_cut_copper", new ItemStack(Material.WAXED_WEATHERED_CUT_COPPER, 3), Material.WAXED_WEATHERED_CUT_COPPER_STAIRS, 4);
        newShapelessStairRecipe("waxed_oxidized_cut_copper", new ItemStack(Material.WAXED_OXIDIZED_CUT_COPPER, 3), Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS, 4);
        newShapelessStairRecipe("cobbled_deepslate", new ItemStack(Material.COBBLED_DEEPSLATE, 3), Material.COBBLED_DEEPSLATE_STAIRS, 4);
        newShapelessStairRecipe("polished_deepslate", new ItemStack(Material.POLISHED_DEEPSLATE, 3), Material.POLISHED_DEEPSLATE_STAIRS, 4);
        newShapelessStairRecipe("deepslate_bricks", new ItemStack(Material.DEEPSLATE_BRICKS, 3), Material.DEEPSLATE_BRICK_STAIRS, 4);
        newShapelessStairRecipe("deepslate_tiles", new ItemStack(Material.DEEPSLATE_TILES, 3), Material.DEEPSLATE_TILE_STAIRS, 4);

        newShapedSlabRecipe("acacia_planks", new ItemStack(Material.ACACIA_PLANKS, 1), Material.ACACIA_SLAB);
        newShapedSlabRecipe("birch_planks", new ItemStack(Material.BIRCH_PLANKS, 1), Material.BIRCH_SLAB);
        newShapedSlabRecipe("blackstone", new ItemStack(Material.BLACKSTONE, 1), Material.BLACKSTONE_SLAB);
        newShapedSlabRecipe("bricks", new ItemStack(Material.BRICKS, 1), Material.BRICK_SLAB);
        newShapedSlabRecipe("cobblestone", new ItemStack(Material.COBBLESTONE, 1), Material.COBBLESTONE_SLAB);
        newShapedSlabRecipe("crimson_planks", new ItemStack(Material.CRIMSON_PLANKS, 1), Material.CRIMSON_SLAB);
        newShapedSlabRecipe("dark_oak_planks", new ItemStack(Material.DARK_OAK_PLANKS, 1), Material.DARK_OAK_SLAB);
        newShapedSlabRecipe("dark_prismarine", new ItemStack(Material.DARK_PRISMARINE, 1), Material.DARK_PRISMARINE_SLAB);
        newShapedSlabRecipe("diorite", new ItemStack(Material.DIORITE, 1), Material.DIORITE_SLAB);
        newShapedSlabRecipe("end_stone_bricks", new ItemStack(Material.END_STONE_BRICKS, 1), Material.END_STONE_BRICK_SLAB);
        newShapedSlabRecipe("granite", new ItemStack(Material.GRANITE, 1), Material.GRANITE_SLAB);
        newShapedSlabRecipe("jungle_planks", new ItemStack(Material.JUNGLE_PLANKS, 1), Material.JUNGLE_SLAB);
        newShapedSlabRecipe("mossy_cobblestone", new ItemStack(Material.MOSSY_COBBLESTONE, 1), Material.MOSSY_COBBLESTONE_SLAB);
        newShapedSlabRecipe("mossy_stone_bricks", new ItemStack(Material.MOSSY_STONE_BRICKS, 1), Material.MOSSY_STONE_BRICK_SLAB);
        newShapedSlabRecipe("nether_bricks", new ItemStack(Material.NETHER_BRICKS, 1), Material.NETHER_BRICK_SLAB);
        newShapedSlabRecipe("oak_planks", new ItemStack(Material.OAK_PLANKS, 1), Material.OAK_SLAB);
        newShapedSlabRecipe("polished_andesite", new ItemStack(Material.POLISHED_ANDESITE, 1), Material.POLISHED_ANDESITE_SLAB);
        newShapedSlabRecipe("polished_blackstone_bricks", new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS, 1), Material.POLISHED_BLACKSTONE_BRICK_SLAB);
        newShapedSlabRecipe("prismarine_bricks", new ItemStack(Material.PRISMARINE_BRICKS, 1), Material.PRISMARINE_BRICK_SLAB);
        newShapedSlabRecipe("prismarine", new ItemStack(Material.PRISMARINE, 1), Material.PRISMARINE_SLAB);
        newShapedSlabRecipe("purpur_block", new ItemStack(Material.PURPUR_BLOCK, 1), Material.PURPUR_SLAB);
        newShapedSlabRecipe("quartz_block", new ItemStack(Material.QUARTZ_BLOCK, 1), Material.QUARTZ_SLAB);
        newShapedSlabRecipe("red_nether_bricks", new ItemStack(Material.RED_NETHER_BRICKS, 1), Material.RED_NETHER_BRICK_SLAB);
        newShapedSlabRecipe("red_sandstone", new ItemStack(Material.RED_SANDSTONE, 1), Material.RED_SANDSTONE_SLAB);
        newShapedSlabRecipe("sandstone", new ItemStack(Material.SANDSTONE, 1), Material.SANDSTONE_SLAB);
        newShapedSlabRecipe("smooth_quartz", new ItemStack(Material.SMOOTH_QUARTZ, 1), Material.SMOOTH_QUARTZ_SLAB);
        newShapedSlabRecipe("smooth_red_sandstone", new ItemStack(Material.SMOOTH_RED_SANDSTONE, 1), Material.SMOOTH_RED_SANDSTONE_SLAB);
        newShapedSlabRecipe("smooth_sandstone", new ItemStack(Material.SMOOTH_SANDSTONE, 1), Material.SMOOTH_SANDSTONE_SLAB);
        newShapedSlabRecipe("spruce_planks", new ItemStack(Material.SPRUCE_PLANKS, 1), Material.SPRUCE_SLAB);
        newShapedSlabRecipe("stone_bricks", new ItemStack(Material.STONE_BRICKS, 1), Material.STONE_BRICK_SLAB);
        newShapedSlabRecipe("stone", new ItemStack(Material.STONE, 1), Material.STONE_SLAB);
        newShapedSlabRecipe("warped_planks", new ItemStack(Material.WARPED_PLANKS, 1), Material.WARPED_SLAB);
        newShapedSlabRecipe("cut_copper", new ItemStack(Material.CUT_COPPER, 1), Material.CUT_COPPER_SLAB);
        newShapedSlabRecipe("exposed_cut_copper", new ItemStack(Material.EXPOSED_CUT_COPPER, 1), Material.EXPOSED_CUT_COPPER_SLAB);
        newShapedSlabRecipe("weathered_cut_copper", new ItemStack(Material.WEATHERED_CUT_COPPER, 1), Material.WEATHERED_CUT_COPPER_SLAB);
        newShapedSlabRecipe("oxidized_cut_copper", new ItemStack(Material.OXIDIZED_CUT_COPPER, 1), Material.OXIDIZED_CUT_COPPER_SLAB);
        newShapedSlabRecipe("waxed_cut_copper", new ItemStack(Material.WAXED_CUT_COPPER, 1), Material.WAXED_CUT_COPPER_SLAB);
        newShapedSlabRecipe("waxed_exposed_cut_copper", new ItemStack(Material.WAXED_EXPOSED_CUT_COPPER, 1), Material.WAXED_EXPOSED_CUT_COPPER_SLAB);
        newShapedSlabRecipe("waxed_weathered_cut_copper", new ItemStack(Material.WAXED_WEATHERED_CUT_COPPER, 1), Material.WAXED_WEATHERED_CUT_COPPER_SLAB);
        newShapedSlabRecipe("waxed_oxidized_cut_copper", new ItemStack(Material.WAXED_OXIDIZED_CUT_COPPER, 1), Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        newShapedSlabRecipe("cobbled_deepslate", new ItemStack(Material.COBBLED_DEEPSLATE, 1), Material.COBBLED_DEEPSLATE_SLAB);
        newShapedSlabRecipe("polished_deepslate", new ItemStack(Material.POLISHED_DEEPSLATE, 1), Material.POLISHED_DEEPSLATE_SLAB);
        newShapedSlabRecipe("deepslate_bricks", new ItemStack(Material.DEEPSLATE_BRICKS, 1), Material.DEEPSLATE_BRICK_SLAB);
        newShapedSlabRecipe("deepslate_tiles", new ItemStack(Material.DEEPSLATE_TILES, 1), Material.DEEPSLATE_TILE_SLAB);
    }

    public static void newShapelessStairRecipe(String keyName, ItemStack result, Material ingredient, int amount) {
        NamespacedKey key = new NamespacedKey(main, keyName + "_vt_stair");

        ShapelessRecipe recipe = new ShapelessRecipe(key, result);

        recipe.addIngredient(amount, ingredient);

        Bukkit.addRecipe(recipe);
    }
    public static void newShapedSlabRecipe(String keyName, ItemStack result, Material ingredient) {
        NamespacedKey key = new NamespacedKey(main, keyName + "_vt_slab");

        ShapedRecipe recipe = new ShapedRecipe(key, result);

        recipe.shape("##");
        recipe.setIngredient('#', ingredient);

        Bukkit.addRecipe(recipe);
    }


}
