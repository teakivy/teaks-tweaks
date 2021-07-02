package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableNameTags {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "name_tags_vt_tags");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.NAME_TAG));

        recipe.shape(" is", " pi", "p  ");
        recipe.setIngredient('i', Material.IRON_INGOT);
        recipe.setIngredient('p', Material.PAPER);
        recipe.setIngredient('s', Material.STRING);


        Bukkit.addRecipe(recipe);
    }
}
