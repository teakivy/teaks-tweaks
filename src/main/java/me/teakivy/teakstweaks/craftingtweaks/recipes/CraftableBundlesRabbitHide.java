package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableBundlesRabbitHide extends AbstractRecipe {

    public CraftableBundlesRabbitHide() {
        super("craftable-bundles-rabbit-hide", Material.BUNDLE);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("bundle_rabbit_hide_bundles"), new ItemStack(Material.BUNDLE));
        recipe.shape("s#s", "# #", "###");
        recipe.setIngredient('#', Material.RABBIT_HIDE);
        recipe.setIngredient('s', Material.STRING);
        Bukkit.addRecipe(recipe);
    }
}
