package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import me.teakivy.teakstweaks.packs.tpa.TPAHandler;
import me.teakivy.teakstweaks.packs.tpa.TPARequest;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.entity.Player;

public class TPAHereCommand extends AbstractCommand {

    public TPAHereCommand() {
        super(TTCommand.TPAHERE, "tpahere");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("tpahere")
                .requires(perm(Permission.COMMAND_TPA))
                .then(Commands.argument("target", ArgumentTypes.player())
                        .executes(playerOnly(this::tpahere)))
                .then(Commands.literal("accept")
                        .then(Commands.argument("target", ArgumentTypes.player())
                                .executes(playerOnly(this::accept))))
                .then(Commands.literal("cancel")
                        .executes(playerOnly(this::cancel)))
                .build();
    }

    private int tpahere(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        final PlayerSelectorArgumentResolver targetResolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        try {
            final Player target = targetResolver.resolve(context.getSource()).getFirst();
            if (target == null) {
                player.sendMessage(ErrorType.PLAYER_DNE.m());
                return Command.SINGLE_SUCCESS;
            }

            if (TPAHandler.hasOutgoingRequest(player)) {
                player.sendMessage(getError("has_outgoing_request"));
                return Command.SINGLE_SUCCESS;
            }

            TPARequest request = new TPARequest(player, target, TPARequest.TPAType.TPAHERE);
            TPAHandler.sendRequest(request);
            return Command.SINGLE_SUCCESS;
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private int accept(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        final PlayerSelectorArgumentResolver targetResolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        try {
            final Player target = targetResolver.resolve(context.getSource()).getFirst();
            if (target == null) {
                player.sendMessage(ErrorType.PLAYER_DNE.m());
                return Command.SINGLE_SUCCESS;
            }
            TPARequest request = TPAHandler.getRequest(target, player);
            if (request == null) {
                player.sendMessage(getError("no_pending_requests"));
                return Command.SINGLE_SUCCESS;
            }
            TPAHandler.acceptRequest(request);
            return Command.SINGLE_SUCCESS;
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private int cancel(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        TPARequest request = TPAHandler.getOutgoingRequest(player);
        if (request == null) {
            player.sendMessage(getError("no_outgoing_request"));
            return Command.SINGLE_SUCCESS;
        }
        TPAHandler.cancelRequest(request);
        return Command.SINGLE_SUCCESS;
    }
}
