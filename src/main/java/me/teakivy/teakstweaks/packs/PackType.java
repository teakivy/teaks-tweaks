package me.teakivy.teakstweaks.packs;

import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.ChatColor;

public enum PackType {
    EXPERIMENTAL,
    HERMITCRAFT,
    ITEMS,
    MOBS,
    SURVIVAL,
    TEAKSTWEAKS,
    TELEPORTATION,
    UTILITIES,
    CRAFTING_TWEAKS;

    /**
     * Gets the color for the pack type
     * @return chat color
     */
    public String getColor() {
        return switch (this) {
            case EXPERIMENTAL -> "<dark_red>";
            case HERMITCRAFT -> "<dark_green>";
            case ITEMS -> "<aqua>";
            case MOBS -> "<yellow>";
            case SURVIVAL -> "<gold>";
            case TEAKSTWEAKS -> "<dark_purple>";
            case TELEPORTATION -> "<dark_aqua>";
            case UTILITIES -> "<light_purple>";
            case CRAFTING_TWEAKS -> "<red>";
        };
    }

    /**
     * Gets the formatted name of the pack type
     * @return formatted name
     */
    public String getName() {
        return switch (this) {
            case EXPERIMENTAL -> getColor() + get("experimental");
            case HERMITCRAFT -> getColor() + get("hermitcraft");
            case ITEMS -> getColor() + get("items");
            case MOBS -> getColor() + get("mobs");
            case SURVIVAL -> getColor() + get("survival");
            case TEAKSTWEAKS -> getColor() + get("teaks_tweaks");
            case TELEPORTATION -> getColor() + get("teleportation");
            case UTILITIES -> getColor() + get("utilities");
            case CRAFTING_TWEAKS -> getColor() + get("crafting_tweaks");
        };
    }

    /**
     * Get a string from the lang file
     * @param key the key to get
     * @return the string
     */
    private String get(String key) {
        return Translatable.getString("packtype." + key);
    }
}
