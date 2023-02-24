package me.teakivy.teakstweaks.craftingtweaks;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;

public class AbstractRecipe {
    protected String name;
    protected String path;
    protected static Main main = Main.getPlugin(Main.class);

    public AbstractRecipe(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void init() {
        Logger.log(Logger.LogLevel.INFO, "Registering Crafting Tweak: " + name);
        main.addCraftingTweaks(this.name);
        this.registerRecipes();
    }

    public void register() {
        if (main.getConfig().getBoolean("crafting-tweaks." + path + ".enabled")) init();
    }

    public void registerRecipes() {
        // TODO : Implement
    }

}
