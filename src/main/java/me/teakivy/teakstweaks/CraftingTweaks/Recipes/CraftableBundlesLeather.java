package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableBundlesLeather {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        if (Bukkit.getVersion().toString().contains("1.17")) {
            NamespacedKey key = new NamespacedKey(main, "bundle_leather_vt_bundles");

            ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.BUNDLE));

            recipe.shape("s#s", "# #", "###");
            recipe.setIngredient('#', Material.LEATHER);
            recipe.setIngredient('s', Material.STRING);

            Bukkit.addRecipe(recipe);
        }
    }
}
