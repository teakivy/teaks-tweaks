package me.teakivy.teakstweaks.packs.sudoku;

import me.teakivy.teakstweaks.commands.SudokuCommand;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.MM;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Sudoku extends BasePack {

    public Sudoku() {
        super("sudoku", PackType.TEAKSTWEAKS, Material.GOLDEN_SWORD, new SudokuCommand());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!event.getEntity().getScoreboardTags().contains("sudoku-message")) return;
        event.setDeathMessage(MM.toString(getText("death_message", insert("player", event.getEntity().getName()))));
        event.getEntity().getScoreboardTags().remove("sudoku-message");
    }
}
