package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreBark extends AbstractRecipe {

    public MoreBark() {
        super("More Bark", "more-bark");
    }

    @Override
    public void registerRecipes() {
        newBarkedRecipe("oak", Material.OAK_LOG, Material.OAK_WOOD);
        newBarkedRecipe("stripped_oak", Material.STRIPPED_OAK_LOG, Material.STRIPPED_OAK_WOOD);
        newBarkedRecipe("dark_oak", Material.DARK_OAK_LOG, Material.DARK_OAK_WOOD);
        newBarkedRecipe("stripped_dark_oak", Material.STRIPPED_DARK_OAK_LOG, Material.STRIPPED_DARK_OAK_WOOD);
        newBarkedRecipe("birch", Material.BIRCH_LOG, Material.BIRCH_WOOD);
        newBarkedRecipe("stripped_birch", Material.STRIPPED_BIRCH_LOG, Material.STRIPPED_BIRCH_WOOD);
        newBarkedRecipe("spruce", Material.SPRUCE_LOG, Material.SPRUCE_WOOD);
        newBarkedRecipe("stripped_spruce", Material.STRIPPED_SPRUCE_LOG, Material.STRIPPED_SPRUCE_WOOD);
        newBarkedRecipe("acacia", Material.ACACIA_LOG, Material.ACACIA_WOOD);
        newBarkedRecipe("stripped_acacia", Material.STRIPPED_ACACIA_LOG, Material.STRIPPED_ACACIA_WOOD);
        newBarkedRecipe("jungle", Material.JUNGLE_LOG, Material.JUNGLE_WOOD);
        newBarkedRecipe("stripped_jungle", Material.STRIPPED_JUNGLE_LOG, Material.STRIPPED_JUNGLE_WOOD);
        newBarkedRecipe("crimson", Material.CRIMSON_STEM, Material.CRIMSON_HYPHAE);
        newBarkedRecipe("stripped_crimson", Material.STRIPPED_CRIMSON_STEM, Material.STRIPPED_CRIMSON_HYPHAE);
        newBarkedRecipe("warped", Material.WARPED_STEM, Material.WARPED_HYPHAE);
        newBarkedRecipe("stripped_warped", Material.STRIPPED_WARPED_STEM, Material.STRIPPED_WARPED_HYPHAE);
        newBarkedRecipe("mangrove", Material.MANGROVE_LOG, Material.MANGROVE_WOOD);
        newBarkedRecipe("stripped_mangrove", Material.STRIPPED_MANGROVE_LOG, Material.STRIPPED_MANGROVE_WOOD);
    }

    public static void newBarkedRecipe(String name, Material input, Material output) {
        NamespacedKey key = new NamespacedKey(main, name + "_bark");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(output, 4));

        recipe.shape("xx", "xx");
        recipe.setIngredient('x', input);

        Bukkit.addRecipe(recipe);
    }
}
