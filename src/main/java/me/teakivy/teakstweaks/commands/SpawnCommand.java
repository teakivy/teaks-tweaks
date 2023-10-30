package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SpawnCommand extends AbstractCommand {

    public SpawnCommand() {
        super("spawn", "spawn", "/spawn", CommandType.PLAYER_ONLY);

        setCooldownTime(getConfig().getInt("packs.spawn.teleport-cooldown"));
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (isOnCooldown(player)) {
            player.sendMessage(getError("on_cooldown").replace("%time%", getCooldownTime() + ""));
            return;
        }

        player.sendMessage(getString("teleporting"));

        if (getConfig().getInt("packs.spawn.teleport-delay") > 0) {
            Location loc = player.getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
                if (!player.getLocation().equals(loc)) {
                    player.sendMessage(getError("moved"));
                    return;
                }
                teleportToSpawn(player);
            }, getConfig().getInt("packs.spawn.teleport-delay") * 20L);

            return;
        }

        teleportToSpawn(player);
    }

    private void teleportToSpawn(Player player) {
        World world = Bukkit.getWorld(Objects.requireNonNull(getConfig().getString("packs.spawn.world")));

        Back.backLoc.put(player.getUniqueId(), player.getLocation());
        player.teleport(world.getSpawnLocation());
    }
}
