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
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.minimessage.translation.Argument;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ReplyQuickCommand {

    private final HashMap<UUID, UUID> lastMessage = new HashMap<>();

    public void register() {
        new MessageCommand().register();
        new ReplyCommand().register();
    }

    public class MessageCommand extends AbstractCommand {

        public MessageCommand() {
            super(TTPack.QUICK_COMMANDS, "quick_commands.message", List.of("msg", "tell", "whisper", "w"));
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
            PlayerSelectorArgumentResolver targetResolver = context.getArgument("player", PlayerSelectorArgumentResolver.class);
            try {
                Player target = targetResolver.resolve(context.getSource()).getFirst();
                String message = StringArgumentType.getString(context, "message");

                target.sendMessage(getText("whisper_to_you", Argument.string("player", player.getName()), Argument.string("message", message)));
                player.sendMessage(getText("whisper_to_player", Argument.string("player", player.getName()), Argument.string("message", message)));

                lastMessage.put(player.getUniqueId(), target.getUniqueId());
                lastMessage.put(target.getUniqueId(), player.getUniqueId());
            } catch (CommandSyntaxException e) {
                throw new RuntimeException(e);
            }

            return Command.SINGLE_SUCCESS;
        }
    }

    public class ReplyCommand extends AbstractCommand {

        public ReplyCommand() {
            super(TTPack.QUICK_COMMANDS, "quick_commands.reply", List.of("r"));
        }

        @Override
        public LiteralCommandNode<CommandSourceStack> getCommand() {
            return Commands.literal("reply")
                    .requires(perm(Permission.COMMAND_REPLY))
                    .then(Commands.argument("message", StringArgumentType.greedyString())
                            .executes(playerOnly(this::execute)))
                    .build();
        }

        private int execute(CommandContext<CommandSourceStack> context) {
            Player player = (Player) context.getSource().getSender();
            String message = StringArgumentType.getString(context, "message");
            UUID targetUUID = lastMessage.get(player.getUniqueId());
            if (targetUUID == null) {
                player.sendMessage(getError("no_reply"));
                return Command.SINGLE_SUCCESS;
            }

            Player target = player.getServer().getPlayer(targetUUID);
            if (target == null) {
                player.sendMessage(ErrorType.PLAYER_DNE.m());
                return Command.SINGLE_SUCCESS;
            }

            target.sendMessage(get("quick_commands.message.whisper_to_you", Argument.string("player", player.getName()), Argument.string("message", message)));
            player.sendMessage(get("quick_commands.message.whisper_to_player", Argument.string("player", player.getName()), Argument.string("message", message)));

            lastMessage.put(player.getUniqueId(), targetUUID);
            lastMessage.put(targetUUID, player.getUniqueId());

            return Command.SINGLE_SUCCESS;
        }
    }
}
