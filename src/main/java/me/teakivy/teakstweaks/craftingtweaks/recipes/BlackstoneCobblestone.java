package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class BlackstoneCobblestone extends AbstractCraftingTweak {

    public BlackstoneCobblestone() {
        super("blackstone-cobblestone", Material.BLACKSTONE);
    }

    @Override
    public void registerRecipes() {
        RecipeChoice blackstone = new RecipeChoice.MaterialChoice(List.of(Material.BLACKSTONE, Material.COBBLESTONE));

        List<Material> woodList = new ArrayList<>();
        woodList.add(Material.OAK_PLANKS);
        woodList.add(Material.BIRCH_PLANKS);
        woodList.add(Material.ACACIA_PLANKS);
        woodList.add(Material.SPRUCE_PLANKS);
        woodList.add(Material.JUNGLE_PLANKS);
        woodList.add(Material.DARK_OAK_PLANKS);
        woodList.add(Material.WARPED_PLANKS);
        woodList.add(Material.CRIMSON_PLANKS);
        woodList.add(Material.CHERRY_PLANKS);
        woodList.add(Material.BAMBOO_PLANKS);
        woodList.add(Material.MANGROVE_PLANKS);
        woodList.add(Material.PALE_OAK_PLANKS);
        RecipeChoice wood = new RecipeChoice.MaterialChoice(woodList);

        ShapedRecipe pistonRecipe = new ShapedRecipe(Key.get("piston_blackstone"), new ItemStack(Material.PISTON));
        pistonRecipe.shape("---", "bob", "bab");
        pistonRecipe.setIngredient('b', blackstone);
        pistonRecipe.setIngredient('-', wood);
        pistonRecipe.setIngredient('o', Material.IRON_INGOT);
        pistonRecipe.setIngredient('a', Material.REDSTONE);
        addRecipe(pistonRecipe);

        ShapedRecipe observerRecipe = new ShapedRecipe(Key.get("observer_blackstone"), new ItemStack(Material.OBSERVER));
        observerRecipe.shape("bbb", "aaq", "bbb");
        observerRecipe.setIngredient('b', blackstone);
        observerRecipe.setIngredient('q', Material.QUARTZ);
        observerRecipe.setIngredient('a', Material.REDSTONE);
        addRecipe(observerRecipe);

        ShapedRecipe dropperRecipe = new ShapedRecipe(Key.get("dropper_blackstone"), new ItemStack(Material.DROPPER));
        dropperRecipe.shape("bbb", "b b", "bab");
        dropperRecipe.setIngredient('b', blackstone);
        dropperRecipe.setIngredient('a', Material.REDSTONE);
        addRecipe(dropperRecipe);

        ShapedRecipe dispenserRecipe = new ShapedRecipe(Key.get("dispenser_blackstone"), new ItemStack(Material.DISPENSER));
        dispenserRecipe.shape("bbb", "b=b", "bab");
        dispenserRecipe.setIngredient('b', blackstone);
        dispenserRecipe.setIngredient('a', Material.REDSTONE);
        dispenserRecipe.setIngredient('=', Material.BOW);
        addRecipe(dispenserRecipe);

        ShapedRecipe brewingStandRecipe = new ShapedRecipe(Key.get("brewing_stand_blackstone"), new ItemStack(Material.BREWING_STAND));
        brewingStandRecipe.shape(" | ", "bbb");
        brewingStandRecipe.setIngredient('b', blackstone);
        brewingStandRecipe.setIngredient('|', Material.BLAZE_ROD);
        addRecipe(brewingStandRecipe);

        ShapedRecipe leverRecipe = new ShapedRecipe(Key.get("lever_blackstone"), new ItemStack(Material.LEVER));
        leverRecipe.shape("/", "b");
        leverRecipe.setIngredient('/', Material.STICK);
        leverRecipe.setIngredient('b', blackstone);
        addRecipe(leverRecipe);
    }
}
