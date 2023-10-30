package me.teakivy.teakstweaks.commands;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class PortalCommand extends AbstractCommand {

    public PortalCommand() {
        super("nether-portal-coords", "portal", "/portal", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        int x = (int) player.getLocation().getX();
        int y = (int) player.getLocation().getY();
        int z = (int) player.getLocation().getZ();
        if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
            x /= 8;
            z /= 8;
        } else if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            x *= 8;
            z *= 8;
        } else {
            player.sendMessage(getError("wrong_dimension").replace("%world%", player.getWorld().getName()));
            return;
        }
        player.sendMessage(getString("location")
                .replace("%x%", x + "")
                .replace("%y%", y + "")
                .replace("%z%", z + "")
        );
    }
}
