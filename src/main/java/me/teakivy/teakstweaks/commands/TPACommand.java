package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MM;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TPACommand extends AbstractCommand {

    private final List<TPARequest> requests = new ArrayList<>();

    public TPACommand() {
        super(CommandType.PLAYER_ONLY, "tpa", "tpa", Permission.COMMAND_TPA, Arg.required("accept", "player"), Arg.optional("player"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (event.isArg(0, "accept")) {
            if (!event.hasArgs(2)) {
                TPARequest req = getRequest(player);
                if (req == null || req.isExpired()) {
                    sendError("no_pending_requests");
                    return;
                }

                req.accept();
                requests.remove(req);
                return;
            }

            Player from = Bukkit.getPlayer(event.getArg(1));
            if (from == null) {
                sendError(ErrorType.PLAYER_DNE);
                return;
            }

            TPARequest req = getRequest(from, player);
            if (req == null || req.isExpired()) {
                sendError("player_no_pending_requests");
                return;
            }

            req.accept();
            requests.remove(req);

            return;
        }

        Player to = Bukkit.getPlayer(event.getArg(0));

        if (to == null) {
            sendError(ErrorType.PLAYER_DNE);
            return;
        }

        String text = "<hover:show_text:\"" + getString("request_message.hover") + "\"><click:run_command:/tpa accept " + player.getName() + ">" + getString("request_message") + "</click></hover>";
        Component message = MiniMessage.miniMessage().deserialize(text, insert("player", player.getName()));

        TPARequest req = new TPARequest(player, to);
        requests.add(req);

        MM.player(to).sendMessage(message);
        sendMessage("request_sent", insert("player", to.getName()));

        Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
            if (req.isAccepted()) return;

            sendMessage("request_expired", insert("player", to.getName()));
            requests.remove(req);
        }, 61 * 20L);
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.isArg(0)) {
            if (event.getArg(0).isEmpty()) return List.of("accept", "<player>");

            List<String> arguments = new ArrayList<>();
            arguments.add("accept");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getUniqueId().equals(event.getPlayer().getUniqueId())) continue;

                arguments.add(p.getName());
            }

            return arguments;
        }

        if (event.isArg(2) && event.isArg(0, "accept")) {
            List<String> arguments = new ArrayList<>();
            for (TPARequest request : requests) {
                if (!request.getTo().getUniqueId().equals(event.getPlayer().getUniqueId())) continue;

                arguments.add(request.getFrom().getName());
            }

            return arguments;
        }
        return null;
    }

    private TPARequest getRequest(Player from, Player to) {
        for (TPARequest request : requests) {
            if (!request.getFrom().getUniqueId().equals(from.getUniqueId())) continue;
            if (!request.getTo().getUniqueId().equals(to.getUniqueId())) continue;

            return request;
        }
        return null;
    }

    private TPARequest getRequest(Player to) {
        TPARequest recent = null;
        for (TPARequest request : requests) {
            if (!request.getTo().getUniqueId().equals(to.getUniqueId())) continue;
            if (recent != null && request.getTime() <= recent.getTime()) continue;

            recent = request;
        }
        return recent;
    }


    class TPARequest {
        private final Player from;
        private final Player to;
        private final long time;
        private boolean accepted = false;
        public TPARequest(Player from, Player to) {
            this.from = from;
            this.to = to;
            this.time = System.currentTimeMillis();
        }

        public Player getFrom() {
            return from;
        }

        public Player getTo() {
            return to;
        }

        public long getTime() {
            return time;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - time > 60 * 1000L;
        }

        public boolean isAccepted() {
            return accepted;
        }

        public void accept() {
            accepted = true;
            Back.backLoc.put(to.getUniqueId(), to.getLocation());
            from.teleport(to.getLocation());
            MM.player(to).sendMessage(getText("teleporting_to_you", insert("player", from.getName())));
            MM.player(from).sendMessage(getText("teleporting", insert("player", to.getName())));
        }
    }
}
