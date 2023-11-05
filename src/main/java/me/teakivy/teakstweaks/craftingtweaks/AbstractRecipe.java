package me.teakivy.teakstweaks.craftingtweaks;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AbstractRecipe {
    protected String name;
    protected String path;

    public Material material;
    public String description;

    public ItemStack item;

    /**
     * Set up the pack
     * @param path Config path
     * @param material Material for the item
     */
    public AbstractRecipe(String path, Material material) {
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

        List<Component> components = new ArrayList<>();
        for (String l : lore) {
            components.add(MiniMessage.miniMessage().deserialize(l).decoration(TextDecoration.ITALIC, false));
        }

        item.lore(components);

        item.editMeta(meta -> meta.displayName(MiniMessage.miniMessage().deserialize(PackType.CRAFTING_TWEAKS.getColor() + name).decoration(TextDecoration.ITALIC, false)));
    }

    /**
     * Register the pack
     */
    public void register() {
        if (TeaksTweaks.getInstance().getConfig().getBoolean("crafting-tweaks." + path + ".enabled")) init();
    }

    /**
     * Register all recipes for the pack
     */
    public void registerRecipes() {
        // TODO : Implement
    }

    /**
     * Get the item for the pack
     * @return item
     */
    public ItemStack getItem() {
        return item;
    }

}
