package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class SmeltableRawOreBlocks extends AbstractCraftingTweak {

    public SmeltableRawOreBlocks() {
        super(TTCraftingTweak.SMELTABLE_RAW_ORE_BLOCKS, Material.RAW_GOLD_BLOCK);
    }

    @Override
    public void registerRecipes() {
        FurnaceRecipe ironRecipe = new FurnaceRecipe(Key.get("smeltable_raw_iron_block"), new ItemStack(Material.IRON_BLOCK), Material.RAW_IRON_BLOCK, 100, 1000);
        addRecipe(ironRecipe);
        FurnaceRecipe goldRecipe = new FurnaceRecipe(Key.get("smeltable_raw_gold_block"), new ItemStack(Material.GOLD_BLOCK), Material.RAW_GOLD_BLOCK, 100, 1000);
        addRecipe(goldRecipe);
        FurnaceRecipe copperRecipe = new FurnaceRecipe(Key.get("smeltable_raw_copper_block"), new ItemStack(Material.COPPER_BLOCK), Material.RAW_COPPER_BLOCK, 100, 1000);
        addRecipe(copperRecipe);

        BlastingRecipe ironBlast = new BlastingRecipe(Key.get("smeltable_raw_iron_block_blast"), new ItemStack(Material.IRON_BLOCK), Material.RAW_IRON_BLOCK, 100, 600);
        addRecipe(ironBlast);
        BlastingRecipe goldBlast = new BlastingRecipe(Key.get("smeltable_raw_gold_block_blast"), new ItemStack(Material.GOLD_BLOCK), Material.RAW_GOLD_BLOCK, 100, 600);
        addRecipe(goldBlast);
        BlastingRecipe copperBlast = new BlastingRecipe(Key.get("smeltable_raw_copper_block_blast"), new ItemStack(Material.COPPER_BLOCK), Material.RAW_COPPER_BLOCK, 100, 600);
        addRecipe(copperBlast);
    }
}
