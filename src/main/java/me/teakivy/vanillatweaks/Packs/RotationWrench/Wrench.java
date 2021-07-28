package me.teakivy.vanillatweaks.Packs.RotationWrench;

import com.google.common.collect.Lists;
import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Piston;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class Wrench implements Listener {

    static Main main = Main.getPlugin(Main.class);
    private final List<BlockFace> faces = Lists.newArrayList(BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.UP, BlockFace.DOWN);

    public static void register() {
        NamespacedKey key = new NamespacedKey(main, "vt_redstone_wrench");

        ItemStack result = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta meta = result.getItemMeta();
        meta.setUnbreakable(true);
        result.setDurability((short) 1);
        meta.setDisplayName(ChatColor.GOLD + "Wrench");
        result.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(key, result);

        recipe.shape(" # ", " ##", "$  ");
        recipe.setIngredient('#', Material.GOLD_INGOT);
        recipe.setIngredient('$', Material.IRON_INGOT);
        if (Bukkit.getRecipe(key) == null) {
            Bukkit.addRecipe(recipe);
        }
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.hasItemMeta()) return;
        if (!Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Wrench")) return;
        if (item.getType() != Material.CARROT_ON_A_STICK) return;
        if (!item.getItemMeta().isUnbreakable()) return;
        if (event.getPlayer().getVehicle() != null) event.setCancelled(true);
        if (event.getClickedBlock() == null) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!isRedstone(event.getClickedBlock().getType()) && !isTerracotta(event.getClickedBlock().getType())) return;
        if (event.getItem() == null) return;
        Block block = event.getClickedBlock();

        if (isRedstone(block.getType()) && main.getConfig().getBoolean("packs.rotation-wrench.rotate-redstone")) {
            Directional data = (Directional) block.getBlockData();
            if (data instanceof Piston && ((Piston) data).isExtended()) {
                return;
            }
            int facing = faces.indexOf(data.getFacing());
            BlockFace nextFace = null;
            int i = 0;
            while (nextFace == null || !data.getFaces().contains(nextFace)) {
                if (i >= 6) throw new IllegalStateException("Infinite");
                nextFace = event.getPlayer().isSneaking() ? facing - 1 < 0 ? faces.get(facing + 6 - 1) : faces.get(facing - 1) : faces.get((facing + 1) % 6);
                facing = faces.indexOf(nextFace);
                i++;
            }
            event.setCancelled(true);
            data.setFacing(nextFace);
            block.setBlockData(data);
            return;
        }
        if (isTerracotta(block.getType()) && main.getConfig().getBoolean("packs.rotation-wrench.rotate-terracotta")) {
            Directional data = (Directional) block.getBlockData();
            int facing = faces.indexOf(data.getFacing());
            BlockFace nextFace = null;
            int i = 0;
            while (nextFace == null || !data.getFaces().contains(nextFace)) {
                if (i >= 6) throw new IllegalStateException("Infinite");
                nextFace = event.getPlayer().isSneaking() ? facing - 1 < 0 ? faces.get(facing + 6 - 1) : faces.get(facing - 1) : faces.get((facing + 1) % 6);
                facing = faces.indexOf(nextFace);
                i++;
            }
            event.setCancelled(true);
            data.setFacing(nextFace);
            block.setBlockData(data);
        }
    }

    private static boolean isRedstone(Material material) {
        if (material == Material.PISTON) return true;
        if (material == Material.OBSERVER) return true;
        if (material == Material.REPEATER) return true;
        if (material == Material.COMPARATOR) return true;
        if (material == Material.HOPPER) return true;
        if (material == Material.STICKY_PISTON) return true;
        if (material == Material.DISPENSER) return true;
        if (material == Material.DROPPER) return true;
        return false;
    }

    private static boolean isTerracotta(Material material) {
        if (material == Material.WHITE_GLAZED_TERRACOTTA) return true;
        if (material == Material.ORANGE_GLAZED_TERRACOTTA) return true;
        if (material == Material.MAGENTA_GLAZED_TERRACOTTA) return true;
        if (material == Material.LIGHT_BLUE_GLAZED_TERRACOTTA) return true;
        if (material == Material.LIGHT_GRAY_GLAZED_TERRACOTTA) return true;
        if (material == Material.PINK_GLAZED_TERRACOTTA) return true;
        if (material == Material.GRAY_GLAZED_TERRACOTTA) return true;
        if (material == Material.CYAN_GLAZED_TERRACOTTA) return true;
        if (material == Material.PURPLE_GLAZED_TERRACOTTA) return true;
        if (material == Material.BLUE_GLAZED_TERRACOTTA) return true;
        if (material == Material.BROWN_GLAZED_TERRACOTTA) return true;
        if (material == Material.GREEN_GLAZED_TERRACOTTA) return true;
        if (material == Material.LIME_GLAZED_TERRACOTTA) return true;
        if (material == Material.RED_GLAZED_TERRACOTTA) return true;
        if (material == Material.BLACK_GLAZED_TERRACOTTA) return true;
        return false;
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
