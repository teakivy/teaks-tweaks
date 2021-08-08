package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Teleportation.Back.Back;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;


public class HomeCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    HashMap<UUID, Long> cooldown = new HashMap<>();

    FileConfiguration data = main.data.getConfig();

    public HomeCommand() {
        super("home", "/home", "Teleport, Set, or Delete your Homes!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.homes.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(vt + "This command can only be ran by a Player!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(vt + ChatColor.RED + "Please specify which home would like to teleport to!");
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {

            if (args.length < 2) {
                player.sendMessage(vt + ChatColor.RED + "Please specify a home name!");
                return true;
            }
            String name = args[1].toLowerCase();

            if (data.contains("homes." + player.getUniqueId() + "." + name)) {
                player.sendMessage(vt + ChatColor.RED + "The home " + name + " already exists!");
                return true;
            }
            if (main.getConfig().getInt("packs.homes.max-homes") > 0) {
                if (data.getConfigurationSection("homes." + player.getUniqueId()).getKeys(false).stream().count() >= main.getConfig().getInt("packs.homes.max-homes")) {
                    player.sendMessage(vt + ChatColor.RED + "You can set a Maximum of " + main.getConfig().getInt("packs.homes.max-homes") + " Homes!");
                    return true;
                }
            }

            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            String world = player.getWorld().getName();

            data.set("homes." + player.getUniqueId() + "." + name + ".world", world);
            data.set("homes." + player.getUniqueId() + "." + name + ".x", x);
            data.set("homes." + player.getUniqueId() + "." + name + ".y", y);
            data.set("homes." + player.getUniqueId() + "." + name + ".z", z);
            try {
                main.data.saveConfig();
                player.sendMessage(vt + ChatColor.GREEN + "Set Home " + name + "!");
            } catch (IOException e) {
                e.printStackTrace();
                player.sendMessage(vt + ChatColor.RED + "An Error has occurred! Could not set Home " + name + "!");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("remove")) {
            if (args.length < 2) {
                player.sendMessage(vt + ChatColor.RED + "Please specify a home name!");
                return true;
            }
            String name = args[1].toLowerCase();

            if (!data.contains("homes." + player.getUniqueId() + "." + name)) {
                player.sendMessage(vt + ChatColor.RED + "The home " + name + " doesn't exist!");
                return true;
            }

            data.set("homes." + player.getUniqueId() + "." + name, null);
            player.sendMessage(vt + ChatColor.DARK_RED + "Removed Home " + name + "!");
            return true;
        }

        if (!data.contains("homes." + player.getUniqueId())) {
            player.sendMessage(vt + ChatColor.RED + "You don't have any Homes set yet! Use '/home set <name>' to set one!");
            return true;
        }

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
            if (data.contains("homes." + player.getUniqueId() + "." + args[0].toLowerCase())) {
                player.sendMessage(vt + ChatColor.YELLOW + "Teleporting to home " + args[0].toLowerCase() + "...");
                Back.backLoc.put(player.getUniqueId(), player.getLocation());
                player.teleport(new Location(Bukkit.getWorld(data.getString("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".world")), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".x"), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".y"), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".z")));
            } else {
                player.sendMessage(vt + ChatColor.RED + "The home " + args[0].toLowerCase() + " doesn't exist!");
            }
            return true;
        } else if (main.getConfig().getInt("packs.spawn.teleport-delay") > 0) {
            Location loc = player.getLocation();

            if (data.contains("homes." + player.getUniqueId() + "." + args[0].toLowerCase())) {
                player.sendMessage(vt + ChatColor.YELLOW + "Teleporting to home " + args[0].toLowerCase() + "...");
                Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                    if (player.getLocation().getX() == loc.getX() && player.getLocation().getY() == loc.getY() && player.getLocation().getZ() == loc.getZ()) {
                        Back.backLoc.put(player.getUniqueId(), player.getLocation());
                        player.teleport(new Location(Bukkit.getWorld(data.getString("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".world")), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".x"), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".y"), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".z")));
                    } else {
                        player.sendMessage(vt + ChatColor.RED + "You must stand still to teleport!");
                    }
                }, main.getConfig().getInt("packs.spawn.teleport-delay") * 20L);
            } else {
                player.sendMessage(vt + ChatColor.RED + "The home " + args[0].toLowerCase() + " doesn't exist!");
            }
            return true;


        }
        return false;
    }
}
