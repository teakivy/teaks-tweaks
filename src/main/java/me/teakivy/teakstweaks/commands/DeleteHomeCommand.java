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

                    deletehome(player, "home");
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.argument("name", StringArgumentType.word())
                        .suggests(this::homeNameSuggestions)
                        .executes(ctx -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            String name = StringArgumentType.getString(ctx, "name");
                            deletehome(player, name);
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }

    private void deletehome(Player player, String name) {
        if (name == null || name.isEmpty()) name = "home";
        Home home = HomesPack.getHome(player, name);
        if (home == null) {
            player.sendMessage(getError("home_dne", insert("name", name)));
            return;
        }

        if (!HomesPack.removeHome(player, name)) {
            player.sendMessage(getError("cant_delete_home"));
            return;
        }
        player.sendMessage(getText("deleted_home", insert("name", name)));
    }

    private CompletableFuture<Suggestions> homeNameSuggestions(final CommandContext<CommandSourceStack> ctx, final SuggestionsBuilder builder) {
        builder.restart();
        if (!(ctx.getSource().getSender() instanceof Player player)) return builder.buildFuture();
        for (Home home : HomesPack.getHomes(player)) {
            if (home.getName().toLowerCase().startsWith(builder.getRemainingLowerCase())) builder.suggest(home.getName());
        }
        return builder.buildFuture();
    }
}
