package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableSmallDripleaf extends AbstractCraftingTweak {

    public CraftableSmallDripleaf() {
        super(TTCraftingTweak.CRAFTABLE_SMALL_DRIPLEAF, Material.SMALL_DRIPLEAF);
    }

    @Override
    public void registerRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("craftable_dripleaf"), new ItemStack(Material.SMALL_DRIPLEAF));
        recipe.addIngredient(Material.BIG_DRIPLEAF);
        addRecipe(recipe);
    }
}
