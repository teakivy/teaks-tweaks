package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoordsHudCommand extends AbstractCommand {

    public CoordsHudCommand() {
        super("coords-hud", "coordshud", "/coordshud", "Coordinates Hud Main Command", List.of("ch"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!TeaksTweaks.getInstance().getConfig().getBoolean("packs.coords-hud.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(permission+".toggle")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (TeaksTweaks.getInstance().getConfig().getBoolean("packs.coords-hud.force-enable")) {
            player.sendMessage(getString("error.force_enabled"));
            return true;
        }

        if (!TeaksTweaks.chEnabled.contains(player.getUniqueId())) {
            TeaksTweaks.chEnabled.add(player.getUniqueId());
        } else {
            TeaksTweaks.chEnabled.remove(player.getUniqueId());
        }

        player.sendMessage(getString("toggled"));
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