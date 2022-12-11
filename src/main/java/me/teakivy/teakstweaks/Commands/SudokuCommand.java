package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudokuCommand extends AbstractCommand {


    public SudokuCommand() {
        super(MessageHandler.getCmdName("sudoku"), MessageHandler.getCmdUsage("sudoku"), MessageHandler.getCmdDescription("sudoku"), MessageHandler.getCmdAliases("sudoku"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("teakstweaks.sudoku.execute")) return false;
        player.getScoreboardTags().add("sudoku-message");
        player.sendMessage(ChatColor.GREEN + "Sudoku!");
        player.setHealth(0);
        return false;
    }
}
