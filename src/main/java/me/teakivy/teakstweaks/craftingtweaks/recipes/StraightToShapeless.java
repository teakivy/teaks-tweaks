package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class StraightToShapeless extends AbstractRecipe {

    public StraightToShapeless() {
        super("Straight To Shapeless", "straight-to-shapeless", Material.BREAD, "Craft items such as Paper, Bread, and Shulker Boxes directly in your 2x2.");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey breadKey = new NamespacedKey(main, "bread_shapeless");
        ShapelessRecipe breadRecipe = new ShapelessRecipe(breadKey, new ItemStack(Material.BREAD));
        breadRecipe.addIngredient(3, Material.WHEAT);
        Bukkit.addRecipe(breadRecipe);

        NamespacedKey shulkerBoxKey = new NamespacedKey(main, "shulker_box_shapeless");
        ShapelessRecipe shulkerBoxRecipe = new ShapelessRecipe(shulkerBoxKey, new ItemStack(Material.SHULKER_BOX));
        shulkerBoxRecipe.addIngredient(2, Material.SHULKER_SHELL);
        shulkerBoxRecipe.addIngredient(Material.CHEST);
        Bukkit.addRecipe(shulkerBoxRecipe);

        NamespacedKey paperKey = new NamespacedKey(main, "paper_shapeless");
        ShapelessRecipe paperRecipe = new ShapelessRecipe(paperKey, new ItemStack(Material.PAPER, 3));
        paperRecipe.addIngredient(3, Material.SUGAR_CANE);
        Bukkit.addRecipe(paperRecipe);
    }
}
