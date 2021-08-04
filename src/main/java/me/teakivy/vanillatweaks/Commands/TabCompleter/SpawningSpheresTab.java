package me.teakivy.vanillatweaks.Commands.TabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SpawningSpheresTab implements TabCompleter {

    List<String> arguments1 = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();


    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        arguments1.add("create");
        arguments1.add("remove");
        arguments1.add("teleport");
        arguments1.add("tp");

        arguments2.add("red");
        arguments2.add("blue");
        arguments2.add("green");

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2) {
            for (String a : arguments2) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}
