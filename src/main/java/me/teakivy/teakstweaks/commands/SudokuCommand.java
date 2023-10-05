package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudokuCommand extends AbstractCommand {

    public SudokuCommand() {
        super("sudoku", "sudoku", "/sudoku", "Commit Sudoku!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }
        player.getScoreboardTags().add("sudoku-message");
        player.sendMessage(getString("committed"));
        player.setHealth(0);
        return false;
    }
}
