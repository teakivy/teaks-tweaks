package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class RottenFleshToLeather {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        FurnaceRecipe recipe = new FurnaceRecipe(new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH);
        Bukkit.addRecipe(recipe);
    }

}
