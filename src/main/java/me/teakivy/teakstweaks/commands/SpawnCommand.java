package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SpawnCommand extends AbstractCommand {

    public SpawnCommand() {
        super(CommandType.PLAYER_ONLY, "spawn", "spawn");

        setCooldownTime(getPackConfig().getInt("teleport-cooldown"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (isOnCooldown()) {
            sendError("on_cooldown", insert("time", getCooldownTime()));
            return;
        }

        teleportToSpawn(event.getPlayer());
        sendMessage("teleporting");
    }

    private void teleportToSpawn(Player player) {
        World world = Bukkit.getWorld(Objects.requireNonNull(getPackConfig().getString("world")));

        Back.backLoc.put(player.getUniqueId(), player.getLocation());
        player.teleport(world.getSpawnLocation());
    }
}
