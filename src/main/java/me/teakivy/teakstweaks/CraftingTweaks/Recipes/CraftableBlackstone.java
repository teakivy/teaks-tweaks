package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class CraftableBlackstone extends AbstractRecipe {

    public CraftableBlackstone() {
        super("Craftable Blackstone");
    }

    @Override
    public void registerRecipes() {
        List<Material> coalList = new ArrayList<Material>();
        coalList.add(Material.COAL);
        coalList.add(Material.CHARCOAL);

        RecipeChoice coal = new RecipeChoice.MaterialChoice(coalList);

        List<Material> basaltList = new ArrayList<Material>();
        basaltList.add(Material.BASALT);
        basaltList.add(Material.SMOOTH_BASALT);

        RecipeChoice basalt = new RecipeChoice.MaterialChoice(basaltList);


        NamespacedKey key = new NamespacedKey(main, "blackstone_craftables");
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.BLACKSTONE));
        recipe.shape("#x", "x#");
        recipe.setIngredient('#', coal);
        recipe.setIngredient('x', basalt);

        Bukkit.addRecipe(recipe);
    }
}
