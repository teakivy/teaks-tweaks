package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableNameTags extends AbstractRecipe {

    public CraftableNameTags() {
        super("Craftable Name Tags");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "name_tags_tags");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.NAME_TAG));

        recipe.shape(" is", " pi", "p  ");
        recipe.setIngredient('i', Material.IRON_INGOT);
        recipe.setIngredient('p', Material.PAPER);
        recipe.setIngredient('s', Material.STRING);


        Bukkit.addRecipe(recipe);
    }
}
