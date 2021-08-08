package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Teleportation.Back.Back;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SpawnCommand extends BukkitCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public SpawnCommand(String name) {
        super(name);
        this.setDescription("Teleport to a worlds spawn!");
        this.usageMessage = "/spawn";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!main.getConfig().getBoolean("packs.spawn.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(vt + "This command can only be ran by a Player!");
            return true;
        }
        Player player = (Player) sender;

        if (main.getConfig().getInt("packs.spawn.teleport-cooldown") > 0) {
            if (cooldown.containsKey(player.getUniqueId())) {
                if (cooldown.get(player.getUniqueId()) + (main.getConfig().getInt("packs.spawn.teleport-cooldown") * 1000L) > System.currentTimeMillis()) {
                    player.sendMessage(vt + ChatColor.RED + "You must wait " + main.getConfig().getInt("packs.spawn.teleport-cooldown") + " seconds between uses of /spawn!");
                    return true;
                }
            }
            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
        }

        if (main.getConfig().getInt("packs.spawn.teleport-delay") == 0) {
            player.sendMessage(vt + ChatColor.YELLOW + "Teleporting to world spawn...");
            teleportToSpawn(player);
        } else if (main.getConfig().getInt("packs.spawn.teleport-delay") > 0) {
            Location loc = player.getLocation();
            player.sendMessage(vt + ChatColor.YELLOW + "Teleporting to world spawn...");
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                if (player.getLocation().getX() == loc.getX() && player.getLocation().getY() == loc.getY() && player.getLocation().getZ() == loc.getZ()) {
                    teleportToSpawn(player);
                } else {
                    player.sendMessage(vt + ChatColor.RED + "You must stand still to teleport!");
                }
            }, main.getConfig().getInt("packs.spawn.teleport-delay") * 20L);
        }
        return false;
    }

    private void teleportToSpawn(Player player) {
        World world = player.getWorld();

        Back.backLoc.put(player.getUniqueId(), player.getLocation());
        player.teleport(world.getSpawnLocation());
    }
}
