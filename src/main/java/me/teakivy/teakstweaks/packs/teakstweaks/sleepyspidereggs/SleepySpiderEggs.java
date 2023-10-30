package me.teakivy.teakstweaks.packs.teakstweaks.sleepyspidereggs;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class SleepySpiderEggs extends BasePack {

    public SleepySpiderEggs() {
        super("sleepy-spider-eggs", PackType.TEAKSTWEAKS, Material.SPIDER_EYE);
    }

    @EventHandler
    public void onSleep(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getTime() > 10000) return;

        player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10).forEach(entity -> {
            if (entity.getType() != EntityType.SPIDER) return;

            player.setExp(player.getExp() + (int) (Math.random() * 5) + 1);
        });
    }
}
