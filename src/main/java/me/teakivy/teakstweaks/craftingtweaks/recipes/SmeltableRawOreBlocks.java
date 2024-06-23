package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class SmeltableRawOreBlocks extends AbstractCraftingTweak {

    public SmeltableRawOreBlocks() {
        super("smeltable-raw-ore-blocks", Material.RAW_GOLD_BLOCK);
    }

    @Override
    public void registerRecipes() {
        FurnaceRecipe ironRecipe = new FurnaceRecipe(Key.get("tt_smeltable_raw_iron_block"), new ItemStack(Material.IRON_BLOCK), Material.RAW_IRON_BLOCK, 10, 1000);
        Bukkit.addRecipe(ironRecipe);
        FurnaceRecipe goldRecipe = new FurnaceRecipe(Key.get("tt_smeltable_raw_gold_block"), new ItemStack(Material.GOLD_BLOCK), Material.RAW_GOLD_BLOCK, 10, 1000);
        Bukkit.addRecipe(goldRecipe);
        FurnaceRecipe copperRecipe = new FurnaceRecipe(Key.get("tt_smeltable_raw_copper_block"), new ItemStack(Material.COPPER_BLOCK), Material.RAW_COPPER_BLOCK, 10, 1000);
        Bukkit.addRecipe(copperRecipe);

        BlastingRecipe ironBlast = new BlastingRecipe(Key.get("tt_smeltable_raw_iron_block_blast"), new ItemStack(Material.IRON_BLOCK), Material.RAW_IRON_BLOCK, 10, 600);
        Bukkit.addRecipe(ironBlast);
        BlastingRecipe goldBlast = new BlastingRecipe(Key.get("tt_smeltable_raw_gold_block_blast"), new ItemStack(Material.GOLD_BLOCK), Material.RAW_GOLD_BLOCK, 10, 600);
        Bukkit.addRecipe(goldBlast);
        BlastingRecipe copperBlast = new BlastingRecipe(Key.get("tt_smeltable_raw_copper_block_blast"), new ItemStack(Material.COPPER_BLOCK), Material.RAW_COPPER_BLOCK, 10, 600);
        Bukkit.addRecipe(copperBlast);
    }
}
