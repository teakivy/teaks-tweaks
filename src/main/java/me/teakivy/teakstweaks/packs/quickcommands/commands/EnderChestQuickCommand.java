package me.teakivy.teakstweaks.packs.quickcommands.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.entity.Player;

import java.util.List;

public class EnderChestQuickCommand extends AbstractCommand {

    public EnderChestQuickCommand() {
        super(TTPack.QUICK_COMMANDS, "quick_commands.enderchest", List.of("echest", "ec"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("enderchest")
                .requires(perm(Permission.COMMAND_ENDERCHEST))
                .executes(playerOnly(this::execute))
                .build();
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();

        player.openInventory(player.getEnderChest());
        return Command.SINGLE_SUCCESS;
    }
}
