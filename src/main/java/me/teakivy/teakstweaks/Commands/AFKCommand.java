package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Packs.Survival.AFKDisplay.AFK;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AFKCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public AFKCommand() {
        super(MessageHandler.getCmdName("afk"), MessageHandler.getCmdUsage("afk"), MessageHandler.getCmdDescription("afk"), MessageHandler.getCmdAliases("afk"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.afk-display.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            if (!main.getConfig().getBoolean("packs.afk-display.allow-afk-command")) {
                sender.sendMessage(ErrorType.COMMAND_DISABLED.m());
                return true;
            }
            if (!player.hasPermission("vanillatweaks.afk.toggle")) {
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
            if (player.hasPermission("vanillatweaks.afk.uninstall")) {
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
