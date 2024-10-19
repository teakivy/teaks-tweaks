package me.teakivy.teakstweaks.utils.customitems;

import org.bukkit.inventory.ItemStack;

public enum TItem {
    ROTATION_WRENCH("rotation_wrench"),
    GRAVE_KEY("grave_key");

    private final String name;

    TItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CustomItem getCustomItem() {
        return ItemHandler.getCustomItem(name);
    }

    public ItemStack getItem() {
        return ItemHandler.getItem(name);
    }
}
