package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.ItemUtils;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class BackToBlocks extends AbstractCraftingTweak {

    public BackToBlocks() {
        super(TTCraftingTweak.BACK_TO_BLOCKS, Material.BRICK_SLAB);
    }

    @Override
    public void registerRecipes() {
        Tag.STAIRS.getValues().forEach(stair -> registerBackToBlocksRecipe(stair, "_STAIRS", 4, 3));
        Tag.SLABS.getValues().forEach(slab -> registerBackToBlocksRecipe(slab, "_SLAB", 2, 1));
    }

    private void registerBackToBlocksRecipe(Material ingredient, String suffix, int inputAmount, int outputAmount) {
        Material result = ItemUtils.getBaseBlock(ingredient, suffix);

        if (result == null) {
            Logger.warning("[Back to Blocks] Could not find a base block for: " + ingredient.name() + ", Please report this to the plugin author.");
            return;
        }

        NamespacedKey key = Key.get(ingredient.name().toLowerCase() + "_back_to_blocks");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(result, outputAmount));
        recipe.addIngredient(inputAmount, ingredient);

        addRecipe(recipe);
    }
}
