package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class LogChests extends AbstractCraftingTweak {

    public LogChests() {
        super(TTCraftingTweak.LOG_CHESTS, Material.CHEST);
    }

    @Override
    public void registerRecipes() {
        List<Material> logList = new ArrayList<>(Tag.LOGS.getValues());

        RecipeChoice logs = new RecipeChoice.MaterialChoice(logList);

        ShapedRecipe recipe = new ShapedRecipe(Key.get("log_chests"), new ItemStack(Material.CHEST, 4));
        recipe.shape("xxx", "x x", "xxx");
        recipe.setIngredient('x', logs);
        addRecipe(recipe);
    }
}
