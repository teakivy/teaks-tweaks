package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SudokuCommand extends AbstractCommand {

    public SudokuCommand() {
        super(TTCommand.SUDOKU, "sudoku");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("sudoku")
                .requires(perm(Permission.COMMAND_SUDOKU))
                .executes(playerOnly(this::sudoku))
                .build();
    }

    private int sudoku(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        player.sendMessage(getText("committed"));
        player.getScoreboardTags().add("sudoku-message");
        player.setHealth(0);
        return Command.SINGLE_SUCCESS;
    }
}
