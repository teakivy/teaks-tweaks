package me.teakivy.teakstweaks.packs.sawmill;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.sawmill.recipes.*;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Sawmill extends BasePack {

    public Sawmill() {
        super(TTPack.SAWMILL, Material.STONECUTTER);
    }

    @Override
    public void init() {
        super.init();

        BaseSawmillRecipe[] sawmillRecipes = new BaseSawmillRecipe[] {
                new AcaciaSawmill(),
                new BambooSawmill(),
                new BirchSawmill(),
                new CherrySawmill(),
                new CrimsonSawmill(),
                new DarkOakSawmill(),
                new JungleSawmill(),
                new MangroveSawmill(),
                new OakSawmill(),
                new PaleOakSawmill(),
                new SpruceSawmill(),
                new WarpedSawmill()
        };

        List<Recipe> recipes = new ArrayList<>();

        for (BaseSawmillRecipe recipe : sawmillRecipes) {
            recipes.addAll(recipe.getAllRecipes());
        }

        for (Recipe recipe : recipes) {
            addRecipe(recipe);
        }

    }
}
