package me.teakivy.vanillatweaks.Packs.ClassicFishingLoot;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Fishing implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (event.getCaught() == null) return;
        int luckNum = 0;
        Item caught = (Item) event.getCaught();
        if (event.getPlayer().getInventory().getItem(EquipmentSlot.HAND).getEnchantments().containsKey(Enchantment.LUCK)) {
            luckNum = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND).getEnchantmentLevel(Enchantment.LUCK);
        }

        caught.setItemStack(FishingLootTable.generateFishingLoot(luckNum, false));
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
