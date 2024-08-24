package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teakstweaks.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AltsCommand extends AbstractCommand {

    public AltsCommand() {
        super(CommandType.PLAYER_ONLY, "spectator-alts", "alts", Permission.COMMAND_ALT, Arg.required("add", "remove", "list"), Arg.optional("alt"), Arg.optional("player"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        UUID main = player.getUniqueId();
        UUID secondary = null;

        String action = event.getArg(0);

        if (event.hasArgs(2)) {
            secondary = getUUID(event.getArg(1));

            if (secondary == null) {
                sendError(ErrorType.PLAYER_DNE);
                return;
            }
        }

        if (event.hasArgs(3)) {
            if (!checkPermission(Permission.COMMAND_ALT_MANAGE, true)) {
                sendError("no_permission_modify_others");
                return;
            }

            main = getUUID(event.getArg(2));

            if (main == null) {
                sendError(ErrorType.PLAYER_DNE);
                return;
            }
        }

        if (action.equals("list")) {
            if (secondary != null) main = secondary;

            sendList(main);
            return;
        }

        if (!event.hasArgs(2)) {
            sendUsage();
            return;
        }

        if (action.equals("add")) {
            if (!canAddAlt(player)) {
                sendError("max_alts");
                return;
            }

            if (main.equals(secondary)) {
                sendError("self");
                return;
            }

            for (OfflinePlayer whitelisted : Bukkit.getWhitelistedPlayers()) {
                if (whitelisted.getUniqueId().equals(secondary)) {
                    sendError("whitelisted");
                    return;
                }
            }

            if (SpectatorAlts.isAlt(secondary)) {
                sendError("already_alt", insert("alt", getName(secondary)));
                return;
            }

            SpectatorAlts.addAlt(main, secondary);
            sendMessage("added_alt", insert("alt", getName(secondary)), insert("player", getName(main)));
            return;
        }

        if (action.equals("remove")) {
            if (!SpectatorAlts.isAlt(secondary)) {
                sendError("not_alt", insert("alt", getName(secondary)));
                return;
            }

            SpectatorAlts.removeAlt(secondary);
            sendMessage("removed_alt", insert("alt", getName(secondary)), insert("player", getName(main)));
            return;
        }

        sendUsage();
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.isArg(0)) return List.of("add", "remove", "list");

        if (event.isArg(1)) {
            if (event.isArg(0, "list")) {
                return List.of("[player]");
            }
            return List.of("<alt>");
        }

        if (event.isArg(2)) {
            if (event.isArg(0, "add") || event.isArg(0, "remove")) {
                return List.of("[player]");
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
     */
    private void sendList(UUID account) {
        List<UUID> alts = SpectatorAlts.getAlts(account);

        if (alts.isEmpty()) {
            sendError("no_alts", insert("player", getName(account)));
            return;
        }

        sendMessage("list_alts", insert("player", getName(account)));
        for (UUID altAcc : alts) {
            sendMessage("listed_alt", insert("alt", getName(altAcc)));
        }
    }

    /**
     * Checks if the given player can add another alt
     * @return Whether or not the player can add another alt
     */
    public boolean canAddAlt(Player player) {
        if (player.isOp()) return true;
        if (getPackConfig().getInt("max-alts") == -1) return true;
        return SpectatorAlts.getAlts(player.getUniqueId()).size() < getPackConfig().getInt("max-alts");
    }

    private UUID getUUID(String name) {
        for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p.getUniqueId();
            }
        }
        return null;
    }
}
