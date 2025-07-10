package me.teakivy.teakstweaks.packs.collectiblebuddingamethyst;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class CollectibleBuddingAmethyst extends BasePack {

    public CollectibleBuddingAmethyst() {
        super("collectible-budding-amethyst", Material.BUDDING_AMETHYST);
    }

    @EventHandler
    public void onBuddingAmethystBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.BUDDING_AMETHYST) return;

        boolean shouldDrop = true;
        if (getConfig().getBoolean("require-silk-touch")) {
            shouldDrop = event.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH);
        }

        if (!shouldDrop) return;
        event.setDropItems(false);
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.BUDDING_AMETHYST));
    }
}
