package me.teakivy.teakstweaks.packs.itemaverages;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemTracker extends BasePack {
    public static boolean inUse = false;
    public static Location tracking = null;
    private static boolean shouldStop = false;


    static ArrayList<Entity> itemsTracked = new ArrayList<>();
    static HashMap<Material, Integer> items = new HashMap<>();

    public ItemTracker() {
        super("item-averages", Material.HOPPER);
    }

    public static void spawnTracker(Location loc, Player player) {
        glowingBlock(loc, 2*60*20);
        startTracker(player);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getScoreboardTags().contains("tracker")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getScoreboardTags().contains("tracker")) {
            event.getDrops().clear();
        }
    }

    public static void startTracker(Player player) {
        trackItems(player);
    }

    public static void trackItems(Player player) {

        for (Entity entity : tracking.getWorld().getEntities()) {
            if (entity.getType() == EntityType.ITEM) {
                if (entity.getLocation().getBlock().getLocation().distanceSquared(tracking) < 1 && !itemsTracked.contains(entity)) {
                    if (!(entity instanceof Item)) continue;
                    ItemStack item = ((Item) entity).getItemStack();
                    itemsTracked.add(entity);
                    if (!items.containsKey(item.getType())) {
                        items.put(item.getType(), item.getAmount());
                    } else {
                        items.put(item.getType(), items.get(item.getType()) + item.getAmount());
                    }
                }
            }
        }
        if (!shouldStop) {
            Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
                trackItems(player);
            }, 1L);
            return;
        }
        shouldStop = false;
        inUse = false;
        tracking = null;

        sendTotaledMessage(player, items);

        itemsTracked = new ArrayList<>();
        items = new HashMap<>();
    }

    private static void sendTotaledMessage(Player player, HashMap<Material, Integer> items) {
        HashMap<Material, Integer> totalItems = new HashMap<>();

        items.forEach((item, amount) -> {
            totalItems.put(item, amount * 30);
        });

        if (!totalItems.isEmpty()) {
            player.sendMessage(newText("<dark_gray>-------------------------"));
            player.sendMessage(Translatable.get("item_averages.source_production"));
            totalItems.forEach((item, amount) -> {
                player.sendMessage(
                        Translatable.get("item_averages.item",
                                insert("amount", amount),
                                insert("item", item.toString().toLowerCase().replace("_", " "))));
            });
            player.sendMessage(newText("<dark_gray>-------------------------"));
        } else {
            player.sendMessage(newText("<dark_gray>-------------------------"));
            player.sendMessage(Translatable.get("item_averages.no_items"));
            player.sendMessage(newText("<dark_gray>-------------------------"));
        }
    }

    public static void glowingBlock(Location loc, int glowLength) {
        LivingEntity entity = (LivingEntity) loc.getWorld().spawnEntity(loc.getBlock().getLocation().add(.5, 0, .5), EntityType.MAGMA_CUBE);
        MagmaCube cube = (MagmaCube) entity;
        cube.setSize(2);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, glowLength, 0, true, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, glowLength, 0, true, false));
        entity.setAI(false);
        entity.setInvulnerable(true);
        entity.addScoreboardTag("tracker");
        inUse = true;
        tracking = loc.getBlock().getLocation();


        Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
            entity.remove();
            Logger.info(Translatable.get("item_averages.log_finish"));
            shouldStop = true;
        }, glowLength);
    }
}
