package me.teakivy.teakstweaks.packs.unwaxsigns;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class UnwaxSigns extends BasePack {

    public UnwaxSigns() {
        super(TTPack.UNWAX_SIGNS, Material.OAK_SIGN);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();

        if (event.getPlayer().getGameMode() == GameMode.ADVENTURE) return;
        if (!event.getPlayer().isSneaking()) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!Permission.UNWAX_SIGNS.check(event.getPlayer())) return;
        if (clickedBlock == null) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (Tag.ITEMS_AXES.isTagged(itemStack.getType()) && Tag.ALL_SIGNS.isTagged(clickedBlock.getType())) {
            
            Sign sign = (Sign) clickedBlock.getState();

            if (sign.isWaxed()) {
                clickedBlock.getWorld().playEffect(clickedBlock.getLocation().add(0.5, 0.5, 0.5), Effect.COPPER_WAX_OFF, 0);
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_AXE_WAX_OFF, 1, 1.0f);
                sign.setWaxed(false);
                sign.update();
            }

        }
    }
}