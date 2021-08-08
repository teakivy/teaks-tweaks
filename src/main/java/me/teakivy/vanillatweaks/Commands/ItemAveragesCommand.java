package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Utilities.ItemAverages.ItemTracker;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collections;

public class ItemAveragesCommand extends BukkitCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";


    public ItemAveragesCommand(String name) {
        super(name);
        this.setDescription("Check how many items flow through a source in 2 minutes!");
        this.setAliases(Collections.singletonList("ia"));
        this.usageMessage = "/itemaverages";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!main.getConfig().getBoolean("packs.item-averages.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(vt + "This command can only be ran by a Player!");
            return true;
        }

        Player player = (Player) sender;
        if (args.length < 1) {
            if (main.getConfig().getBoolean("packs.item-averages.require-op") && !player.isOp()) {
                player.sendMessage(vt + ChatColor.RED + "You must be OP to use this command!");
                return true;
            }
            if (!ItemTracker.inUse) {
                player.sendMessage(vt + ChatColor.YELLOW + "Did you just track me?");
                player.sendMessage(vt + ChatColor.YELLOW + "Created a tracker source at XYZ: " + (int) Math.floor(player.getLocation().getX()) + " " + (int) Math.floor(player.getLocation().getY()) + " " + (int) Math.floor(player.getLocation().getZ()));
                ItemTracker.spawnTracker(player.getLocation().getBlock().getLocation(), player);
            } else {
                player.sendMessage(ChatColor.RED + "A tracker is already in use!");
            }
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("create")) {
                if (main.getConfig().getBoolean("packs.item-averages.require-op") && !player.isOp()) {
                    player.sendMessage(vt + ChatColor.RED + "You must be OP to use this command!");
                    return true;
                }
                if (!ItemTracker.inUse) {
                    player.sendMessage(vt + ChatColor.YELLOW + "Created a tracker source at XYZ: " + (int) Math.floor(player.getLocation().getX()) + " " + (int) Math.floor(player.getLocation().getY()) + " " + (int) Math.floor(player.getLocation().getZ()));
                    ItemTracker.spawnTracker(player.getLocation().getBlock().getLocation(), player);
                } else {
                    player.sendMessage(ChatColor.RED + "A tracker is already in use!");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if (!player.isOp()) {
                    player.sendMessage(vt + ChatColor.RED + "You must be OP to use this command!");
                    return true;
                }
                int count = 0;
                for (Entity entity : player.getWorld().getEntities()) {
                    if (entity.getScoreboardTags().contains("vt_tracker")) {
                        count++;
                        entity.remove();
                    }
                }
                player.sendMessage(vt + ChatColor.YELLOW + "Removed " + count + " Tracker Sources!");
            }
        }
        return false;
    }
}
