package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class RealTimeClockCommand extends AbstractCommand {

    public RealTimeClockCommand() {
        super("real-time-clock", "realtimeclock", "/realtimeclock", List.of("rtc"), CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        World world = player.getWorld();
        int days = (int) ((world.getGameTime() / 20 / 60 / 60) / 24);
        int hours = (int) (world.getGameTime() / 20 / 60 / 60) % 24;
        int minutes = (int) (world.getGameTime() / 20 / 60 ) % 60;
        player.sendMessage(getString("world_time")
                .replace("%days%", days + "")
                .replace("%hours%", hours + "")
                .replace("%minutes%", minutes + "")
        );
    }
}
