package me.teakivy.teakstweaks.utils.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class GUIListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Teak's Tweaks Mechanics")) {
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

    @EventHandler
    public void onInv(InventoryInteractEvent event) {
        if (event.getView().getTitle().equals("Paginated GUI")) {
            event.setCancelled(true);
        }
    }
}
