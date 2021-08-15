package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.ErrorType;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RealTimeClockCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public RealTimeClockCommand() {
        super(MessageHandler.getCmdName("realtimeclock"), MessageHandler.getCmdUsage("realtimeclock"), MessageHandler.getCmdDescription("realtimeclock"), MessageHandler.getCmdAliases("realtimeclock"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!main.getConfig().getBoolean("packs.real-time-clock.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = player.getWorld();
            int days = (int) ((world.getGameTime() / 20 / 60 / 60) / 24);
            int hours = (int) (world.getGameTime() / 20 / 60 / 60) % 24;
            int minutes = (int) (world.getGameTime() / 20 / 60 ) % 60;
            player.sendMessage(MessageHandler.getCmdMessage("realtimeclock", "world-time")
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
