package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SpawnCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public SpawnCommand() {
        super("spawn", MessageHandler.getCmdName("spawn"), MessageHandler.getCmdUsage("spawn"), MessageHandler.getCmdDescription("spawn"), MessageHandler.getCmdAliases("spawn"));
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


        if (!sender.hasPermission("teakstweaks.spawn.teleport")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (main.getConfig().getInt("packs.spawn.teleport-cooldown") > 0) {
            if (cooldown.containsKey(player.getUniqueId())) {
                if (cooldown.get(player.getUniqueId()) + (main.getConfig().getInt("packs.spawn.teleport-cooldown") * 1000L) > System.currentTimeMillis()) {
                    player.sendMessage(MessageHandler.getCmdMessage("spawn", "error.on-cooldown").replace("%time%",  + main.getConfig().getInt("packs.spawn.teleport-cooldown") + ""));
                    return true;
                }
            }
            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
        }

        if (main.getConfig().getInt("packs.spawn.teleport-delay") == 0) {
            player.sendMessage(MessageHandler.getCmdMessage("spawn", "teleporting"));
            teleportToSpawn(player);
        } else if (main.getConfig().getInt("packs.spawn.teleport-delay") > 0) {
            Location loc = player.getLocation();
            player.sendMessage(MessageHandler.getCmdMessage("spawn", "teleporting"));
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                if (player.getLocation().getX() == loc.getX() && player.getLocation().getY() == loc.getY() && player.getLocation().getZ() == loc.getZ()) {
                    teleportToSpawn(player);
                } else {
                    player.sendMessage(MessageHandler.getCmdMessage("spawn", "error.must-stand-still"));
                }
            }, main.getConfig().getInt("packs.spawn.teleport-delay") * 20L);
        }
        return false;
    }

    private void teleportToSpawn(Player player) {
        World world = Bukkit.getWorld("world");

        Back.backLoc.put(player.getUniqueId(), player.getLocation());
        player.teleport(world.getSpawnLocation());
    }
}
