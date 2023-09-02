package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.teakstweaks.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AltsCommand extends AbstractCommand {

    public AltsCommand() {
        super("spectator-alts", "alts", "/alts <add/remove/list> <alt> [player]", "Manage spectator alt accounts");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: " + this.usage);
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }

        String action = args[0];
        UUID main = ((Player) sender).getUniqueId();
        UUID alt = null;

        if (action.equalsIgnoreCase("list") && args.length > 1) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to see other's alts!");
                return true;
            }

            main = Bukkit.getPlayerUniqueId(args[1]);

            if (main == null) {
                sender.sendMessage(ChatColor.RED + "That player does not exist!");
                return true;
            }
        }

        if (action.equalsIgnoreCase("list")) {
            List<UUID> alts = SpectatorAlts.getAlts(main);

            if (alts.size() == 0) {
                sender.sendMessage(ChatColor.RED + getName(main) + " does not have any alts!");
                return true;
            }

            sender.sendMessage(ChatColor.GOLD + getName(main) + ChatColor.YELLOW + "'s Alts:");
            for (UUID altAcc : alts) {
                sender.sendMessage(ChatColor.GRAY + " - " + ChatColor.YELLOW + getName(altAcc));
            }

            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: " + this.usage);
            return true;
        }

        alt = Bukkit.getPlayerUniqueId(args[1]);

        if (alt == null) {
            sender.sendMessage(ChatColor.RED + "That player does not exist!");
            return true;
        }

        if (args.length > 2) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to modify other's alts!");
                return true;
            }
            main = Bukkit.getPlayerUniqueId(args[2]);

            if (main == null) {
                sender.sendMessage(ChatColor.RED + "That player does not exist!");
                return true;
            }
        }

        if (action.equalsIgnoreCase("add")) {
            if (!sender.isOp() && Main.getInstance().getConfig().getInt("packs.spectator-alts.max-alts") != -1) {
                if (SpectatorAlts.getAlts(main).size() >= Main.getInstance().getConfig().getInt("packs.spectator-alts.max-alts")) {
                    sender.sendMessage(ChatColor.RED + "You cannot add any more alts!");
                    return true;
                }
            }

            if (main.equals(alt)) {
                sender.sendMessage(ChatColor.RED + "You cannot add yourself as an alt!");
                return true;
            }

            for (OfflinePlayer player : Bukkit.getWhitelistedPlayers()) {
                if (player.getUniqueId().equals(alt)) {
                    sender.sendMessage(ChatColor.RED + "You cannot add a whitelisted player as an alt!");
                    return true;
                }
            }

            if (SpectatorAlts.isAlt(alt)) {
                sender.sendMessage(ChatColor.RED + getName(alt) + " is already an alt!");
                return true;
            }

            SpectatorAlts.addAlt(main, alt);
            sender.sendMessage(ChatColor.GREEN + getName(alt) + " is now " + getName(main) + "'s alt!");
            return true;
        }

        if (action.equalsIgnoreCase("remove")) {
            if (!SpectatorAlts.isAlt(alt)) {
                sender.sendMessage(ChatColor.RED + getName(alt) + " is not an alt!");
                return true;
            }

            SpectatorAlts.removeAlt(alt);
            sender.sendMessage(ChatColor.GREEN + getName(alt) + " is no longer " + getName(main) + "'s alt!");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Usage: " + this.usage);
        return true;
    }

    private String getName(UUID uuid) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        if (player == null || player.getName() == null) return uuid.toString();
        return player.getName();
    }

    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {


        if (args.length == 1) {
            arguments.add("list");
            arguments.add("add");
            arguments.add("remove");
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("list")) {
                return List.of("<player>");
            } else {
                return List.of("<alt>");
            }
        }

        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
                return List.of("<player>");
            }
        }

        List<String> result = new ArrayList<String>();
        for (String a : arguments) {
            if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                result.add(a);
        }
        arguments.clear();
        return result;
    }
}
