package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.hermitcraft.thundershrine.Shrine;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShrineCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public ShrineCommand() {
        super("thunder-shrine", MessageHandler.getCmdName("shrine"), MessageHandler.getCmdUsage("shrine"), MessageHandler.getCmdDescription("shrine"), MessageHandler.getCmdAliases("shrine"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.thunder-shrine.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ErrorType.MISSING_ACTION.m());
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (!sender.hasPermission("teakstweaks.shrines.create")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            try {
                Location loc = player.getLocation();
                String world = loc.getWorld().getName();
                int x = (int) Math.floor(loc.getX());
                int y = (int) Math.floor(loc.getY());
                int z = (int) Math.floor(loc.getZ());
                Shrine.createShrine(player.getLocation());
                player.sendMessage(MessageHandler.getCmdMessage("shrine", "shrine-created")
                        .replace("%x%", x + "")
                        .replace("%y%", y + "")
                        .replace("%z%", z + "")
                        .replace("%world%", world)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("remove")) {

            if (!sender.hasPermission("teakstweaks.shrines.remove")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            Entity shrine = null;
            for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
                if (Shrine.getShrines().contains(entity)) {
                    shrine = entity;
                }
            }

            if (shrine == null) {
                player.sendMessage(MessageHandler.getCmdMessage("shrine", "no-shrine-nearby"));
                return true;
            }

            shrine.remove();
            TextComponent uuid = new TextComponent(MessageHandler.getCmdMessage("shrine", "shrine-removed.text").replace("%uuid%", shrine.getUniqueId().toString()));
            uuid.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(MessageHandler.getCmdMessage("shrine", "shrine-removed.hover"))));
            uuid.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + shrine.getLocation().getX() + " " + shrine.getLocation().getY() + " " + shrine.getLocation().getZ()));
            player.spigot().sendMessage(uuid);
            return true;
        }

        if (args[0].equalsIgnoreCase("uninstall")) {
            if (!sender.hasPermission("teakstweaks.shrines.uninstall")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            for (Entity shrine : Shrine.getShrines()) {
                shrine.remove();
            }
            player.sendMessage(MessageHandler.getCmdMessage("shrine", "mass-remove"));
            return true;
        }
        return false;
    }

    List<String> arguments1 = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments1.isEmpty()) {
            arguments1.add("create");
            arguments1.add("remove");
            arguments1.add("uninstall");
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
