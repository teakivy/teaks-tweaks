package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreBark extends AbstractCraftingTweak {

    public MoreBark() {
        super(TTCraftingTweak.MORE_BARK, Material.OAK_WOOD);
    }

    @Override
    public void registerRecipes() {
        newBarkedRecipe(Material.OAK_LOG, Material.OAK_WOOD);
        newBarkedRecipe(Material.STRIPPED_OAK_LOG, Material.STRIPPED_OAK_WOOD);
        newBarkedRecipe(Material.DARK_OAK_LOG, Material.DARK_OAK_WOOD);
        newBarkedRecipe(Material.STRIPPED_DARK_OAK_LOG, Material.STRIPPED_DARK_OAK_WOOD);
        newBarkedRecipe(Material.BIRCH_LOG, Material.BIRCH_WOOD);
        newBarkedRecipe(Material.STRIPPED_BIRCH_LOG, Material.STRIPPED_BIRCH_WOOD);
        newBarkedRecipe(Material.SPRUCE_LOG, Material.SPRUCE_WOOD);
        newBarkedRecipe(Material.STRIPPED_SPRUCE_LOG, Material.STRIPPED_SPRUCE_WOOD);
        newBarkedRecipe(Material.ACACIA_LOG, Material.ACACIA_WOOD);
        newBarkedRecipe(Material.STRIPPED_ACACIA_LOG, Material.STRIPPED_ACACIA_WOOD);
        newBarkedRecipe(Material.JUNGLE_LOG, Material.JUNGLE_WOOD);
        newBarkedRecipe(Material.STRIPPED_JUNGLE_LOG, Material.STRIPPED_JUNGLE_WOOD);
        newBarkedRecipe(Material.CRIMSON_STEM, Material.CRIMSON_HYPHAE);
        newBarkedRecipe(Material.STRIPPED_CRIMSON_STEM, Material.STRIPPED_CRIMSON_HYPHAE);
        newBarkedRecipe(Material.WARPED_STEM, Material.WARPED_HYPHAE);
        newBarkedRecipe(Material.STRIPPED_WARPED_STEM, Material.STRIPPED_WARPED_HYPHAE);
        newBarkedRecipe(Material.MANGROVE_LOG, Material.MANGROVE_WOOD);
        newBarkedRecipe(Material.STRIPPED_MANGROVE_LOG, Material.STRIPPED_MANGROVE_WOOD);
        newBarkedRecipe(Material.CHERRY_LOG, Material.CHERRY_WOOD);
        newBarkedRecipe(Material.STRIPPED_CHERRY_LOG, Material.STRIPPED_CHERRY_WOOD);
        newBarkedRecipe(Material.PALE_OAK_LOG, Material.PALE_OAK_WOOD);
        newBarkedRecipe(Material.STRIPPED_PALE_OAK_LOG, Material.STRIPPED_PALE_OAK_WOOD);
    }

    public void newBarkedRecipe(Material input, Material output) {
        Bukkit.removeRecipe(NamespacedKey.minecraft(output.toString().toLowerCase()));
        ShapedRecipe recipe = new ShapedRecipe(Key.get(input.toString().toLowerCase() + "_bark"), new ItemStack(output, 4));
        recipe.shape("xx", "xx");
        recipe.setIngredient('x', input);
        addRecipe(recipe);
    }
}
