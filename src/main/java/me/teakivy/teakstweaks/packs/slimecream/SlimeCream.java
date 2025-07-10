package me.teakivy.teakstweaks.packs.slimecream;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class SlimeCream extends BasePack {

    public SlimeCream() {
        super("slime-cream", Material.SLIME_BALL);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!Permission.SLIME_CREAM.check(event.getPlayer())) return;
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Block block = event.getClickedBlock();

        if (event.getClickedBlock().getType() == Material.WATER_CAULDRON && item.getType() == Material.MAGMA_CREAM) {

            item.setAmount(item.getAmount() - 1);
            Item drop = block.getLocation().getWorld().dropItem(block.getLocation().add(.5, 1, .5), new ItemStack(Material.SLIME_BALL));
            drop.setVelocity(new Vector(0, 0, 0));
            drop.setGravity(false);
            player.playSound(block.getLocation(), Sound.BLOCK_POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON, 1, .5f);
        } else if (event.getClickedBlock().getType() == Material.LAVA_CAULDRON && item.getType() == Material.SLIME_BALL) {

            item.setAmount(item.getAmount() - 1);
            Item drop = block.getLocation().getWorld().dropItem(block.getLocation().add(.5, 1, .5), new ItemStack(Material.MAGMA_CREAM));
            drop.setVelocity(new Vector(0, 0, 0));
            drop.setGravity(false);
            player.playSound(block.getLocation(), Sound.BLOCK_POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON, 1, 1);
        }
    }

}
