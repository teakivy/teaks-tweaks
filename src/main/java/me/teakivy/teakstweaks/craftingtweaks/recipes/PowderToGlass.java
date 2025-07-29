package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class PowderToGlass extends AbstractCraftingTweak {

    public PowderToGlass() {
        super(TTCraftingTweak.POWDER_TO_GLASS, Material.GLASS);
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

    public void newSmeltableGlass(String color) {
        FurnaceRecipe recipe = new FurnaceRecipe(Key.get(color.toLowerCase() + "_powder_to_glass"), new ItemStack(Material.valueOf(color + "_STAINED_GLASS")), Material.valueOf(color + "_CONCRETE_POWDER"), 10, 100);
        addRecipe(recipe);
    }
}
