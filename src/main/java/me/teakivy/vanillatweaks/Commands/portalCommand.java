package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class portalCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("portal")) {

            if (!main.getConfig().getBoolean("packs.nether-portal-coords.enabled")) {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
                return true;
            }

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "    X: " + Math.round(player.getLocation().getX() / 8) + " | Y: " + Math.round(player.getLocation().getY()) + " | Z: " + Math.round(player.getLocation().getZ()) / 8);
                } else if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "    X: " + Math.round(player.getLocation().getX() * 8) + " | Y: " + Math.round(player.getLocation().getY()) + " | Z: " + Math.round(player.getLocation().getZ()) * 8);
                } else {
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "You cannot run this command in " + player.getWorld().getName());
                }
            } else {
                sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
            }
        }
        return false;
    }
}
