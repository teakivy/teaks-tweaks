package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class StraightToShapeless extends AbstractCraftingTweak {

    public StraightToShapeless() {
        super(TTCraftingTweak.STRAIGHT_TO_SHAPELESS, Material.BREAD);
    }

    @Override
    public void registerRecipes() {
        ShapelessRecipe breadRecipe = new ShapelessRecipe(Key.get("bread_shapeless"), new ItemStack(Material.BREAD));
        breadRecipe.addIngredient(3, Material.WHEAT);
        addRecipe(breadRecipe);

        ShapelessRecipe shulkerBoxRecipe = new ShapelessRecipe(Key.get("shulker_box_shapeless"), new ItemStack(Material.SHULKER_BOX));
        shulkerBoxRecipe.addIngredient(2, Material.SHULKER_SHELL);
        shulkerBoxRecipe.addIngredient(Material.CHEST);
        addRecipe(shulkerBoxRecipe);

        ShapelessRecipe paperRecipe = new ShapelessRecipe(Key.get("paper_shapeless"), new ItemStack(Material.PAPER, 3));
        paperRecipe.addIngredient(3, Material.SUGAR_CANE);
        addRecipe(paperRecipe);
    }
}
