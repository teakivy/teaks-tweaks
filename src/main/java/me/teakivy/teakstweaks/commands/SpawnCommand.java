package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.back.Back;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SpawnCommand extends AbstractCommand {

    public SpawnCommand() {
        super(CommandType.PLAYER_ONLY, "spawn", "spawn", Permission.COMMAND_SPAWN);

        setCooldownTime(getPackConfig().getInt("teleport-cooldown"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (isOnCooldown()) {
            sendError("on_cooldown", insert("time", getCooldownTime()));
            return;
        }

        teleportToSpawn(event.getPlayer());
        setCooldown();
    }

    private void teleportToSpawn(Player player) {
        World world = Bukkit.getWorld(Objects.requireNonNull(getPackConfig().getString("world")));
        if (world == null) {
            sendError(ErrorType.UNKNOWN_ERROR);
            return;
        }
        int teleportDelay = getPackConfig().getInt("teleport-delay");

        if (teleportDelay <= 0) {
            Back.backLoc.put(player.getUniqueId(), player.getLocation());
            player.teleportAsync(world.getSpawnLocation());
            sendMessage("teleporting");
            return;
        }
        sendMessage("teleporting_delayed", insert("time", teleportDelay));
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
            if (x != player.getLocation().getBlockX() || y != player.getLocation().getBlockY() || z != player.getLocation().getBlockZ()) {
                player.sendMessage(getError("teleport_moved"));
                return;
            }
            Back.backLoc.put(player.getUniqueId(), player.getLocation());
            player.teleportAsync(world.getSpawnLocation());
            player.sendMessage(getText("teleporting"));
        }, teleportDelay * 20L);
    }
}
