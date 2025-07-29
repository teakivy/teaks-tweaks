package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.BlockPositionResolver;
import io.papermc.paper.math.BlockPosition;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.customitems.TItem;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GraveCommand extends AbstractCommand {

    public GraveCommand() {
        super(TTCommand.GRAVE, "grave");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("grave")
                .requires(perm(Permission.COMMAND_GRAVE))
                .then(Commands.literal("locate")
                        .requires(perm(Permission.COMMAND_GRAVE_LOCATE))
                        .executes(playerOnly(this::locate)))
                .then(Commands.literal("key")
                        .requires(perm(Permission.COMMAND_GRAVE_KEY))
                        .executes(playerOnly(this::key)))
                .then(Commands.literal("uninstall")
                        .requires(perm(Permission.COMMAND_GRAVE_UNINSTALL))
                        .executes(playerOnly(this::uninstall)))
                .build();
    }

    private int locate(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();

        if (!getPackConfig().getBoolean("locatable")) {
            player.sendMessage(ErrorType.COMMAND_DISABLED.m());
            return Command.SINGLE_SUCCESS;
        }

        PersistentDataContainer data = player.getPersistentDataContainer();
        if (!data.has(Key.get("graves_last"), PersistentDataType.STRING)) {
            player.sendMessage(getError("no_grave"));
            return Command.SINGLE_SUCCESS;
        }

        player.sendMessage(data.get(Key.get("graves_last"), PersistentDataType.STRING));
        return Command.SINGLE_SUCCESS;
    }

    private int key(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        player.getInventory().addItem(TItem.GRAVE_KEY.getItem());
        player.sendMessage(getText("given_key"));
        return Command.SINGLE_SUCCESS;
    }

    private int uninstall(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getScoreboardTags().contains("grave")) {
                    entity.remove();
                }
            }
        }
        player.sendMessage(getText("removed_graves"));
        return Command.SINGLE_SUCCESS;
    }
}
