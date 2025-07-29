package me.teakivy.teakstweaks.packs.trackstatistics;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class TrackStatistics extends BasePack {
    Scoreboard sb;

    Objective playTimeMinutes;
    Objective elytraKm;
    Objective swimKm;

    public TrackStatistics() {
        super(TTPack.TRACK_STATISTICS, Material.MAP);
    }

    @Override
    public void init() {
        super.init();

        sb = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        if (playTimeMinutes == null) {
            Objective playTimeMinutes_ = sb.getObjective("hc_playTime");
            if (playTimeMinutes_ == null) {
                sb.registerNewObjective("hc_playTime", "dummy", "Play Minutes");
            }
            playTimeMinutes = sb.getObjective("hc_playTime");
        }
        if (elytraKm == null) {
            Objective elytraKm_ = sb.getObjective("hc_elytraKm");
            if (elytraKm_ == null) {
                sb.registerNewObjective("hc_elytraKm", "dummy", "Elytra km");
            }
            elytraKm = sb.getObjective("hc_elytraKm");
        }
        if (swimKm == null) {
            Objective swimKm_ = sb.getObjective("hc_swimKm");
            if (swimKm_ == null) {
                sb.registerNewObjective("hc_swimKm", "dummy", "Swim km");
            }
            swimKm = sb.getObjective("hc_swimKm");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        setStatistic(event.getPlayer(), playTimeMinutes, event.getPlayer().getStatistic(Statistic.PLAY_ONE_MINUTE) / 1200);
        setStatistic(event.getPlayer(), elytraKm, event.getPlayer().getStatistic(Statistic.AVIATE_ONE_CM) / 100000);
        setStatistic(event.getPlayer(), swimKm, event.getPlayer().getStatistic(Statistic.SWIM_ONE_CM) / 100000);
    }

    public void setStatistic(Player player, Objective objective, int amount) {
        objective.getScore(player.getName()).setScore(amount);
    }

}
