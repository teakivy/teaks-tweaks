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
import me.teakivy.teakstweaks.packs.homes.Homes;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HomeCommand extends AbstractCommand {

    public HomeCommand() {
        super(TTCommand.HOME, "home");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("home")
                .requires(perm(Permission.COMMAND_HOME))
                .executes(ctx -> {
                    Player player = checkPlayer(ctx);
                    if (player == null) return Command.SINGLE_SUCCESS;

                    tpHome(player, "home");
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.argument("name", StringArgumentType.word())
                        .suggests(this::homeNameSuggestions)
                        .executes(ctx -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            String name = StringArgumentType.getString(ctx, "name");
                            tpHome(player, name);
                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("set")
                        .requires(perm(Permission.COMMAND_HOME_SET))
                        .executes(ctx -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            setHome(player, "home");
                            return Command.SINGLE_SUCCESS;
                        })
                        .then(Commands.argument("name", StringArgumentType.word())
                                .executes(ctx -> {
                                    Player player = checkPlayer(ctx);
                                    if (player == null) return Command.SINGLE_SUCCESS;

                                    String name = StringArgumentType.getString(ctx, "name");
                                    setHome(player, name);
                                    return Command.SINGLE_SUCCESS;
                                })))
                .then(Commands.literal("delete")
                        .requires(perm(Permission.COMMAND_HOME_DELETE))
                        .executes(ctx -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            deleteHome(player, "home");
                            return Command.SINGLE_SUCCESS;
                        })
                        .then(Commands.argument("name", StringArgumentType.word())
                                .suggests(this::homeNameSuggestions)
                                .executes(ctx -> {
                                    Player player = checkPlayer(ctx);
                                    if (player == null) return Command.SINGLE_SUCCESS;

                                    String name = StringArgumentType.getString(ctx, "name");
                                    deleteHome(player, name);
                                    return Command.SINGLE_SUCCESS;
                                })))
                .build();
    }

    public void tpHome(Player player, String name) {
        if (name == null || name.isEmpty()) name = "home";
        Home home = Homes.getHome(player, name);
        if (home == null) {
            player.sendMessage(getError("home_dne", insert("name", name)));
            return;
        }

        home.teleport();
    }

    public void setHome(Player player, String name) {
        if (name == null || name.isEmpty()) name = "home";
        if (Homes.getHome(player, name) != null) {
            player.sendMessage(getError("home_already_exists", insert("name", name)));
            return;
        }

        List<Home> homes = Homes.getHomes(player);
        int maxHomes = getPackConfig().getInt("max-homes");
        if (maxHomes > 0 && homes.size() >= maxHomes) {
            player.sendMessage(getError("max_homes", insert("max_homes", maxHomes)));
            return;
        }

        if (!Homes.setHome(player, name, player.getLocation())) {
            player.sendMessage(getError("cant_set_home"));
            return;
        }

        player.sendMessage(getText("set_home", insert("name", name)));
    }

    public void deleteHome(Player player, String name) {
        if (name == null || name.isEmpty()) name = "home";
        Home home = Homes.getHome(player, name);
        if (home == null) {
            player.sendMessage(getError("home_dne", insert("name", name)));
            return;
        }

        if (!Homes.removeHome(player, name)) {
            player.sendMessage(getError("cant_delete_home"));
            return;
        }
        player.sendMessage(getText("deleted_home", insert("name", name)));
    }

    public CompletableFuture<Suggestions> homeNameSuggestions(final CommandContext<CommandSourceStack> ctx, final SuggestionsBuilder builder) {
        builder.restart();
        if (!(ctx.getSource().getSender() instanceof Player player)) return builder.buildFuture();
        for (Home home : Homes.getHomes(player)) {
            if (home.getName().toLowerCase().startsWith(builder.getRemainingLowerCase())) builder.suggest(home.getName());
        }
        return builder.buildFuture();
    }
}
