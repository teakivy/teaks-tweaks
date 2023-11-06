package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreTrapdoors extends AbstractRecipe {

    public MoreTrapdoors() {
        super("more-trapdoors", Material.SPRUCE_TRAPDOOR);
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
    }

    public static void newTrapdoorRecipe(String type) {
        ShapedRecipe recipe = new ShapedRecipe(Key.get(type.toLowerCase() + "_trapdoors"),
                new ItemStack(Material.valueOf(type + "_TRAPDOOR"), 12));
        recipe.shape("xxx", "xxx");
        recipe.setIngredient('x', Material.valueOf(type + "_PLANKS"));
        Bukkit.addRecipe(recipe);
    }


}
