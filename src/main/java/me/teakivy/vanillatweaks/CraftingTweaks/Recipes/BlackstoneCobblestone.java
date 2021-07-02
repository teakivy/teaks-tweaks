package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class BlackstoneCobblestone {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        List<Material> blackstoneList = new ArrayList<Material>();
        blackstoneList.add(Material.BLACKSTONE);
        blackstoneList.add(Material.COBBLESTONE);

        RecipeChoice blackstone = new RecipeChoice.MaterialChoice(blackstoneList);


        List<Material> woodList = new ArrayList<Material>();
        woodList.add(Material.OAK_PLANKS);
        woodList.add(Material.BIRCH_PLANKS);
        woodList.add(Material.ACACIA_PLANKS);
        woodList.add(Material.SPRUCE_PLANKS);
        woodList.add(Material.JUNGLE_PLANKS);
        woodList.add(Material.DARK_OAK_PLANKS);
        woodList.add(Material.WARPED_PLANKS);
        woodList.add(Material.CRIMSON_PLANKS);

        RecipeChoice wood = new RecipeChoice.MaterialChoice(woodList);

        NamespacedKey pistonKey = new NamespacedKey(main, "piston_vt_blackstone");
        ShapedRecipe pistonRecipe = new ShapedRecipe(pistonKey, new ItemStack(Material.PISTON));

        pistonRecipe.shape("---", "bob", "bab");
        pistonRecipe.setIngredient('b', blackstone);
        pistonRecipe.setIngredient('-', wood);
        pistonRecipe.setIngredient('o', Material.IRON_INGOT);
        pistonRecipe.setIngredient('a', Material.REDSTONE);

        Bukkit.addRecipe(pistonRecipe);

        NamespacedKey observerKey = new NamespacedKey(main, "observer_vt_blackstone");
        ShapedRecipe observerRecipe = new ShapedRecipe(observerKey, new ItemStack(Material.OBSERVER));

        observerRecipe.shape("bbb", "aaq", "bbb");
        observerRecipe.setIngredient('b', blackstone);
        observerRecipe.setIngredient('q', Material.QUARTZ);
        observerRecipe.setIngredient('v', Material.REDSTONE);

        Bukkit.addRecipe(observerRecipe);

        NamespacedKey dropperKey = new NamespacedKey(main, "dropper_vt_blackstone");
        ShapedRecipe dropperRecipe = new ShapedRecipe(dropperKey, new ItemStack(Material.DROPPER));

        dropperRecipe.shape("bbb", "b b", "bab");
        dropperRecipe.setIngredient('b', blackstone);
        dropperRecipe.setIngredient('v', Material.REDSTONE);

        Bukkit.addRecipe(dropperRecipe);

        NamespacedKey dispenserKey = new NamespacedKey(main, "dispenser_vt_blackstone");
        ShapedRecipe dispenserRecipe = new ShapedRecipe(dispenserKey, new ItemStack(Material.DISPENSER));

        dispenserRecipe.shape("bbb", "b=b", "bab");
        dispenserRecipe.setIngredient('b', blackstone);
        dispenserRecipe.setIngredient('v', Material.REDSTONE);
        dispenserRecipe.setIngredient('=', Material.BOW);

        Bukkit.addRecipe(dispenserRecipe);

        NamespacedKey brewingStandKey = new NamespacedKey(main, "dispenser_vt_blackstone");
        ShapedRecipe brewingStandRecipe = new ShapedRecipe(brewingStandKey, new ItemStack(Material.BREWING_STAND));

        brewingStandRecipe.shape(" | ", "bbb");
        brewingStandRecipe.setIngredient('b', blackstone);
        brewingStandRecipe.setIngredient('|', Material.BLAZE_ROD);

        Bukkit.addRecipe(brewingStandRecipe);

        NamespacedKey leverKey = new NamespacedKey(main, "lever_vt_blackstone");
        ShapedRecipe leverRecipe = new ShapedRecipe(leverKey, new ItemStack(Material.LEVER));

        leverRecipe.shape("/", "b");
        leverRecipe.setIngredient('/', Material.STICK);
        leverRecipe.setIngredient('b', blackstone);

        Bukkit.addRecipe(leverRecipe);
    }
}
