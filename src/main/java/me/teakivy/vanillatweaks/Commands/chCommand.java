package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class chCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ch") || command.getName().equalsIgnoreCase("coordshud")) {
            if (!main.getConfig().getBoolean("packs.coords-hud.enabled")) {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /" + command.getName() + " <option>");
                return true;
            }

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("toggle")) {
                    if (main.getConfig().getBoolean("packs.coords-hud.force-enable")) {
                        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "You cannot toggle Coordinates HUD!");
                        return true;
                    }
                    if (!Main.chEnabled.contains(player.getUniqueId())) Main.chEnabled.add(player.getUniqueId());
                    else Main.chEnabled.remove(player.getUniqueId());
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Toggled Coords HUD");
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /" + command.getName() + " <option>");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
            }
        }

        return false;
    }
}