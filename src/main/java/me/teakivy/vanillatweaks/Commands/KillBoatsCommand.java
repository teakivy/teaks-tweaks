package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class KillBoatsCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("killboat") || command.getName().equalsIgnoreCase("killboats")) {
            if (!main.getConfig().getBoolean("packs.kill-boats.enabled")) {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
                return true;
            }
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "You cannot use this command!");
                return true;
            }
            int boats = 0;
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getType() == EntityType.BOAT) {
                        if (entity.getPassengers().isEmpty()) {
                            entity.remove();
                            boats++;
                        }
                    }
                }
            }
            sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Removed " + ChatColor.GOLD.toString() + ChatColor.BOLD + boats + ChatColor.GREEN + " boats!");
        }

        return false;
    }

}
