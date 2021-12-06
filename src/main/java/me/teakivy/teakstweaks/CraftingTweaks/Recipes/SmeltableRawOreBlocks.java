package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class SmeltableRawOreBlocks {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        FurnaceRecipe ironRecipe = new FurnaceRecipe(new ItemStack(Material.RAW_IRON_BLOCK), Material.IRON_BLOCK);
        Bukkit.addRecipe(ironRecipe);
        FurnaceRecipe goldRecipe = new FurnaceRecipe(new ItemStack(Material.RAW_GOLD_BLOCK), Material.GOLD_BLOCK);
        Bukkit.addRecipe(goldRecipe);
        FurnaceRecipe copperRecipe = new FurnaceRecipe(new ItemStack(Material.RAW_COPPER_BLOCK), Material.COPPER_BLOCK);
        Bukkit.addRecipe(copperRecipe);
    }
}
