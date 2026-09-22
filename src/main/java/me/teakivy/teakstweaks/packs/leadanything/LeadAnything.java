package me.teakivy.teakstweaks.packs.leadanything;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;
import java.util.List;

public class LeadAnything extends BasePack {

    public LeadAnything() {
        super(TTPack.LEAD_ANYTHING, Material.LEAD);
    }

    @EventHandler
    public void onLead(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking()) return;
        if (player.getInventory().getItem(event.getHand()).getType() != Material.LEAD) return;

        if (!(event.getRightClicked() instanceof LivingEntity entity)) return;
        if (entity.isLeashed()) return;
        if (!getLeadableEntities().contains(entity.getType())) return;

        event.setCancelled(true);
        entity.setLeashHolder(player);
        entity.getWorld().playSound(entity.getLocation(), Sound.ITEM_LEAD_TIED, 1, 1);
    }

    public List<EntityType> getLeadableEntities() {
        List<EntityType> leadableEntities = new ArrayList<>();

        if (getConfig().getBoolean("allow-villagers")) {
            leadableEntities.add(EntityType.VILLAGER);
            leadableEntities.add(EntityType.WANDERING_TRADER);
        }

        if (getConfig().getBoolean("allow-passive")) {
            leadableEntities.add(EntityType.BAT);
            leadableEntities.add(EntityType.PANDA);
        }

        if (getConfig().getBoolean("allow-aquatic")) {
            leadableEntities.add(EntityType.COD);
            leadableEntities.add(EntityType.SALMON);
            leadableEntities.add(EntityType.TADPOLE);
            leadableEntities.add(EntityType.TROPICAL_FISH);
            leadableEntities.add(EntityType.TURTLE);
        }

        if (getConfig().getBoolean("allow-hostile")) {
            leadableEntities.add(EntityType.BLAZE);
            leadableEntities.add(EntityType.BOGGED);
            leadableEntities.add(EntityType.BREEZE);
            leadableEntities.add(EntityType.CAVE_SPIDER);
            leadableEntities.add(EntityType.CREAKING);
            leadableEntities.add(EntityType.CREEPER);
            leadableEntities.add(EntityType.DROWNED);
            leadableEntities.add(EntityType.ENDERMAN);
            leadableEntities.add(EntityType.ENDERMITE);
            leadableEntities.add(EntityType.EVOKER);
            leadableEntities.add(EntityType.GHAST);
            leadableEntities.add(EntityType.GUARDIAN);
            leadableEntities.add(EntityType.HUSK);
            leadableEntities.add(EntityType.MAGMA_CUBE);
            leadableEntities.add(EntityType.PARCHED);
            leadableEntities.add(EntityType.PHANTOM);
            leadableEntities.add(EntityType.PIGLIN);
            leadableEntities.add(EntityType.PIGLIN_BRUTE);
            leadableEntities.add(EntityType.PILLAGER);
            leadableEntities.add(EntityType.RAVAGER);
            leadableEntities.add(EntityType.SHULKER);
            leadableEntities.add(EntityType.SILVERFISH);
            leadableEntities.add(EntityType.SKELETON);
            leadableEntities.add(EntityType.SLIME);
            leadableEntities.add(EntityType.SPIDER);
            leadableEntities.add(EntityType.STRAY);
            leadableEntities.add(EntityType.VEX);
            leadableEntities.add(EntityType.VINDICATOR);
            leadableEntities.add(EntityType.WITCH);
            leadableEntities.add(EntityType.WITHER_SKELETON);
            leadableEntities.add(EntityType.ZOGLIN);
            leadableEntities.add(EntityType.ZOMBIE);
            leadableEntities.add(EntityType.ZOMBIE_VILLAGER);
            leadableEntities.add(EntityType.ZOMBIFIED_PIGLIN);
        }

        if (getConfig().getBoolean("allow-bosses")) {
            leadableEntities.add(EntityType.ELDER_GUARDIAN);
            leadableEntities.add(EntityType.ENDER_DRAGON);
            leadableEntities.add(EntityType.WARDEN);
            leadableEntities.add(EntityType.WITHER);
        }

        return leadableEntities;
    }
}
