package me.teakivy.teakstweaks.Packs.Survival.CauldronConcrete;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ConcreteConverter implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onDrop(EntityDropItemEvent event) {
        if (!main.getConfig().getBoolean("packs.cauldron-concrete.enabled")) return;
        ItemStack item = event.getItemDrop().getItemStack();
        Material material = item.getType();
        if (material == Material.WHITE_CONCRETE_POWDER) concreteConverter(Material.WHITE_CONCRETE, event);
        if (material == Material.BLACK_CONCRETE_POWDER) concreteConverter(Material.BLACK_CONCRETE, event);
        if (material == Material.BLUE_CONCRETE_POWDER) concreteConverter(Material.BLUE_CONCRETE, event);
        if (material == Material.CYAN_CONCRETE_POWDER) concreteConverter(Material.CYAN_CONCRETE, event);
        if (material == Material.LIGHT_BLUE_CONCRETE_POWDER) concreteConverter(Material.LIGHT_BLUE_CONCRETE, event);
        if (material == Material.GRAY_CONCRETE_POWDER) concreteConverter(Material.GRAY_CONCRETE, event);
        if (material == Material.LIGHT_GRAY_CONCRETE_POWDER) concreteConverter(Material.LIGHT_GRAY_CONCRETE, event);
        if (material == Material.LIME_CONCRETE_POWDER) concreteConverter(Material.LIME_CONCRETE, event);
        if (material == Material.GREEN_CONCRETE_POWDER) concreteConverter(Material.GREEN_CONCRETE, event);
        if (material == Material.PURPLE_CONCRETE_POWDER) concreteConverter(Material.PURPLE_CONCRETE, event);
        if (material == Material.RED_CONCRETE_POWDER) concreteConverter(Material.RED_CONCRETE, event);
        if (material == Material.PINK_CONCRETE_POWDER) concreteConverter(Material.PINK_CONCRETE, event);
        if (material == Material.MAGENTA_CONCRETE_POWDER) concreteConverter(Material.MAGENTA_CONCRETE, event);
        if (material == Material.BROWN_CONCRETE_POWDER) concreteConverter(Material.BROWN_CONCRETE, event);
        if (material == Material.YELLOW_CONCRETE_POWDER) concreteConverter(Material.YELLOW_CONCRETE, event);
        if (material == Material.ORANGE_CONCRETE_POWDER) concreteConverter(Material.ORANGE_CONCRETE, event);

    }

    @EventHandler
    public void blockDrop(ItemSpawnEvent event) {
        if (!main.getConfig().getBoolean("packs.cauldron-concrete.enabled")) return;
        ItemStack item = event.getEntity().getItemStack();
        Material material = item.getType();
        if (material == Material.WHITE_CONCRETE_POWDER) concreteConverter(Material.WHITE_CONCRETE, event);
        if (material == Material.BLACK_CONCRETE_POWDER) concreteConverter(Material.BLACK_CONCRETE, event);
        if (material == Material.BLUE_CONCRETE_POWDER) concreteConverter(Material.BLUE_CONCRETE, event);
        if (material == Material.CYAN_CONCRETE_POWDER) concreteConverter(Material.CYAN_CONCRETE, event);
        if (material == Material.LIGHT_BLUE_CONCRETE_POWDER) concreteConverter(Material.LIGHT_BLUE_CONCRETE, event);
        if (material == Material.GRAY_CONCRETE_POWDER) concreteConverter(Material.GRAY_CONCRETE, event);
        if (material == Material.LIGHT_GRAY_CONCRETE_POWDER) concreteConverter(Material.LIGHT_GRAY_CONCRETE, event);
        if (material == Material.LIME_CONCRETE_POWDER) concreteConverter(Material.LIME_CONCRETE, event);
        if (material == Material.GREEN_CONCRETE_POWDER) concreteConverter(Material.GREEN_CONCRETE, event);
        if (material == Material.PURPLE_CONCRETE_POWDER) concreteConverter(Material.PURPLE_CONCRETE, event);
        if (material == Material.RED_CONCRETE_POWDER) concreteConverter(Material.RED_CONCRETE, event);
        if (material == Material.PINK_CONCRETE_POWDER) concreteConverter(Material.PINK_CONCRETE, event);
        if (material == Material.MAGENTA_CONCRETE_POWDER) concreteConverter(Material.MAGENTA_CONCRETE, event);
        if (material == Material.BROWN_CONCRETE_POWDER) concreteConverter(Material.BROWN_CONCRETE, event);
        if (material == Material.YELLOW_CONCRETE_POWDER) concreteConverter(Material.YELLOW_CONCRETE, event);
        if (material == Material.ORANGE_CONCRETE_POWDER) concreteConverter(Material.ORANGE_CONCRETE, event);

    }



    public void concreteConverter(Material result, EntityDropItemEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (event.getItemDrop().getLocation().getBlock().getType().equals(Material.WATER_CAULDRON)) {
                    event.getItemDrop().getWorld().dropItem(event.getItemDrop().getLocation(), new ItemStack(result, event.getItemDrop().getItemStack().getAmount()));
                    event.getItemDrop().remove();
                }
                if (event.getItemDrop().isDead()) this.cancel();
            }
        }.runTaskTimer(main, 0, 20L);
    }

    public void concreteConverter(Material result, ItemSpawnEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (event.getEntity().getLocation().getBlock().getType().equals(Material.WATER_CAULDRON)) {
                    event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(result, event.getEntity().getItemStack().getAmount()));
                    event.getEntity().remove();
                }
                if (event.getEntity().isDead()) this.cancel();
            }
        }.runTaskTimer(main, 0, 20L);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }




}
