package me.teakivy.teakstweaks.packs.xpmanagement;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.XPUtils;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
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

public class XPManagement extends BasePack {

    public XPManagement() {
        super("xp-management", Material.EXPERIENCE_BOTTLE);
    }

    @Override
    public void init() {
        super.init();
        registerRecipe();
    }

    public void registerRecipe() {
        if (!getConfig().getBoolean("allow-smelting")) return;

        FurnaceRecipe recipe = new FurnaceRecipe(Key.get("smeltable_xp_bottle"), new ItemStack(Material.GLASS_BOTTLE), Material.EXPERIENCE_BOTTLE, getConfig().getInt("take-xp-amount"), 100);
        addRecipe(recipe);
    }

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent event) {
        if (event.getSource().getType() != Material.EXPERIENCE_BOTTLE) return;
        if (event.getSource().hasItemMeta()) {
            PersistentDataContainer data = event.getSource().getItemMeta().getPersistentDataContainer();
            if (data.has(Key.get("xp_amount"), PersistentDataType.INTEGER)) {
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
            if (data.has(Key.get("xp_amount"), PersistentDataType.INTEGER)) {
                return;
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void bottleXP(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (!Permission.XP_MANAGEMENT_BOTTLE.check(player)) return;

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != Material.ENCHANTING_TABLE) return;
        if (item == null) return;
        if (item.getType() != Material.GLASS_BOTTLE) return;

        if (XPUtils.getPlayerExp(player) <= getConfig().getInt("take-xp-amount")) return;

        final int takeXPAmount = getConfig().getInt("take-xp-amount");
        int finalXPAmount = takeXPAmount;
        int timesToBottle = 1;
        if (player.isSneaking() && getConfig().getBoolean("sneak-to-bottle-all")) {
            if (XPUtils.getPlayerExp(player) >= item.getAmount() * takeXPAmount) {
                timesToBottle = item.getAmount();
                finalXPAmount = item.getAmount() * takeXPAmount;
            } else {
                int bottles = item.getAmount();
                int xpTimes = (int) Math.floor((double) XPUtils.getPlayerExp(player) / takeXPAmount);
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

        if (getConfig().getBoolean("display-amount")) {
            List<Component> lore = new ArrayList<>();
            lore.add(getText("bottle_contains", insert("return_amount", getConfig().getInt("take-xp-amount"))));
            xpMeta.lore(lore);
        }

        PersistentDataContainer data = xpMeta.getPersistentDataContainer();
        data.set(Key.get("xp_amount"), PersistentDataType.INTEGER, Config.getInt("packs.xp-management.return-xp-amount"));
        xpBottle.setItemMeta(xpMeta);

        data.set(Key.get("xp_smelt_amount"), PersistentDataType.INTEGER, Config.getInt("packs.xp-management.take-xp-amount"));
        xpBottle.setItemMeta(xpMeta);

        if (getConfig().getBoolean("sneak-to-bottle-all") && player.isSneaking()) {
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
            if (data.has(Key.get("xp_amount"), PersistentDataType.INTEGER)) {
                event.setExperience(data.get(Key.get("xp_amount"), PersistentDataType.INTEGER));
            }
        }
    }

}
