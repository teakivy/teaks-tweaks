package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class PortalCommand extends BukkitCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public PortalCommand(String name) {
        super(name);
        this.setDescription("Calculate where a portal would link to.");
        this.usageMessage = "/portal";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
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
