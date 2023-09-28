package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DeleteHomeCommand extends AbstractCommand {

    public DeleteHomeCommand() {
        super("homes", "delhome", "/deletehome <home>", "Delete a home", List.of("rmhome"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        Player player = (Player) sender;
        if (args.length < 1 && HomesPack.getHome(player, "home") == null) {
            player.sendMessage(ChatColor.RED + "You must specify a name for your home.");
            return true;
        }
        String name = args.length < 1 ? "home" : args[0].toLowerCase();

        Home home = HomesPack.getHome(player, name);
        if (home == null) {
            player.sendMessage(ChatColor.RED + "You do not have a home with that name.");
            return true;
        }

        if (!HomesPack.removeHome(player, name)) {
            player.sendMessage(ChatColor.RED + "An error occurred while removing your home.");
        }
        return true;
    }
}
