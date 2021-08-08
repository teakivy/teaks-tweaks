package me.teakivy.vanillatweaks.Packs.Experimental.XPManagement;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Messages.MessageHandler;
import org.bukkit.Bukkit;
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
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != Material.ENCHANTING_TABLE) return;
        if (item == null) return;
        if (item.getType() != Material.GLASS_BOTTLE) return;

        if (player.getTotalExperience() <= main.getConfig().getInt("packs.xp-management.take-xp-amount")) return;

        final int takeXPAmount = main.getConfig().getInt("packs.xp-management.take-xp-amount");
        int finalXPAmount = takeXPAmount;
        int timesToBottle = 1;
        if (player.isSneaking() && main.getConfig().getBoolean("packs.xp-management.sneak-to-bottle-all")) {
            if (player.getTotalExperience() >= item.getAmount() * takeXPAmount) {
                timesToBottle = item.getAmount();
                finalXPAmount = item.getAmount() * takeXPAmount;
            } else {
                int bottles = item.getAmount();
                int xpTimes = (int) Math.floor(player.getTotalExperience() / takeXPAmount);
                if (xpTimes > bottles) {
                    timesToBottle = item.getAmount();
                    finalXPAmount = takeXPAmount * item.getAmount();
                }
                if (bottles > xpTimes) {
                    timesToBottle = xpTimes;
                    finalXPAmount = xpTimes * takeXPAmount;
                }
            }
        }

        event.setCancelled(true);
        int itemAmount = item.getAmount();
        int newAmount = itemAmount - timesToBottle;
        if (newAmount < 0) newAmount = 0;
        if (newAmount > 64) newAmount = 64;
        item.setAmount(newAmount);
        player.giveExp(-finalXPAmount);

        ItemStack xpBottle = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta xpMeta = xpBottle.getItemMeta();

        if (main.getConfig().getBoolean("packs.xp-management.display-amount")) {
            List<String> lore = new ArrayList<>();
            lore.add(MessageHandler.replace(MessageHandler.getMessage("pack.xp-management.bottle-contains"), "%take_amount%", String.valueOf(main.getConfig().getInt("packs.xp-management.return-xp-amount"))));
            if (main.getConfig().getBoolean("packs.xp-management.allow-smelting")) {
                lore.add(MessageHandler.replace(MessageHandler.getMessage("pack.xp-management.bottle-contains"), "%take_amount%", String.valueOf(main.getConfig().getInt("packs.xp-management.take-xp-amount"))));
            }
            xpMeta.setLore(lore);
        }

        PersistentDataContainer data = xpMeta.getPersistentDataContainer();
        data.set(new NamespacedKey(main, "vt_xp_amount"), PersistentDataType.INTEGER, main.getConfig().getInt("packs.xp-management.return-xp-amount"));
        xpBottle.setItemMeta(xpMeta);

        data.set(new NamespacedKey(main, "vt_xp_smelt_amount"), PersistentDataType.INTEGER, main.getConfig().getInt("packs.xp-management.take-xp-amount"));
        xpBottle.setItemMeta(xpMeta);

        if (main.getConfig().getBoolean("packs.xp-management.sneak-to-bottle-all") && player.isSneaking()) {
            for (int i = 0; i < timesToBottle; i++) {
                player.getInventory().addItem(xpBottle);
            }
        } else {
            player.getInventory().addItem(xpBottle);
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
