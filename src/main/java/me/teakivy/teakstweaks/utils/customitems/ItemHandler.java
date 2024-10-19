package me.teakivy.teakstweaks.utils.customitems;

import me.teakivy.teakstweaks.utils.log.Logger;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
    private static final List<CustomItem> customItems = new ArrayList<>();

    public static void addCustomItem(CustomItem customItem) {
        if (isCustomItem(customItem.getName())) return;
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

    public static ItemStack getItem(String name, int amount) {
        ItemStack item = getItem(name);
        if (item == null) return null;
        item.setAmount(amount);
        return item;
    }

    public static ItemStack getItem(String name) {
        CustomItem customItem = getCustomItem(name);
        if (customItem == null) return null;
        return customItem.getItem();
    }

    public static List<String> getAllKeys() {
        List<String> names = new ArrayList<>();
        for (CustomItem customItem : customItems) {
            names.add(customItem.getName());
        }
        return names;
    }
}
