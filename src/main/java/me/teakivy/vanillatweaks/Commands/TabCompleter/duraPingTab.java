package me.teakivy.vanillatweaks.Commands.TabCompleter;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class duraPingTab implements TabCompleter {

    Main main = Main.getPlugin(Main.class);

    List<String> arguments1 = new ArrayList<String>();
    List<String> previewArguments = new ArrayList<String>();
    List<String> setArguments = new ArrayList<String>();
    List<String> displayArguments = new ArrayList<String>();
    List<String> tfArguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        Player player = (Player) sender;

        if (arguments1.isEmpty()) {
            arguments1.add("config");
            arguments1.add("preview");
            arguments1.add("set");
        }

        if (previewArguments.isEmpty()) {
            previewArguments.add("sound");
            previewArguments.add("display_subtitle");
            previewArguments.add("display_title");
            previewArguments.add("display_chat");
            previewArguments.add("display_actionbar");
        }

        if (setArguments.isEmpty()) {
            setArguments.add("ping_for_hand_items");
            setArguments.add("ping_for_armor_items");
            setArguments.add("ping_with_sound");
            setArguments.add("display");
        }

        if (tfArguments.isEmpty()) {
            tfArguments.add("true");
            tfArguments.add("false");
        }

        if (displayArguments.isEmpty()) {
            displayArguments.add("hidden");
            displayArguments.add("subtitle");
            displayArguments.add("title");
            displayArguments.add("chat");
            displayArguments.add("actionbar");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args[0].equalsIgnoreCase("preview") && args.length == 2) {
            for (String a : previewArguments) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args[0].equalsIgnoreCase("set") && args.length == 2) {
            for (String a : setArguments) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        if (args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("display")) {
            for (String a : displayArguments) {
                if (a.toLowerCase().startsWith(args[2].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args[0].equalsIgnoreCase("set") && (args[1].equalsIgnoreCase("ping_for_hand_items") || args[1].equalsIgnoreCase("ping_for_armor_items") || args[1].equalsIgnoreCase("ping_with_sound"))) {
            for (String a : tfArguments) {
                if (a.toLowerCase().startsWith(args[2].toLowerCase()))
                    result.add(a);
            }
            return result;
        }


        return null;
    }
}
