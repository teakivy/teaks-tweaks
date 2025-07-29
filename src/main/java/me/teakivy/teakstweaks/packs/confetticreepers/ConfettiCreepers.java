package me.teakivy.teakstweaks.packs.confetticreepers;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class ConfettiCreepers extends BasePack {

    public ConfettiCreepers() {
        super(TTPack.CONFETTI_CREEPERS, Material.FIREWORK_ROCKET);
    }

    @EventHandler
    public void onPrime(ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.CREEPER) return;
        if (entity.getScoreboardTags().contains("confetti_true") || entity.getScoreboardTags().contains("confetti_false")) return;

        if (!randomChance(getConfig().getInt("confetti-chance"))) {
            entity.addScoreboardTag("confetti_false");
            return;
        }

        entity.addScoreboardTag("confetti_true");

        FireworkEffect fwEffect = FireworkEffect.builder()
                .trail(false)
                .flicker(false)
                .with(FireworkEffect.Type.BURST)
                .withColor(
                        Color.fromRGB(11743532),
                        Color.fromRGB(15435844),
                        Color.fromRGB(14602026),
                        Color.fromRGB(4312372),
                        Color.fromRGB(6719955),
                        Color.fromRGB(8073150),
                        Color.fromRGB(14188952)
                )
                .build();

        Firework fw = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK_ROCKET);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.setDisplayName("Confetti");
        fwMeta.addEffect(fwEffect);
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.CREEPER) return;
        if (!entity.getScoreboardTags().contains("confetti_true")) return;
        if (getConfig().getBoolean("do-block-damage")) return;

        event.blockList().clear();
    }

    @EventHandler
    public void onExplosionDamage(EntityDamageByEntityEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) return;
        if (!event.getDamager().getScoreboardTags().contains("confetti_true")) return;

        event.setDamage(
                event.getDamage() - (event.getDamage() * (getConfig().getInt("entity-damage-reduction") / 100.0)));
    }

    private boolean randomChance(int percent) {
        if (percent == 100) return true;
        if (percent == 0) return false;

        Random rand = new Random();
        int randInt = rand.nextInt(100) + 1;

        return randInt <= percent;
    }

}
