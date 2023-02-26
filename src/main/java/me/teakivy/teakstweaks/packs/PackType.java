package me.teakivy.teakstweaks.packs;

import org.bukkit.ChatColor;

public enum PackType {
    EXPERIMENTAL,
    HERMITCRAFT,
    ITEMS,
    MOBS,
    SURVIVAL,
    TEAKSTWEAKS,
    TELEPORTATION,
    UTILITIES;

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
        };
    }

    public String getName() {
        return switch (this) {
            case EXPERIMENTAL -> getColor() + "Experimental";
            case HERMITCRAFT -> getColor() + "Hermitcraft";
            case ITEMS -> getColor() + "Items";
            case MOBS -> getColor() + "Mobs";
            case SURVIVAL -> getColor() + "Survival";
            case TEAKSTWEAKS -> getColor() + "TeaksTweaks";
            case TELEPORTATION -> getColor() + "Teleportation";
            case UTILITIES -> getColor() + "Utilities";
        };
    }
}
