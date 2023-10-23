package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableNameTags extends AbstractRecipe {

    public CraftableNameTags() {
        super("craftable-name-tags", Material.NAME_TAG);
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(teaksTweaks, "name_tags_tags");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.NAME_TAG));

        recipe.shape(" is", " pi", "p  ");
        recipe.setIngredient('i', Material.IRON_INGOT);
        recipe.setIngredient('p', Material.PAPER);
        recipe.setIngredient('s', Material.STRING);


        Bukkit.addRecipe(recipe);
    }
}
