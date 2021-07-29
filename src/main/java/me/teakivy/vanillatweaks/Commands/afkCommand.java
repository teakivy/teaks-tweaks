package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Survival.AFKDisplay.AFK;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class afkCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("afk")) {
            if (!main.getConfig().getBoolean("packs.afk-display.enabled")) {
                sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(vt + "This command can only be ran by a Player!");
                return true;
            }
            Player player = (Player) sender;

            if (args.length < 1) {
                if (!main.getConfig().getBoolean("packs.afk-display.allow-afk-command")) {
                    player.sendMessage(vt + ChatColor.RED + "This command has been disabled a server administrator!");
                    return true;
                }
                if (AFK.afk.containsKey(player.getUniqueId())) {
                    if (AFK.afk.get(player.getUniqueId())) {
                        AFK.unAFK(player);
                    } else {
                        AFK.afk(player);
                    }
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("uninstall")) {
                if (player.isOp()) {
                    AFK.uninstall();
                } else {
                    player.sendMessage(vt + ChatColor.RED + "You must be an OP to run this command!");
                }
            }
        }
        return false;
    }
}
