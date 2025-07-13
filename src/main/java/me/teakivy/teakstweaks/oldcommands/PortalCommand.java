package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.utils.oldcommand.AbstractCommand;
import me.teakivy.teakstweaks.utils.oldcommand.CommandType;
import me.teakivy.teakstweaks.utils.oldcommand.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Location;
import org.bukkit.World;

public class PortalCommand extends AbstractCommand {

    public PortalCommand() {
        super(CommandType.PLAYER_ONLY, "nether-portal-coords", "portal", Permission.COMMAND_PORTAL);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Location location = event.getPlayer().getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        if (location.getWorld().getEnvironment() == World.Environment.NORMAL) {
            x /= 8;
            z /= 8;
        } else if (location.getWorld().getEnvironment() == World.Environment.NETHER) {
            x *= 8;
            z *= 8;
        } else {
            sendError("wrong_dimension", insert("world", location.getWorld().getName()));
            return;
        }
        sendMessage("location", insert("x", x), insert("y", y), insert("z", z));
    }
}
