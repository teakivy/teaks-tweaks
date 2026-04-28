package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class CheaperResinBlocks extends AbstractCraftingTweak {

    public CheaperResinBlocks() {
        super(TTCraftingTweak.CHEAPER_RESIN_BLOCKS, Material.RESIN_BLOCK);
    }

    @Override
    public void registerRecipes() {
        Bukkit.removeRecipe(NamespacedKey.minecraft("resin_block"));
        ShapedRecipe blockRecipe = new ShapedRecipe(Key.get("resin_block"), new ItemStack(Material.RESIN_BLOCK, 1));
        blockRecipe.shape("##", "##");
        blockRecipe.setIngredient('#', Material.RESIN_CLUMP);
        addRecipe(blockRecipe);

        Bukkit.removeRecipe(NamespacedKey.minecraft("resin_clump"));
        ShapelessRecipe clumpRecipe = new ShapelessRecipe(Key.get("resin_clump"), new ItemStack(Material.RESIN_CLUMP, 4));
        clumpRecipe.addIngredient(Material.RESIN_BLOCK);
        addRecipe(clumpRecipe);
    }
}
