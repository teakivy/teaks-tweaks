package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class PackListCommand extends AbstractCommand {

    public PackListCommand() {
        super(null, "packlist", "/packlist", List.of("pkl"), CommandType.ALL);
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        ArrayList<String> packs = TeaksTweaks.getInstance().getPacks();
        String str = getString("packs").replace("%count%", packs.size() + "") + arrayToString(packs);

        ArrayList<String> ctweaks = TeaksTweaks.getInstance().getCraftingTweaks();
        String str2 = getString("craftingtweaks").replace("%count%", ctweaks.size() + "") + arrayToString(ctweaks);

        sender.sendMessage(str);
        sender.sendMessage(str2);
    }

    public String arrayToString(ArrayList<String> arr) {
        StringBuilder str = new StringBuilder();
        if (!arr.isEmpty()) {
            for (int i = 0; i < arr.size() - 1; i++) {
                str.append(ChatColor.GREEN).append(arr.get(i));
                str.append(ChatColor.WHITE).append(", ");
            }
            str.append(ChatColor.GREEN).append(arr.get(arr.size() - 1));
        }

        return str.toString();
    }
}
