package me.teakivy.vanillatweaks.Packs.XPManagement;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class XPManagement implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void bottleXP(PlayerInteractEvent event) {
        if (!main.getConfig().getBoolean("packs.xp-management.enabled")) return;
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock() == null) return;
            if (event.getClickedBlock().getType() == Material.ENCHANTING_TABLE) {
                if (event.getItem() == null) return;
                if (event.getItem().getType() == Material.GLASS_BOTTLE) {
                    event.setCancelled(true);
                    if (player.getTotalExperience() >= 8) {
                        player.giveExp(-8);
                        player.getInventory().removeItem(new ItemStack(Material.GLASS_BOTTLE));
                        player.getInventory().addItem(new ItemStack(Material.EXPERIENCE_BOTTLE));
                    }
                }
            }
        }
    }

}
