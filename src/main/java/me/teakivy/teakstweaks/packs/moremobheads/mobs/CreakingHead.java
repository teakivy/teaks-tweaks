package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.MMHDatapackCreator;
import me.teakivy.teakstweaks.packs.moremobheads.MobHeads;
import me.teakivy.teakstweaks.utils.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Registry;
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
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

import static me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead.getUrlFromBase64;

public class CreakingHead implements Listener {

    private final static String TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmMwNzdjOTc1ZjEzZGJkYWVkYTVmMGQyMDE2ZWIzYWY1MWU0YzI3ZDk3MTRhMTIzYzQxMjg5MDE1MDVjNzg4YyJ9fX0";

    public static void init() {
        MMHDatapackCreator.addBaseAdvancement("creaking_head", "Creaking", TEXTURE);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.CREAKING_HEART) return;
        Creaking creaking = null;
        List<Entity> entities = (List<Entity>) event.getBlock().getWorld().getNearbyEntities(event.getBlock().getLocation(), 64, 64, 64);

        for (Entity entity : entities) {
            if (entity.getType() != EntityType.CREAKING) continue;
            creaking = (Creaking) entity;
            if (!creaking.getHome().equals(event.getBlock().getLocation())) continue;

            break;
        }

        if (creaking == null) return;

        Creaking finalCreaking = creaking;
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
            if (!MobHeads.shouldDrop(event.getPlayer(), "creaking")) return;
            finalCreaking.getWorld().dropItemNaturally(finalCreaking.getLocation(), createCreakingHead());

            Advancement advancement = Bukkit.getAdvancement(Key.get("moremobheads/creaking_head"));
            if (advancement == null) return;
            AdvancementProgress progress = event.getPlayer().getAdvancementProgress(advancement);
            for(String criteria : progress.getRemainingCriteria()) progress.awardCriteria(criteria);
        }, 50L);
    }

    public ItemStack createCreakingHead() {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), "MobHead");
        SkullMeta meta = (SkullMeta)head.getItemMeta();
        Component c = MiniMessage.miniMessage().deserialize("<yellow>Creaking's Head").decoration(TextDecoration.ITALIC, false);
        meta.displayName(c);
        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(getUrlFromBase64(TEXTURE));
        } catch (MalformedURLException var8) {
            var8.printStackTrace();
        }

        meta.setOwnerProfile(profile);
        meta.setNoteBlockSound(Registry.SOUNDS.getKey(Sound.ENTITY_CREAKING_AMBIENT));
        head.setItemMeta(meta);
        return head;
    }
}
