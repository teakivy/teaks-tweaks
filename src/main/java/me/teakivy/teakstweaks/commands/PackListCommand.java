package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class PackListCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public PackListCommand() {
        super(MessageHandler.getCmdName("pack-list"), MessageHandler.getCmdUsage("pack-list"), MessageHandler.getCmdDescription("pack-list"), MessageHandler.getCmdAliases("pack-list"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("teakstweaks.packlist.execute")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        ArrayList<String> packs = main.getPacks();
        String str = ChatColor.WHITE + "Packs (" + packs.size() + "): " + arrayToString(packs);
        ArrayList<String> ctweaks = main.getCraftingTweaks();
        String str2 = ChatColor.WHITE + "Crafting Tweaks (" + ctweaks.size() + "): " + arrayToString(ctweaks);

        sender.sendMessage(str);
        sender.sendMessage(str2);

        return false;
    }

    public String arrayToString(ArrayList<String> arr) {
        StringBuilder str = new StringBuilder();
        if (arr.size() > 0) {
            for (int i = 0; i < arr.size() - 1; i++) {
                str.append(ChatColor.GREEN).append(arr.get(i));
                str.append(ChatColor.WHITE).append(", ");
            }
            str.append(ChatColor.GREEN).append(arr.get(arr.size() - 1));
        }

        return str.toString();
    }
}
