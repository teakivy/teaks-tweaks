package me.teakivy.vanillatweaks.Commands.TabCompleter;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ShrineTab implements TabCompleter {

    Main main = Main.getPlugin(Main.class);

    List<String> arguments1 = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments1.isEmpty()) {
            arguments1.add("create");
            arguments1.add("remove");
            arguments1.add("uninstall");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return null;
    }
}
