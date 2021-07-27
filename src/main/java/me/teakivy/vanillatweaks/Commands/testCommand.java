package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.ClassicFishingLoot.FishingLootTable;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class testCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("test")) {
            Player player = (Player) sender;
            World world = player.getWorld();
            if (main.getConfig().getBoolean("config.dev-mode")) {
                player.sendMessage(vt + ChatColor.YELLOW + "Did I even need a fishing rod?");
                player.getInventory().addItem(FishingLootTable.generateFishingLoot(0, false));
            } else {
                player.sendMessage(vt + ChatColor.YELLOW + "Hey! Looks like you found my test command!");
                if (player.isOp()) {
                    player.sendMessage(ChatColor.YELLOW + "This command is used to test new features! Enable it marking " + ChatColor.GRAY + "config.dev-mode: true" + ChatColor.YELLOW + "!");
                }
            }
        }
        return false;
    }
}
