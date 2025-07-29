package me.teakivy.teakstweaks.packs.invisibleitemframes;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.ItemUtils;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class InvisibleItemFrames extends BasePack {

    public InvisibleItemFrames() {
        super(TTPack.INVISIBLE_ITEM_FRAMES, Material.GLOW_ITEM_FRAME);
    }

    @EventHandler
    public void onRotate(PlayerInteractEntityEvent event) {
        if (!Permission.INVISIBLE_ITEM_FRAMES.check(event.getPlayer())) return;

        if (event.getRightClicked().getType() == EntityType.ITEM_FRAME || event.getRightClicked().getType() == EntityType.GLOW_ITEM_FRAME) {
            if (!event.getPlayer().isSneaking()) return;
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
            if (item.getType() != Material.valueOf(getConfig().getString("item-to-use"))) return;
            event.setCancelled(true);

            event.getPlayer().getInventory().setItemInMainHand(ItemUtils.handleUse(item, event.getPlayer()));

            ItemFrame frame = (ItemFrame) event.getRightClicked();
            frame.setVisible(!frame.isVisible());
            frame.getWorld().spawnParticle(Particle.END_ROD, frame.getLocation().add(0, .5, 0), 1, .1, .1, .1, 0);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEEHIVE_SHEAR, 1, 1.4f);
        }
    }
}
