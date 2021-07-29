package me.teakivy.vanillatweaks.Packs.Experimental.XPManagement;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class XPManagement implements Listener {

    Main main = Main.getPlugin(Main.class);

    public void registerRecipe() {
        if (main.getConfig().getBoolean("packs.xp-management.allow-smelting")) {
            FurnaceRecipe recipe = new FurnaceRecipe(new ItemStack(Material.GLASS_BOTTLE), Material.EXPERIENCE_BOTTLE);
            recipe.setExperience(main.getConfig().getInt("packs.xp-management.take-xp-amount"));
            Bukkit.addRecipe(recipe);
        }
    }

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent event) {
        if (event.getSource().getType() != Material.EXPERIENCE_BOTTLE) return;
        if (event.getSource().hasItemMeta()) {
            PersistentDataContainer data = event.getSource().getItemMeta().getPersistentDataContainer();
            if (data.has(new NamespacedKey(main, "vt_xp_amount"), PersistentDataType.INTEGER)) {
                return;
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBurn(FurnaceBurnEvent event) {
        Furnace furnace = (Furnace) event.getBlock().getState();
        if (furnace.getInventory().getSmelting() == null) return;
        if (furnace.getInventory().getSmelting().getType() != Material.EXPERIENCE_BOTTLE) return;
        if (furnace.getInventory().getSmelting().hasItemMeta()) {
            PersistentDataContainer data = furnace.getInventory().getSmelting().getItemMeta().getPersistentDataContainer();
            if (data.has(new NamespacedKey(main, "vt_xp_amount"), PersistentDataType.INTEGER)) {
                return;
            }
        }
        event.setCancelled(true);
    }

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
                    if (player.getTotalExperience() >= main.getConfig().getInt("packs.xp-management.take-xp-amount")) {
                        player.giveExp(-main.getConfig().getInt("packs.xp-management.take-xp-amount"));
                        player.getInventory().removeItem(new ItemStack(Material.GLASS_BOTTLE));

                        ItemStack xpBottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
                        ItemMeta xpMeta = xpBottle.getItemMeta();
                        if (main.getConfig().getBoolean("packs.xp-management.display-amount")) {
                            List<String> lore = new ArrayList<>();
                            lore.add(ChatColor.GOLD + "Contains " + main.getConfig().getInt("packs.xp-management.return-xp-amount") + " XP");
                            if (main.getConfig().getBoolean("packs.xp-management.allow-smelting")) {
                                lore.add(ChatColor.DARK_GRAY + "Smelt for " + main.getConfig().getInt("packs.xp-management.take-xp-amount") + " XP");
                            }
                            xpMeta.setLore(lore);
                        }
                        PersistentDataContainer data = xpMeta.getPersistentDataContainer();
                        data.set(new NamespacedKey(main, "vt_xp_amount"), PersistentDataType.INTEGER, main.getConfig().getInt("packs.xp-management.return-xp-amount"));
                        xpBottle.setItemMeta(xpMeta);

                        data.set(new NamespacedKey(main, "vt_xp_smelt_amount"), PersistentDataType.INTEGER, main.getConfig().getInt("packs.xp-management.take-xp-amount"));
                        xpBottle.setItemMeta(xpMeta);

                        player.getInventory().addItem(xpBottle);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onThrow(ExpBottleEvent event) {
        ThrownExpBottle bottle = event.getEntity();

        if (bottle.getItem().getItemMeta() != null) {
            PersistentDataContainer data = bottle.getItem().getItemMeta().getPersistentDataContainer();
            if (data.has(new NamespacedKey(main, "vt_xp_amount"), PersistentDataType.INTEGER)) {
                event.setExperience(data.get(new NamespacedKey(main, "vt_xp_amount"), PersistentDataType.INTEGER));
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
