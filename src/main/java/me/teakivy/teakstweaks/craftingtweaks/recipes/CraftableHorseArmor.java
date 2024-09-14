package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableHorseArmor extends AbstractCraftingTweak {

    public CraftableHorseArmor() {
        super("craftable-horse-armor", Material.DIAMOND_HORSE_ARMOR);
    }

    @Override
    public void registerRecipes() {
        newHorseArmor(Material.IRON_INGOT, Material.IRON_HORSE_ARMOR);
        newHorseArmor(Material.GOLD_INGOT, Material.GOLDEN_HORSE_ARMOR);
        newHorseArmor(Material.DIAMOND, Material.DIAMOND_HORSE_ARMOR);
    }

    private static void newHorseArmor(Material input, Material output) {
        ShapedRecipe recipe = new ShapedRecipe(Key.get(output.toString().toLowerCase()), new ItemStack(output));
        recipe.shape("# #", "###", "# #");
        recipe.setIngredient('#', input);
        Bukkit.addRecipe(recipe);
    }
}
