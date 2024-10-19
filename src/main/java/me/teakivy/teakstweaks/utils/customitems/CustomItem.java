package me.teakivy.teakstweaks.utils.customitems;

import org.bukkit.inventory.ItemStack;

public class CustomItem {
    private final String name;
    private final ItemStack item;

    public CustomItem(String name, ItemStack item) {
        this.name = name;
        item.setAmount(1);
        this.item = item;

        ItemHandler.addCustomItem(this);
    }

    public String getName() {
        return name;
    }

    public ItemStack getItem() {
        return item;
    }

    public void register() {
        ItemHandler.addCustomItem(this);
    }
}
