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
        super("thunder-shrine", "shrine", "/shrine", "Thunder Shrines!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.thunder-shrine.enabled")) {
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

        if (args.length < 1) {
            player.sendMessage(ErrorType.MISSING_ACTION.m());
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (!sender.hasPermission(permission+".create")) {
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
                player.sendMessage(getString("created")
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

            if (!sender.hasPermission(permission+".remove")) {
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
                player.sendMessage(getString("error.none_nearby"));
                return true;
            }

            shrine.remove();
            player.sendMessage(getString("removed")
                    .replace("%x%", (int) shrine.getLocation().getX() + "")
                    .replace("%y%", (int) shrine.getLocation().getY() + "")
                    .replace("%z%", (int) shrine.getLocation().getZ() + "")
                    .replace("%world%", shrine.getLocation().getWorld().getName())
            );
            return true;
        }

        if (args[0].equalsIgnoreCase("uninstall")) {
            if (!sender.hasPermission(permission+".uninstall")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            for (Entity shrine : Shrine.getShrines()) {
                shrine.remove();
            }
            player.sendMessage(getString("shrines_mass_removed"));
            return true;
        }
        return false;
    }

    List<String> arguments1 = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments1.isEmpty()) {
            arguments1.add("create");
            arguments1.add("remove");
            arguments1.add("uninstall");
        }

        List<String> result = new ArrayList<>();
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
