package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.utilities.itemaverages.ItemTracker;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ItemAveragesCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);


    public ItemAveragesCommand() {
        super("item-averages", "itemaverages", "/itemaverages", "Calculate how many items flow through a source in 2 minutes!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.item-averages.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        Player player = (Player) sender;
        if (args.length < 1) {
            if (!sender.hasPermission(permission+".create")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (!ItemTracker.inUse) {
                player.sendMessage(getString("tracker-created")
                        .replace("%x%", "" + (int) player.getLocation().getX())
                        .replace("%y%", "" + (int) player.getLocation().getY())
                        .replace("%z%", "" + (int) player.getLocation().getZ())
                );
                ItemTracker.spawnTracker(player.getLocation().getBlock().getLocation(), player);
            } else {
                player.sendMessage(getString("error.tracker_in_use"));
            }
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("create")) {
                if (!sender.hasPermission(permission+".create")) {
                    sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                    return true;
                }
                if (!ItemTracker.inUse) {
                    player.sendMessage(getString("tracker-created")
                            .replace("%x%", "" + (int) player.getLocation().getX())
                            .replace("%y%", "" + (int) player.getLocation().getY())
                            .replace("%z%", "" + (int) player.getLocation().getZ())
                    );
                    ItemTracker.spawnTracker(player.getLocation().getBlock().getLocation(), player);
                } else {
                    player.sendMessage(getString("error.tracker_in_use"));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("uninstall")) {
                if (!sender.hasPermission(permission+".uninstall")) {
                    sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                    return true;
                }
                int count = 0;
                for (Entity entity : player.getWorld().getEntities()) {
                    if (entity.getScoreboardTags().contains("tracker")) {
                        count++;
                        entity.remove();
                    }
                }
                player.sendMessage(getString("tracker_mass_removed").replace("%count%", count + ""));
            }
        }
        return false;
    }

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("create");
            if (sender.isOp()) {
                arguments.add("uninstall");
            }
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
