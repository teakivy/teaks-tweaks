package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreBark extends AbstractCraftingTweak {

    public MoreBark() {
        super(TTCraftingTweak.MORE_BARK, Material.OAK_WOOD);
    }

    @Override
    public void registerRecipes() {
        Tag.LOGS.getValues().forEach(log -> {
            if (log.name().endsWith("_WOOD") || log.name().endsWith("_HYPHAE")) return;

            Material wood = Material.getMaterial(log.name().replace("_LOG", "_WOOD"));
            if (wood == null) wood = Material.getMaterial(log.name().replace("_LOG", "_HYPHAE"));
            if (wood == null) return;

            newBarkedRecipe(log, wood);
        });
    }

    public void newBarkedRecipe(Material input, Material output) {
        Bukkit.removeRecipe(NamespacedKey.minecraft(output.toString().toLowerCase()));
        ShapedRecipe recipe = new ShapedRecipe(Key.get(input.toString().toLowerCase() + "_bark"), new ItemStack(output, 4));
        recipe.shape("xx", "xx");
        recipe.setIngredient('x', input);
        addRecipe(recipe);
    }
}
