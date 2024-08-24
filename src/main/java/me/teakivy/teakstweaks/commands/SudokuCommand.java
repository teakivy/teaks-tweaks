package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;

public class SudokuCommand extends AbstractCommand {

    public SudokuCommand() {
        super(CommandType.PLAYER_ONLY, "sudoku", "sudoku", Permission.COMMAND_SUDOKU);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        sendMessage("committed");
        event.getPlayer().getScoreboardTags().add("sudoku-message");
        event.getPlayer().setHealth(0);
    }
}
