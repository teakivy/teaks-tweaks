package me.teakivy.teakstweaks.Packs.TeaksTweaks.UnstickyPistons;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class UnstickyPistons implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != Material.STICKY_PISTON && event.getClickedBlock().getType() != Material.PISTON) return;
        if (event.getPlayer().getGameMode() == GameMode.ADVENTURE) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (itemStack.getType().toString().contains("AXE") && event.getClickedBlock().getType() == Material.STICKY_PISTON) {
            BlockData rotatable = event.getClickedBlock().getBlockData();
            Directional directional = (Directional) rotatable;
            event.getClickedBlock().setType(Material.PISTON);
            BlockData rotatable2 = event.getClickedBlock().getLocation().getBlock().getBlockData();
            Directional directional2 = (Directional) rotatable2;
            directional2.setFacing(directional.getFacing());
            event.getClickedBlock().getLocation().getBlock().setBlockData(directional2);

            event.getClickedBlock().getLocation().getWorld().dropItemNaturally(event.getClickedBlock().getLocation().add(0, 1, 0), new ItemStack(Material.SLIME_BALL));

            return;
        }
        if (itemStack.getType() == Material.SLIME_BALL && event.getClickedBlock().getType() == Material.PISTON) {
            BlockData rotatable = event.getClickedBlock().getBlockData();
            Directional directional = (Directional) rotatable;
            event.getClickedBlock().setType(Material.STICKY_PISTON);
            BlockData rotatable2 = event.getClickedBlock().getLocation().getBlock().getBlockData();
            Directional directional2 = (Directional) rotatable2;
            directional2.setFacing(directional.getFacing());
            event.getClickedBlock().getLocation().getBlock().setBlockData(directional2);

            Player player = event.getPlayer();
            if (player.getGameMode() != org.bukkit.GameMode.CREATIVE) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
