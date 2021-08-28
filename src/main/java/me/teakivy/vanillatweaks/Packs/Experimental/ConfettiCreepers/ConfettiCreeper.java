package me.teakivy.vanillatweaks.Packs.Experimental.ConfettiCreepers;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class ConfettiCreeper implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onPrime(ExplosionPrimeEvent event) {
        System.out.println("test1");
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.CREEPER) return;
        System.out.println("test2");
        if (entity.getScoreboardTags().contains("vt_confetti_true") || entity.getScoreboardTags().contains("vt_confetti_false")) return;
        System.out.println("test3");

        int chance = main.getConfig().getInt("packs.confetti-creepers.confetti-chance");
        boolean confetti = randomChance(chance);

        if (!confetti) {
            entity.addScoreboardTag("vt_confetti_false");
            return;
        }
        System.out.println("test4");
        entity.addScoreboardTag("vt_confetti_true");
        if (!main.getConfig().getBoolean("packs.confetti-creepers.do-block-damage")) {
            event.setRadius(0);
        }
        System.out.println("test5");

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

        Firework fw = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwMeta = fw.getFireworkMeta();
        fwMeta.setDisplayName("Confetti");
        fwMeta.addEffect(fwEffect);
        fwMeta.setPower(0);
        fw.setFireworkMeta(fwMeta);
        fw.detonate();
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.CREEPER) return;
        boolean confetti = entity.getScoreboardTags().contains("vt_confetti_true");

        if (!confetti) return;
    }

    @EventHandler public void onExplosionDamage(EntityDamageByEntityEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            if (event.getDamager().getScoreboardTags().contains("vt_confetti_true")) {
                if (main.getConfig().getInt("packs.confetti-creepers.entity-damage-reduction") > 1) {
                    double damage = (event.getDamage() * (100 - main.getConfig().getInt("packs.confetti-creepers.entity-damage-reduction"))) * .01;
                    event.setDamage(damage);
                    event.setCancelled(true);
                } else if (main.getConfig().getInt("packs.confetti-creepers.entity-damage-reduction") > 99) {
                    event.setDamage(0);
                    event.setCancelled(true);
                }
            }
        }
    }

    private boolean randomChance(int percent) {
        if (percent == 100) return true;
        if (percent == 0) return false;

        Random rand = new Random();
        int randInt = rand.nextInt(100) + 1;

        return randInt <= percent;
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
