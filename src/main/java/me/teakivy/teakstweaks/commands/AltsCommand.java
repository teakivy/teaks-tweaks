package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teakstweaks.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.Bukkit;
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
            sender.sendMessage(getUsage());
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
                sender.sendMessage(getString("error.no_permission_list_others"));
                return true;
            }

            main = Bukkit.getPlayerUniqueId(args[1]);

            if (main == null) {
                sender.sendMessage(getString("player_dne"));
                return true;
            }
        }

        if (action.equalsIgnoreCase("list")) {
            List<UUID> alts = SpectatorAlts.getAlts(main);

            if (alts.size() == 0) {
                sender.sendMessage(getString("error.no_alts").replace("%player%", getName(main)));
                return true;
            }

            sender.sendMessage(getString("list_alts").replace("%player%", getName(main)));
            for (UUID altAcc : alts) {
                sender.sendMessage(getString("listed_alt").replace("%alt%", getName(altAcc)));
            }

            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(getUsage());
            return true;
        }

        alt = Bukkit.getPlayerUniqueId(args[1]);

        if (alt == null) {
            sender.sendMessage(ErrorType.PLAYER_DNE.m());
            return true;
        }

        if (args.length > 2) {
            if (!sender.isOp()) {
                sender.sendMessage(getString("error.no_permission_modify_others"));
                return true;
            }
            main = Bukkit.getPlayerUniqueId(args[2]);

            if (main == null) {
                sender.sendMessage(ErrorType.PLAYER_DNE.m());
                return true;
            }
        }

        if (action.equalsIgnoreCase("add")) {
            if (!sender.isOp() && TeaksTweaks.getInstance().getConfig().getInt("packs.spectator-alts.max-alts") != -1) {
                if (SpectatorAlts.getAlts(main).size() >= TeaksTweaks.getInstance().getConfig().getInt("packs.spectator-alts.max-alts")) {
                    sender.sendMessage(getString("error.max_alts"));
                    return true;
                }
            }

            if (main.equals(alt)) {
                sender.sendMessage(getString("error.self"));
                return true;
            }

            for (OfflinePlayer player : Bukkit.getWhitelistedPlayers()) {
                if (player.getUniqueId().equals(alt)) {
                    sender.sendMessage(getString("error.whitelisted"));
                    return true;
                }
            }

            if (SpectatorAlts.isAlt(alt)) {
                sender.sendMessage(getString("error.already_alt").replace("%alt%", getName(alt)));
                return true;
            }

            SpectatorAlts.addAlt(main, alt);
            sender.sendMessage(getString("added_alt").replace("%alt%", getName(alt)).replace("%player%", getName(main)));
            return true;
        }

        if (action.equalsIgnoreCase("remove")) {
            if (!SpectatorAlts.isAlt(alt)) {
                sender.sendMessage(getString("error.not_alt").replace("%alt%", getName(alt)));
                return true;
            }

            SpectatorAlts.removeAlt(alt);
            sender.sendMessage(getString("removed_alt").replace("%alt%", getName(alt)).replace("%player%", getName(main)));
            return true;
        }

        sender.sendMessage(getUsage());
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

        List<String> result = new ArrayList<>();
        for (String a : arguments) {
            if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                result.add(a);
        }
        arguments.clear();
        return result;
    }
}
