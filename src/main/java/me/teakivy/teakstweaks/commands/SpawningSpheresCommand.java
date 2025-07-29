package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.spawningspheres.SphereType;
import me.teakivy.teakstweaks.packs.spawningspheres.SpawningSpheres;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiFunction;

public class SpawningSpheresCommand extends AbstractCommand {

    public SpawningSpheresCommand() {
        super(TTCommand.SPAWNINGSPHERES, "spawningspheres", List.of("ss", "sphere"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("spawningspheres")
                .requires(perm(Permission.COMMAND_SPAWNINGSPHERES))
                .then(Commands.literal("create")
                        .requires(perm(Permission.COMMAND_SPAWNINGSPHERES_CREATE))
                        .then(Commands.literal("red")
                                .executes(sPlayerOnly(this::create, SphereType.RED)))
                        .then(Commands.literal("blue")
                                .executes(sPlayerOnly(this::create, SphereType.BLUE)))
                        .then(Commands.literal("green")
                                .executes(sPlayerOnly(this::create, SphereType.GREEN))))
                .then(Commands.literal("remove")
                        .requires(perm(Permission.COMMAND_SPAWNINGSPHERES_REMOVE))
                        .then(Commands.literal("red")
                                .executes(sPlayerOnly(this::remove, SphereType.RED)))
                        .then(Commands.literal("blue")
                                .executes(sPlayerOnly(this::remove, SphereType.BLUE)))
                        .then(Commands.literal("green")
                                .executes(sPlayerOnly(this::remove, SphereType.GREEN))))
                .then(Commands.literal("teleport")
                        .requires(perm(Permission.COMMAND_SPAWNINGSPHERES_TELEPORT))
                        .then(Commands.literal("red")
                                .executes(sPlayerOnly(this::teleport, SphereType.RED)))
                        .then(Commands.literal("blue")
                                .executes(sPlayerOnly(this::teleport, SphereType.BLUE)))
                        .then(Commands.literal("green")
                                .executes(sPlayerOnly(this::teleport, SphereType.GREEN))))
                .build();
    }

    private int create(CommandContext<CommandSourceStack> context, SphereType type) {
        Player player = (Player) context.getSource().getSender();
        boolean success = SpawningSpheres.summonSphere(type, player.getLocation());
        if (!success) {
            player.sendMessage(getError("in_use", insert("color", type.getName())));
            return Command.SINGLE_SUCCESS;
        }

        player.sendMessage(getText("summoned", insert("color", type.getName())));
        return Command.SINGLE_SUCCESS;
    }

    private int remove(CommandContext<CommandSourceStack> context, SphereType type) {
        Player player = (Player) context.getSource().getSender();
        boolean success = SpawningSpheres.removeSphere(type, player);
        if (!success) {
            player.sendMessage(getError("not_in_use", insert("color", type.getName())));
            return Command.SINGLE_SUCCESS;
        }

        player.sendMessage(getText("removing", insert("color", type.getName())));
        return Command.SINGLE_SUCCESS;
    }

    private int teleport(CommandContext<CommandSourceStack> context, SphereType type) {
        Player player = (Player) context.getSource().getSender();
        boolean success = SpawningSpheres.teleport(type, player);
        if (!success) {
            player.sendMessage(getError("not_in_use", insert("color", type.getName())));
            return Command.SINGLE_SUCCESS;
        }

        player.sendMessage(getText("teleporting", insert("color", type.getName())));
        return Command.SINGLE_SUCCESS;
    }

    private Command<CommandSourceStack> sPlayerOnly(BiFunction<CommandContext<CommandSourceStack>, SphereType, Integer> function, SphereType sphereType) {
        return (context) -> {
            if (!(context.getSource().getSender() instanceof Player)) {
                context.getSource().getSender().sendMessage(ErrorType.NOT_PLAYER.m());
                return Command.SINGLE_SUCCESS;
            }
            return function.apply(context, sphereType);
        };
    }
}
