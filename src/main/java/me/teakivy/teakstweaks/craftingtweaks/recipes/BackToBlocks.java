package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class BackToBlocks extends AbstractCraftingTweak {

    public BackToBlocks() {
        super("back-to-blocks", Material.BRICK_SLAB);
    }

    @Override
    public void registerRecipes() {
        registerDualRecipe(Material.OAK_PLANKS, Material.OAK_STAIRS, Material.OAK_SLAB);
        registerDualRecipe(Material.SPRUCE_PLANKS, Material.SPRUCE_STAIRS, Material.SPRUCE_SLAB);
        registerDualRecipe(Material.BIRCH_PLANKS, Material.BIRCH_STAIRS, Material.BIRCH_SLAB);
        registerDualRecipe(Material.JUNGLE_PLANKS, Material.JUNGLE_STAIRS, Material.JUNGLE_SLAB);
        registerDualRecipe(Material.ACACIA_PLANKS, Material.ACACIA_STAIRS, Material.ACACIA_SLAB);
        registerDualRecipe(Material.DARK_OAK_PLANKS, Material.DARK_OAK_STAIRS, Material.DARK_OAK_SLAB);
        registerDualRecipe(Material.MANGROVE_PLANKS, Material.MANGROVE_STAIRS, Material.MANGROVE_SLAB);
        registerDualRecipe(Material.CHERRY_PLANKS, Material.CHERRY_STAIRS, Material.CHERRY_SLAB);
        registerStairRecipe(Material.BAMBOO_PLANKS, Material.BAMBOO_STAIRS);
        registerDualRecipe(Material.BAMBOO_MOSAIC, Material.BAMBOO_MOSAIC_STAIRS, Material.BAMBOO_MOSAIC_SLAB);
        registerDualRecipe(Material.CRIMSON_PLANKS, Material.CRIMSON_STAIRS, Material.CRIMSON_SLAB);
        registerDualRecipe(Material.WARPED_PLANKS, Material.WARPED_STAIRS, Material.WARPED_SLAB);
        registerDualRecipe(Material.STONE, Material.STONE_STAIRS, Material.STONE_SLAB);
        registerDualRecipe(Material.COBBLESTONE, Material.COBBLESTONE_STAIRS, Material.COBBLESTONE_SLAB);
        registerDualRecipe(Material.MOSSY_COBBLESTONE, Material.MOSSY_COBBLESTONE_STAIRS, Material.MOSSY_COBBLESTONE_SLAB);
        registerSlabRecipe(Material.SMOOTH_STONE, Material.SMOOTH_STONE_SLAB);
        registerDualRecipe(Material.STONE_BRICKS, Material.STONE_BRICK_STAIRS, Material.STONE_BRICK_SLAB);
        registerDualRecipe(Material.MOSSY_STONE_BRICKS, Material.MOSSY_STONE_BRICK_STAIRS, Material.MOSSY_STONE_BRICK_SLAB);
        registerDualRecipe(Material.GRANITE, Material.GRANITE_STAIRS, Material.GRANITE_SLAB);
        registerDualRecipe(Material.POLISHED_GRANITE, Material.POLISHED_GRANITE_STAIRS, Material.POLISHED_GRANITE_SLAB);
        registerDualRecipe(Material.DIORITE, Material.DIORITE_STAIRS, Material.DIORITE_SLAB);
        registerDualRecipe(Material.POLISHED_DIORITE, Material.POLISHED_DIORITE_STAIRS, Material.POLISHED_DIORITE_SLAB);
        registerDualRecipe(Material.ANDESITE, Material.ANDESITE_STAIRS, Material.ANDESITE_SLAB);
        registerDualRecipe(Material.POLISHED_ANDESITE, Material.POLISHED_ANDESITE_STAIRS, Material.POLISHED_ANDESITE_SLAB);
        registerDualRecipe(Material.COBBLED_DEEPSLATE, Material.COBBLED_DEEPSLATE_STAIRS, Material.COBBLED_DEEPSLATE_SLAB);
        registerDualRecipe(Material.POLISHED_DEEPSLATE, Material.POLISHED_DEEPSLATE_STAIRS, Material.POLISHED_DEEPSLATE_SLAB);
        registerDualRecipe(Material.DEEPSLATE_BRICKS, Material.DEEPSLATE_BRICK_STAIRS, Material.DEEPSLATE_BRICK_SLAB);
        registerDualRecipe(Material.DEEPSLATE_TILES, Material.DEEPSLATE_TILE_STAIRS, Material.DEEPSLATE_TILE_SLAB);
        registerDualRecipe(Material.TUFF, Material.TUFF_STAIRS, Material.TUFF_SLAB);
        registerDualRecipe(Material.POLISHED_TUFF, Material.POLISHED_TUFF_STAIRS, Material.POLISHED_TUFF_SLAB);
        registerDualRecipe(Material.TUFF_BRICKS, Material.TUFF_BRICK_STAIRS, Material.TUFF_BRICK_SLAB);
        registerDualRecipe(Material.BRICKS, Material.BRICK_STAIRS, Material.BRICK_SLAB);
        registerDualRecipe(Material.MUD_BRICKS, Material.MUD_BRICK_STAIRS, Material.MUD_BRICK_SLAB);
        registerDualRecipe(Material.SANDSTONE, Material.SANDSTONE_STAIRS, Material.SANDSTONE_SLAB);
        registerDualRecipe(Material.SMOOTH_SANDSTONE, Material.SMOOTH_SANDSTONE_STAIRS, Material.SMOOTH_SANDSTONE_SLAB);
        registerSlabRecipe(Material.CUT_SANDSTONE, Material.CUT_SANDSTONE_SLAB);
        registerDualRecipe(Material.RED_SANDSTONE, Material.RED_SANDSTONE_STAIRS, Material.RED_SANDSTONE_SLAB);
        registerDualRecipe(Material.SMOOTH_RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE_STAIRS, Material.SMOOTH_RED_SANDSTONE_SLAB);
        registerSlabRecipe(Material.CUT_RED_SANDSTONE, Material.CUT_RED_SANDSTONE_SLAB);
        registerDualRecipe(Material.PRISMARINE, Material.PRISMARINE_STAIRS, Material.PRISMARINE_SLAB);
        registerDualRecipe(Material.PRISMARINE_BRICKS, Material.PRISMARINE_BRICK_STAIRS, Material.PRISMARINE_BRICK_SLAB);
        registerDualRecipe(Material.DARK_PRISMARINE, Material.DARK_PRISMARINE_STAIRS, Material.DARK_PRISMARINE_SLAB);
        registerDualRecipe(Material.NETHER_BRICKS, Material.NETHER_BRICK_STAIRS, Material.NETHER_BRICK_SLAB);
        registerDualRecipe(Material.RED_NETHER_BRICKS, Material.RED_NETHER_BRICK_STAIRS, Material.RED_NETHER_BRICK_SLAB);
        registerDualRecipe(Material.BLACKSTONE, Material.BLACKSTONE_STAIRS, Material.BLACKSTONE_SLAB);
        registerDualRecipe(Material.POLISHED_BLACKSTONE, Material.POLISHED_BLACKSTONE_STAIRS, Material.POLISHED_BLACKSTONE_SLAB);
        registerDualRecipe(Material.POLISHED_BLACKSTONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICK_STAIRS, Material.POLISHED_BLACKSTONE_BRICK_SLAB);
        registerDualRecipe(Material.END_STONE_BRICKS, Material.END_STONE_BRICK_STAIRS, Material.END_STONE_BRICK_SLAB);
        registerDualRecipe(Material.PURPUR_BLOCK, Material.PURPUR_STAIRS, Material.PURPUR_SLAB);
        registerDualRecipe(Material.QUARTZ_BLOCK, Material.QUARTZ_STAIRS, Material.QUARTZ_SLAB);
        registerDualRecipe(Material.SMOOTH_QUARTZ, Material.SMOOTH_QUARTZ_STAIRS, Material.SMOOTH_QUARTZ_SLAB);
        registerDualRecipe(Material.CUT_COPPER, Material.CUT_COPPER_STAIRS, Material.CUT_COPPER_SLAB);
        registerDualRecipe(Material.EXPOSED_CUT_COPPER, Material.EXPOSED_CUT_COPPER_STAIRS, Material.EXPOSED_CUT_COPPER_SLAB);
        registerDualRecipe(Material.WEATHERED_CUT_COPPER, Material.WEATHERED_CUT_COPPER_STAIRS, Material.WEATHERED_CUT_COPPER_SLAB);
        registerDualRecipe(Material.OXIDIZED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER_STAIRS, Material.OXIDIZED_CUT_COPPER_SLAB);
        registerDualRecipe(Material.WAXED_CUT_COPPER, Material.WAXED_CUT_COPPER_STAIRS, Material.WAXED_CUT_COPPER_SLAB);
        registerDualRecipe(Material.WAXED_EXPOSED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER_STAIRS, Material.WAXED_EXPOSED_CUT_COPPER_SLAB);
        registerDualRecipe(Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_WEATHERED_CUT_COPPER_STAIRS, Material.WAXED_WEATHERED_CUT_COPPER_SLAB);
        registerDualRecipe(Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS, Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        registerDualRecipe(Material.PALE_OAK_PLANKS, Material.PALE_OAK_STAIRS, Material.PALE_OAK_SLAB);
        registerDualRecipe(Material.RESIN_BRICKS, Material.RESIN_BRICK_STAIRS, Material.RESIN_BRICK_SLAB);
    }

    private void registerStairRecipe(Material result, Material ingredient) {
        NamespacedKey key = Key.get(ingredient.toString().toLowerCase() + "_stair");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(result, 3));

        recipe.addIngredient(4, ingredient);

        addRecipe(recipe);
    }

    private void registerSlabRecipe(Material result, Material ingredient) {
        NamespacedKey key = Key.get(ingredient.toString().toLowerCase() + "_slab");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(result, 1));

        recipe.addIngredient(2, ingredient);

        addRecipe(recipe);
    }

    private void registerDualRecipe(Material result, Material stairIngredient, Material slabIngredient) {
        registerStairRecipe(result, stairIngredient);
        registerSlabRecipe(result, slabIngredient);
    }
}
