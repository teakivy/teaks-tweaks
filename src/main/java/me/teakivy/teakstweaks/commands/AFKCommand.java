package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.afkdisplay.AFK;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AFKCommand extends AbstractCommand {

    public AFKCommand() {
        super("afk-display", "afk");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("afk")
                .requires(sender -> sender.getSender().hasPermission(Permission.COMMAND_AFK.getPermission()) && getPackConfig().getBoolean("allow-afk-command"))
                .executes(this::afk)
                .then(Commands.literal("uninstall")
                        .requires(sender -> sender.getSender().hasPermission(Permission.COMMAND_AFK_UNINSTALL.getPermission()))
                        .executes(playerOnly(this::uninstall)))
                .build();
    }

    private int afk(CommandContext<CommandSourceStack> ctx) {
        Player player = (Player) ctx.getSource().getSender();
        if (AFK.afk.containsKey(player.getUniqueId())) {
            if (AFK.afk.get(player.getUniqueId())) {
                AFK.unAFK(player);
                return Command.SINGLE_SUCCESS;
            }

            AFK.afk(player, true);
        }
        return Command.SINGLE_SUCCESS;
    }

    private int uninstall(CommandContext<CommandSourceStack> ctx) {
        AFK.uninstall();
        return Command.SINGLE_SUCCESS;
    }
}
