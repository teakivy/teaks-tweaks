package me.teakivy.teakstweaks.packs.alwaysactivebeacons;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.*;
import org.bukkit.block.Beacon;
import org.bukkit.block.BlockState;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.List;

public class AlwaysActiveBeacons extends BasePack {

    public AlwaysActiveBeacons() {
        super("always-active-beacons", Material.BEACON);
    }

    public void init() {
        super.init();
        start();
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Chunk chunk : world.getLoadedChunks()) {
                        for (BlockState state : chunk.getTileEntities()) {
                            if (state.getType() != Material.BEACON) continue;

                            Beacon beacon = (Beacon) state.getBlock().getState();
                            if (beacon.getTier() == 0) continue;

                            List<LivingEntity> entities = (List<LivingEntity>) beacon.getEntitiesInRange();
                            PotionEffect primaryEffect = beacon.getPrimaryEffect();
                            PotionEffect secondaryEffect = beacon.getSecondaryEffect();

                            for (LivingEntity entity : entities) {
                                if (entity instanceof Player player) {
                                    if (primaryEffect != null) {
                                        player.addPotionEffect(primaryEffect);
                                    }
                                    if (secondaryEffect != null) {
                                        player.addPotionEffect(secondaryEffect);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(TeaksTweaks.getInstance(), 0L, 100L); // every 5 seconds
    }
}
