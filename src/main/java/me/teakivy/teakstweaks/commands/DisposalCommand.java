package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class DisposalCommand extends AbstractCommand {

    public DisposalCommand() {
        super(TTCommand.DISPOSAL, "disposal", List.of("trash", "bin", "garbage", "rubbish"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("disposal")
                .requires(perm(Permission.COMMAND_DISPOSAL))
                .executes(playerOnly(this::disposal))
                .build();
    }

    private int disposal(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        player.openInventory(Bukkit.createInventory(null, 27, Component.text("Disposal")));
        return Command.SINGLE_SUCCESS;
    }
}
