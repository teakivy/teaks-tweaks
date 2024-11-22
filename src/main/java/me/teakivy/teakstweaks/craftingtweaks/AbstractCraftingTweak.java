package me.teakivy.teakstweaks.craftingtweaks;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.MM;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.metrics.CustomMetrics;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.recipe.RecipeManager;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractCraftingTweak {
    private final String name;
    private final String path;

    private final Material material;
    private final String description;

    private ItemStack item;

    /**
     * Set up the pack
     * @param path Config path
     * @param material Material for the item
     */
    public AbstractCraftingTweak(String path, Material material) {
        String langKey = path.replaceAll("-", "_");
        this.name = Translatable.getString(langKey + ".name");
        this.path = path;

        this.material = material;
        this.description = Translatable.getString(langKey + ".description");
    }

    /**
     * Initialize all recipes for the pack
     */
    public void init() {
        Logger.info(Translatable.get("startup.register.crafting_tweak", Placeholder.parsed("name", PackType.CRAFTING_TWEAKS.getColor() + name)));
        TeaksTweaks.getInstance().addCraftingTweaks(this.name);
        CraftingRegister.addEnabledRecipe(this);
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
        lore.add(" ");

        lore.add(PackType.CRAFTING_TWEAKS.getName());

        List<String> components = new ArrayList<>();
        for (String l : lore) {
            components.add(MM.toString(MiniMessage.miniMessage().deserialize(l).decoration(TextDecoration.ITALIC, false)));
        }
        ItemMeta meta = item.getItemMeta();
        meta.setLore(components);
        meta.setDisplayName(MM.toString(MiniMessage.miniMessage().deserialize(PackType.CRAFTING_TWEAKS.getColor() + name).decoration(TextDecoration.ITALIC, false)));
        item.setItemMeta(meta);

        CustomMetrics.addCraftingTweakEnabled(this.name);
    }

    /**
     * Register the pack
     */
    public void register() {
        if (Config.isCraftingTweakEnabled(path)) init();
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

}
