package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.BlockPositionResolver;
import io.papermc.paper.math.BlockPosition;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PortalCommand extends AbstractCommand {

    public PortalCommand() {
        super(TTCommand.PORTAL, "portal");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("portal")
                .requires(perm(Permission.COMMAND_PORTAL))
                .executes(playerOnly(this::portal))
                .then(Commands.argument("location", ArgumentTypes.blockPosition())
                        .requires(perm(Permission.COMMAND_PORTAL))
                        .executes(this::portalLoc))
                .build();
    }

    private int portal(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        Location location = player.getLocation();
        sendLocation(player, location);
        return Command.SINGLE_SUCCESS;
    }

    private int portalLoc(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player player = (Player) context.getSource().getSender();
        final BlockPositionResolver blockPositionResolver = context.getArgument("location", BlockPositionResolver.class);
        final BlockPosition blockPosition = blockPositionResolver.resolve(context.getSource());
        final Location location = blockPosition.toLocation(player.getWorld());
        sendLocation(player, location);
        return Command.SINGLE_SUCCESS;
    }

    private void sendLocation(Player player, Location location) {
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
            player.sendMessage(getText("wrong_dimension", insert("world", location.getWorld().getName())));
            return;
        }
        player.sendMessage(getText("location", insert("x", x), insert("y", y), insert("z", z)));
    }
}
