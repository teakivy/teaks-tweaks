package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SpawnCommand extends AbstractCommand {

    public SpawnCommand() {
        super("spawn", "spawn", "/spawn", CommandType.PLAYER_ONLY);

        setCooldownTime(getPackConfig().getInt("packs.spawn.teleport-cooldown"));
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (isOnCooldown(player)) {
            player.sendMessage(getError("on_cooldown", Placeholder.parsed("time", getCooldownTime() + "")));
            return;
        }

        player.sendMessage(getString("teleporting"));

        if (getPackConfig().getInt("packs.spawn.teleport-delay") > 0) {
            Location loc = player.getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
                if (!player.getLocation().equals(loc)) {
                    player.sendMessage(getError("moved"));
                    return;
                }
                teleportToSpawn(player);
            }, getPackConfig().getInt("packs.spawn.teleport-delay") * 20L);

            return;
        }

        teleportToSpawn(player);
    }

    private void teleportToSpawn(Player player) {
        World world = Bukkit.getWorld(Objects.requireNonNull(getPackConfig().getString("packs.spawn.world")));

        Back.backLoc.put(player.getUniqueId(), player.getLocation());
        player.teleport(world.getSpawnLocation());
    }
}
