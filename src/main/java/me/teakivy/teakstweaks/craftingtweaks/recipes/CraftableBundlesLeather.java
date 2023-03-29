package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableBundlesLeather extends AbstractRecipe {

    public CraftableBundlesLeather() {
        super("Craftable Bundles Leather", "craftable-bundles-leather", Material.BUNDLE, "Allows you to craft 1.18's Bundles in 1.17, using Leather instead of Rabbit Hide.");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "bundle_leather_bundles");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.BUNDLE));

        recipe.shape("s#s", "# #", "###");
        recipe.setIngredient('#', Material.LEATHER);
        recipe.setIngredient('s', Material.STRING);

        Bukkit.addRecipe(recipe);
    }
}
