package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoordsHudCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public CoordsHudCommand() {
        super("coords-hud", "coordshud", "/coordshud toggle", "Coordinates Hud Main Command", List.of("ch"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.coords-hud.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(getUsage());
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("toggle")) {
                if (!player.hasPermission(permission+".toggle")) {
                    sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                    return true;
                }
                if (main.getConfig().getBoolean("packs.coords-hud.force-enable")) {
                    player.sendMessage(getString("error.force_enabled"));
                    return true;
                }
                if (!Main.chEnabled.contains(player.getUniqueId())) Main.chEnabled.add(player.getUniqueId());
                else Main.chEnabled.remove(player.getUniqueId());
                player.sendMessage(getString("toggled"));
            } else {
                sender.sendMessage(getUsage());
            }
        } else {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
        }
        return false;
    }

    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("toggle");
        }

        List<String> result = new ArrayList<>();
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