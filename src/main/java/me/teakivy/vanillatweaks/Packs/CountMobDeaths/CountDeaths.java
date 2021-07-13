package me.teakivy.vanillatweaks.Packs.CountMobDeaths;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class CountDeaths implements Listener {

    Main main = Main.getPlugin(Main.class);

    Objective objective;

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (!main.getConfig().getBoolean("packs.count-mob-deaths.enabled")) return;
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

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
