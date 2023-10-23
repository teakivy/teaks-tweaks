package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BackCommand extends AbstractCommand {

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public BackCommand() {
        super("back", "back", "/back", "Teleport back to your last location.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!TeaksTweaks.getInstance().getConfig().getBoolean("packs.back.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (TeaksTweaks.getInstance().getConfig().getInt("packs.back.teleport-cooldown") > 0) {
            if (cooldown.containsKey(player.getUniqueId())) {
                if (cooldown.get(player.getUniqueId()) + (TeaksTweaks.getInstance().getConfig().getInt("packs.back.teleport-cooldown") * 1000L) > System.currentTimeMillis()) {
                    player.sendMessage(getString("error.on_cooldown").replace("%cooldown_seconds%", String.valueOf(TeaksTweaks.getInstance().getConfig().getInt("packs.back.teleport-cooldown"))));
                    return true;
                }
            }
            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
        }

        if (!Back.backLoc.containsKey(player.getUniqueId())) {
            player.sendMessage(getString("error.no_back_location"));
            return true;
        }
        if (TeaksTweaks.getInstance().getConfig().getInt("packs.back.teleport-delay") == 0) {
            player.sendMessage(getString("teleporting"));
            Back.tpBack(player);
        } else if (TeaksTweaks.getInstance().getConfig().getInt("packs.back.teleport-delay") > 0) {
            Location loc = player.getLocation();
            player.sendMessage(getString("teleporting"));
            Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
                if (player.getLocation().getX() == loc.getX() && player.getLocation().getY() == loc.getY() && player.getLocation().getZ() == loc.getZ()) {
                    Back.tpBack(player);
                } else {
                    player.sendMessage(getString("error.moved"));
                }
            }, TeaksTweaks.getInstance().getConfig().getInt("packs.back.teleport-delay") * 20L);
        }
        return false;
    }
}
