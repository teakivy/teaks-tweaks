package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

public class UnpackableWool {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        List<Material> woolList = new ArrayList<Material>();
        woolList.add(Material.WHITE_WOOL);
        woolList.add(Material.ORANGE_WOOL);
        woolList.add(Material.MAGENTA_WOOL);
        woolList.add(Material.LIGHT_BLUE_WOOL);
        woolList.add(Material.YELLOW_WOOL);
        woolList.add(Material.LIME_WOOL);
        woolList.add(Material.PINK_WOOL);
        woolList.add(Material.GRAY_WOOL);
        woolList.add(Material.LIGHT_GRAY_WOOL);
        woolList.add(Material.CYAN_WOOL);
        woolList.add(Material.PURPLE_WOOL);
        woolList.add(Material.BLUE_WOOL);
        woolList.add(Material.BROWN_WOOL);
        woolList.add(Material.GREEN_WOOL);
        woolList.add(Material.RED_WOOL);
        woolList.add(Material.BLACK_WOOL);

        RecipeChoice wool = new RecipeChoice.MaterialChoice(woolList);

        NamespacedKey key = new NamespacedKey(main, "wool_vt_unpackables");
        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.STRING, 4));
        recipe.addIngredient(wool);

        Bukkit.addRecipe(recipe);
    }
}
