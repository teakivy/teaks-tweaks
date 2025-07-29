package me.teakivy.teakstweaks.packs.countmobdeaths;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class CountMobDeaths extends BasePack {

    public CountMobDeaths() {
        super(TTPack.COUNT_MOB_DEATHS, Material.ECHO_SHARD);
    }

    Objective objective;

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) return;

        if (objective == null) {
            Scoreboard board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
            if (board.getObjective("mobDeathCount") == null) {
                board.registerNewObjective("mobDeathCount", Criteria.DUMMY, getText("objective_name"));
            }
            objective = board.getObjective("mobDeathCount");
        }


        String name = event.getEntity().getName();
        objective.getScore(name).setScore(objective.getScore(name).getScore() + 1);
    }

}
