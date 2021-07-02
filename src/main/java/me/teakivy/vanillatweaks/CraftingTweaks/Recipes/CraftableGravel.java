package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableGravel {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {

        NamespacedKey key = new NamespacedKey(main, "gravel_vt_gravel");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.GRAVEL, 4));

        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.FLINT);
        Bukkit.addRecipe(recipe);

    }

}
