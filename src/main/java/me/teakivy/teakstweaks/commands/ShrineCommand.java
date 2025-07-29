package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.thundershrine.ThunderShrine;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ShrineCommand extends AbstractCommand {

    public ShrineCommand() {
        super(TTCommand.SHRINE, "shrine");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("shrine")
                .requires(perm(Permission.COMMAND_SHRINE))
                .then(Commands.literal("create")
                    .requires(perm(Permission.COMMAND_SHRINE_CREATE))
                    .executes(playerOnly(this::create)))
                .then(Commands.literal("remove")
                    .requires(perm(Permission.COMMAND_SHRINE_REMOVE))
                    .executes(playerOnly(this::remove)))
                .then(Commands.literal("uninstall")
                    .requires(perm(Permission.COMMAND_SHRINE_UNINSTALL))
                    .executes(playerOnly(this::uninstall)))
                .build();
    }

    private int create(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        Location loc = player.getLocation();
        String world = loc.getWorld().getName();
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        try {
            ThunderShrine.createShrine(new Location(loc.getWorld(), x, y, z));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player.sendMessage(getText("created", insert("x", x), insert("y", y), insert("z", z), insert("world", world)));
        return Command.SINGLE_SUCCESS;
    }

    private int remove(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        Entity shrine = null;
        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
            if (ThunderShrine.getShrines().contains(entity)) {
                shrine = entity;
            }
        }

        if (shrine == null) {
            player.sendMessage(getError("none_nearby"));
            return Command.SINGLE_SUCCESS;
        }

        shrine.remove();
        player.sendMessage(getText("removed",
                insert("x", shrine.getLocation().getBlockX()),
                insert("y", shrine.getLocation().getBlockY()),
                insert("z", shrine.getLocation().getBlockZ()),
                insert("world", shrine.getLocation().getWorld().getName())));
        return Command.SINGLE_SUCCESS;
    }

    private int uninstall(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        for (Entity shrine : ThunderShrine.getShrines()) {
            shrine.remove();
        }
        player.sendMessage(getText("shrines_mass_removed"));
        return Command.SINGLE_SUCCESS;
    }
}
