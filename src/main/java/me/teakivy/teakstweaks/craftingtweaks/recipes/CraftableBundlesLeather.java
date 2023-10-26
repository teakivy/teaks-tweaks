package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableBundlesLeather extends AbstractRecipe {

    public CraftableBundlesLeather() {
        super("craftable-bundles-leather", Material.BUNDLE);
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = Key.get("bundle_leather_bundles");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.BUNDLE));

        recipe.shape("s#s", "# #", "###");
        recipe.setIngredient('#', Material.LEATHER);
        recipe.setIngredient('s', Material.STRING);

        Bukkit.addRecipe(recipe);
    }
}
