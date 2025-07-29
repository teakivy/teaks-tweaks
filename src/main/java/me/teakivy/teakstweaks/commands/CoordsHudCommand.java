package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.coordshud.CoordsHud;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class CoordsHudCommand extends AbstractCommand {

    public CoordsHudCommand() {
        super(TTCommand.COORDSHUD, "coordshud", List.of("ch"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("coordshud")
                .requires(sender -> sender.getSender().hasPermission(Permission.COMMAND_AFK.getPermission()) && !getPackConfig().getBoolean("force-enable"))
                .executes(playerOnly(this::coordshud))
                .build();
    }

    private int coordshud(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        CoordsHud.setEnabled(player, !CoordsHud.isEnabled(player));
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
            player.sendActionBar(Component.empty());
        }, 2L);

        player.sendMessage(getText("toggled"));
        return Command.SINGLE_SUCCESS;
    }
}
