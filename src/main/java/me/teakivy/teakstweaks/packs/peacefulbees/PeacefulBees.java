package me.teakivy.teakstweaks.packs.peacefulbees;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.block.Beehive;
import org.bukkit.block.Block;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PeacefulBees extends BasePack {

    public PeacefulBees() {
        super(TTPack.PEACEFUL_BEES, Material.BEEHIVE);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Bee bee)) return;
        if (!(event.getDamager() instanceof Player)) return;
        final double damage = event.getDamage();
        event.setCancelled(true);

        bee.damage(damage);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.BEE_NEST && block.getType() != Material.BEEHIVE) return;
        event.setCancelled(true);
        Beehive beehive = (Beehive) block.getState();
        beehive.releaseEntities();
        block.breakNaturally(event.getPlayer().getInventory().getItemInMainHand());
    }
}
