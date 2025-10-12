package me.teakivy.teakstweaks.packs.stormchanneling;

import io.papermc.paper.event.entity.EntityMoveEvent;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class StormChanneling extends BasePack {
    public StormChanneling() {
        super(TTPack.STORM_CHANNELING, Material.TRIDENT);
    }

    @EventHandler
    public void onTrident(ProjectileLaunchEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.TRIDENT) return;
        Trident trident = (Trident) entity;
        if (trident.getOwnerUniqueId() == null) return;
        ItemStack item = trident.getItemStack();
        if (item.getType() != Material.TRIDENT) return;
        if (!item.getEnchantments().containsKey(Enchantment.CHANNELING)) return;

        tickTrident(trident);
    }

    public void tickTrident(Trident trident) {
        if (trident.isOnGround()) return;
        World world = trident.getWorld();
        if (world.getEnvironment() != World.Environment.NORMAL) return;
        if (world.isThundering()) return;
        if (trident.getLocation().getY() > 1) {
            world.setWeatherDuration(6000);
            world.setThunderDuration(6000);
            world.setStorm(true);
            world.setThundering(true);
            world.strikeLightningEffect(trident.getLocation());
            ItemStack item = trident.getItemStack();
            Damageable damageable = (Damageable) item.getItemMeta();
            if (damageable == null) return;
            damageable.setDamage(damageable.getDamage() + 150);
            item.setItemMeta(damageable);
            trident.addScoreboardTag("storm_channeling_struck");
            return;
        }

        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> tickTrident(trident), 1L);
    }

    @EventHandler
    public void onLand(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        if (!entity.getScoreboardTags().contains("storm_channeling_struck")) return;

        ItemStack item = ((Trident) entity).getItemStack();
        if (item.getType() != Material.TRIDENT) return;
        Damageable damageable = (Damageable) item.getItemMeta();
        if (damageable == null) return;
        if (damageable.getDamage() + 150 >= item.getType().getMaxDurability()) {
            entity.remove();
        }
        damageable.setDamage(damageable.getDamage() + 150);
        item.setItemMeta(damageable);
        ((Trident) entity).setItemStack(item);
        entity.removeScoreboardTag("storm_channeling_struck");
    }
}
