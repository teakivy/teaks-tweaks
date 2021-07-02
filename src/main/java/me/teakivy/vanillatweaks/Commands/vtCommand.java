package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class vtCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("vt") || command.getName().equalsIgnoreCase("vanillatweaks")) {
            if (!sender.hasPermission("vt.reload")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /" + command.getName() + " reload");
            }

            if (args[0].equalsIgnoreCase("reload")) {
                Main.getPlugin(Main.class).reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Main.getPlugin(Main.class).getConfig().getString("reload.message"))));
            }

        }
        return false;
    }
}
