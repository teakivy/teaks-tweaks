package me.teakivy.teakstweaks.craftingtweaks;

import me.teakivy.teakstweaks.Main;

public class AbstractRecipe {
    protected String name;
    protected static Main main = Main.getPlugin(Main.class);

    public AbstractRecipe(String name) {
        this.name = name;
    }

    public void init() {
        main.addCraftingTweaks(this.name);
        this.registerRecipes();
    }

    public void registerRecipes() {
        // TODO : Implement
    }

}
