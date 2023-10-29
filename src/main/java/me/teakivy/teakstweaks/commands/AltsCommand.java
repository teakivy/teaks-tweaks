package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teakstweaks.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AltsCommand extends AbstractCommand {

    public AltsCommand() {
        super("spectator-alts", "alts", "/alts <add|remove|list> <alt> [player]", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 1) {
            sendUsage(player);
            return;
        }

        String action = args[0];
        UUID main = player.getUniqueId();
        UUID secondary = null;

        if (args.length > 1) {
            secondary = Bukkit.getPlayerUniqueId(args[1]);

            if (secondary == null) {
                player.sendMessage(ErrorType.PLAYER_DNE.m());
                return;
            }
        }

        if (args.length > 2) {
            if (!player.isOp()) {
                player.sendMessage(getError("no_permission_modify_others"));
                return;
            }

            main = Bukkit.getPlayerUniqueId(args[2]);

            if (main == null) {
                player.sendMessage(ErrorType.PLAYER_DNE.m());
                return;
            }
        }

        if (action.equals("list")) {
            if (secondary != null) main = secondary;

            sendList(main, player);
            return;
        }

        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        if (action.equals("add")) {
            if (!canAddAlt(player)) {
                player.sendMessage(getError("max_alts"));
                return;
            }

            if (main.equals(secondary)) {
                player.sendMessage(getError("self"));
                return;
            }

            for (OfflinePlayer whitelisted : Bukkit.getWhitelistedPlayers()) {
                if (whitelisted.getUniqueId().equals(secondary)) {
                    player.sendMessage(getError("whitelisted"));
                    return;
                }
            }

            if (SpectatorAlts.isAlt(secondary)) {
                player.sendMessage(getError("already_alt").replace("%alt%", getName(secondary)));
                return;
            }

            SpectatorAlts.addAlt(main, secondary);
            player.sendMessage(getString("added_alt").replace("%alt%", getName(secondary)).replace("%player%", getName(main)));
            return;
        }

        if (action.equals("remove")) {
            if (!SpectatorAlts.isAlt(secondary)) {
                player.sendMessage(getError("not_alt").replace("%alt%", getName(secondary)));
                return;
            }

            SpectatorAlts.removeAlt(secondary);
            player.sendMessage(getString("removed_alt").replace("%alt%", getName(secondary)).replace("%player%", getName(main)));
            return;
        }

        sendUsage(player);
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return List.of("add", "remove", "list");

        if (args.length == 2) {
            if (args[0].equals("list")) {
                return List.of("<player>");
            }
            return List.of("<alt>");
        }

        if (args.length == 3) {
            if (args[0].equals("add") || args[0].equals("remove")) {
                return List.of("<player>");
            }
        }

        return null;
    }

    /**
     * Gets the name of the given UUID
     * @param uuid The UUID to get the name of
     * @return The name of the given UUID
     */
    private String getName(UUID uuid) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        if (player == null || player.getName() == null) return uuid.toString();
        return player.getName();
    }

    /**
     * Sends a list of alts for the given account to the given sender
     * @param account The account to list the alts of
     * @param sender The sender to send the list to
     */
    private void sendList(UUID account, CommandSender sender) {
        List<UUID> alts = SpectatorAlts.getAlts(account);

        if (alts.size() == 0) {
            sender.sendMessage(getError("no_alts").replace("%player%", getName(account)));
            return;
        }

        sender.sendMessage(getString("list_alts").replace("%player%", getName(account)));
        for (UUID altAcc : alts) {
            sender.sendMessage(getString("listed_alt").replace("%alt%", getName(altAcc)));
        }
    }

    /**
     * Checks if the given player can add another alt
     * @param player The player to check
     * @return Whether or not the player can add another alt
     */
    public boolean canAddAlt(Player player) {
        if (player.isOp()) return true;
        if (TeaksTweaks.getInstance().getConfig().getInt("packs.spectator-alts.max-alts") == -1) return true;
        return SpectatorAlts.getAlts(player.getUniqueId()).size() < TeaksTweaks.getInstance().getConfig().getInt("packs.spectator-alts.max-alts");
    }
}
