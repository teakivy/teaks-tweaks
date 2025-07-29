package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class DoubleSlabs extends AbstractCraftingTweak {

    public DoubleSlabs() {
        super(TTCraftingTweak.DOUBLE_SLABS, Material.STONE_SLAB);
    }

    @Override
    public void registerRecipes() {
        newShapelessRecipe(Material.BLACKSTONE, Material.BLACKSTONE_SLAB);
        newShapelessRecipe(Material.BRICKS, Material.BRICK_SLAB);
        newShapelessRecipe(Material.COBBLESTONE, Material.COBBLESTONE_SLAB);
        newShapelessRecipe(Material.DARK_PRISMARINE, Material.DARK_PRISMARINE_SLAB);
        newShapelessRecipe(Material.DIORITE, Material.DIORITE_SLAB);
        newShapelessRecipe(Material.END_STONE_BRICKS, Material.END_STONE_BRICK_SLAB);
        newShapelessRecipe(Material.GRANITE, Material.GRANITE_SLAB);
        newShapelessRecipe(Material.MOSSY_COBBLESTONE, Material.MOSSY_COBBLESTONE_SLAB);
        newShapelessRecipe(Material.MOSSY_STONE_BRICKS, Material.MOSSY_STONE_BRICK_SLAB);
        newShapelessRecipe(Material.NETHER_BRICKS, Material.NETHER_BRICK_SLAB);
        newShapelessRecipe(Material.POLISHED_ANDESITE, Material.POLISHED_ANDESITE_SLAB);
        newShapelessRecipe(Material.ANDESITE, Material.ANDESITE_SLAB);
        newShapelessRecipe(Material.POLISHED_BLACKSTONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICK_SLAB);
        newShapelessRecipe(Material.PRISMARINE_BRICKS, Material.PRISMARINE_BRICK_SLAB);
        newShapelessRecipe(Material.PRISMARINE, Material.PRISMARINE_SLAB);
        newShapelessRecipe(Material.PURPUR_BLOCK, Material.PURPUR_SLAB);
        newShapelessRecipe(Material.QUARTZ_BLOCK, Material.QUARTZ_SLAB);
        newShapelessRecipe(Material.RED_NETHER_BRICKS, Material.RED_NETHER_BRICK_SLAB);
        newShapelessRecipe(Material.RED_SANDSTONE, Material.RED_SANDSTONE_SLAB);
        newShapelessRecipe(Material.SANDSTONE, Material.SANDSTONE_SLAB);
        newShapelessRecipe(Material.SMOOTH_QUARTZ, Material.SMOOTH_QUARTZ_SLAB);
        newShapelessRecipe(Material.SMOOTH_RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE_SLAB);
        newShapelessRecipe(Material.SMOOTH_SANDSTONE, Material.SMOOTH_SANDSTONE_SLAB);
        newShapelessRecipe(Material.STONE_BRICKS, Material.STONE_BRICK_SLAB);
        newShapelessRecipe(Material.CUT_COPPER, Material.CUT_COPPER_SLAB);
        newShapelessRecipe(Material.EXPOSED_CUT_COPPER, Material.EXPOSED_CUT_COPPER_SLAB);
        newShapelessRecipe(Material.WEATHERED_CUT_COPPER, Material.WEATHERED_CUT_COPPER_SLAB);
        newShapelessRecipe(Material.OXIDIZED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER_SLAB);
        newShapelessRecipe(Material.WAXED_CUT_COPPER, Material.WAXED_CUT_COPPER_SLAB);
        newShapelessRecipe(Material.WAXED_EXPOSED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER_SLAB);
        newShapelessRecipe(Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_WEATHERED_CUT_COPPER_SLAB);
        newShapelessRecipe(Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        newShapelessRecipe(Material.COBBLED_DEEPSLATE, Material.COBBLED_DEEPSLATE_SLAB);
        newShapelessRecipe(Material.POLISHED_DEEPSLATE, Material.POLISHED_DEEPSLATE_SLAB);
        newShapelessRecipe(Material.DEEPSLATE_BRICKS, Material.DEEPSLATE_BRICK_SLAB);
        newShapelessRecipe(Material.DEEPSLATE_TILES, Material.DEEPSLATE_TILE_SLAB);
        newShapelessRecipe(Material.MUD_BRICKS, Material.MUD_BRICK_SLAB);
        newShapelessRecipe(Material.BAMBOO_MOSAIC, Material.BAMBOO_MOSAIC_SLAB);
        newShapelessRecipe(Material.SMOOTH_STONE, Material.SMOOTH_STONE_SLAB);
        newShapelessRecipe(Material.POLISHED_GRANITE, Material.POLISHED_GRANITE_SLAB);
        newShapelessRecipe(Material.POLISHED_DIORITE, Material.POLISHED_DIORITE_SLAB);
        newShapelessRecipe(Material.TUFF, Material.TUFF_SLAB);
        newShapelessRecipe(Material.POLISHED_TUFF, Material.POLISHED_TUFF_SLAB);
        newShapelessRecipe(Material.TUFF_BRICKS, Material.TUFF_BRICK_SLAB);
        newShapelessRecipe(Material.CUT_SANDSTONE, Material.CUT_SANDSTONE_SLAB);
        newShapelessRecipe(Material.CUT_RED_SANDSTONE, Material.CUT_RED_SANDSTONE_SLAB);
        newShapelessRecipe(Material.RESIN_BRICKS, Material.RESIN_BRICK_SLAB);
    }

    public void newShapelessRecipe(Material ingredient, Material result) {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get(ingredient.toString().toLowerCase() + "_double_slab"), new ItemStack(result, 2));
        recipe.addIngredient(ingredient);
        addRecipe(recipe);
    }
}
