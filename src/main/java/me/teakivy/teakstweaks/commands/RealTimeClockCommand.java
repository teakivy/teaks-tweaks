package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RealTimeClockCommand extends AbstractCommand {

    public RealTimeClockCommand() {
        super(TTCommand.REALTIMECLOCK, "realtimeclock", List.of("rtc"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("realtimeclock")
                .requires(perm(Permission.COMMAND_REALTIMECLOCK))
                .executes(this::realtimeclock)
                .build();
    }

    private int realtimeclock(CommandContext<CommandSourceStack> context) {
        CommandSender sender = context.getSource().getSender();
        World world = Bukkit.getWorlds().getFirst();
        int days = (int) ((world.getGameTime() / 20 / 60 / 60) / 24);
        int hours = (int) (world.getGameTime() / 20 / 60 / 60) % 24;
        int minutes = (int) (world.getGameTime() / 20 / 60 ) % 60;
        sender.sendMessage(getText("world_time", insert("days", days), insert("hours", hours), insert("minutes", minutes)));
        return Command.SINGLE_SUCCESS;
    }
}
