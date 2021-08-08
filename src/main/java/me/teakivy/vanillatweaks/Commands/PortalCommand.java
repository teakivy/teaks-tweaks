package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public PortalCommand() {
        super("portal", "/portal", "Calculate where a portal would link to.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.nether-portal-coords.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;
        if (player.getWorld().getEnvironment() == World.Environment.NORMAL) {
            player.sendMessage(vt + ChatColor.YELLOW + "    X: " + Math.round(player.getLocation().getX() / 8) + " | Y: " + Math.round(player.getLocation().getY()) + " | Z: " + Math.round(player.getLocation().getZ()) / 8);
        } else if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            player.sendMessage(vt + ChatColor.YELLOW + "    X: " + Math.round(player.getLocation().getX() * 8) + " | Y: " + Math.round(player.getLocation().getY()) + " | Z: " + Math.round(player.getLocation().getZ()) * 8);
        } else {
            player.sendMessage(vt + ChatColor.RED + "You cannot run this command in " + player.getWorld().getName());
        }
        return false;
    }
}
