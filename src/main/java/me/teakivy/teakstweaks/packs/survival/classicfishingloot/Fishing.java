package me.teakivy.teakstweaks.packs.survival.classicfishingloot;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Fishing extends BasePack {

    public Fishing() {
        super("Classic Fishing Loot", "classic-fishing-loot");
    }

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

}
