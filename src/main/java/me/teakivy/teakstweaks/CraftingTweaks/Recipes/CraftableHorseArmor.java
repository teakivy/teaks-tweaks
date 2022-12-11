package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableHorseArmor extends AbstractRecipe {

    public CraftableHorseArmor() {
        super("Craftable Horse Armor");
    }

    @Override
    public void registerRecipes() {

        newHorseArmor("iron", Material.IRON_INGOT, Material.IRON_HORSE_ARMOR);
        newHorseArmor("gold", Material.GOLD_INGOT, Material.GOLDEN_HORSE_ARMOR);
        newHorseArmor("diamond", Material.DIAMOND, Material.DIAMOND_HORSE_ARMOR);
    }

    private static void newHorseArmor(String name, Material input, Material output) {

        NamespacedKey key = new NamespacedKey(main, name + "_horse_armor");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(output));

        recipe.shape("  #", "###", "#@#");
        recipe.setIngredient('#', input);
        recipe.setIngredient('@', Material.SADDLE);
        Bukkit.addRecipe(recipe);

    }
}
