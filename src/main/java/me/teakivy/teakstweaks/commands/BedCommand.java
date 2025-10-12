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
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BedCommand extends AbstractCommand {

    public BedCommand() {
        super(TTCommand.BED, "back_to_bed");

        setCooldownTime(getPackConfig().getInt("teleport-cooldown"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("bed")
                .requires(perm(Permission.COMMAND_BED))
                .executes(playerOnly(this::bed))
                .build();

    }

    private int bed(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        if (isOnCooldown(player)) {
            player.sendMessage(getError("on_cooldown", insert("time", getCooldownTime())));
            return Command.SINGLE_SUCCESS;
        }

        teleportToBed(player);
        setCooldown(player);
        return Command.SINGLE_SUCCESS;
    }

    private void teleportToBed(Player player) {
        Location location = getBedLocation(player);
        if (location == null) {
            player.sendMessage(getError("no_respawn"));
            return;
        }
        int teleportDelay = getPackConfig().getInt("teleport-delay");

        if (teleportDelay <= 0) {
            Back.backLoc.put(player.getUniqueId(), player.getLocation());
            player.teleportAsync(location);
            player.sendMessage(getText("teleporting"));
            return;
        }
        player.sendMessage(getText("teleporting_delayed", insert("time", teleportDelay)));
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
            if (x != player.getLocation().getBlockX() || y != player.getLocation().getBlockY() || z != player.getLocation().getBlockZ()) {
                player.sendMessage(getError("teleport_moved"));
                resetCooldown(player);
                return;
            }
            Back.backLoc.put(player.getUniqueId(), player.getLocation());
            player.teleportAsync(location);
            player.sendMessage(getText("teleporting"));
        }, teleportDelay * 20L);
    }

    private Location getBedLocation(Player player) {
        Location loc = player.getRespawnLocation(true);
        if (loc == null && getPackConfig().getBoolean("to-spawn")) {
            World world = Bukkit.getWorld(Objects.requireNonNull(getPackConfig().getString("world")));
            if (world == null) return null;
            loc = world.getSpawnLocation();
        }
        return loc;
    }
}
