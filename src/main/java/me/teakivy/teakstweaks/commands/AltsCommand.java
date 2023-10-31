package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teakstweaks.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.command.TabCompleteEvent;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AltsCommand extends AbstractCommand {

    public AltsCommand() {
        super("spectator-alts", "alts", "<add | remove | list> <alt> [player]", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (event.getArgsLength() < 1) {
            sendUsage();
            return;
        }

        String action = event.getArg(0);
        UUID main = player.getUniqueId();
        UUID secondary = null;

        if (event.getArgsLength() > 1) {
            secondary = Bukkit.getPlayerUniqueId(event.getArg(1));

            if (secondary == null) {
                sendError(ErrorType.PLAYER_DNE);
                return;
            }
        }

        if (event.getArgsLength() > 2) {
            if (!player.isOp()) {
                sendError("no_permission_modify_others");
                return;
            }

            main = Bukkit.getPlayerUniqueId(event.getArg(2));

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

        if (event.getArgsLength() < 2) {
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
                sendError("already_alt", Placeholder.parsed("alt", getName(secondary)));
                return;
            }

            SpectatorAlts.addAlt(main, secondary);
            sendMessage("added_alt", Placeholder.parsed("alt", getName(secondary)), Placeholder.parsed("player", getName(main)));
            return;
        }

        if (action.equals("remove")) {
            if (!SpectatorAlts.isAlt(secondary)) {
                sendError("not_alt", Placeholder.parsed("alt", getName(secondary)));
                return;
            }

            SpectatorAlts.removeAlt(secondary);
            sendMessage("removed_alt", Placeholder.parsed("alt", getName(secondary)), Placeholder.parsed("player", getName(main)));
            return;
        }

        sendUsage();
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.getArgsLength() == 1) return List.of("add", "remove", "list");

        if (event.getArgsLength() == 2) {
            if (event.getArg(0).equals("list")) {
                return List.of("<player>");
            }
            return List.of("<alt>");
        }

        if (event.getArgsLength() == 3) {
            if (event.getArg(0).equals("add") || event.getArg(0).equals("remove")) {
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
     */
    private void sendList(UUID account) {
        List<UUID> alts = SpectatorAlts.getAlts(account);

        if (alts.isEmpty()) {
            sendError("no_alts", Placeholder.parsed("player", getName(account)));
            return;
        }

        sendMessage("list_alts", Placeholder.parsed("player", getName(account)));
        for (UUID altAcc : alts) {
            sendMessage("listed_alt", Placeholder.parsed("alt", getName(altAcc)));
        }
    }

    /**
     * Checks if the given player can add another alt
     * @return Whether or not the player can add another alt
     */
    public boolean canAddAlt(Player player) {
        if (player.isOp()) return true;
        if (TeaksTweaks.getInstance().getConfig().getInt("packs.spectator-alts.max-alts") == -1) return true;
        return SpectatorAlts.getAlts(player.getUniqueId()).size() < TeaksTweaks.getInstance().getConfig().getInt("packs.spectator-alts.max-alts");
    }
}
