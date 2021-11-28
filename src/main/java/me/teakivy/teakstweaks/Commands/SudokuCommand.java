package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudokuCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public SudokuCommand() {
        super(MessageHandler.getCmdName("sudoku"), MessageHandler.getCmdUsage("sudoku"), MessageHandler.getCmdDescription("sudoku"), MessageHandler.getCmdAliases("sudoku"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        player.getScoreboardTags().add("sudoku-message");
        player.sendMessage(vt + ChatColor.GREEN + "Sudoku!");
        player.setHealth(0);
        return false;
    }
}
