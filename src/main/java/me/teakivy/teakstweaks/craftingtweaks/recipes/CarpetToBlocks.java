package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CarpetToBlocks extends AbstractCraftingTweak {

    public CarpetToBlocks() {
        super(TTCraftingTweak.CARPET_TO_BLOCKS, Material.WHITE_CARPET);
    }

    @Override
    public void registerRecipes() {
        registerBlockRecipe(Material.WHITE_WOOL,  Material.WHITE_CARPET);
        registerBlockRecipe(Material.ORANGE_WOOL, Material.ORANGE_CARPET);
        registerBlockRecipe(Material.MAGENTA_WOOL, Material.MAGENTA_CARPET);
        registerBlockRecipe(Material.LIGHT_BLUE_WOOL, Material.LIGHT_BLUE_CARPET);
        registerBlockRecipe(Material.YELLOW_WOOL, Material.YELLOW_CARPET);
        registerBlockRecipe(Material.LIME_WOOL, Material.LIME_CARPET);
        registerBlockRecipe(Material.PINK_WOOL, Material.PINK_CARPET);
        registerBlockRecipe(Material.GRAY_WOOL, Material.GRAY_CARPET);
        registerBlockRecipe(Material.LIGHT_GRAY_WOOL, Material.LIGHT_GRAY_CARPET);
        registerBlockRecipe(Material.CYAN_WOOL, Material.CYAN_CARPET);
        registerBlockRecipe(Material.PURPLE_WOOL, Material.PURPLE_CARPET);
        registerBlockRecipe(Material.BLUE_WOOL, Material.BLUE_CARPET);
        registerBlockRecipe(Material.BROWN_WOOL, Material.BROWN_CARPET);
        registerBlockRecipe(Material.GREEN_WOOL, Material.GREEN_CARPET);
        registerBlockRecipe(Material.RED_WOOL, Material.RED_CARPET);
        registerBlockRecipe(Material.BLACK_WOOL, Material.BLACK_CARPET);
        registerBlockRecipe(Material.MOSS_BLOCK, Material.MOSS_CARPET);
        registerBlockRecipe(Material.PALE_MOSS_BLOCK, Material.PALE_MOSS_CARPET);
    }

    private void registerBlockRecipe(Material result, Material ingredient) {
        NamespacedKey key = Key.get(ingredient.toString().toLowerCase() + "_to_block");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(result, 2));

        recipe.addIngredient(3, ingredient);

        addRecipe(recipe);
    }
}
