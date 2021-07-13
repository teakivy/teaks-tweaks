package me.teakivy.vanillatweaks.Packs.Tag;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class Tag implements Listener {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    @EventHandler
    public void tagPlayer(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();
        if (damager.getItemInHand().getType() == Material.AIR) return;
        if (damager.getItemInHand().getItemMeta() == null) return;
        if (!damager.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Tag!") || !damager.getItemInHand().getItemMeta().isUnbreakable()) return;
        if (!damager.getScoreboardTags().contains("vt_tag_it")) return;
        damager.removeScoreboardTag("vt_tag_it");
        player.addScoreboardTag("vt_tag_it");

        ItemStack tag = new ItemStack(Material.NAME_TAG);
        ItemMeta tagMeta = tag.getItemMeta();
        tagMeta.setDisplayName(ChatColor.YELLOW + "Tag!");
        tagMeta.setUnbreakable(true);
        tag.setItemMeta(tagMeta);

        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        if (sb.getTeam("TaggedTeam") == null) {
            Team taggedTeam = sb.registerNewTeam("TaggedTeam");
            taggedTeam.setColor(ChatColor.RED);
        }
        Team taggedTeam = sb.getTeam("TaggedTeam");
        taggedTeam.removeEntry(damager.getName());
        taggedTeam.addEntry(player.getName());

        damager.getInventory().removeItem(damager.getInventory().getItem(EquipmentSlot.HAND));
        boolean emptySlot = false;
        for (int i = 0; i < 36; i++) {
            if (player.getInventory().getItem(i) == null) {
                emptySlot = true;
            }
        }
        if (emptySlot) {
            player.getInventory().addItem(tag);
        } else {
            tagFullInventory(player);
        }
        if (main.getConfig().getBoolean("packs.tag.enabled")) {
            Bukkit.broadcastMessage(vt + ChatColor.GOLD + damager.getName() + ChatColor.YELLOW + " tagged " + ChatColor.RED + player.getName());
        }
        event.setDamage(0);
    }

    private void tagFullInventory(Player player) {
        ItemStack tag = new ItemStack(Material.NAME_TAG);
        ItemMeta tagMeta = tag.getItemMeta();
        tagMeta.setDisplayName(ChatColor.YELLOW + "Tag!");
        tagMeta.setUnbreakable(true);
        tag.setItemMeta(tagMeta);

        player.getWorld().dropItemNaturally(player.getLocation(), tag);
    }

    @EventHandler
    public void itemPickup(EntityPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();
        Entity entity = event.getEntity();
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Tag!") && item.getType() == Material.NAME_TAG && item.getItemMeta().isUnbreakable()) {
                if (!entity.getScoreboardTags().contains("vt_tag_it")) event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void itemDespawn(ItemDespawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Tag!") && item.getType() == Material.NAME_TAG && item.getItemMeta().isUnbreakable()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void itemDespawn(PlayerPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();
        Player player = event.getPlayer();
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Tag!") && item.getType() == Material.NAME_TAG && item.getItemMeta().isUnbreakable()) {
                if (!player.getScoreboardTags().contains("vt_tag_it")) event.setCancelled(true);
            }
        }
    }



    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getScoreboardTags().contains("vt_tag_it")) {

            ItemStack tag = new ItemStack(Material.NAME_TAG);
            ItemMeta tagMeta = tag.getItemMeta();
            tagMeta.setDisplayName(ChatColor.YELLOW + "Tag!");
            tagMeta.setUnbreakable(true);
            tag.setItemMeta(tagMeta);

            event.getDrops().remove(tag);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Tag!") && item.getType() == Material.NAME_TAG && item.getItemMeta().isUnbreakable()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (player.getScoreboardTags().contains("vt_tag_it")) {
            ItemStack tag = new ItemStack(Material.NAME_TAG);
            ItemMeta tagMeta = tag.getItemMeta();
            tagMeta.setDisplayName(ChatColor.YELLOW + "Tag!");
            tagMeta.setUnbreakable(true);
            tag.setItemMeta(tagMeta);

            player.getInventory().addItem(tag);
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

    public void uninstall() {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        for (String i : Objects.requireNonNull(sb.getTeam("TaggedTeam")).getEntries()) {
            for (OfflinePlayer oPlayer : Bukkit.getOfflinePlayers()) {
                if (Objects.requireNonNull(oPlayer.getName()).equalsIgnoreCase(i)) {
                    Player player = Bukkit.getOfflinePlayer(oPlayer.getUniqueId()).getPlayer();
                    if (player == null) return;
                    player.removeScoreboardTag("vt_tag_it");
                    Objects.requireNonNull(sb.getTeam("TaggedTeam")).removeEntry(player.getName());

                    ItemStack tag = new ItemStack(Material.NAME_TAG);
                    ItemMeta tagMeta = tag.getItemMeta();
                    tagMeta.setDisplayName(ChatColor.YELLOW + "Tag!");
                    tagMeta.setUnbreakable(true);
                    tag.setItemMeta(tagMeta);

                    player.getInventory().removeItem(tag);
                }
            }

        }
        Objects.requireNonNull(sb.getTeam("TaggedTeam")).unregister();
    }

}
