package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableEnchantedGoldenApples {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "enchanted_golden_apples_vt_apples");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));

        recipe.shape("xxx", "x@x", "xxx");
        recipe.setIngredient('x', Material.GOLD_BLOCK);
        recipe.setIngredient('@', Material.APPLE);

        Bukkit.addRecipe(recipe);
    }

}
