package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

public class UnpackableQuartzBlocks extends AbstractCraftingTweak {

    public UnpackableQuartzBlocks() {
        super(TTCraftingTweak.UNPACKABLE_QUARTZ_BLOCKS, Material.QUARTZ);
    }

    @Override
    public void registerRecipes() {
        List<Material> materials = new ArrayList<>();
        materials.add(Material.QUARTZ_BLOCK);
        materials.add(Material.QUARTZ_PILLAR);
        materials.add(Material.QUARTZ_BRICKS);
        materials.add(Material.CHISELED_QUARTZ_BLOCK);

        ShapelessRecipe iceRecipe = new ShapelessRecipe(Key.get("unpackable_quartz"), new ItemStack(Material.QUARTZ, 4));
        iceRecipe.addIngredient(new RecipeChoice.MaterialChoice(materials));
        addRecipe(iceRecipe);
    }
}
