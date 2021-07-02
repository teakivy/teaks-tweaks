package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rtcCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("rtc") || command.getName().equalsIgnoreCase("realtime") || command.getName().equalsIgnoreCase("realtimeclock")) {

            if (!main.getConfig().getBoolean("packs.real-time-clock.enabled")) {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
                return true;
            }

            if (sender instanceof Player) {
                Player player = (Player) sender;
                World world = player.getWorld();
                int days = (int) ((world.getGameTime() / 20 / 60 / 60) / 24);
                int hours = (int) (world.getGameTime() / 20 / 60 / 60) % 24;
                int minutes = (int) (world.getGameTime() / 20 / 60 ) % 60;
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "This world has been active for " + ChatColor.GOLD.toString() + ChatColor.BOLD + days + ChatColor.YELLOW + " Days, " + ChatColor.GOLD.toString() + ChatColor.BOLD + hours + ChatColor.YELLOW + " Hours, and " + ChatColor.GOLD.toString() + ChatColor.BOLD + minutes + ChatColor.YELLOW + " Minutes!");
            } else {
                sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
            }
        }
        return false;
    }
//
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//
//        if (command.getName().equalsIgnoreCase("rtc") || command.getName().equalsIgnoreCase("realtime") || command.getName().equalsIgnoreCase("realtimeclock")) {
//            System.out.println(main.getConfig());
//            if (main.getConfig().getBoolean("packs.real-time-clock.enabled")) {
//                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
//                return true;
//            }
//
//            if (sender instanceof Player) {
//                Player player = (Player) sender;
//                World world = player.getWorld();
//                int days = (int) ((world.getGameTime() / 20 / 60 / 60) / 24);
//                int hours = (int) (world.getGameTime() / 20 / 60 / 60) % 24;
//                int minutes = (int) (world.getGameTime() / 20 / 60 ) % 60;
//                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "This world has been active for " + ChatColor.GOLD.toString() + ChatColor.BOLD + days + ChatColor.YELLOW + " Days, " + ChatColor.GOLD.toString() + ChatColor.BOLD + hours + ChatColor.YELLOW + " Hours, and " + ChatColor.GOLD.toString() + ChatColor.BOLD + minutes + ChatColor.YELLOW + " Minutes!");
//            } else {
//                sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
//            }
//        }
//        return false;
//    }
}
