package me.teakivy.teakstweaks.utils.gui;

import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PaginatedGUI {
    private final static HashMap<UUID, PaginatedGUI> guis = new HashMap<>();
    private int page = 0;
    private final List<ItemStack> items;
    private final Inventory inv;

    /**
     * Creates a new PaginatedGUI
     * @param items The items to display
     * @param title The title of the GUI
     */
    public PaginatedGUI(List<ItemStack> items, String title) {
        this.items = items;
        this.inv = Bukkit.createInventory(null, 54, title);
    }

    /**
     * Opens the GUI for a player
     * @param player The player to open the GUI for
     */
    public void open(Player player) {
        player.openInventory(inv);
        update();

        guis.put(player.getUniqueId(), this);
    }

    /**
     * Goes to the next page
     */
    public void nextPage() {
        if (page < items.size() / 45) {
            page++;
            update();
        }
    }

    /**
     * Goes to the previous page
     */
    public void previousPage() {
        if (page > 0) {
            page--;
            update();
        }
    }

    /**
     * Updates the GUI
     */
    private void update() {
        inv.clear();
        for (int i = 0; i < 45; i++) {
            int index = i + (page * 45);
            if (index >= items.size()) break;
            inv.setItem(i, items.get(index));
        }

        // Add navigation buttons
        ItemStack nextButton = new ItemStack(Material.ARROW);
        ItemMeta nextMeta = nextButton.getItemMeta();
        nextMeta.displayName(Translatable.get("mechanics.gui.next_page").decoration(TextDecoration.ITALIC, false));
        nextButton.setItemMeta(nextMeta);

        ItemStack prevButton = new ItemStack(Material.ARROW);
        ItemMeta prevMeta = prevButton.getItemMeta();
        prevMeta.displayName(Translatable.get("mechanics.gui.previous_page").decoration(TextDecoration.ITALIC, false));
        prevButton.setItemMeta(prevMeta);

        if (page < items.size() / 45) inv.setItem(53, nextButton);
        if (page > 0) inv.setItem(45, prevButton);
    }

    /**
     * Gets the GUI for a player
     * @param player The player
     * @return The GUI
     */
    public static PaginatedGUI getGui(Player player) {
        return guis.get(player.getUniqueId());
    }

    /**
     * Goes to the next page
     * @param player The player
     */
    public static void next(Player player) {
        PaginatedGUI gui = getGui(player);
        gui.nextPage();
    }

    /**
     * Goes to the previous page
     * @param player The player
     */
    public static void previous(Player player) {
        PaginatedGUI gui = getGui(player);
        gui.previousPage();
    }
}

