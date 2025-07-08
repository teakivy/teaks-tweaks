package me.teakivy.teakstweaks.utils.gui;

import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

    /**
     * Handles the GUI clicks
     * @param event InventoryClickEvent
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().title().equals(Translatable.get("mechanics.gui.title"))) {
            event.setCancelled(true);
            if (event.getRawSlot() == 53) {
                // Next page button clicked
                PaginatedGUI.next((Player) event.getWhoClicked());
            } else if (event.getRawSlot() == 45) {
                // Previous page button clicked
                PaginatedGUI.previous((Player) event.getWhoClicked());
            }
        }
    }
}
