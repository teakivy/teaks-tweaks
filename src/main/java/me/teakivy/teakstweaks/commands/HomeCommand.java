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
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class HomeCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public HomeCommand() {
        super("homes", MessageHandler.getCmdName("home"), MessageHandler.getCmdUsage("home"), MessageHandler.getCmdDescription("home"), MessageHandler.getCmdAliases("home"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            List<Home> homes = HomesPack.getHomes(player);

            if (homes.size() == 0 && HomesPack.getHome(player, "home") == null) {
                player.sendMessage(ChatColor.RED + "You have no homes set.");
                return true;
            }

            Home home = HomesPack.getHome(player, "home");
            if (home == null) {
                home = homes.get(0);
            }

            home.teleport();
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            List<Home> homes = HomesPack.getHomes(player);

            if (args.length < 2 && HomesPack.getHome(player, "home") != null) {
                player.sendMessage(ChatColor.RED + "You must specify a name for your home.");
                return true;
            }
            if (!sender.hasPermission(permission+".set")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            String name = args.length < 2 ? "home" : args[1].toLowerCase();

            if (HomesPack.getHome(player, name) != null) {
                player.sendMessage(ChatColor.RED + "You already have a home with that name.");
                return true;
            }

            int maxHomes = main.getConfig().getInt("packs.homes.max-homes");
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

        if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("remove")) {
            if (args.length < 2 && HomesPack.getHome(player, "home") == null) {
                player.sendMessage(ChatColor.RED + "You must specify a name for your home.");
                return true;
            }
            if (!sender.hasPermission(permission+".delete")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            String name = args.length < 2 ? "home" : args[1].toLowerCase();

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

        String name = args[0].toLowerCase();
        Home home = HomesPack.getHome(player, name);

        if (home == null) {
            player.sendMessage(ChatColor.RED + "You do not have a home with that name.");
            return true;
        }

        home.teleport();
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        List<String> arguments1 = new ArrayList<String>();
        List<String> arguments2 = new ArrayList<String>();

        Player player = (Player) sender;

        arguments1.add("set");
        arguments1.add("delete");

        List<Home> homes = HomesPack.getHomes(player);
        for (Home home : homes) {
            arguments2.add(home.getName());
            arguments1.add(home.getName());
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2) {
            for (String a : arguments2) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }


}
