package me.teakivy.teakstweaks.craftingtweaks;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.lang.TranslationManager;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.metrics.CustomMetrics;
import me.teakivy.teakstweaks.utils.recipe.RecipeManager;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCraftingTweak {
    private final String name;
    private final String path;
    private final TTCraftingTweak craftingTweak;

    private final Material material;
    private final String description;

    private ItemStack item;

    /**
     * Set up the pack
     * @param craftingTweak The crafting tweak object
     * @param material Material for the item
     */
    public AbstractCraftingTweak(TTCraftingTweak craftingTweak, Material material) {
        this.craftingTweak = craftingTweak;
        this.path = craftingTweak.getKey();
        String langKey = path.replaceAll("-", "_");
        this.name = TranslationManager.getString(Config.getLanguage(), langKey + ".name");

        this.material = material;
        this.description = TranslationManager.getString(Config.getLanguage(), langKey + ".description");
    }

    /**
     * Initialize all recipes for the pack
     */
    public void init() {
        Logger.info(Component.text(TranslationManager.getString(Config.getLanguage(), "startup.register.crafting_tweak").replace("<name>", name)));
        TeaksTweaks.getInstance().addCraftingTweaks(this.name);
        this.registerRecipes();

        item = new ItemStack(material);

        List<String> lore = new ArrayList<>();
        StringBuilder newLine = new StringBuilder();
        for (String word : description.split(" ")) {
            if (newLine.length() > 30) {
                lore.add("<gray>" + newLine);
                newLine = new StringBuilder();
            }
            newLine.append(word).append(" ");
        }
        lore.add("<gray>" + newLine);

        List<Component> components = new ArrayList<>();
        for (String l : lore) {
            components.add(MiniMessage.miniMessage().deserialize(l).decoration(TextDecoration.ITALIC, false));
        }
        ItemMeta meta = item.getItemMeta();
        meta.lore(components);
        meta.displayName(MiniMessage.miniMessage().deserialize("<gold>" + name).decoration(TextDecoration.ITALIC, false));
        item.setItemMeta(meta);

        CustomMetrics.addCraftingTweakEnabled(this.name);
    }

    /**
     * Register the pack
     */
    public void register() {
        if (Config.isCraftingTweakEnabled(path) || Config.isDevMode()) init();
    }

    public void unregister() {
        RecipeManager.unregister(path);
    }

    /**
     * Register all recipes for the pack
     */
    public abstract void registerRecipes();

    public void addRecipe(Recipe recipe) {
        RecipeManager.register(path, recipe);
    }

    /**
     * Get the item for the pack
     * @return item
     */
    public ItemStack getItem() {
        return item;
    }

    public TTCraftingTweak getCraftingTweak() {
        return craftingTweak;
    }

}
