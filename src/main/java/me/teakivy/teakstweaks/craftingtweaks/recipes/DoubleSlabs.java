package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.ItemUtils;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.HashSet;
import java.util.Set;

public class DoubleSlabs extends AbstractCraftingTweak {
    private static final Set<Material> buttonMaterials = new HashSet<>();

    public DoubleSlabs() {
        super(TTCraftingTweak.DOUBLE_SLABS, Material.DARK_PRISMARINE_SLAB);
    }

    @Override
    public void registerRecipes() {
        Tag.BUTTONS.getValues().forEach(button -> {
            Material base = ItemUtils.getBaseBlock(button, "_BUTTON");
            if (base != null) buttonMaterials.add(base);
        });

        Tag.SLABS.getValues().forEach(slab -> registerSlabRecipe(slab));
    }

    public void registerSlabRecipe(Material slab) {
        Material base = ItemUtils.getBaseBlock(slab, "_SLAB");
        if (base == null) return;
        if (buttonMaterials.contains(base)) return;

        ShapelessRecipe recipe = new ShapelessRecipe(Key.get(base.name().toLowerCase() + "_double_slabs"), new ItemStack(slab, 2));
        recipe.addIngredient(base);
        addRecipe(recipe);
    }
}
