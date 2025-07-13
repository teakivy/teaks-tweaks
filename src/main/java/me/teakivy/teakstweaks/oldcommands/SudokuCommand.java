package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.utils.oldcommand.AbstractCommand;
import me.teakivy.teakstweaks.utils.oldcommand.CommandType;
import me.teakivy.teakstweaks.utils.oldcommand.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;

import java.util.List;

public class SudokuCommand extends AbstractCommand {

    public SudokuCommand() {
        super(CommandType.PLAYER_ONLY, "sudoku", "sudoku", Permission.COMMAND_SUDOKU, List.of("suicide"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        sendMessage("committed");
        event.getPlayer().getScoreboardTags().add("sudoku-message");
        event.getPlayer().setHealth(0);
    }
}
