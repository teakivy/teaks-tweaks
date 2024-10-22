package me.teakivy.teakstweaks.utils.recipe;

import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Recipe;

public class RecipeData {
    private final String parent;
    private final NamespacedKey key;
    private final Recipe recipe;

    public RecipeData(String parent, Recipe recipe) {
        this.parent = parent;
        this.recipe = recipe;
        this.key = ((Keyed) recipe).getKey();
    }

    public String getParent() {
        return parent;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void grantRecipe() {
        register();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            grantRecipe(player);
        }
    }

    public void grantRecipe(Player player) {
        register();
        if (player.hasDiscoveredRecipe(key)) return;
        player.discoverRecipe(key);
    }

    public void revokeRecipe() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            revokeRecipe(player);
        }
    }

    public void revokeRecipe(Player player) {
        if (!player.hasDiscoveredRecipe(key)) return;
        player.undiscoverRecipe(key);
    }

    public void register() {
        if (Bukkit.getRecipe(key) != null) return;
        Bukkit.addRecipe(recipe);
        grantRecipe();
    }

    public void unregister() {
        Bukkit.removeRecipe(key);
        revokeRecipe();
    }
}
