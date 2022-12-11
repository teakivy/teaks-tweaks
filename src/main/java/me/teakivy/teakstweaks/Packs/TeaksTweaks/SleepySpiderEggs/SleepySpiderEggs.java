package me.teakivy.teakstweaks.Packs.TeaksTweaks.SleepySpiderEggs;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class SleepySpiderEggs extends BasePack {

    public SleepySpiderEggs() {
        super("Sleepy Spider Eggs", "sleepy-spider-eggs");
    }

    @EventHandler
    public void onSleep(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        if (!player.getWorld().isDayTime()) return;

        player.getLocation().getNearbyEntities(10, 10, 10).forEach(entity -> {
            if (entity.getType() != EntityType.SPIDER) return;

            player.setExp(player.getExp() + (int) (Math.random() * 5) + 1);
        });
    }
}
