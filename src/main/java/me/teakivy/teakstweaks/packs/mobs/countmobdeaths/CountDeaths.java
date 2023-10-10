package me.teakivy.teakstweaks.packs.mobs.countmobdeaths;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class CountDeaths extends BasePack {

    public CountDeaths() {
        super("Count Mob Deaths", "count-mob-deaths", PackType.MOBS, Material.ECHO_SHARD);
    }

    Objective objective;

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) return;
        if (event.getEntity().getCustomName() == null) return;

        if (objective == null) {
            Scoreboard board = Objects.requireNonNull(event.getEntity().getServer().getScoreboardManager()).getMainScoreboard();
            if (board.getObjective("mobDeathCount") == null) {
                board.registerNewObjective("mobDeathCount", "dummy", getString("objective_name"));
            }
            objective = board.getObjective("mobDeathCount");
        }


        String name = ChatColor.YELLOW + event.getEntity().getName();
        objective.getScore(name).setScore(objective.getScore(name).getScore() + 1);
    }

}
