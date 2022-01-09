package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class SmeltableRawOreBlocks {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        FurnaceRecipe ironRecipe = new FurnaceRecipe(new NamespacedKey(main, "tt_smeltable_raw_iron_block"), new ItemStack(Material.IRON_BLOCK), Material.RAW_IRON_BLOCK, 10, 1000);
        Bukkit.addRecipe(ironRecipe);
        FurnaceRecipe goldRecipe = new FurnaceRecipe(new NamespacedKey(main, "tt_smeltable_raw_gold_block"), new ItemStack(Material.GOLD_BLOCK), Material.RAW_GOLD_BLOCK, 10, 1000);
        Bukkit.addRecipe(goldRecipe);
        FurnaceRecipe copperRecipe = new FurnaceRecipe(new NamespacedKey(main, "tt_smeltable_raw_copper_block"), new ItemStack(Material.COPPER_BLOCK, 2), Material.RAW_COPPER_BLOCK, 10, 1000);
        Bukkit.addRecipe(copperRecipe);

        BlastingRecipe ironBlast = new BlastingRecipe(new NamespacedKey(main, "tt_smeltable_raw_iron_block_blast"), new ItemStack(Material.IRON_BLOCK), Material.RAW_IRON_BLOCK, 10, 600);
        Bukkit.addRecipe(ironBlast);
        BlastingRecipe goldBlast = new BlastingRecipe(new NamespacedKey(main, "tt_smeltable_raw_gold_block_blast"), new ItemStack(Material.GOLD_BLOCK), Material.RAW_GOLD_BLOCK, 10, 600);
        Bukkit.addRecipe(goldBlast);
        BlastingRecipe copperBlast = new BlastingRecipe(new NamespacedKey(main, "tt_smeltable_raw_copper_block_blast"), new ItemStack(Material.COPPER_BLOCK, 2), Material.RAW_COPPER_BLOCK, 10, 600);
        Bukkit.addRecipe(copperBlast);
    }
}
