package me.teakivy.teakstweaks.packs.moremobheads.mobs.special;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.moremobheads.types.TexturedHead;
import me.teakivy.teakstweaks.packs.oldmoremobheads.MobHeads;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Creaking;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CreakingHead implements Listener {
    private TexturedHead head;

    public CreakingHead() {
        this.head = (TexturedHead) MoreMobHeads.getHead("creaking");

        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(this, TeaksTweaks.getInstance());
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        System.out.println("BlockBreakEvent for Creaking Head");
        if (event.getBlock().getType() != Material.CREAKING_HEART) return;
        System.out.println("Block is Creaking Heart");
        Creaking creaking = null;
        List<Entity> entities = (List<Entity>) event.getBlock().getWorld().getNearbyEntities(event.getBlock().getLocation(), 64, 64, 64);

        for (Entity entity : entities) {
            if (entity.getType() != EntityType.CREAKING) continue;
            creaking = (Creaking) entity;
            if (!creaking.getHome().equals(event.getBlock().getLocation())) continue;

            break;
        }
        System.out.println("Found Creaking: " + (creaking != null));

        if (creaking == null) return;

        Creaking finalCreaking = creaking;
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
            System.out.println("Dropping Creaking Head");
            if (!MoreMobHeads.shouldDrop(event.getPlayer(), "creaking")) return;
            System.out.println("Player can drop Creaking Head");
            ItemStack headItem = MoreMobHeads.getHeadItem("creaking", Sound.BLOCK_CREAKING_HEART_SPAWN);
            finalCreaking.getWorld().dropItemNaturally(finalCreaking.getLocation(), headItem);
        }, 50L);
    }
}
