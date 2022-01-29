package me.teakivy.teakstweaks.Packs.TeaksTweaks.Sudoku;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Sudoku extends BasePack {

    public Sudoku() {
        super("Sudoku", "sudoku");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!event.getEntity().getScoreboardTags().contains("sudoku-message")) return;
        event.setDeathMessage(event.getEntity().getName() + " committed Sudoku");
        event.getEntity().getScoreboardTags().remove("sudoku-message");
    }
}
