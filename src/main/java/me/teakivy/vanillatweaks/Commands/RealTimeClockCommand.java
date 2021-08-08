package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Collections;

public class RealTimeClockCommand extends BukkitCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public RealTimeClockCommand(String name) {
        super(name);
        this.setDescription("View a world's Real Time");
        this.usageMessage = "/realtimeclock";
        this.setAliases(Collections.singletonList("rtc"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!main.getConfig().getBoolean("packs.real-time-clock.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = player.getWorld();
            int days = (int) ((world.getGameTime() / 20 / 60 / 60) / 24);
            int hours = (int) (world.getGameTime() / 20 / 60 / 60) % 24;
            int minutes = (int) (world.getGameTime() / 20 / 60 ) % 60;
            player.sendMessage(vt + ChatColor.YELLOW + "This world has been active for " + ChatColor.GOLD.toString() + ChatColor.BOLD + days + ChatColor.YELLOW + " Days, " + ChatColor.GOLD.toString() + ChatColor.BOLD + hours + ChatColor.YELLOW + " Hours, and " + ChatColor.GOLD.toString() + ChatColor.BOLD + minutes + ChatColor.YELLOW + " Minutes!");
        } else {
            sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
        }
        return false;
    }
}
