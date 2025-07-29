package me.teakivy.teakstweaks.packs.cauldronmud;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CauldronMud extends BasePack {

    public CauldronMud() {
        super (TTPack.CAULDRON_MUD, Material.CAULDRON);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!Permission.CAULDRON_MUD.check(event.getPlayer())) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != Material.WATER_CAULDRON) return;

        if (event.getItem() == null) return;
        if (event.getItem().getType() != Material.DIRT) return;

        Levelled levelled = (Levelled) event.getClickedBlock().getBlockData();
        if (levelled.getLevel() == 1 && getConfig().getBoolean("drops-water-level")) {
            event.getClickedBlock().setType(Material.BARRIER);
            Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> event.getClickedBlock().setType(Material.CAULDRON), 1L);
        }

        event.setCancelled(true);

        if (event.getClickedBlock().getType() == Material.WATER_CAULDRON && getConfig().getBoolean("drops-water-level")) {
            levelled.setLevel(levelled.getLevel() - 1);
        }

        event.getClickedBlock().setBlockData(levelled);

        event.getItem().setAmount(event.getItem().getAmount() - 1);

        ItemStack newItem = new ItemStack(Material.MUD);

        event.getPlayer().getInventory().addItem(newItem);
    }
}
