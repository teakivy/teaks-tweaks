package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SphereType;
import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SpheresPack;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawningSpheresCommand extends AbstractCommand {

    public SpawningSpheresCommand() {
        super("spawning-spheres", "spawningspheres", "/spawningspheres <create|remove|teleport> <red|blue|green>", "Spawn a sphere to help with mob spawning", List.of("ss", "sphere"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage(getUsage());
            return true;
        }

        SphereType type = SphereType.getSphereType(args[1]);
        if (type == null) {
            player.sendMessage(getString("error.invalid_color"));
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (!sender.hasPermission(permission+".create")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }

            boolean success = SpheresPack.summonSphere(type, player.getLocation());
            if (!success) {
                player.sendMessage(getString("error.in_use").replace("%color%", type.getName()));
                return true;
            }

            player.sendMessage(getString("summoned").replace("%color%", type.getName()));
            return true;
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (!sender.hasPermission(permission+".remove")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }

            boolean success = SpheresPack.removeSphere(type, player);
            if (!success) {
                player.sendMessage(getString("error.not_in_use").replace("%color%", type.getName()));
                return true;
            }

            player.sendMessage(getString("removing").replace("%color%", type.getName()));
            return true;
        }

        if (args[0].equalsIgnoreCase("teleport") || args[0].equalsIgnoreCase("tp")) {
            if (!sender.hasPermission(permission+".teleport")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }

            boolean success = SpheresPack.teleport(type, player);
            if (!success) {
                player.sendMessage(getString("error.not_in_use").replace("%color%", type.getName()));
                return true;
            }

            player.sendMessage(getString("teleporting").replace("%color%", type.getName()));
            return true;
        }

        player.sendMessage(getUsage());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabComplete = new ArrayList<>();

        if (args.length == 1) {
            tabComplete.add("create");
            tabComplete.add("remove");
            tabComplete.add("teleport");
        }

        if (args.length == 2) {
            tabComplete.add("red");
            tabComplete.add("blue");
            tabComplete.add("green");
        }

        List<String> result = new ArrayList<>();
        for (String a : tabComplete) {
            if (a.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                result.add(a);
        }

        return result;
    }
}
