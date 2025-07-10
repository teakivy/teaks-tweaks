package me.teakivy.teakstweaks.packs.unstickypistons;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.util.Vector;

import java.util.Random;

public class UnstickyPistons extends BasePack {

    public UnstickyPistons() {
        super("unsticky-pistons", Material.STICKY_PISTON);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!Permission.UNSTICKY_PISTONS.check(event.getPlayer())) return;
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

            Item item = event.getClickedBlock().getLocation().getWorld().dropItemNaturally(event.getClickedBlock().getLocation().add(0, .7, 0), new ItemStack(Material.SLIME_BALL));
            item.setVelocity(new Vector(0, 0.01, 0));
            event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_SLIME_ATTACK, 1, .75f);

            if (event.getPlayer().getGameMode() != org.bukkit.GameMode.CREATIVE) {
                Damageable dmgItem = (Damageable) itemStack.getItemMeta();
                dmgItem.setDamage(dmgItem.getDamage() + getDamage(itemStack));
                itemStack.setItemMeta(dmgItem);
            }
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
            event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.BLOCK_SLIME_BLOCK_PLACE, 1, .6f);
        }
    }

    public int getDamage(ItemStack itemStack) {
        int unbLvl = itemStack.getEnchantmentLevel(Enchantment.UNBREAKING);
        int dmgTop = 100 / (unbLvl + 1);

        Random rand = new Random();
        if (rand.nextInt(100) < dmgTop) return 1;
        return 0;
    }

}
