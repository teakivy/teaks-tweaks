package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CheaperResinBlocks extends AbstractCraftingTweak {

    public CheaperResinBlocks() {
        super("cheaper-resin-blocks", Material.RESIN_BLOCK);
    }

    @Override
    public void registerRecipes() {
        Bukkit.removeRecipe(NamespacedKey.minecraft("resin_block"));
        ShapedRecipe recipe = new ShapedRecipe(Key.get("resin_block"), new ItemStack(Material.RESIN_BLOCK, 1));
        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.RESIN_CLUMP);
        addRecipe(recipe);
    }
}
