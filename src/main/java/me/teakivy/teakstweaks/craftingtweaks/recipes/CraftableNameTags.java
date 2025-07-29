package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableNameTags extends AbstractCraftingTweak {

    public CraftableNameTags() {
        super(TTCraftingTweak.CRAFTABLE_NAME_TAGS, Material.NAME_TAG);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("name_tags_tags"), new ItemStack(Material.NAME_TAG));
        recipe.shape(" is", " pi", "p  ");
        recipe.setIngredient('i', Material.IRON_INGOT);
        recipe.setIngredient('p', Material.PAPER);
        recipe.setIngredient('s', Material.STRING);
        addRecipe(recipe);
    }
}
