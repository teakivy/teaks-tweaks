package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RealTimeClockCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public RealTimeClockCommand() {
        super("real-time-clock", "realtimeclock", "/realtimeclock", "View a world's real play time", List.of("rtc"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!main.getConfig().getBoolean("packs.real-time-clock.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }


        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = player.getWorld();
            int days = (int) ((world.getGameTime() / 20 / 60 / 60) / 24);
            int hours = (int) (world.getGameTime() / 20 / 60 / 60) % 24;
            int minutes = (int) (world.getGameTime() / 20 / 60 ) % 60;
            player.sendMessage(getString("world_time")
                    .replace("%days%", days + "")
                    .replace("%hours%", hours + "")
                    .replace("%minutes%", minutes + "")
            );
        } else {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
        }
        return false;
    }
}
