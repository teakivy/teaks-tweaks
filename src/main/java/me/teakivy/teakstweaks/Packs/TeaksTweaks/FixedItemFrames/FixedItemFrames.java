package me.teakivy.teakstweaks.Packs.TeaksTweaks.FixedItemFrames;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class FixedItemFrames extends BasePack {

	public FixedItemFrames() {
        super("Fixed Item Frames", "fixed-item-frames");
    }

	@EventHandler
	public void onRotate(PlayerInteractEntityEvent event) {
		if (event.getRightClicked().getType() == EntityType.ITEM_FRAME || event.getRightClicked().getType() == EntityType.GLOW_ITEM_FRAME) {
			if (!event.getPlayer().isSneaking()) return;
			if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.IRON_BARS) return;
			if (((ItemFrame) event.getRightClicked()).isFixed()) return;
			event.setCancelled(true);

			event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);

			ItemFrame frame = (ItemFrame) event.getRightClicked();
			frame.setFixed(true);
			frame.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, frame.getLocation().add(0, .5, 0), 1, .1, .1, .1, 0);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHEST_LOCKED, 1, 1.4f);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent event) {
		System.out.println(event.getRightClicked().getType());
		if (event.getRightClicked().getType() == EntityType.ITEM_FRAME || event.getRightClicked().getType() == EntityType.GLOW_ITEM_FRAME) {
            if (!event.getPlayer().isSneaking()) return;
            if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.SHEARS) return;
            if (!((ItemFrame) event.getRightClicked()).isFixed()) return;
            event.setCancelled(true);

            ItemFrame frame = (ItemFrame) event.getRightClicked();
            frame.setFixed(false);
            frame.getWorld().spawnParticle(Particle.HEART, frame.getLocation().add(0, .5, 0), 1, .1, .1, .1, 0);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1, 1.4f);
        }
	}
}
