package me.teakivy.teakstweaks.Packs.TeaksTweaks.Sudoku;

import me.teakivy.teakstweaks.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Sudoku implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!event.getEntity().getScoreboardTags().contains("sudoku-message")) return;
        event.setDeathMessage(event.getEntity().getName() + " committed Sudoku");
        event.getEntity().getScoreboardTags().remove("sudoku-message");
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }
}
