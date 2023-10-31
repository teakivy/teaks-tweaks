package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import org.bukkit.entity.Player;

public class SudokuCommand extends AbstractCommand {

    public SudokuCommand() {
        super("sudoku", "sudoku", "/sudoku", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        player.sendMessage(getString("committed"));
        player.getScoreboardTags().add("sudoku-message");
        player.setHealth(0);
    }
}
