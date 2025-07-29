package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreTrapdoors extends AbstractCraftingTweak {

    public MoreTrapdoors() {
        super(TTCraftingTweak.MORE_TRAPDOORS, Material.SPRUCE_TRAPDOOR);
    }

    @Override
    public void registerRecipes() {
        newTrapdoorRecipe("OAK");
        newTrapdoorRecipe("DARK_OAK");
        newTrapdoorRecipe("BIRCH");
        newTrapdoorRecipe("SPRUCE");
        newTrapdoorRecipe("JUNGLE");
        newTrapdoorRecipe("ACACIA");
        newTrapdoorRecipe("CRIMSON");
        newTrapdoorRecipe("WARPED");
        newTrapdoorRecipe("MANGROVE");
        newTrapdoorRecipe("CHERRY");
        newTrapdoorRecipe("BAMBOO");
        newTrapdoorRecipe("PALE_OAK");
    }

    public void newTrapdoorRecipe(String type) {
        Bukkit.removeRecipe(NamespacedKey.minecraft(type.toLowerCase() + "_trapdoor"));
        ShapedRecipe recipe = new ShapedRecipe(Key.get(type.toLowerCase() + "_trapdoors"),
                new ItemStack(Material.valueOf(type + "_TRAPDOOR"), 12));
        recipe.shape("xxx", "xxx");
        recipe.setIngredient('x', Material.valueOf(type + "_PLANKS"));
        addRecipe(recipe);
    }


}
