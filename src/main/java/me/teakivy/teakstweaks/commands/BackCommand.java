package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.back.Back;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BackCommand extends AbstractCommand {

    public BackCommand() {
        super(TTCommand.BACK, "back");

        setCooldownTime(getPackConfig().getInt("teleport-cooldown"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("back")
                .requires(perm(Permission.COMMAND_BACK))
                .executes(playerOnly(this::back))
                .build();

    }

    private int back(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        if (isOnCooldown(player)) {
            player.sendMessage(getText("on_cooldown", insert("cooldown_seconds", getCooldownTime())));
            return Command.SINGLE_SUCCESS;
        }

        if (!Back.backLoc.containsKey(player.getUniqueId())) {
            player.sendMessage(getError("no_back_location"));
            return Command.SINGLE_SUCCESS;
        }

        int teleportDelay = getPackConfig().getInt("teleport-delay");

        if (teleportDelay <= 0) {
            Back.tpBack(player);
            player.sendMessage(getText("teleporting"));
            return Command.SINGLE_SUCCESS;
        }
        player.sendMessage(getText("teleporting_delayed", insert("time", teleportDelay)));
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
            if (x != player.getLocation().getBlockX() || y != player.getLocation().getBlockY() || z != player.getLocation().getBlockZ()) {
                player.sendMessage(getError("teleport_moved"));
                return;
            }
            Back.tpBack(player);
            player.sendMessage(getText("teleporting"));
        }, teleportDelay * 20L);
        setCooldown(player);
        return Command.SINGLE_SUCCESS;
    }
}
