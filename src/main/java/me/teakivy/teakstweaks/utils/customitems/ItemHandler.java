package me.teakivy.teakstweaks.utils.customitems;

import me.teakivy.teakstweaks.utils.log.Logger;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
    private static final List<CustomItem> customItems = new ArrayList<>();

    public static void addCustomItem(CustomItem customItem) {
        if (isCustomItem(customItem.getName())) return;
        Logger.info("Adding custom item: " + customItem.getName());
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

    public static boolean isCustomItem(String name) {
        for (CustomItem customItem : customItems) {
            if (customItem.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getItem(String name) {
        CustomItem customItem = getCustomItem(name);
        if (customItem == null) return null;
        return customItem.getItem();
    }
}
