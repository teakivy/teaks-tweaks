package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.World;

import java.util.List;

public class RealTimeClockCommand extends AbstractCommand {

    public RealTimeClockCommand() {
        super(CommandType.PLAYER_ONLY, "real-time-clock", "realtimeclock", Permission.COMMAND_REALTIMECLOCK, List.of("rtc"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        World world = event.getPlayer().getWorld();
        int days = (int) ((world.getGameTime() / 20 / 60 / 60) / 24);
        int hours = (int) (world.getGameTime() / 20 / 60 / 60) % 24;
        int minutes = (int) (world.getGameTime() / 20 / 60 ) % 60;
        sendMessage("world_time", insert("days", days), insert("hours", hours), insert("minutes", minutes));
    }
}
