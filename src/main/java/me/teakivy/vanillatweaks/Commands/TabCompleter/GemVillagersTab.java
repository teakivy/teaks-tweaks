package me.teakivy.vanillatweaks.Commands.TabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GemVillagersTab implements TabCompleter {


    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        List<String> arguments1 = new ArrayList<String>();

        List<String> villagerArgs = new ArrayList<String>();
        List<String> gemArgs = new ArrayList<String>();

        Player player = (Player) sender;

        arguments1.add("villager");
        arguments1.add("give");

        villagerArgs.add("aquatic");
        villagerArgs.add("concrete");
        villagerArgs.add("gem_trading");
        villagerArgs.add("functional");
        villagerArgs.add("gem_collector");
        villagerArgs.add("more_blocks");
        villagerArgs.add("natural");
        villagerArgs.add("nether");
        villagerArgs.add("ores");
        villagerArgs.add("precious");
        villagerArgs.add("redstone");
        villagerArgs.add("stones");
        villagerArgs.add("wood");

        gemArgs.add("aquamarine");
        gemArgs.add("amethyst");
        gemArgs.add("ruby");
        gemArgs.add("topaz");
        gemArgs.add("sapphire");

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("villager")) {
            for (String a : villagerArgs) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            for (String a : gemArgs) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}
