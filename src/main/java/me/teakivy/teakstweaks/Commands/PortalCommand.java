package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public PortalCommand() {
        super(MessageHandler.getCmdName("portal"), MessageHandler.getCmdUsage("portal"), MessageHandler.getCmdDescription("portal"), MessageHandler.getCmdAliases("portal"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.nether-portal-coords.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }

        if (!sender.hasPermission("teakstweaks.netherportalcoords.execute")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        Player player = (Player) sender;
        int x = (int) player.getLocation().getX();
        int y = (int) player.getLocation().getY();
        int z = (int) player.getLocation().getZ();
        if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
            x /= 8;
            y /= 8;
            z /= 8;
        } else if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            x *= 8;
            y *= 8;
            z *= 8;
        } else {
            player.sendMessage(MessageHandler.getCmdMessage("portal", "unavaliable-dimension".replace("%world%", player.getWorld().getName())));
            return true;
        }
        player.sendMessage(MessageHandler.getCmdMessage("portal", "location-found")
                .replace("%x%", x + "")
                .replace("%y%", y + "")
                .replace("%z%", z + "")
        );
        return false;
    }
}
