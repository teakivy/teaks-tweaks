package me.teakivy.teakstweaks.packs.quickcommands.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.translation.Argument;
import org.bukkit.entity.Player;

import java.util.List;

public class MessageQuickCommand extends AbstractCommand {

    public MessageQuickCommand() {
        super("quick-commands", "quick_commands.message", List.of("msg", "tell", "whisper", "w"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("message")
                .then(Commands.argument("player", ArgumentTypes.player())
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(playerOnly(this::execute))))
                .build();
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        PlayerSelectorArgumentResolver targetResolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        try {
            Player target = targetResolver.resolve(context.getSource()).getFirst();
            String message = StringArgumentType.getString(context, "message");

            target.sendMessage(getText("whisper_to_you", Argument.string("player", player.getName()), Argument.string("message", message)));
            player.sendMessage(getText("whisper_to_player", Argument.string("player", player.getName()), Argument.string("message", message)));

        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        return Command.SINGLE_SUCCESS;
    }
}
