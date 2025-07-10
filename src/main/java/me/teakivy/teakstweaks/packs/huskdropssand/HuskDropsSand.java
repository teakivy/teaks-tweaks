package me.teakivy.teakstweaks.packs.huskdropssand;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HuskDropsSand extends BasePack {


    public HuskDropsSand() {
        super("husk-drops-sand", Material.SAND);
    }

    @EventHandler
    public void onHuskDropSand(EntityDeathEvent event) {
        if (event.getEntityType() != EntityType.HUSK) return;
        int maxAmount = getMaxSandAmount(event);
        // int between 0 and maxAmount
        int amount = (int) (Math.random() * (maxAmount + 1));
        if (amount == 0) return;
        event.getDrops().add(new ItemStack(Material.SAND, amount));
    }

    public int getMaxSandAmount(EntityDeathEvent event) {
        int maxAmount = 2;
        Player player = event.getEntity().getKiller();
        if (player == null) return maxAmount;

        ItemMeta weaponMeta = player.getInventory().getItemInMainHand().getItemMeta();
        if (weaponMeta == null) weaponMeta = player.getInventory().getItemInOffHand().getItemMeta();
        if (weaponMeta == null) return maxAmount;

        return maxAmount + weaponMeta.getEnchantLevel(Enchantment.LOOTING);
    }
}
