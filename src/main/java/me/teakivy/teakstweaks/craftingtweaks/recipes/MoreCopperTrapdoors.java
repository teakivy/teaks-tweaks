package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreCopperTrapdoors extends AbstractCraftingTweak {

    public MoreCopperTrapdoors() {
        super(TTCraftingTweak.MORE_COPPER_TRAPDOORS, Material.COPPER_TRAPDOOR);
    }

    @Override
    public void registerRecipes() {
        Bukkit.removeRecipe(NamespacedKey.minecraft("copper_trapdoor"));
        ShapedRecipe recipe = new ShapedRecipe(Key.get("copper_trapdoor"),
                new ItemStack(Material.COPPER_TRAPDOOR, 12));
        recipe.shape("xxx", "xxx");
        recipe.setIngredient('x', Material.COPPER_INGOT);
        addRecipe(recipe);
    }
}