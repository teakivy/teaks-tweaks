package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;

public class SetHomeCommand extends AbstractCommand {

    public SetHomeCommand() {
        super("homes", "home");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("sethome")
                .requires(perm(Permission.COMMAND_HOME_SET))
                .executes(ctx -> {
                    Player player = checkPlayer(ctx);
                    if (player == null) return Command.SINGLE_SUCCESS;

                    new HomeCommand().setHome(player, "home");
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.argument("name", StringArgumentType.word())
                        .executes(ctx -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            String name = StringArgumentType.getString(ctx, "name");
                            new HomeCommand().setHome(player, name);
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
