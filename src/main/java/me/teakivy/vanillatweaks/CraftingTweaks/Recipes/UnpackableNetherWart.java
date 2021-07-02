package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class UnpackableNetherWart {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "nether_wart_vt_unpackables");
        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.NETHER_WART, 9));
        recipe.addIngredient(Material.NETHER_WART_BLOCK);

        Bukkit.addRecipe(recipe);
    }
}
