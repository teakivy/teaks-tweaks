package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.homes.Home;
import me.teakivy.teakstweaks.packs.homes.HomesPack;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetHomeCommand extends AbstractCommand {

    public SetHomeCommand() {
        super("homes", "sethome");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("sethome")
                .requires(perm(Permission.COMMAND_HOME_SET))
                .executes(ctx -> {
                    Player player = checkPlayer(ctx);
                    if (player == null) return Command.SINGLE_SUCCESS;

                    sethome(player, "home");
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.argument("name", StringArgumentType.word())
                        .executes(ctx -> {
                            Player player = checkPlayer(ctx);
                            if (player == null) return Command.SINGLE_SUCCESS;

                            String name = StringArgumentType.getString(ctx, "name");
                            sethome(player, name);
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }

    private void sethome(Player player, String name) {
        if (name == null || name.isEmpty()) name = "home";
        if (HomesPack.getHome(player, name) != null) {
            player.sendMessage(getError("home_already_exists", insert("name", name)));
            return;
        }

        List<Home> homes = HomesPack.getHomes(player);
        int maxHomes = getPackConfig().getInt("max-homes");
        if (maxHomes > 0 && homes.size() >= maxHomes) {
            player.sendMessage(getError("max_homes", insert("max_homes", maxHomes)));
            return;
        }

        if (!HomesPack.setHome(player, name, player.getLocation())) {
            player.sendMessage(getError("cant_set_home"));
            return;
        }

        player.sendMessage(getText("set_home", insert("name", name)));
    }
}
