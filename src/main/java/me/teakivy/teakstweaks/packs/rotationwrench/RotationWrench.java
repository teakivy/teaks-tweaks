package me.teakivy.teakstweaks.packs.rotationwrench;

import com.google.common.collect.Lists;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import me.teakivy.teakstweaks.utils.customitems.TItem;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RotationWrench extends BasePack {
    private final String resourcePackUrl = "https://drive.google.com/uc?export=download&id=1poeTqOmlGj0e40s1FSlop0lhuUNTxrVZ";
    private final byte[] hash = hexStringToByteArray("848afde4632d98f9db390a811737ff8f82374869");


    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    private final List<BlockFace> faces = Lists.newArrayList(BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.UP, BlockFace.DOWN);

    public RotationWrench() {
        super(TTPack.ROTATION_WRENCH, Material.CARROT_ON_A_STICK);
    }

    @Override
    public List<CustomItem> registerItems() {
        List<CustomItem> items = new ArrayList<>();

        ItemStack item = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setCustomModelData(4321);
        item.setDurability((short) 1);
        meta.displayName(getText("item_name"));
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
            event.getPlayer().addResourcePack(UUID.randomUUID(), resourcePackUrl, hash, "Would You like to install the Rotation Wrench resource pack?", false);
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
            event.getPlayer().playSound(block.getLocation(), Sound.BLOCK_COPPER_DOOR_OPEN, 1.0F, 0.7F);
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
            event.getPlayer().playSound(block.getLocation(), Sound.BLOCK_COPPER_DOOR_OPEN, 1.0F, 0.7F);
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
