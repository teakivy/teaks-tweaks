package me.teakivy.teakstweaks.utils.recipe;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager implements Listener {
    private static final List<RecipeData> recipes = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (RecipeData recipe : recipes) {
            recipe.grantRecipe(event.getPlayer());
        }
    }

    public static void register(RecipeData recipe) {
        recipes.add(recipe);
        recipe.register();
    }

    public static void register(List<RecipeData> recipes) {
        for (RecipeData recipe : recipes) {
            register(recipe);
        }
    }

    public static void register(String parent, Recipe recipe) {
        register(new RecipeData(parent, recipe));
    }

    public static void register(String parent, List<Recipe> recipes) {
        List<RecipeData> recipeData = new ArrayList<>();
        for (Recipe recipe : recipes) {
            recipeData.add(new RecipeData(parent, recipe));
        }
        register(recipeData);
    }

    public static void unregister(RecipeData recipe) {
        recipes.remove(recipe);
        recipe.unregister();
    }

    public static void unregister(List<RecipeData> recipes) {
        for (RecipeData recipe : recipes) {
            unregister(recipe);
        }
    }

    public static void unregister(String parent) {
        List<RecipeData> toRemove = getRecipes(parent);
        unregister(toRemove);
    }

    public static void unregisterAll() {
        unregister(new ArrayList<>(recipes));
    }

    public static List<RecipeData> getRecipes() {
        return recipes;
    }

    public static List<RecipeData> getRecipes(String parent) {
        List<RecipeData> parentRecipes = new ArrayList<>();
        for (RecipeData recipe : recipes) {
            if (!recipe.getParent().equals(parent)) continue;
            parentRecipes.add(recipe);
        }
        return parentRecipes;
    }
}
