package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.packs.tpa.TPAHandler;
import me.teakivy.teakstweaks.packs.tpa.TPARequest;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.oldcommand.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TPAHereCommand extends AbstractCommand {

    public TPAHereCommand() {
        super(CommandType.PLAYER_ONLY, "tpa", "tpahere", Permission.COMMAND_TPA, "tpa", Arg.required("accept", "cancel", "player"), Arg.optional("player"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player from = event.getPlayer();
        Player to = null;

        if (event.isArg(0, "accept")) {
            if (event.getArgs().length < 2) {
                sendUsage();
                return;
            }

            to = Bukkit.getPlayer(event.getArg(1));
            if (to == null) {
                sendError(ErrorType.PLAYER_DNE);
                return;
            }

            TPARequest request = TPAHandler.getRequest(to, from);
            if (request == null) {
                sendError("no_pending_requests");
                return;
            }
            TPAHandler.acceptRequest(request);
            return;
        }

        if (event.isArg(0, "cancel")) {
            if (event.getArgs().length > 1) {
                sendUsage();
                return;
            }

            TPARequest request = TPAHandler.getOutgoingRequest(from);
            if (request == null) {
                sendError("no_outgoing_request");
                return;
            }
            TPAHandler.cancelRequest(request);
            return;
        }

        if (event.getArgs().length > 1) {
            sendUsage();
            return;
        }

        to = Bukkit.getPlayer(event.getArg(0));
        if (to == null) {
            sendError(ErrorType.PLAYER_DNE);
            return;
        }

        if (TPAHandler.hasOutgoingRequest(from)) {
            sendError("has_outgoing_request");
            return;
        }

        TPARequest request = new TPARequest(from, to, TPARequest.TPAType.TPAHERE);
        TPAHandler.sendRequest(request);
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.isArg(0)) {
            if (event.getArg(0).isEmpty()) return List.of("accept", "cancel", "<player>");

            List<String> arguments = new ArrayList<>();
            arguments.add("accept");
            arguments.add("cancel");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getUniqueId().equals(event.getPlayer().getUniqueId())) continue;

                arguments.add(p.getName());
            }

            return arguments;
        }

        if (event.isArg(1) && event.isArg(0, "accept")) {
            List<String> arguments = new ArrayList<>();
            for (TPARequest request : TPAHandler.getPendingRequests(event.getPlayer())) {
                arguments.add(request.getSender().getName());
            }

            return arguments;
        }

        if (event.isArg(1) && event.isArg(0, "cancel")) {
            return List.of();
        }
        return null;
    }
}
