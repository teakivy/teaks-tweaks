package me.teakivy.teakstweaks.packs.bettergoldendandelion;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class BetterGoldenDandelion extends BasePack {

    /**
     * List of entity types that are considered zombies and should be affected by the golden dandelion.
     */
    private static final EntityType[] ZOMBIE_TYPES = {
            EntityType.ZOMBIE,
            EntityType.HUSK,
            EntityType.DROWNED,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN,
            EntityType.PIGLIN,
            EntityType.ZOGLIN
    };

    /**
     * List of entity types that are ageable but should not be affected by the golden dandelion.
     */
    private static final EntityType[] BLOCKED_TYPES = {
            EntityType.WANDERING_TRADER,
            EntityType.PARROT,
            EntityType.PIGLIN_BRUTE,
            EntityType.ZOMBIE_NAUTILUS,
            EntityType.SULFUR_CUBE,
            EntityType.FROG,
            EntityType.TADPOLE
    };

    public BetterGoldenDandelion() {
        super(TTPack.BETTER_GOLDEN_DANDELION, Material.GOLDEN_DANDELION);
    }

    @EventHandler
    public void onUse(PlayerInteractEntityEvent event) {
        if (!checkGoldenDandelion(event)) return;

        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        EquipmentSlot hand = event.getHand();
        
        if (checkEntityTypeList(entity, BLOCKED_TYPES)) return;
        if (entity instanceof Ageable ageable) {
            if (!ageable.isAdult() && !ageable.getAgeLock() && !checkEntityTypeList(entity, ZOMBIE_TYPES)) return;
            event.setCancelled(true);
            handleAgeable(player, entity, hand);
        }
    }

    /**
     * Checks if the player is holding a golden dandelion.
     *
     * @param event The PlayerInteractEntityEvent to check.
     * @return true if the player is holding a golden dandelion, false otherwise.
     */
    private boolean checkGoldenDandelion(PlayerInteractEntityEvent event) {
        return event.getPlayer().getInventory().getItem(event.getHand()).getType() == Material.GOLDEN_DANDELION;
    }

    /**
     * Handles the interaction with an ageable entity. If the entity is an adult, it will be turned into a baby and its age will be locked. If the entity is a baby, it will be turned into an adult and its age will be unlocked.
     * @param player The player who interacted with the entity.
     * @param entity The entity that was interacted with.
     * @param hand The hand the player used to interact with the entity.
     */
    private void handleAgeable(Player player, Entity entity, EquipmentSlot hand) {
        if (!(entity instanceof Ageable ageable)) return;

        if (ageable.isAdult() && getConfig().getBoolean("allow-shrinking")) {
            ageable.setBaby();
            ageable.setAgeLock(true);
            runSuccess(player, entity, hand, true);
            return;
        }

        if (getConfig().getBoolean("allow-growing")) ageable.setAdult();
        ageable.setAgeLock(!ageable.getAgeLock());
        runSuccess(player, entity, hand, false);
    }

    /**
     * Runs the success actions for using the golden dandelion on an entity. This includes swinging the player's hand, reducing the item count if in creative mode, playing a sound, and spawning particles if the entity is becoming baby.
     * @param player The player who used the golden dandelion.
     * @param entity The entity that was affected by the golden dandelion.
     * @param hand The hand the player used to interact with the entity.
     * @param isBaby Whether the entity is becoming a baby (true) or an adult (false).
     */
    private void runSuccess(Player player, Entity entity, EquipmentSlot hand, boolean isBaby) {
        player.swingHand(hand);
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.getInventory().getItem(hand).setAmount(player.getInventory().getItem(hand).getAmount() - 1);
        }

        player.playSound(entity, Sound.ITEM_GOLDEN_DANDELION_USE, 1, isBaby ? 1.5f : 0.5f);
        if (!isBaby) return;
        player.getWorld().spawnParticle(Particle.PAUSE_MOB_GROWTH, entity.getLocation().add(0, 1, 0), 6, 0.3, 0.2, 0.3, 0.001);
    }

    /**
     * Checks if the entity is in the provided list of entity types.
     * @param entity The entity to check.
     * @param list The list of entity types to check against.
     * @return true if the entity is in the list, false otherwise.
     */
    private boolean checkEntityTypeList(Entity entity, EntityType[] list) {
        if (entity == null) return false;
        for (EntityType type : list) {
            if (entity.getType() == type) return true;
        }
        return false;
    }
}
