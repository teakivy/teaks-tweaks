package me.teakivy.teakstweaks.packs.hermitcraft.tag;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.packs.survival.afkdisplay.AFK;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
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

public class Tag extends BasePack {

    public Tag() {
        super ("Tag", "tag", PackType.HERMITCRAFT, Material.NAME_TAG, "Adds the Hermitcraft Tag game directly into your world! '/tag'");
    }

    @EventHandler
    public void tagPlayer(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();
        if (damager.getItemInHand().getType() == Material.AIR) return;
        if (damager.getItemInHand().getItemMeta() == null) return;
        if (!damager.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Tag!") || !damager.getItemInHand().getItemMeta().isUnbreakable()) return;
        if (!damager.getScoreboardTags().contains("tag_it")) return;

        if (AFK.afk.get(player.getUniqueId())) {
            if (!getConfig().getBoolean("allow-tagging-afk")) {
                damager.sendMessage(MessageHandler.getMessage("pack.tag.cant-tag-afk"));
                event.setCancelled(true);
                return;
            }
        }

        damager.removeScoreboardTag("tag_it");
        player.addScoreboardTag("tag_it");

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
        if (getConfig().getBoolean("display-when-tagged")) {
            Bukkit.broadcastMessage(MessageHandler.getMessage("pack.tag.tag-message").replace("%tagged_name%", player.getName()).replace("%tagger_name%", damager.getName()));
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
                if (!entity.getScoreboardTags().contains("tag_it")) event.setCancelled(true);
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
                if (!player.getScoreboardTags().contains("tag_it")) event.setCancelled(true);
            }
        }
    }



    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getScoreboardTags().contains("tag_it")) {

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
        if (player.getScoreboardTags().contains("tag_it")) {
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
        if (sb.getTeam("TaggedTeam") == null) return;
        for (String i : Objects.requireNonNull(sb.getTeam("TaggedTeam")).getEntries()) {
            for (OfflinePlayer oPlayer : Bukkit.getOfflinePlayers()) {
                if (Objects.requireNonNull(oPlayer.getName()).equalsIgnoreCase(i)) {
                    Player player = Bukkit.getOfflinePlayer(oPlayer.getUniqueId()).getPlayer();
                    if (player == null) return;
                    player.removeScoreboardTag("tag_it");
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
