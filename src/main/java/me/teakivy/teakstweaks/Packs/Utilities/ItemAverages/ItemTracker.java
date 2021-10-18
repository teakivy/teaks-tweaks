package me.teakivy.teakstweaks.Packs.Utilities.ItemAverages;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.Logger.Log;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemTracker implements Listener {

    static Main main = Main.getPlugin(Main.class);
    public static boolean inUse = false;
    public static Location tracking = null;
    private static boolean shouldStop = false;


    static ArrayList<Entity> itemsTracked = new ArrayList<>();
    static HashMap<Material, Integer> items = new HashMap<>();

    public static void spawnTracker(Location loc, Player player) {
        glowingBlock(loc, 2*60*20);
        startTracker(player);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getScoreboardTags().contains("vt_tracker")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getScoreboardTags().contains("vt_tracker")) {
            event.getDrops().clear();
        }
    }

    public static void startTracker(Player player) {
        trackItems(player);
    }

    public static void trackItems(Player player) {

        for (Entity entity : tracking.getWorld().getEntities()) {
            if (entity.getType() == EntityType.DROPPED_ITEM) {
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
            Bukkit.getScheduler().runTaskLater(main, () -> {
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
            player.sendMessage(ChatColor.DARK_GRAY + "-------------------------");
            player.sendMessage(MessageHandler.getMessage("pack.item-averages.source-production"));
            totalItems.forEach((item, amount) -> {
                player.sendMessage(ChatColor.GOLD.toString() + amount + ChatColor.WHITE + " " + item.toString().toLowerCase());
            });
            player.sendMessage(ChatColor.DARK_GRAY + "-------------------------");
        } else {
            player.sendMessage(ChatColor.DARK_GRAY + "-------------------------");
            player.sendMessage(MessageHandler.getMessage("pack.item-averages.no-items"));
            player.sendMessage(ChatColor.DARK_GRAY + "-------------------------");
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
        entity.addScoreboardTag("vt_tracker");
        inUse = true;
        tracking = loc.getBlock().getLocation();


        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
            entity.remove();
            Log.message(MessageHandler.getMessage("pack.item-averages.log-finish"));
            shouldStop = true;
        }, glowLength);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }
}
