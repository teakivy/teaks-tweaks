package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreStairs extends AbstractRecipe {

    public MoreStairs() {
        super("More Stairs");
    }

    @Override
    public void registerRecipes() {
        newStairsRecipe(Material.OAK_PLANKS, Material.OAK_STAIRS);
        newStairsRecipe(Material.DARK_OAK_PLANKS, Material.DARK_OAK_STAIRS);
        newStairsRecipe(Material.BIRCH_PLANKS, Material.BIRCH_STAIRS);
        newStairsRecipe(Material.SPRUCE_PLANKS, Material.SPRUCE_STAIRS);
        newStairsRecipe(Material.JUNGLE_PLANKS, Material.JUNGLE_STAIRS);
        newStairsRecipe(Material.ACACIA_PLANKS, Material.ACACIA_STAIRS);
        newStairsRecipe(Material.CRIMSON_PLANKS, Material.CRIMSON_STAIRS);
        newStairsRecipe(Material.WARPED_PLANKS, Material.WARPED_STAIRS);
        newStairsRecipe(Material.CUT_COPPER, Material.CUT_COPPER_STAIRS);
        newStairsRecipe(Material.EXPOSED_CUT_COPPER, Material.EXPOSED_CUT_COPPER_STAIRS);
        newStairsRecipe(Material.WEATHERED_CUT_COPPER, Material.WEATHERED_CUT_COPPER_STAIRS);
        newStairsRecipe(Material.OXIDIZED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER_STAIRS);
        newStairsRecipe(Material.WAXED_CUT_COPPER, Material.WAXED_CUT_COPPER_STAIRS);
        newStairsRecipe(Material.WAXED_EXPOSED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER_STAIRS);
        newStairsRecipe(Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_WEATHERED_CUT_COPPER_STAIRS);
        newStairsRecipe(Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
        newStairsRecipe(Material.PURPUR_BLOCK, Material.PURPUR_STAIRS);
        newStairsRecipe(Material.COBBLESTONE, Material.COBBLESTONE_STAIRS);
        newStairsRecipe(Material.BRICKS, Material.BRICK_STAIRS);
        newStairsRecipe(Material.STONE, Material.STONE_STAIRS);
        newStairsRecipe(Material.STONE_BRICKS, Material.STONE_BRICK_STAIRS);
        newStairsRecipe(Material.NETHER_BRICKS, Material.NETHER_BRICK_STAIRS);
        newStairsRecipe(Material.SANDSTONE, Material.SANDSTONE_STAIRS);
        newStairsRecipe(Material.QUARTZ_BLOCK, Material.QUARTZ_STAIRS);
        newStairsRecipe(Material.PRISMARINE, Material.PRISMARINE_STAIRS);
        newStairsRecipe(Material.PRISMARINE_BRICKS, Material.PRISMARINE_BRICK_STAIRS);
        newStairsRecipe(Material.DARK_PRISMARINE, Material.DARK_PRISMARINE_STAIRS);
        newStairsRecipe(Material.RED_SANDSTONE, Material.RED_SANDSTONE_STAIRS);
        newStairsRecipe(Material.POLISHED_GRANITE, Material.POLISHED_GRANITE_STAIRS);
        newStairsRecipe(Material.SMOOTH_RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE_STAIRS);
        newStairsRecipe(Material.MOSSY_STONE_BRICKS, Material.MOSSY_STONE_BRICK_STAIRS);
        newStairsRecipe(Material.POLISHED_DIORITE, Material.POLISHED_DIORITE_STAIRS);
        newStairsRecipe(Material.MOSSY_COBBLESTONE, Material.MOSSY_COBBLESTONE_STAIRS);
        newStairsRecipe(Material.END_STONE_BRICKS, Material.END_STONE_BRICK_STAIRS);
        newStairsRecipe(Material.SMOOTH_SANDSTONE, Material.SMOOTH_SANDSTONE_STAIRS);
        newStairsRecipe(Material.SMOOTH_QUARTZ, Material.SMOOTH_QUARTZ_STAIRS);
        newStairsRecipe(Material.GRANITE, Material.GRANITE_STAIRS);
        newStairsRecipe(Material.ANDESITE, Material.ANDESITE_STAIRS);
        newStairsRecipe(Material.RED_NETHER_BRICKS, Material.RED_NETHER_BRICK_STAIRS);
        newStairsRecipe(Material.POLISHED_ANDESITE, Material.POLISHED_ANDESITE_STAIRS);
        newStairsRecipe(Material.DIORITE, Material.DIORITE_STAIRS);
        newStairsRecipe(Material.COBBLED_DEEPSLATE, Material.COBBLED_DEEPSLATE_STAIRS);
        newStairsRecipe(Material.POLISHED_DEEPSLATE, Material.POLISHED_DEEPSLATE_STAIRS);
        newStairsRecipe(Material.DEEPSLATE_BRICKS, Material.DEEPSLATE_BRICK_STAIRS);
        newStairsRecipe(Material.DEEPSLATE_TILES, Material.DEEPSLATE_TILE_STAIRS);
        newStairsRecipe(Material.BLACKSTONE, Material.BLACKSTONE_STAIRS);
        newStairsRecipe(Material.POLISHED_BLACKSTONE, Material.POLISHED_BLACKSTONE_STAIRS);
        newStairsRecipe(Material.POLISHED_BLACKSTONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICK_STAIRS);
        newStairsRecipe(Material.MANGROVE_PLANKS, Material.MANGROVE_STAIRS);
    }

    public static void newStairsRecipe(Material input, Material output) {
        NamespacedKey key = new NamespacedKey(main, output.name().toLowerCase() + "_vt_stairs");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(output, 8));

        recipe.shape("x  ", "xx ", "xxx");
        recipe.setIngredient('x', input);

        Bukkit.addRecipe(recipe);
    }
}
