package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SphereType;
import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SpheresPack;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawningSpheresCommand extends AbstractCommand {

    public SpawningSpheresCommand() {
        super("spawning-spheres", "spawningspheres", "/spawningspheres <create|remove|teleport> <red|blue|green>", List.of("ss", "sphere"), CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        SphereType type = SphereType.getSphereType(args[1]);
        if (type == null) {
            player.sendMessage(getError("invalid_color"));
            return;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (!checkPermission(player, "create")) return;

            boolean success = SpheresPack.summonSphere(type, player.getLocation());
            if (!success) {
                player.sendMessage(getString("error.in_use").replace("%color%", type.getName()));
                return;
            }

            player.sendMessage(getString("summoned").replace("%color%", type.getName()));
            return;
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (!checkPermission(player, "remove")) return;

            boolean success = SpheresPack.removeSphere(type, player);
            if (!success) {
                player.sendMessage(getString("error.not_in_use").replace("%color%", type.getName()));
                return;
            }

            player.sendMessage(getString("removing").replace("%color%", type.getName()));
            return;
        }

        if (args[0].equalsIgnoreCase("teleport")) {
            if (!checkPermission(player, "teleport")) return;

            boolean success = SpheresPack.teleport(type, player);
            if (!success) {
                player.sendMessage(getString("error.not_in_use").replace("%color%", type.getName()));
                return;
            }

            player.sendMessage(getString("teleporting").replace("%color%", type.getName()));
            return;
        }

        sendUsage(player);
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return List.of("create", "remove", "teleport");
        if (args.length == 2) return List.of("red", "blue", "green");
        return null;
    }
}
