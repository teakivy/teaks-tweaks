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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KillBoatsCommand extends AbstractCommand {

    public KillBoatsCommand() {
        super(TTCommand.KILLBOATS, "killboats");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("killboats")
                .requires(perm(Permission.COMMAND_KILLBOATS))
                .executes(this::killboats)
                .build();
    }

    private int killboats(CommandContext<CommandSourceStack> context) {
        CommandSender sender = context.getSource().getSender();
        List<EntityType> boatTypes = new ArrayList<>();
        for (EntityType value : EntityType.values()) {
            if (value.name().contains("BOAT") || value.name().contains("RAFT")) {
                boatTypes.add(value);
            }
        }
        int boats = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (!boatTypes.contains(entity.getType())) continue;
                if (!entity.getPassengers().isEmpty()) continue;

                entity.remove();
                boats++;
            }
        }

        sender.sendMessage(getText("removed_boats", insert("count", boats)));
        return Command.SINGLE_SUCCESS;
    }
}
