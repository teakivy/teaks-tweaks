package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PackList extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "TT" + ChatColor.GRAY + "] ";

    public PackList() {
        super(MessageHandler.getCmdName("pack-list"), MessageHandler.getCmdUsage("pack-list"), MessageHandler.getCmdDescription("pack-list"), MessageHandler.getCmdAliases("pack-list"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!sender.hasPermission("teakstweaks.packlist.execute")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        ArrayList<String> packs = main.getPacks();

        String str = ChatColor.WHITE + "Packs (" + packs.size() + "): " + arrayToString(packs);

        player.sendMessage(str);

        return false;
    }

    public String arrayToString(ArrayList<String> arr) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arr.size() - 1; i++) {
            str.append(ChatColor.GREEN).append(arr.get(i));
            str.append(ChatColor.WHITE).append(", ");
        }
        str.append(ChatColor.GREEN).append(arr.get(arr.size() - 1));

        return str.toString();
    }
}
