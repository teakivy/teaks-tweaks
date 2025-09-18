package me.teakivy.teakstweaks.packs.cauldronpotions;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class CauldronPotions extends BasePack {
    public CauldronPotions() {
        super(TTPack.CAULDRON_POTIONS, Material.POTION);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();
        if (block.getType() != Material.CAULDRON) return;
        if (event.getItem() == null) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (item.getType() == Material.POTION) {
            clickPotion(player, block, item);
        } else if (item.getType() == Material.GLASS_BOTTLE) {
            clickBottle(player, block, item);
        }
    }

    public void clickPotion(Player player, Block block, ItemStack item) {
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        PotionType type = meta.getBasePotionType();
        if (type == null) return;
        if (type == PotionType.WATER || type == PotionType.AWKWARD || type == PotionType.MUNDANE || type == PotionType.THICK) return;
        PotionCauldron cauldron = PotionCauldron.getCauldronAt(block.getLocation());
        if (cauldron == null) {
            cauldron = new PotionCauldron(block.getLocation(), 1, type);
        } else {
            if (type != cauldron.getType()) return;
            if (cauldron.getLevel() >= 3) return;
            cauldron.setLevel(cauldron.getLevel() + 1);
        }

        item.setAmount(item.getAmount() - 1);
        player.getInventory().addItem(new ItemStack(Material.GLASS_BOTTLE));
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), player::updateInventory, 1L);
    }

    public void clickBottle(Player player, Block block, ItemStack item) {
        PotionCauldron cauldron = PotionCauldron.getCauldronAt(block.getLocation());
        if (cauldron == null) return;
        if (cauldron.getLevel() <= 0) return;
        cauldron.setLevel(cauldron.getLevel() - 1);

        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setBasePotionType(cauldron.getType());
        potion.setItemMeta(meta);
        item.setAmount(item.getAmount() - 1);
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), player::updateInventory, 1L);
    }
}
