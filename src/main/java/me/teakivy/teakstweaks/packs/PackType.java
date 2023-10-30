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
    public Object getColor() {
        return switch (this) {
            case EXPERIMENTAL -> ChatColor.DARK_RED;
            case HERMITCRAFT -> ChatColor.DARK_GREEN;
            case ITEMS -> ChatColor.AQUA;
            case MOBS -> ChatColor.YELLOW;
            case SURVIVAL -> ChatColor.GOLD;
            case TEAKSTWEAKS -> ChatColor.DARK_PURPLE;
            case TELEPORTATION -> ChatColor.DARK_AQUA;
            case UTILITIES -> ChatColor.LIGHT_PURPLE;
            case CRAFTING_TWEAKS -> ChatColor.RED;
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
        return Translatable.getLegacy("packtype." + key);
    }
}
