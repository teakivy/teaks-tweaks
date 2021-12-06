package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableGildedBlackstone {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {

        NamespacedKey key = new NamespacedKey(main, "vt_craftable_gilded_blackstone");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.GILDED_BLACKSTONE, 1));

        recipe.addIngredient(Material.BLACKSTONE);
        recipe.addIngredient(Material.GOLD_INGOT);
        Bukkit.addRecipe(recipe);

    }
}
