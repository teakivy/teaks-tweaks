package me.teakivy.teakstweaks.craftingtweaks;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AbstractRecipe {
    protected String name;
    protected String path;
    protected static Main main = Main.getPlugin(Main.class);

    public Material material;
    public String[] description;

    public ItemStack item;

    public AbstractRecipe(String name, String path, Material material, String... description) {
        this.name = name;
        this.path = path;

        this.material = material;
        this.description = description;
    }

    public void init() {
        Logger.log(Logger.LogLevel.INFO, "Registering Crafting Tweak: " + name);
        main.addCraftingTweaks(this.name);
        CraftingRegister.addEnabledRecipe(this);
        this.registerRecipes();

        item = new ItemStack(material);

        List<String> lore = new ArrayList<>();

        if (description.length > 0) {
            for (String line : description) {
                StringBuilder newLine = new StringBuilder();
                for (String word : line.split(" ")) {
                    if (newLine.length() > 30) {
                        lore.add(ChatColor.GRAY + newLine.toString());
                        newLine = new StringBuilder();
                    }
                    newLine.append(word).append(" ");
                }
                lore.add(ChatColor.GRAY + newLine.toString());
                lore.add("");
            }
        }
        if (lore.size() >= 1) lore.remove(lore.size() - 1);

        lore.add("");

        lore.add(ChatColor.RED + "Crafting Tweaks");

        item.setLore(lore);

        item.editMeta(meta -> {
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.RED + name);
        });
    }

    public void register() {
        if (main.getConfig().getBoolean("crafting-tweaks." + path + ".enabled")) init();
    }

    public void registerRecipes() {
        // TODO : Implement
    }

    public ItemStack getItem() {
        return item;
    }

}
