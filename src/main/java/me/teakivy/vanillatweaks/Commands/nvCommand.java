package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class nvCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("nv") || command.getName().equalsIgnoreCase("nightvision")) {

            if (!main.getConfig().getBoolean("packs.spectator-night-vision.enabled")) {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
                return true;
            }

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    else player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 0, true, true));
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Toggled Night Vision");
                } else {
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "You must be in Spectator Mode to use this command!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
            }
        }
        return false;
    }
}
