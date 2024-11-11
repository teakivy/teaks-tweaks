package me.teakivy.teakstweaks.packs.fixeditemframes;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class FixedItemFrames extends BasePack {

	public FixedItemFrames() {
        super("fixed-item-frames", PackType.TEAKSTWEAKS, Material.ITEM_FRAME);
    }

	@EventHandler
	public void onRotate(PlayerInteractEntityEvent event) {
		if (!Permission.FIXED_ITEM_FRAMES.check(event.getPlayer())) return;

		if (event.getRightClicked().getType() == EntityType.ITEM_FRAME || event.getRightClicked().getType() == EntityType.GLOW_ITEM_FRAME) {
			if (!event.getPlayer().isSneaking()) return;
			if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.IRON_BARS) return;
			if (((ItemFrame) event.getRightClicked()).isFixed()) return;
			event.setCancelled(true);

			event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - getConfig().getInt("take-item-for-fix"));

			ItemFrame frame = (ItemFrame) event.getRightClicked();
			frame.setFixed(true);
			frame.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, frame.getLocation().add(0, .5, 0), 1, .1, .1, .1, 0);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHEST_LOCKED, 1, 1.4f);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		if (!Permission.FIXED_ITEM_FRAMES.check(event.getPlayer())) return;
        if (Material.getMaterial(getConfig().getString("item-for-unfix")) == null) return;
		if (event.getRightClicked().getType() == EntityType.ITEM_FRAME || event.getRightClicked().getType() == EntityType.GLOW_ITEM_FRAME) {
            if (!event.getPlayer().isSneaking()) return;
            if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.getMaterial(getConfig().getString("item-for-unfix"))) return;
            if (!((ItemFrame) event.getRightClicked()).isFixed()) return;
            event.setCancelled(true);

            ItemFrame frame = (ItemFrame) event.getRightClicked();
            frame.setFixed(false);
            frame.getWorld().spawnParticle(Particle.HEART, frame.getLocation().add(0, .5, 0), 1, .1, .1, .1, 0);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1, 1.4f);
        }
	}
}
