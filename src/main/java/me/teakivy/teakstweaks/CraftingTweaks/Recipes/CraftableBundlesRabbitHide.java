package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableBundlesRabbitHide {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "bundle_rabbit_hide_vt_bundles");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.BUNDLE));

        recipe.shape("s#s", "# #", "###");
        recipe.setIngredient('#', Material.RABBIT_HIDE);
        recipe.setIngredient('s', Material.STRING);

        Bukkit.addRecipe(recipe);
    }
}
