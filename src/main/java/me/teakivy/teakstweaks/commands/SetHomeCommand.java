package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class SetHomeCommand extends AbstractCommand {

    public SetHomeCommand() {
        super("homes", MessageHandler.getCmdName("sethome"), MessageHandler.getCmdUsage("sethome"), MessageHandler.getCmdDescription("sethome"), MessageHandler.getCmdAliases("sethome"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        List<Home> homes = HomesPack.getHomes(player);

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (args.length < 1 && HomesPack.getHome(player, "home") != null) {
            player.sendMessage(ChatColor.RED + "You must specify a name for your home.");
            return true;
        }

        String name = args.length < 1 ? "home" : args[0].toLowerCase();

        if (HomesPack.getHome(player, name) != null) {
            player.sendMessage(ChatColor.RED + "You already have a home with that name.");
            return true;
        }

        int maxHomes = Main.getInstance().getConfig().getInt("packs.homes.max-homes");
        if (maxHomes > 0 && homes.size() >= maxHomes) {
            player.sendMessage(ChatColor.RED + "You have reached the maximum amount of homes you can set.");
            return true;
        }

        if (HomesPack.setHome(player, name, player.getLocation())) {
            player.sendMessage(ChatColor.GREEN + "Home set!");
        } else {
            player.sendMessage(ChatColor.RED + "An error occurred while setting your home.");
        }
        return true;
    }
}
