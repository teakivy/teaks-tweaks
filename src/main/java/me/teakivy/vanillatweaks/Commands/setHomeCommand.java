package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class setHomeCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    FileConfiguration data = main.data.getConfig();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("sethome")) {
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
                player.sendMessage(vt + ChatColor.RED + "Please specify a home name!");
                return true;
            }

            String name = args[0].toLowerCase();

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
        return false;
    }
}
