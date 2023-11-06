package me.teakivy.teakstweaks.packs.hermitcraft.tag;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.packs.survival.afkdisplay.AFK;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Tag extends BasePack {

    public Tag() {
        super ("tag", PackType.HERMITCRAFT, Material.NAME_TAG);
    }

    @EventHandler
    public void tagPlayer(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();
        if (!isTag(damager.getInventory().getItem(EquipmentSlot.HAND))) return;
        if (!damager.getScoreboardTags().contains("tag_it")) return;

        if (AFK.afk.get(player.getUniqueId())) {
            if (!getConfig().getBoolean("allow-tagging-afk")) {
                damager.sendMessage(getString("error.cant_tag_afk"));
                event.setCancelled(true);
                return;
            }
        }

        damager.removeScoreboardTag("tag_it");
        player.addScoreboardTag("tag_it");



        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        if (sb.getTeam("TaggedTeam") == null) {
            Team taggedTeam = sb.registerNewTeam("TaggedTeam");
            taggedTeam.color(NamedTextColor.RED);
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
            player.getInventory().addItem(getTagItem());
        } else {
            tagFullInventory(player);
        }
        if (getConfig().getBoolean("display-when-tagged")) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.sendMessage(getText("tagged_message",
                        insert("tagged_name", player.getName()),
                        insert("tagger_name", damager.getName())));
            }
        }
        event.setDamage(0);
    }

    private void tagFullInventory(Player player) {
        player.getWorld().dropItemNaturally(player.getLocation(), getTagItem());
    }

    @EventHandler
    public void itemDespawn(ItemDespawnEvent event) {
        if (!isTag(event.getEntity().getItemStack())) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if (!isTag(event.getItem().getItemStack())) return;
        if (!event.getEntity().getScoreboardTags().contains("tag_it")) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!event.getPlayer().getScoreboardTags().contains("tag_it")) return;

        event.getDrops().remove(getTagItem());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (!isTag(event.getItemDrop().getItemStack())) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (!player.getScoreboardTags().contains("tag_it")) return;

        player.getInventory().addItem(getTagItem());
    }

    private static Component getTagItemName() {
        return Translatable.get("tag.item_name").decoration(TextDecoration.ITALIC, false);
    }

    public static ItemStack getTagItem() {
        ItemStack tag = new ItemStack(Material.NAME_TAG);
        ItemMeta tagMeta = tag.getItemMeta();
        tagMeta.displayName(getTagItemName());
        tagMeta.setUnbreakable(true);
        tag.setItemMeta(tagMeta);

        return tag;
    }

    private boolean isTag(ItemStack itemStack) {
        return itemStack.equals(getTagItem());
    }

}
