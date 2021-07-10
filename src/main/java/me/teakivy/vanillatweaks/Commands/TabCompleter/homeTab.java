package me.teakivy.vanillatweaks.Commands.TabCompleter;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class homeTab implements TabCompleter {

    Main main = Main.getPlugin(Main.class);

    List<String> arguments1 = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();

    FileConfiguration data = main.data.getConfig();

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        Player player = (Player) sender;

        if (arguments1.isEmpty()) {
            arguments1.add("set");
            arguments1.add("delete");

            arguments1.addAll(data.getConfigurationSection("homes." + player.getUniqueId()).getKeys(false));
        }

        if (arguments2.isEmpty()) {
            arguments2.addAll(data.getConfigurationSection("homes." + player.getUniqueId()).getKeys(false));
        }

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
