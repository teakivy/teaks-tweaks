package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalCommand extends AbstractCommand {

    public PortalCommand() {
        super("nether-portal-coords", "portal", "/portal", "Calculate where a nether portal would link to.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!TeaksTweaks.getInstance().getConfig().getBoolean("packs.nether-portal-coords.enabled")) {
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
            player.sendMessage(getString("error.wrong_dimension").replace("%world%", player.getWorld().getName()));
            return true;
        }
        player.sendMessage(getString("location")
                .replace("%x%", x + "")
                .replace("%y%", y + "")
                .replace("%z%", z + "")
        );
        return false;
    }
}
