package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoordsHudCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public CoordsHudCommand() {
        super("coordshud", "/coordshud toggle", "Coords Hud Main Command", Collections.singletonList("ch"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.coords-hud.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <option>");
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
                sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <option>");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
        }
        return false;
    }

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("toggle");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}