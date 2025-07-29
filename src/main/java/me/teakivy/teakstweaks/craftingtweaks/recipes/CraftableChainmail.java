package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableChainmail extends AbstractCraftingTweak {

    public CraftableChainmail() {
        super(TTCraftingTweak.CRAFTABLE_CHAINMAIL, Material.CHAINMAIL_CHESTPLATE);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("chainmail_helmet"), new ItemStack(Material.CHAINMAIL_HELMET));
        recipe.shape("xxx", "x x");
        recipe.setIngredient('x', Material.CHAIN);
        addRecipe(recipe);

        recipe = new ShapedRecipe(Key.get("chainmail_chestplate"), new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        recipe.shape("x x", "xxx", "xxx");
        recipe.setIngredient('x', Material.CHAIN);
        addRecipe(recipe);

        recipe = new ShapedRecipe(Key.get("chainmail_leggings"), new ItemStack(Material.CHAINMAIL_LEGGINGS));
        recipe.shape("xxx", "x x", "x x");
        recipe.setIngredient('x', Material.CHAIN);
        addRecipe(recipe);

        recipe = new ShapedRecipe(Key.get("chainmail_boots"), new ItemStack(Material.CHAINMAIL_BOOTS));
        recipe.shape("x x", "x x");
        recipe.setIngredient('x', Material.CHAIN);
        addRecipe(recipe);
    }
}
