package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Packs.Teleportation.Back.Back;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
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
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    HashMap<Player, Player> requests = new HashMap<>();

    public TPACommand() {
        super(MessageHandler.getCmdName("tpa"), MessageHandler.getCmdUsage("tpa"), MessageHandler.getCmdDescription("tpa"), MessageHandler.getCmdAliases("tpa"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.tpa.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (!sender.hasPermission("teakstweaks.tpa.use")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(MessageHandler.getCmdMessage("tpa", "error.missing-player"));
            return true;
        }

        if (args[0].equalsIgnoreCase("confirm")) {
            if (args.length < 2) {
                player.sendMessage(MessageHandler.getCmdMessage("tpa", "error.missing-player-confirm"));
                return true;
            }
            Player confirmant = Bukkit.getPlayer(args[1]);
            if (confirmant == null) {
                player.sendMessage(MessageHandler.getCmdMessage("tpa", "error.player-doesnt-exist").replace("%name%", args[1]));
                return true;
            }
            if (!requests.containsKey(player)) return true;
            if (requests.get(player).getName().equals(confirmant.getName())) {
                Back.backLoc.put(confirmant.getUniqueId(), confirmant.getLocation());
                confirmant.teleport(player.getLocation());
                requests.remove(player);
                confirmant.sendMessage(MessageHandler.getCmdMessage("tpa", "teleporting-to").replace("%name%", player.getName()));
                player.sendMessage(MessageHandler.getCmdMessage("tpa", "teleporting-from").replace("%name%", confirmant.getName()));
                return true;
            }
        }

        Player player2 = Bukkit.getPlayer(args[0]);

        if (player2 == null) {


            player.sendMessage(MessageHandler.getCmdMessage("tpa", "error.player-doesnt-exist").replace("%name%", args[0]));
            return true;
        }
        String tpCommand = MessageHandler.getCmdMessage("tpa", "teleport-request.click").replace("%name%", player.getName());

        TextComponent text = new TextComponent(MessageHandler.getCmdMessage("tpa", "teleport-request.text").replace("%name%", player.getName()));
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(MessageHandler.getCmdMessage("tpa", "teleport-request.hover"))));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, tpCommand));

        requests.put(player2, player);

        player2.spigot().sendMessage(text);
        player.sendMessage(MessageHandler.getCmdMessage("tpa", "request-sent"));

        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
            if (requests.containsKey(player2)) {
                player.sendMessage(MessageHandler.getCmdMessage("tpa", "request-cancelled"));
                requests.remove(player2);
            }
        }, 60 * 20L);
        return false;
    }

    List<String> arguments1 = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments1.isEmpty() && sender.isOp()) {
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
}
