package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableSmallDripleaves {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {

        NamespacedKey key = new NamespacedKey(main, "vt_craftable_dripleaf");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.SMALL_DRIPLEAF, 1));

        recipe.addIngredient(Material.BIG_DRIPLEAF);
        Bukkit.addRecipe(recipe);

    }

}
