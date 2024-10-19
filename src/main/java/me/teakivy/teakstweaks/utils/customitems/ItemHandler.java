package me.teakivy.teakstweaks.utils.customitems;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
    private static final List<CustomItem> customItems = new ArrayList<>();

    public static void addCustomItem(CustomItem customItem) {
        customItems.add(customItem);
    }

    public static CustomItem getCustomItem(String name) {
        for (CustomItem customItem : customItems) {
            if (customItem.getName().equals(name)) {
                return customItem;
            }
        }
        return null;
    }

    public static ItemStack getItem(String name) {
        CustomItem customItem = getCustomItem(name);
        if (customItem == null) return null;
        return customItem.getItem();
    }
}
