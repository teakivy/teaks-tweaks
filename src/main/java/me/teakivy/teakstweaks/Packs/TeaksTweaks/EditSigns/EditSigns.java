package me.teakivy.teakstweaks.Packs.TeaksTweaks.EditSigns;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

public class EditSigns implements Listener {
    private static Main plugin;

    private static Constructor<?> nmsBlockPositionConstructor;
    private static Constructor<?> nmsPacketPlayOutOpenSignEditorConstructor;
    private static Class<?> craftWorldClass;
    private static Method craftWorldGetHandleMethod;
    private static Method getTileEntityMethod;
    private static Class<?> nmsTileEntitySignClass;
    private static Class<?> nmsEntityHumanClass;

    private static boolean tileEntitySignUsesModernMethods;

    private static Field nmsTileEntitySignIsEditableField;
    private static Field nmsTileEntitySignEntityHumanField;

    private static Method nmsTileEntitySignSetEditableMethod;
    private static Method nmsTileEntitySignSetUUIDMethod;

    private static Class<?> craftPlayerClass;
    private static Method craftPlayerGetHandleMethod;
    private static Field playerConnectionField;
    private static Method sendPacketMethod;

    public static void init(Main main) {
        plugin = main;

        try {
            Class<?> nmsPacketPlayOutOpenSignEditorClass = getNMSClass("net.minecraft.server.%s.PacketPlayOutOpenSignEditor", "net.minecraft.network.protocol.game.PacketPlayOutOpenSignEditor");
            Class<?> nmsBlockPositionClass = getNMSClass("net.minecraft.server.%s.BlockPosition", "net.minecraft.core.BlockPosition");
            nmsBlockPositionConstructor = nmsBlockPositionClass.getConstructor(int.class, int.class, int.class);
            nmsPacketPlayOutOpenSignEditorConstructor = nmsPacketPlayOutOpenSignEditorClass.getConstructor(nmsBlockPositionClass);
            craftWorldClass = getNMSClass("org.bukkit.craftbukkit.%s.CraftWorld");
            craftWorldGetHandleMethod = craftWorldClass.getMethod("getHandle");
            getTileEntityMethod = craftWorldGetHandleMethod.getReturnType().getMethod("getTileEntity", nmsBlockPositionClass);
            nmsTileEntitySignClass = getNMSClass("net.minecraft.server.%s.TileEntitySign", "net.minecraft.world.level.block.entity.TileEntitySign");
            nmsEntityHumanClass = getNMSClass("net.minecraft.server.%s.EntityHuman", "net.minecraft.world.entity.player.EntityHuman");
            tileEntitySignUsesModernMethods = false;
            try {
                nmsTileEntitySignIsEditableField = nmsTileEntitySignClass.getDeclaredField("isEditable");
                nmsTileEntitySignEntityHumanField = Arrays.asList(nmsTileEntitySignClass.getDeclaredFields()).stream().filter(each -> each.getType().equals(nmsEntityHumanClass)).findFirst().get();
            } catch (NoSuchFieldException e) {
                nmsTileEntitySignSetEditableMethod = nmsTileEntitySignClass.getMethod("a", boolean.class);
                nmsTileEntitySignSetUUIDMethod = nmsTileEntitySignClass.getMethod("a", UUID.class);
                tileEntitySignUsesModernMethods = true;
            }
            craftPlayerClass = getNMSClass("org.bukkit.craftbukkit.%s.entity.CraftPlayer");
            craftPlayerGetHandleMethod = craftPlayerClass.getMethod("getHandle");
            try {
                playerConnectionField = craftPlayerGetHandleMethod.getReturnType().getField("playerConnection");
            } catch (NoSuchFieldException e) {
                playerConnectionField = craftPlayerGetHandleMethod.getReturnType().getField("b");
            }
            sendPacketMethod = Arrays.asList(playerConnectionField.getType().getMethods()).stream().filter(each -> each.getName().equals("sendPacket")).findFirst().get();
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (!player.isSneaking()) return;
        if (block != null && block.getType().toString().contains("SIGN") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && isInteractingWithAir(player)) {
            Material material = block.getType();

            Block attachedBlock;
            BlockData data = block.getBlockData();
            if (data instanceof Directional) {
                Directional directional = (Directional) data;
                attachedBlock = block.getRelative(directional.getFacing().getOppositeFace());
            } else {
                attachedBlock = block.getRelative(BlockFace.DOWN);
            }

            BlockPlaceEvent placeEvent = new BlockPlaceEvent(block, block.getState(), attachedBlock, new ItemStack(material), player, true, EquipmentSlot.HAND);
            Bukkit.getPluginManager().callEvent(placeEvent);
            if (!placeEvent.isCancelled()) {
                try {
                    Object entityPlayer = craftPlayerGetHandleMethod.invoke(craftPlayerClass.cast(player));
                    Object blockPosition = nmsBlockPositionConstructor.newInstance(block.getX(), block.getY(), block.getZ());
                    Object sign = nmsTileEntitySignClass.cast(getTileEntityMethod.invoke(craftWorldGetHandleMethod.invoke(craftWorldClass.cast(block.getWorld())), blockPosition));
                    if (tileEntitySignUsesModernMethods) {
                        nmsTileEntitySignSetEditableMethod.invoke(sign, true);
                        nmsTileEntitySignSetUUIDMethod.invoke(sign, player.getUniqueId());
                    } else {
                        nmsTileEntitySignIsEditableField.setAccessible(true);
                        nmsTileEntitySignIsEditableField.set(sign, true);
                        nmsTileEntitySignEntityHumanField.setAccessible(true);
                        nmsTileEntitySignEntityHumanField.set(sign, nmsEntityHumanClass.cast(entityPlayer));
                    }
                    Object packet = nmsPacketPlayOutOpenSignEditorConstructor.newInstance(blockPosition);
                    sendPacketMethod.invoke(playerConnectionField.get(entityPlayer), packet);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isInteractingWithAir(Player player) {
        try {
            ItemStack mainHand = player.getEquipment().getItemInMainHand();
            ItemStack offHand = player.getEquipment().getItemInOffHand();
            return (mainHand == null || mainHand.getType().equals(Material.AIR)) && (offHand == null || !offHand.getType().toString().contains("DYE"));
        } catch (Throwable e) {
            @SuppressWarnings("deprecation")
            ItemStack item = player.getItemInHand();
            return item == null || item.getType().equals(Material.AIR);
        }
    }

    private static Class<?> getNMSClass(String path, String... paths) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        ClassNotFoundException error = null;
        try {
            return Class.forName(path.replace("%s", version));
        } catch (ClassNotFoundException e) {
            error = e;
        }
        for (String classpath : paths) {
            try {
                return Class.forName(classpath.replace("%s", version));
            } catch (ClassNotFoundException e) {
                error = e;
            }
        }
        throw error;
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }




}
