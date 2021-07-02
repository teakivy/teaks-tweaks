package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class PowderToGlass {

    public static void registerRecipes() {
        newSmeltableGlass("BLACK");
        newSmeltableGlass("BLUE");
        newSmeltableGlass("BROWN");
        newSmeltableGlass("CYAN");
        newSmeltableGlass("GRAY");
        newSmeltableGlass("GREEN");
        newSmeltableGlass("LIGHT_BLUE");
        newSmeltableGlass("LIGHT_GRAY");
        newSmeltableGlass("LIME");
        newSmeltableGlass("MAGENTA");
        newSmeltableGlass("ORANGE");
        newSmeltableGlass("PINK");
        newSmeltableGlass("PURPLE");
        newSmeltableGlass("RED");
        newSmeltableGlass("WHITE");
        newSmeltableGlass("YELLOW");
    }

    public static void newSmeltableGlass(String color) {
        FurnaceRecipe recipe = new FurnaceRecipe(new ItemStack(Material.valueOf(color + "_STAINED_GLASS")), Material.valueOf(color + "_CONCRETE_POWDER"));
        Bukkit.addRecipe(recipe);
    }
}
