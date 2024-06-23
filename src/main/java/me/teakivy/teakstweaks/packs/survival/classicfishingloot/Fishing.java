package me.teakivy.teakstweaks.packs.survival.classicfishingloot;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Fishing extends BasePack {

    public Fishing() {
        super("classic-fishing-loot", PackType.SURVIVAL, Material.FISHING_ROD);
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (event.getCaught() == null) return;
        int luckNum = 0;
        Item caught = (Item) event.getCaught();
        if (event.getPlayer().getInventory().getItem(EquipmentSlot.HAND).getEnchantments().containsKey(Enchantment.LUCK_OF_THE_SEA)) {
            luckNum = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND).getEnchantmentLevel(Enchantment.LUCK_OF_THE_SEA);
        }

        caught.setItemStack(FishingLootTable.generateFishingLoot(luckNum, false));
    }

}
