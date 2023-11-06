package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class PowderToGlass extends AbstractRecipe {

    public PowderToGlass() {
        super("powder-to-glass", Material.RED_CONCRETE_POWDER);
    }

    @Override
    public void registerRecipes() {
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
        FurnaceRecipe recipe = new FurnaceRecipe(Key.get("rotten_flesh_to_leather"), new ItemStack(Material.valueOf(color + "_STAINED_GLASS")), Material.valueOf(color + "_CONCRETE_POWDER"), 10, 100);
        Bukkit.addRecipe(recipe);
    }
}
