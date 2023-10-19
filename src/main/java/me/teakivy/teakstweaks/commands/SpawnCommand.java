package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class SpawnCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public SpawnCommand() {
        super("spawn", "spawn", "/spawn", "Teleport to the server's spawn point.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.spawn.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;


        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (main.getConfig().getInt("packs.spawn.teleport-cooldown") > 0) {
            if (cooldown.containsKey(player.getUniqueId())) {
                if (cooldown.get(player.getUniqueId()) + (main.getConfig().getInt("packs.spawn.teleport-cooldown") * 1000L) > System.currentTimeMillis()) {
                    player.sendMessage(getString("error.on_cooldown").replace("%time%", main.getConfig().getInt("packs.spawn.teleport-cooldown") + ""));
                    return true;
                }
            }
            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
        }

        player.sendMessage(getString("teleporting"));

        if (main.getConfig().getInt("packs.spawn.teleport-delay") > 0) {
            Location loc = player.getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                if (player.getLocation().getX() == loc.getX() && player.getLocation().getY() == loc.getY() && player.getLocation().getZ() == loc.getZ()) {
                    teleportToSpawn(player);
                } else {
                    player.sendMessage(getString("error.moved"));
                }
            }, main.getConfig().getInt("packs.spawn.teleport-delay") * 20L);
            return false;
        }

        teleportToSpawn(player);
        return false;
    }

    private void teleportToSpawn(Player player) {
        World world = Bukkit.getWorld(Objects.requireNonNull(Main.getInstance().getConfig().getString("packs.spawn.world")));

        Back.backLoc.put(player.getUniqueId(), player.getLocation());
        player.teleport(world.getSpawnLocation());
    }
}
