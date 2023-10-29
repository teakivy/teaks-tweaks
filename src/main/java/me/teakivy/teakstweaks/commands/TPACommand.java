package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.ErrorType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TPACommand extends AbstractCommand {

    private final List<TPARequest> requests = new ArrayList<>();

    public TPACommand() {
        super("tpa", "tpa", "/tpa", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage(getError("specify_player"));
            return;
        }

        if (args[0].equals("accept")) {
            if (args.length < 2) {
                TPARequest req = getRequest(player);
                if (req == null || req.isExpired()) {
                    player.sendMessage(getError("no_pending_requests"));
                    return;
                }

                req.accept();
                requests.remove(req);

                return;
            }

            Player from = Bukkit.getPlayer(args[1]);
            if (from == null) {
                player.sendMessage(ErrorType.PLAYER_DNE.m());
                return;
            }

            TPARequest req = getRequest(from, player);
            if (req == null || req.isExpired()) {
                player.sendMessage(getError("player_no_pending_requests"));
                return;
            }

            req.accept();
            requests.remove(req);

            return;
        }

        Player to = Bukkit.getPlayer(args[0]);

        if (to == null) {
            player.sendMessage(ErrorType.PLAYER_DNE.m());
            return;
        }

        TextComponent text = new TextComponent(getString("request_message").replace("%player%", player.getName()));
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(getString("request_message.hover"))));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa accept " + player.getName()));

        TPARequest req = new TPARequest(player, to);
        requests.add(req);

        to.spigot().sendMessage(text);
        player.sendMessage(getString("request_sent").replace("%player%", to.getName()));

        Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
            if (req.isAccepted()) return;

            player.sendMessage(getString("request_expired").replace("%player%", to.getName()));
            requests.remove(req);
        }, 61 * 20L);
    }

    @Override
    public List<String> tabComplete(Player player, String[] args) {
        if (args.length == 1) {
            if (args[0].isEmpty()) return List.of("accept", "<player>");

            List<String> arguments = new ArrayList<>();
            arguments.add("accept");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getUniqueId().equals(player.getUniqueId())) continue;

                arguments.add(p.getName());
            }

            return arguments;
        }

        if (args.length == 2 && args[0].equals("accept")) {
            List<String> arguments = new ArrayList<>();
            for (TPARequest request : requests) {
                if (!request.getTo().getUniqueId().equals(player.getUniqueId())) continue;

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
            to.sendMessage(getString("teleporting_to_you").replace("%player%", from.getName()));
            from.sendMessage(getString("teleporting").replace("%player%", to.getName()));
        }
    }
}
