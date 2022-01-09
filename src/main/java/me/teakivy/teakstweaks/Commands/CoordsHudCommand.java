package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoordsHudCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public CoordsHudCommand() {
        super(MessageHandler.getCmdName("coordshud"), MessageHandler.getCmdUsage("coordshud"), MessageHandler.getCmdDescription("coordshud"), MessageHandler.getCmdAliases("coordshud"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.coords-hud.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(MessageHandler.getCmdMessage("coordshud", "proper-usage"));
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("toggle")) {
                if (!player.hasPermission("teakstweaks.coordshud.toggle")) {
                    sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                    return true;
                }
                if (main.getConfig().getBoolean("packs.coords-hud.force-enable")) {
                    player.sendMessage(MessageHandler.getCmdMessage("coordshud", "cant-toggle"));
                    return true;
                }
                if (!Main.chEnabled.contains(player.getUniqueId())) Main.chEnabled.add(player.getUniqueId());
                else Main.chEnabled.remove(player.getUniqueId());
                player.sendMessage(MessageHandler.getCmdMessage("coordshud", "toggled"));
            } else {
                sender.sendMessage(MessageHandler.getCmdMessage("coordshud", "proper-usage"));
            }
        } else {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
        }
        return false;
    }

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("toggle");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}