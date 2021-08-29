package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Utilities.ItemAverages.ItemTracker;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.ErrorType;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ItemAveragesCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);


    public ItemAveragesCommand() {
        super(MessageHandler.getCmdName("itemaverages"), MessageHandler.getCmdUsage("itemaverages"), MessageHandler.getCmdDescription("itemaverages"), MessageHandler.getCmdAliases("itemaverages"));
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

        Player player = (Player) sender;
        if (args.length < 1) {
            if (main.getConfig().getBoolean("packs.item-averages.require-op") && !player.isOp()) {
                player.sendMessage(ErrorType.NOT_OP.m());
                return true;
            }
            if (!ItemTracker.inUse) {
                player.sendMessage(MessageHandler.getCmdMessage("itemaverages", "tracker-created")
                        .replace("%x%", "" + (int) player.getLocation().getX())
                        .replace("%y%", "" + (int) player.getLocation().getY())
                        .replace("%z%", "" + (int) player.getLocation().getZ())
                );
                ItemTracker.spawnTracker(player.getLocation().getBlock().getLocation(), player);
            } else {
                player.sendMessage(MessageHandler.getCmdMessage("itemaverages", "tracker-in-use"));
            }
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("create")) {
                if (main.getConfig().getBoolean("packs.item-averages.require-op") && !player.isOp()) {
                    player.sendMessage(ErrorType.NOT_OP.m());
                    return true;
                }
                if (!ItemTracker.inUse) {
                    player.sendMessage(MessageHandler.getCmdMessage("itemaverages", "tracker-created")
                            .replace("%x%", "" + (int) player.getLocation().getX())
                            .replace("%y%", "" + (int) player.getLocation().getY())
                            .replace("%z%", "" + (int) player.getLocation().getZ())
                    );
                    ItemTracker.spawnTracker(player.getLocation().getBlock().getLocation(), player);
                } else {
                    player.sendMessage(MessageHandler.getCmdMessage("itemaverages", "tracker-in-use"));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("uninstall")) {
                if (!player.isOp()) {
                    player.sendMessage(ErrorType.NOT_OP.m());
                    return true;
                }
                int count = 0;
                for (Entity entity : player.getWorld().getEntities()) {
                    if (entity.getScoreboardTags().contains("vt_tracker")) {
                        count++;
                        entity.remove();
                    }
                }
                player.sendMessage(MessageHandler.getCmdMessage("itemaverages", "tracker-mass-remove").replace("%count%", count + ""));
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
