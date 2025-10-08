package me.teakivy.teakstweaks.packs.infestedvillagers;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class InfestedVillagers extends BasePack {

    public InfestedVillagers() {
        super(TTPack.INFESTED_VILLAGERS, Material.ZOMBIE_VILLAGER_SPAWN_EGG);
    }

    @EventHandler
    public void onInfestation(EntityPotionEffectEvent event) {
        if (event.getEntityType() != EntityType.VILLAGER) return;
        if (event.getNewEffect() == null) return;
        if (event.getNewEffect().getType() != PotionEffectType.INFESTED) return;

        Villager entity = (Villager) event.getEntity();
        long seed = Bukkit.getWorlds().getFirst().getSeed();
        long max = getConfig().getLong("max-time") * 20; // Configured max time in ticks
        long min = getConfig().getLong("min-time") * 20; // Configured min time in ticks

        long timer = new Random(seed).nextLong((max - min) + 1) + min;
        entity.shakeHead();
        shakeAfter(entity, timer);
    }

    public void shakeAfter(Villager villager, long delay) {
        if (delay <= 0) {
            if (villager.isDead()) return;
            villager.zombify();
            return;
        }
        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
            if (villager.isDead()) return;
            villager.shakeHead();
            villager.removePotionEffect(PotionEffectType.INFESTED);

            shakeAfter(villager, delay - 10);
        }, 10L);
    }
}
