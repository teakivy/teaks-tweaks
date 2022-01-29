package me.teakivy.teakstweaks.Packs.Mobs.CountMobDeaths;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class CountDeaths extends BasePack {

    public CountDeaths() {
        super("Count Mob Deaths", "count-mob-deaths");
    }

    Objective objective;

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) return;
        if (event.getEntity().getCustomName() == null) return;

        if (objective == null) {
            Scoreboard board = Objects.requireNonNull(event.getEntity().getServer().getScoreboardManager()).getMainScoreboard();
            if (board.getObjective("mobDeathCount") == null) {
                board.registerNewObjective("mobDeathCount", "dummy", ChatColor.GOLD + "Mob Deaths");
            }
            objective = board.getObjective("mobDeathCount");
        }


        String name = ChatColor.YELLOW + event.getEntity().getName();
        objective.getScore(name).setScore(objective.getScore(name).getScore() + 1);
    }

}
