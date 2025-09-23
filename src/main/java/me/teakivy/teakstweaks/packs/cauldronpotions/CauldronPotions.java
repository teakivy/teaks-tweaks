package me.teakivy.teakstweaks.packs.cauldronpotions;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.HashMap;
import java.util.UUID;

public class CauldronPotions extends BasePack {
    private final HashMap<UUID, Long> lastInteract = new HashMap<>();

    public CauldronPotions() {
        super(TTPack.CAULDRON_POTIONS, Material.POTION);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!Permission.CAULDRON_POTIONS.check(event.getPlayer())) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();
        if (block.getType() != Material.CAULDRON) return;
        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
        if (item.getType() == Material.AIR) return;
        Player player = event.getPlayer();
        if (lastInteract.containsKey(player.getUniqueId())) {
            if (System.currentTimeMillis() - lastInteract.get(player.getUniqueId()) < 200) {
                return;
            }
        }
        lastInteract.put(player.getUniqueId(), System.currentTimeMillis());
        if (item.getType() == Material.GLASS_BOTTLE) {
            clickBottle(player, block, item, event.getHand());
        } else if (item.getType() == Material.POTION) {
            clickPotion(player, block, item, event.getHand());
        } else if (item.getType() == Material.ARROW) {
            clickArrow(player, block, item, event.getHand());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.CAULDRON) return;
        PotionCauldron cauldron = PotionCauldron.getCauldronAt(block.getLocation());
        if (cauldron == null) return;
        cauldron.remove();
    }

    @EventHandler
    public void onPiston(BlockPistonExtendEvent event) {
        for (Block block : event.getBlocks()) {
            if (block.getType() != Material.CAULDRON) continue;
            PotionCauldron cauldron = PotionCauldron.getCauldronAt(block.getLocation());
            if (cauldron == null) continue;

            Location newLocation = block.getLocation().add(event.getDirection().getDirection());
            cauldron.move(newLocation);
        }
    }

    @EventHandler
    public void onPiston(BlockPistonRetractEvent event) {
        for (Block block : event.getBlocks()) {
            if (block.getType() != Material.CAULDRON) continue;
            PotionCauldron cauldron = PotionCauldron.getCauldronAt(block.getLocation());
            if (cauldron == null) continue;

            Location newLocation = block.getLocation().add(event.getDirection().getDirection());
            cauldron.move(newLocation);
        }
    }

    public void clickPotion(Player player, Block block, ItemStack item, EquipmentSlot slot) {
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
        player.getWorld().playSound(block.getLocation(), Sound.ITEM_BOTTLE_EMPTY, 1, 1);
        if (player.getGameMode() == GameMode.CREATIVE) {
            if (player.getInventory().contains(new ItemStack(Material.GLASS_BOTTLE))) return;
            player.getInventory().addItem(new ItemStack(Material.GLASS_BOTTLE));
            return;
        }
        if (item.getAmount() <= 1) {
            player.getInventory().setItem(slot, new ItemStack(Material.GLASS_BOTTLE));
            return;
        }
        item.setAmount(item.getAmount() - 1);
        player.getInventory().addItem(new ItemStack(Material.GLASS_BOTTLE));
    }

    public void clickBottle(Player player, Block block, ItemStack item, EquipmentSlot slot) {
        PotionCauldron cauldron = PotionCauldron.getCauldronAt(block.getLocation());
        if (cauldron == null) return;
        if (cauldron.getLevel() <= 0) return;
        cauldron.setLevel(cauldron.getLevel() - 1);

        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setBasePotionType(cauldron.getType());
        potion.setItemMeta(meta);
        player.getWorld().playSound(block.getLocation(), Sound.ITEM_BOTTLE_FILL, 1, 1);
        if (player.getGameMode() == GameMode.CREATIVE) {
            if (player.getInventory().contains(potion)) return;
            player.getInventory().addItem(potion);
            return;
        }
        if (item.getAmount() <= 1) {
            player.getInventory().setItem(slot, potion);
            return;
        }
        item.setAmount(item.getAmount() - 1);
        player.getInventory().addItem(potion);
    }

    public void clickArrow(Player player, Block block, ItemStack itemStack, EquipmentSlot slot) {
        PotionCauldron cauldron = PotionCauldron.getCauldronAt(block.getLocation());
        if (cauldron == null) return;
        if (cauldron.getLevel() <= 0) return;
        cauldron.setLevel(cauldron.getLevel() - 1);

        ItemStack arrow = new ItemStack(Material.TIPPED_ARROW, Math.min(itemStack.getAmount(), 8));
        PotionMeta meta = (PotionMeta) arrow.getItemMeta();
        meta.setBasePotionType(cauldron.getType());
        arrow.setItemMeta(meta);
        player.getWorld().playSound(block.getLocation(), Sound.ENTITY_VILLAGER_WORK_FLETCHER, 1, 1);
        if (itemStack.getAmount() <= 8 && player.getGameMode() != GameMode.CREATIVE) {
            player.getInventory().setItem(slot, arrow);
            return;
        }
        if (player.getGameMode() != GameMode.CREATIVE) {
            itemStack.setAmount(itemStack.getAmount() - Math.min(itemStack.getAmount(), 8));
        }
        player.getInventory().addItem(arrow);
    }
}
