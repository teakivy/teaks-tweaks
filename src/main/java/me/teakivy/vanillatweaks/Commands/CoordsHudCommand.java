package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Collections;

public class CoordsHudCommand extends BukkitCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public CoordsHudCommand(String name) {
        super(name);
        this.setDescription("Coords Hud Main Command");
        this.setAliases(Collections.singletonList("ch"));
        this.usageMessage = "/coordshud toggle";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!main.getConfig().getBoolean("packs.coords-hud.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /" + commandLabel + " <option>");
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("toggle")) {
                if (main.getConfig().getBoolean("packs.coords-hud.force-enable")) {
                    player.sendMessage(vt + ChatColor.RED + "You cannot toggle Coordinates HUD!");
                    return true;
                }
                if (!Main.chEnabled.contains(player.getUniqueId())) Main.chEnabled.add(player.getUniqueId());
                else Main.chEnabled.remove(player.getUniqueId());
                player.sendMessage(vt + ChatColor.GREEN + "Toggled Coords HUD");
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /" + commandLabel + " <option>");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
        }
        return false;
    }
}