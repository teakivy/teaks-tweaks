package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.homes.Home;
import me.teakivy.teakstweaks.packs.homes.HomesPack;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.customitems.ItemHandler;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DeleteHomeCommand extends AbstractCommand {

    public DeleteHomeCommand() {
        super("homes", "home", List.of("delhome", "rmhome"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("deletehome")
                .requires(perm(Permission.COMMAND_HOME_DELETE))
                .executes(ctx -> {
                    Player player = checkPlayer(ctx);
                    if (player == null) return Command.SINGLE_SUCCESS;

                    new HomeCommand().deleteHome(player, "home");
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.argument("name", StringArgumentType.word())
                        .suggests((CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) -> new HomeCommand().homeNameSuggestions(ctx, builder))
                        .executes(ctx -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            String name = StringArgumentType.getString(ctx, "name");
                            new HomeCommand().deleteHome(player, name);
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
