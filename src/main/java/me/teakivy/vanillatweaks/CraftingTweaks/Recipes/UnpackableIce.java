package me.teakivy.vanillatweaks.CraftingTweaks.Recipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class UnpackableIce {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        NamespacedKey iceKey = new NamespacedKey(main, "ice_vt_unpackables");
        ShapelessRecipe iceRecipe = new ShapelessRecipe(iceKey, new ItemStack(Material.ICE, 9));
        iceRecipe.addIngredient(Material.PACKED_ICE);

        Bukkit.addRecipe(iceRecipe);


        NamespacedKey packedIceKey = new NamespacedKey(main, "packed_ice_vt_unpackables");
        ShapelessRecipe packedIceRecipe = new ShapelessRecipe(packedIceKey, new ItemStack(Material.PACKED_ICE, 9));
        packedIceRecipe.addIngredient(Material.BLUE_ICE);

        Bukkit.addRecipe(packedIceRecipe);
    }
}
