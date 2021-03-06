package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

public class UnpackableQuartz extends AbstractRecipe {

    public UnpackableQuartz() {
        super("Unpackable Quartz");
    }

    @Override
    public void registerRecipes() {
        List<Material> materials = new ArrayList<Material>();
        materials.add(Material.QUARTZ_BLOCK);
        materials.add(Material.QUARTZ_PILLAR);
        materials.add(Material.QUARTZ_BRICKS);
        materials.add(Material.CHISELED_QUARTZ_BLOCK);

        NamespacedKey iceKey = new NamespacedKey(main, "unpackable_quartz");
        ShapelessRecipe iceRecipe = new ShapelessRecipe(iceKey, new ItemStack(Material.QUARTZ, 4));
        iceRecipe.addIngredient(new RecipeChoice.MaterialChoice(materials));

        Bukkit.addRecipe(iceRecipe);
    }
}
