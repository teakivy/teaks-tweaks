package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SphereType;
import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SpheresPack;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.Arg;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawningSpheresCommand extends AbstractCommand {

    public SpawningSpheresCommand() {
        super(CommandType.PLAYER_ONLY, "spawning-spheres", "spawningspheres", Permission.COMMAND_SPAWNINGSPHERES, List.of("ss", "sphere"), Arg.required("create", "remove", "teleport"), Arg.required("red", "blue", "green"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        SphereType type = SphereType.getSphereType(event.getArg(1));
        if (type == null) {
            sendError("invalid_color");
            return;
        }

        Player player = event.getPlayer();
        if (event.isArg(0, "create")) {
            if (!checkPermission(Permission.COMMAND_SPAWNINGSPHERES_CREATE)) return;

            boolean success = SpheresPack.summonSphere(type, player.getLocation());
            if (!success) {
                sendError("in_use", insert("color", type.getName()));
                return;
            }

            sendMessage("summoned", insert("color", type.getName()));
            return;
        }

        if (event.isArg(0, "remove")) {
            if (!checkPermission(Permission.COMMAND_SPAWNINGSPHERES_REMOVE)) return;

            boolean success = SpheresPack.removeSphere(type, player);
            if (!success) {
                sendError("not_in_use", insert("color", type.getName()));
                return;
            }

            sendMessage("removing", insert("color", type.getName()));
            return;
        }

        if (event.isArg(0, "teleport")) {
            if (!checkPermission(Permission.COMMAND_SPAWNINGSPHERES_TELEPORT)) return;

            boolean success = SpheresPack.teleport(type, player);
            if (!success) {
                sendError("not_in_use", insert("color", type.getName()));
                return;
            }

            sendMessage("teleporting", insert("color", type.getName()));
            return;
        }

        sendUsage();
    }
}
