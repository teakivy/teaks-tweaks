package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.teakstweaks.chatcolors.ChatColors;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TPACommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    List<TPARequest> requests = new ArrayList<>();

    public TPACommand() {
        super("tpa", MessageHandler.getCmdName("tpa"), MessageHandler.getCmdUsage("tpa"), MessageHandler.getCmdDescription("tpa"), MessageHandler.getCmdAliases("tpa"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Please specify a player to teleport to.");
            return true;
        }

        if (args[0].equalsIgnoreCase("accept")) {
            if (args.length < 2) {
                TPARequest req = getRequest(player);
                if (req == null || req.isExpired()) {
                    player.sendMessage(ChatColor.RED + "You have no pending requests.");
                    return true;
                }

                req.accept();
                requests.remove(req);

                return true;
            }
            Player from = Bukkit.getPlayer(args[1]);
            if (from == null) {
                player.sendMessage(ChatColor.RED + "That player is not online.");
                return true;
            }

            TPARequest req = getRequest(from, player);
            if (req == null || req.isExpired()) {
                player.sendMessage(ChatColor.RED + "That player has no pending requests.");
                return true;
            }

            req.accept();
            requests.remove(req);

            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        Player player2 = Bukkit.getPlayer(args[0]);

        if (player2 == null) {
            player.sendMessage(ChatColor.RED + "That player is not online.");
            return true;
        }
        String tpCommand = "/tpa accept " + player.getName();

        TextComponent text = new TextComponent(ChatColor.GOLD + player.getName() + ChatColor.YELLOW + " has requested to teleport to you! " + ChatColor.GOLD + "Click to accept");
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GOLD + "Click to accept")));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, tpCommand));

        TPARequest req = new TPARequest(player, player2);
        requests.add(req);

        player2.spigot().sendMessage(text);
        player.sendMessage(ChatColor.YELLOW + "You have requested to teleport to " + ChatColor.GOLD + player2.getName() + ChatColor.YELLOW + ". They have 60 seconds to accept.");

        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
            if (req.isExpired()) {
                player.sendMessage(ChatColor.RED + "Your request to teleport to " + ChatColor.GOLD + player2.getName() + ChatColor.RED + " has expired.");
                requests.remove(req);
            }
        }, 61 * 20L);
        return false;
    }

    List<String> arguments1 = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments1.isEmpty()) {
            arguments1.add("accept");
            for (Player player : Bukkit.getOnlinePlayers()) {
                arguments1.add(player.getName());
            }
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return null;
    }

    private TPARequest getRequest(Player from, Player to) {
        for (TPARequest request : requests) {
            if (request.getFrom().getName().equals(from.getName()) && request.getTo().getName().equals(to.getName())) {
                return request;
            }
        }
        return null;
    }

    private TPARequest getRequest(Player to) {
        TPARequest recent = null;
        for (TPARequest request : requests) {
            if (request.getTo().getName().equals(to.getName())) {
                if (recent == null) {
                    recent = request;
                } else {
                    if (request.getTime() > recent.getTime()) {
                        recent = request;
                    }
                }
            }
        }
        return recent;
    }


    class TPARequest {
        private Player from;
        private Player to;
        private long time;
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

        public void accept() {
            Back.backLoc.put(to.getUniqueId(), to.getLocation());
            to.teleport(from.getLocation());
            to.sendMessage(ChatColor.YELLOW + "Teleporting " + ChatColor.GOLD + from.getName() + ChatColor.YELLOW + " to you...");
            from.sendMessage(ChatColor.YELLOW + "Teleporting you to " + ChatColor.GOLD + to.getName() + ChatColor.YELLOW + "...");
        }
    }
}
