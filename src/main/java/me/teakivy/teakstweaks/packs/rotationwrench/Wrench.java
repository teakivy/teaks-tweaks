package me.teakivy.teakstweaks.packs.rotationwrench;

import com.google.common.collect.Lists;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.MM;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import me.teakivy.teakstweaks.utils.customitems.TItem;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Piston;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Wrench extends BasePack {
    private final String resourcePackUrl = "https://potrebic.box.com/shared/static/uw4fvii2o8qsjuz6xuant1safwjdnrez.zip";
    private final byte[] hash = new BigInteger("1ACF79C491B3CB9EEE50816AD0CC1FC45AABA147", 16).toByteArray();

    private final List<BlockFace> faces = Lists.newArrayList(BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.UP, BlockFace.DOWN);

    public Wrench() {
        super("rotation-wrench", PackType.ITEMS, Material.CARROT_ON_A_STICK);
    }

    @Override
    public List<CustomItem> registerItems() {
        List<CustomItem> items = new ArrayList<>();

        ItemStack item = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setCustomModelData(4321);
        item.setDurability((short) 1);
        meta.setDisplayName(MM.toString(getText("item_name")));
        item.setItemMeta(meta);

        CustomItem wrench = new CustomItem("rotation_wrench", item);
        items.add(wrench);

        return items;
    }

    @Override
    public void init() {
        super.init();

        NamespacedKey key = Key.get("rotation_wrench");

        ShapedRecipe recipe = new ShapedRecipe(key, TItem.ROTATION_WRENCH.getItem());

        recipe.shape(" # ", " ##", "$  ");
        recipe.setIngredient('#', Material.GOLD_INGOT);
        recipe.setIngredient('$', Material.IRON_INGOT);
        addRecipe(recipe);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (getConfig().getBoolean("suggest-pack")) {
            event.getPlayer().setResourcePack(resourcePackUrl, hash);
        }
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.hasItemMeta()) return;
        if (item.getItemMeta() == null) return;
        if (item.getItemMeta().getDisplayName() == null) return;
        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(getText("item_name").toString())) return;
        if (item.getType() != Material.CARROT_ON_A_STICK) return;
        if (!item.getItemMeta().isUnbreakable()) return;
        if (event.getPlayer().getVehicle() != null) event.setCancelled(true);
        if (event.getClickedBlock() == null) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!isRedstone(event.getClickedBlock().getType()) && !isTerracotta(event.getClickedBlock().getType())) return;
        if (event.getItem() == null) return;
        Block block = event.getClickedBlock();

        if (isRedstone(block.getType()) && getConfig().getBoolean("rotate-redstone")) {
            if (!Permission.ROTATION_WRENCH_REDSTONE.check(event.getPlayer())) return;
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
        if (isTerracotta(block.getType()) && getConfig().getBoolean("rotate-terracotta")) {
            if (!Permission.ROTATION_WRENCH_TERRACOTTA.check(event.getPlayer())) return;
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
        boolean rtn = false;
        switch (material) {
            case PISTON,
             STICKY_PISTON,
             DROPPER,
             HOPPER,
             DISPENSER,
             COMPARATOR,
             REPEATER,
             OBSERVER
                -> rtn = true;
        }
        return rtn;
    }

    private static boolean isTerracotta(Material material) {
        return material.toString().toUpperCase().contains("TERRACOTTA");
    }

}
