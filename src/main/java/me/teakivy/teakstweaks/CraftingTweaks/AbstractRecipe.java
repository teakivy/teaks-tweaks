package me.teakivy.teakstweaks.CraftingTweaks;

import me.teakivy.teakstweaks.Main;

public class AbstractRecipe {
    protected String name;
    protected static Main main = Main.getPlugin(Main.class);

    public AbstractRecipe(String name) {
        this.name = name;
    }

    public void init() {
        main.addCraftingTweaks(this.name);
        registerRecipes();
    }

    public void registerRecipes() {
        // TODO : Implement
    }

}
