package me.teakivy.teakstweaks.packs.cauldroncopper;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.cauldronpotions.PotionCauldron;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class CauldronCopper extends BasePack {
    private final HashMap<UUID, Long> lastInteract = new HashMap<>();

    public CauldronCopper() {
        super(TTPack.CAULDRON_COPPER, Material.COPPER_BLOCK);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!Permission.CAULDRON_COPPER.check(event.getPlayer())) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();
        if (block.getType() != Material.CAULDRON) return;
        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
        if (item.getType() == Material.AIR) return;
        Player player = event.getPlayer();
        if (player.isSneaking()) return;
        if (lastInteract.containsKey(player.getUniqueId())) {
            if (System.currentTimeMillis() - lastInteract.get(player.getUniqueId()) < 200) {
                return;
            }
        }
        lastInteract.put(player.getUniqueId(), System.currentTimeMillis());
        if (item.getType() == Material.SLIME_BLOCK) {
            clickSlime(player, block, item, event);
            return;
        } else if (item.getType() == Material.HONEY_BLOCK) {
            clickHoney(player, block, item, event);
            return;
        }

        if (item.getType().name().contains("COPPER")) {
            SlimeCauldron slimeCauldron = SlimeCauldron.getCauldronAt(block.getLocation());
            if (slimeCauldron != null) {
                slimeCauldron.clickCopper(item.getType(), item.getAmount(), event);
                return;
            }
            HoneyCauldron honeyCauldron = HoneyCauldron.getCauldronAt(block.getLocation());
            if (honeyCauldron != null) {
                honeyCauldron.clickCopper(item.getType(), item.getAmount(), event);
            }
        }
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        if (!Permission.CAULDRON_COPPER.check(event.getPlayer())) return;
        if (event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        if (event.getHand() != EquipmentSlot.HAND) return;
        Block block = event.getClickedBlock();
        if (block.getType() != Material.CAULDRON) return;
        Player player = event.getPlayer();
        if (player.isSneaking()) return;
        if (lastInteract.containsKey(player.getUniqueId())) {
            if (System.currentTimeMillis() - lastInteract.get(player.getUniqueId()) < 200) {
                return;
            }
        }
        lastInteract.put(player.getUniqueId(), System.currentTimeMillis());
        ItemStack inHand = player.getInventory().getItem(event.getHand());

        SlimeCauldron slimeCauldron = SlimeCauldron.getCauldronAt(block.getLocation());
        if (slimeCauldron != null) {
            slimeCauldron.removeLevel();
            event.setCancelled(true);
            player.getWorld().playSound(block.getLocation(), Sound.BLOCK_SLIME_BLOCK_BREAK, 1, 1);

            if (player.getGameMode() == GameMode.CREATIVE) {
                if (player.getInventory().contains(Material.SLIME_BLOCK)) return;
            }

            if (inHand.getType() == Material.AIR) {
                player.getInventory().setItem(event.getHand(), new ItemStack(Material.SLIME_BLOCK));
            } else if (inHand.getType() == Material.SLIME_BLOCK && inHand.getAmount() < inHand.getMaxStackSize()) {
                inHand.setAmount(inHand.getAmount() + 1);
            } else {
                player.getInventory().addItem(new ItemStack(Material.SLIME_BLOCK));
            }

            return;
        }
        HoneyCauldron honeyCauldron = HoneyCauldron.getCauldronAt(block.getLocation());
        if (honeyCauldron != null) {
            honeyCauldron.removeLevel();
            event.setCancelled(true);
            player.getWorld().playSound(block.getLocation(), Sound.BLOCK_HONEY_BLOCK_BREAK, 1, 1);

            if (player.getGameMode() == GameMode.CREATIVE) {
                if (player.getInventory().contains(Material.HONEY_BLOCK)) return;
            }

            if (inHand.getType() == Material.AIR) {
                player.getInventory().setItem(event.getHand(), new ItemStack(Material.HONEY_BLOCK));
            } else if (inHand.getType() == Material.HONEY_BLOCK && inHand.getAmount() < inHand.getMaxStackSize()) {
                inHand.setAmount(inHand.getAmount() + 1);
            } else {
                player.getInventory().addItem(new ItemStack(Material.HONEY_BLOCK));
            }
        }
    }

    private void clickSlime(Player player, Block block, ItemStack item, PlayerInteractEvent event) {
        HoneyCauldron cauldronCheck = HoneyCauldron.getCauldronAt(block.getLocation());
        if (cauldronCheck != null) return;
        PotionCauldron potionCauldronCheck = PotionCauldron.getCauldronAt(block.getLocation());
        if (potionCauldronCheck != null) return;

        SlimeCauldron cauldron = SlimeCauldron.getCauldronAt(block.getLocation());
        if (cauldron == null) {
            cauldron = new SlimeCauldron(block.getLocation(), 1);
            event.setCancelled(true);
            player.getWorld().playSound(block.getLocation(), Sound.BLOCK_SLIME_BLOCK_PLACE, 1, 1);
            if (player.getGameMode() == GameMode.CREATIVE) return;
            player.getInventory().getItem(event.getHand()).setAmount(item.getAmount() - 1);
            return;
        }
        if (cauldron.isFull()) return;
        player.getWorld().playSound(block.getLocation(), Sound.BLOCK_SLIME_BLOCK_PLACE, 1, 1);
        cauldron.addLevel();
        event.setCancelled(true);
        if (player.getGameMode() == GameMode.CREATIVE) return;
        player.getInventory().getItem(event.getHand()).setAmount(item.getAmount() - 1);
    }

    private void clickHoney(Player player, Block block, ItemStack item, PlayerInteractEvent event) {
        SlimeCauldron cauldronCheck = SlimeCauldron.getCauldronAt(block.getLocation());
        if (cauldronCheck != null) return;
        PotionCauldron potionCauldronCheck = PotionCauldron.getCauldronAt(block.getLocation());
        if (potionCauldronCheck != null) return;

        HoneyCauldron cauldron = HoneyCauldron.getCauldronAt(block.getLocation());

        if (cauldron == null) {
            cauldron = new HoneyCauldron(block.getLocation(), 1);
            event.setCancelled(true);
            player.getWorld().playSound(block.getLocation(), Sound.BLOCK_HONEY_BLOCK_PLACE, 1, 1);
            if (player.getGameMode() == GameMode.CREATIVE) return;
            player.getInventory().getItem(event.getHand()).setAmount(item.getAmount() - 1);
            return;
        }
        if (cauldron.isFull()) return;
        player.getWorld().playSound(block.getLocation(), Sound.BLOCK_HONEY_BLOCK_PLACE, 1, 1);
        cauldron.addLevel();
        event.setCancelled(true);
        if (player.getGameMode() == GameMode.CREATIVE) return;
        player.getInventory().getItem(event.getHand()).setAmount(item.getAmount() - 1);
    }
}
