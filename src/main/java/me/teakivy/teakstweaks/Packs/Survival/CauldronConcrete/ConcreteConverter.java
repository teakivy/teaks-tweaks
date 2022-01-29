package me.teakivy.teakstweaks.Packs.Survival.CauldronConcrete;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ConcreteConverter extends BasePack {

    public ConcreteConverter() {
        super ("Cauldron Concrete", "cauldron-concrete");
    }

    @EventHandler
    public void onDrop(EntityDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        Material material = item.getType();

        if (material.toString().contains("_CONCRETE_POWDER")) {
            concreteConverter(Material.valueOf(material.toString().replace("_CONCRETE_POWDER", "_CONCRETE")), event);
        }

    }

    @EventHandler
    public void blockDrop(ItemSpawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        Material material = item.getType();

        if (material.toString().contains("_CONCRETE_POWDER")) {
            concreteConverter(Material.valueOf(material.toString().replace("_CONCRETE_POWDER", "_CONCRETE")), event);
        }

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




}
