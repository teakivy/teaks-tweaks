package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.survival.afkdisplay.AFK;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AFKCommand extends AbstractCommand {

    public AFKCommand() {
        super("afk-display", "afk", "/afk [uninstall]", "Toggles AFK mode. When enabled, you will be automatically set to AFK when you are idle for 5 minutes.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!TeaksTweaks.getInstance().getConfig().getBoolean("packs.afk-display.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (args.length < 1) {
            if (!TeaksTweaks.getInstance().getConfig().getBoolean("packs.afk-display.allow-afk-command")) {
                sender.sendMessage(ErrorType.COMMAND_DISABLED.m());
                return true;
            }
            if (!player.hasPermission(permission+".toggle")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (AFK.afk.containsKey(player.getUniqueId())) {
                if (AFK.afk.get(player.getUniqueId())) {
                    AFK.unAFK(player);
                } else {
                    AFK.afk(player, true);
                }
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("uninstall")) {
            if (player.hasPermission(permission+".uninstall")) {
                AFK.uninstall();
            } else {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            }
        }
        return false;
    }

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("uninstall");
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
