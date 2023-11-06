package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class BackToBlocks extends AbstractRecipe {

    public BackToBlocks() {
        super("back-to-blocks", Material.BRICK_SLAB);
    }

    @Override
    public void registerRecipes() {
        registerStairRecipe(Material.ACACIA_PLANKS, Material.ACACIA_STAIRS);
        registerStairRecipe(Material.BIRCH_PLANKS, Material.BIRCH_STAIRS);
        registerStairRecipe(Material.BLACKSTONE, Material.BLACKSTONE_STAIRS);
        registerStairRecipe(Material.BRICKS, Material.BRICK_STAIRS);
        registerStairRecipe(Material.COBBLESTONE, Material.COBBLESTONE_STAIRS);
        registerStairRecipe(Material.CRIMSON_PLANKS, Material.CRIMSON_STAIRS);
        registerStairRecipe(Material.DARK_OAK_PLANKS, Material.DARK_OAK_STAIRS);
        registerStairRecipe(Material.DARK_PRISMARINE, Material.DARK_PRISMARINE_STAIRS);
        registerStairRecipe(Material.DIORITE, Material.DIORITE_STAIRS);
        registerStairRecipe(Material.END_STONE_BRICKS, Material.END_STONE_BRICK_STAIRS);
        registerStairRecipe(Material.GRANITE, Material.GRANITE_STAIRS);
        registerStairRecipe(Material.JUNGLE_PLANKS, Material.JUNGLE_STAIRS);
        registerStairRecipe(Material.MOSSY_COBBLESTONE, Material.MOSSY_COBBLESTONE_STAIRS);
        registerStairRecipe(Material.MOSSY_STONE_BRICKS, Material.MOSSY_STONE_BRICK_STAIRS);
        registerStairRecipe(Material.NETHER_BRICKS, Material.NETHER_BRICK_STAIRS);
        registerStairRecipe(Material.OAK_PLANKS, Material.OAK_STAIRS);
        registerStairRecipe(Material.POLISHED_ANDESITE, Material.POLISHED_ANDESITE_STAIRS);
        registerStairRecipe(Material.POLISHED_BLACKSTONE, Material.POLISHED_BLACKSTONE_STAIRS);
        registerStairRecipe(Material.POLISHED_BLACKSTONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICK_STAIRS);
        registerStairRecipe(Material.PRISMARINE_BRICKS, Material.PRISMARINE_BRICK_STAIRS);
        registerStairRecipe(Material.PRISMARINE, Material.PRISMARINE_STAIRS);
        registerStairRecipe(Material.PURPUR_BLOCK, Material.PURPUR_STAIRS);
        registerStairRecipe(Material.QUARTZ_BLOCK, Material.QUARTZ_STAIRS);
        registerStairRecipe(Material.RED_NETHER_BRICKS, Material.RED_NETHER_BRICK_STAIRS);
        registerStairRecipe(Material.RED_SANDSTONE, Material.RED_SANDSTONE_STAIRS);
        registerStairRecipe(Material.SANDSTONE, Material.SANDSTONE_STAIRS);
        registerStairRecipe(Material.SMOOTH_QUARTZ, Material.SMOOTH_QUARTZ_STAIRS);
        registerStairRecipe(Material.SMOOTH_RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE_STAIRS);
        registerStairRecipe(Material.SMOOTH_SANDSTONE, Material.SMOOTH_SANDSTONE_STAIRS);
        registerStairRecipe(Material.SPRUCE_PLANKS, Material.SPRUCE_STAIRS);
        registerStairRecipe(Material.STONE_BRICKS, Material.STONE_BRICK_STAIRS);
        registerStairRecipe(Material.STONE, Material.STONE_STAIRS);
        registerStairRecipe(Material.WARPED_PLANKS, Material.WARPED_STAIRS);
        registerStairRecipe(Material.CHERRY_PLANKS, Material.CHERRY_STAIRS);
        registerStairRecipe(Material.BAMBOO_PLANKS, Material.BAMBOO_STAIRS);
        registerStairRecipe(Material.BAMBOO_MOSAIC, Material.BAMBOO_MOSAIC_STAIRS);
        registerStairRecipe(Material.CUT_COPPER, Material.CUT_COPPER_STAIRS);
        registerStairRecipe(Material.EXPOSED_CUT_COPPER, Material.EXPOSED_CUT_COPPER_STAIRS);
        registerStairRecipe(Material.WEATHERED_CUT_COPPER, Material.WEATHERED_CUT_COPPER_STAIRS);
        registerStairRecipe(Material.OXIDIZED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER_STAIRS);
        registerStairRecipe(Material.WAXED_CUT_COPPER, Material.WAXED_CUT_COPPER_STAIRS);
        registerStairRecipe(Material.WAXED_EXPOSED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER_STAIRS);
        registerStairRecipe(Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_WEATHERED_CUT_COPPER_STAIRS);
        registerStairRecipe(Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
        registerStairRecipe(Material.COBBLED_DEEPSLATE, Material.COBBLED_DEEPSLATE_STAIRS);
        registerStairRecipe(Material.POLISHED_DEEPSLATE, Material.POLISHED_DEEPSLATE_STAIRS);
        registerStairRecipe(Material.DEEPSLATE_BRICKS, Material.DEEPSLATE_BRICK_STAIRS);
        registerStairRecipe(Material.DEEPSLATE_TILES, Material.DEEPSLATE_TILE_STAIRS);
        registerStairRecipe(Material.MANGROVE_PLANKS, Material.MANGROVE_STAIRS);
        registerStairRecipe(Material.MUD_BRICKS, Material.MUD_BRICK_STAIRS);

        registerSlabRecipe(Material.CUT_COPPER, Material.CUT_COPPER_SLAB);
        registerSlabRecipe(Material.EXPOSED_CUT_COPPER, Material.EXPOSED_CUT_COPPER_SLAB);
        registerSlabRecipe(Material.WEATHERED_CUT_COPPER, Material.WEATHERED_CUT_COPPER_SLAB);
        registerSlabRecipe(Material.OXIDIZED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER_SLAB);
        registerSlabRecipe(Material.WAXED_CUT_COPPER, Material.WAXED_CUT_COPPER_SLAB);
        registerSlabRecipe(Material.WAXED_EXPOSED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER_SLAB);
        registerSlabRecipe(Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_WEATHERED_CUT_COPPER_SLAB);
        registerSlabRecipe(Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        registerSlabRecipe(Material.COBBLED_DEEPSLATE, Material.COBBLED_DEEPSLATE_SLAB);
        registerSlabRecipe(Material.POLISHED_DEEPSLATE, Material.POLISHED_DEEPSLATE_SLAB);
        registerSlabRecipe(Material.DEEPSLATE_BRICKS, Material.DEEPSLATE_BRICK_SLAB);
        registerSlabRecipe(Material.DEEPSLATE_TILES, Material.DEEPSLATE_TILE_SLAB);
        registerSlabRecipe(Material.ACACIA_PLANKS, Material.ACACIA_SLAB);
        registerSlabRecipe(Material.BIRCH_PLANKS, Material.BIRCH_SLAB);
        registerSlabRecipe(Material.BLACKSTONE, Material.BLACKSTONE_SLAB);
        registerSlabRecipe(Material.BRICKS, Material.BRICK_SLAB);
        registerSlabRecipe(Material.COBBLESTONE, Material.COBBLESTONE_SLAB);
        registerSlabRecipe(Material.CRIMSON_PLANKS, Material.CRIMSON_SLAB);
        registerSlabRecipe(Material.DARK_OAK_PLANKS, Material.DARK_OAK_SLAB);
        registerSlabRecipe(Material.DARK_PRISMARINE, Material.DARK_PRISMARINE_SLAB);
        registerSlabRecipe(Material.DIORITE, Material.DIORITE_SLAB);
        registerSlabRecipe(Material.END_STONE_BRICKS, Material.END_STONE_BRICK_SLAB);
        registerSlabRecipe(Material.GRANITE, Material.GRANITE_SLAB);
        registerSlabRecipe(Material.JUNGLE_PLANKS, Material.JUNGLE_SLAB);
        registerSlabRecipe(Material.MOSSY_COBBLESTONE, Material.MOSSY_COBBLESTONE_SLAB);
        registerSlabRecipe(Material.MOSSY_STONE_BRICKS, Material.MOSSY_STONE_BRICK_SLAB);
        registerSlabRecipe(Material.NETHER_BRICKS, Material.NETHER_BRICK_SLAB);
        registerSlabRecipe(Material.OAK_PLANKS, Material.OAK_SLAB);
        registerSlabRecipe(Material.POLISHED_ANDESITE, Material.POLISHED_ANDESITE_SLAB);
        registerSlabRecipe(Material.POLISHED_BLACKSTONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICK_SLAB);
        registerSlabRecipe(Material.PRISMARINE_BRICKS, Material.PRISMARINE_BRICK_SLAB);
        registerSlabRecipe(Material.PRISMARINE, Material.PRISMARINE_SLAB);
        registerSlabRecipe(Material.PURPUR_BLOCK, Material.PURPUR_SLAB);
        registerSlabRecipe(Material.QUARTZ_BLOCK, Material.QUARTZ_SLAB);
        registerSlabRecipe(Material.RED_NETHER_BRICKS, Material.RED_NETHER_BRICK_SLAB);
        registerSlabRecipe(Material.RED_SANDSTONE, Material.RED_SANDSTONE_SLAB);
        registerSlabRecipe(Material.SANDSTONE, Material.SANDSTONE_SLAB);
        registerSlabRecipe(Material.SMOOTH_QUARTZ, Material.SMOOTH_QUARTZ_SLAB);
        registerSlabRecipe(Material.SMOOTH_RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE_SLAB);
        registerSlabRecipe(Material.SMOOTH_SANDSTONE, Material.SMOOTH_SANDSTONE_SLAB);
        registerSlabRecipe(Material.SPRUCE_PLANKS, Material.SPRUCE_SLAB);
        registerSlabRecipe(Material.STONE_BRICKS, Material.STONE_BRICK_SLAB);
        registerSlabRecipe(Material.STONE, Material.STONE_SLAB);
        registerSlabRecipe(Material.WARPED_PLANKS, Material.WARPED_SLAB);
        registerSlabRecipe(Material.CHERRY_PLANKS, Material.CHERRY_SLAB);
        registerSlabRecipe(Material.BAMBOO_PLANKS, Material.BAMBOO_SLAB);
        registerSlabRecipe(Material.BAMBOO_MOSAIC, Material.BAMBOO_MOSAIC_SLAB);
        registerSlabRecipe(Material.MANGROVE_PLANKS, Material.MANGROVE_SLAB);
        registerSlabRecipe(Material.MUD_BRICKS, Material.MUD_BRICK_SLAB);

    }

    private void registerStairRecipe(Material result, Material ingredient) {
        NamespacedKey key = Key.get(ingredient.toString().toLowerCase() + "_stair");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(result, 3));

        recipe.addIngredient(4, ingredient);

        Bukkit.addRecipe(recipe);
    }

    private void registerSlabRecipe(Material result, Material ingredient) {
        NamespacedKey key = Key.get(ingredient.toString().toLowerCase() + "_slab");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(result, 1));

        recipe.shape("##");
        recipe.setIngredient('#', ingredient);

        Bukkit.addRecipe(recipe);
    }
}
