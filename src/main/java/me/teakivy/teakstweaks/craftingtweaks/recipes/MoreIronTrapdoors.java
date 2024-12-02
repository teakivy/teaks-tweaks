package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreIronTrapdoors extends AbstractCraftingTweak {

    public MoreIronTrapdoors() {
        super("more-iron-trapdoors", Material.IRON_TRAPDOOR);
    }

    @Override
    public void registerRecipes() {
        Bukkit.removeRecipe(NamespacedKey.minecraft("iron_trapdoor"));
        ShapedRecipe recipe = new ShapedRecipe(Key.get("iron_trapdoor"),
                new ItemStack(Material.IRON_TRAPDOOR, 8));
        recipe.shape("xx", "xx");
        recipe.setIngredient('x', Material.IRON_INGOT);
        addRecipe(recipe);
    }
}