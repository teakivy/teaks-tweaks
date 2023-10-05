package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
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
        NamespacedKey key = new NamespacedKey(main, type.toLowerCase() + "_trapdoors");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.valueOf(type + "_TRAPDOOR"), 12));

        recipe.shape("xxx", "xxx");
        recipe.setIngredient('x', Material.valueOf(type + "_PLANKS"));

        Bukkit.addRecipe(recipe);
    }


}
