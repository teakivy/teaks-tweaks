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
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("bundle_craftables"), new ItemStack(Material.BUNDLE));
        recipe.addIngredient(Material.LEATHER);
        recipe.addIngredient(Material.STRING);
        addRecipe(recipe);
    }
}
