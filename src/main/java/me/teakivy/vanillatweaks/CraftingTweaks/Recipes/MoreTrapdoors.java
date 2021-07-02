package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreTrapdoors {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        newTrapdoorRecipe("OAK");
        newTrapdoorRecipe("DARK_OAK");
        newTrapdoorRecipe("BIRCH");
        newTrapdoorRecipe("SPRUCE");
        newTrapdoorRecipe("JUNGLE");
        newTrapdoorRecipe("ACACIA");
        newTrapdoorRecipe("CRIMSON");
        newTrapdoorRecipe("WARPED");
    }

    public static void newTrapdoorRecipe(String type) {
        NamespacedKey key = new NamespacedKey(main, type.toLowerCase() + "_vt_trapdoors");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.valueOf(type + "_TRAPDOOR"), 12));

        recipe.shape("xxx", "xxx");
        recipe.setIngredient('x', Material.valueOf(type + "_PLANKS"));

        Bukkit.addRecipe(recipe);
    }


}
