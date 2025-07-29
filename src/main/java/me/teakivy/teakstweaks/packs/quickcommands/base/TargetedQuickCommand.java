package me.teakivy.teakstweaks.packs.quickcommands.base;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class TargetedQuickCommand extends AbstractCommand {

    private final String commandName;
    private final Permission permission;
    private final Consumer<Player> action;

    public TargetedQuickCommand(String commandName, Permission permission, Consumer<Player> action) {
        super(TTPack.QUICK_COMMANDS, "quick_commands." + commandName);
        this.commandName = commandName;
        this.permission = permission;
        this.action = action;
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal(commandName)
                .requires(perm(permission))
                .executes(playerOnly(ctx -> {
                    Player player = (Player) ctx.getSource().getSender();
                    action.accept(player);
                    return Command.SINGLE_SUCCESS;
                }))
                .then(Commands.argument("target", ArgumentTypes.player())
                        .executes(ctx -> {
                            PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("target", PlayerSelectorArgumentResolver.class);
                            Player target = targetResolver.resolve(ctx.getSource()).getFirst();
                            action.accept(target);
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
