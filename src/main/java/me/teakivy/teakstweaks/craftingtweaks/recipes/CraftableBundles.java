package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableBundles extends AbstractCraftingTweak {

    public CraftableBundles() {
        super("craftable-bundles", Material.BUNDLE);
    }

    @Override
    public void registerRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("bundle_rabbit_hide_bundles"), new ItemStack(Material.BUNDLE));
        recipe.addIngredient(Material.LEATHER);
        recipe.addIngredient(Material.STRING);
        Bukkit.addRecipe(recipe);
    }

//    @Override
//    public void registerRecipes() {
//        ShapedRecipe recipe = new ShapedRecipe(Key.get("bundle_rabbit_hide_bundles"), new ItemStack(Material.BUNDLE));
//        recipe.shape ("s#s", "# #", "###");
//        recipe.setIngredient('#', Material.RABBIT_HIDE);
//        recipe.setIngredient('s', Material.STRING);
//        Bukkit.addRecipe(recipe);
//    }
}
