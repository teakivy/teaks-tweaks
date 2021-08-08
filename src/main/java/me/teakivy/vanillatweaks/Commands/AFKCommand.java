package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Survival.AFKDisplay.AFK;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AFKCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public AFKCommand() {
        super("afk", "/afk", "Get away from your keyboard!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.afk-display.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(vt + "This command can only be ran by a Player!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            if (!main.getConfig().getBoolean("packs.afk-display.allow-afk-command")) {
                player.sendMessage(vt + ChatColor.RED + "This command has been disabled a server administrator!");
                return true;
            }
            if (AFK.afk.containsKey(player.getUniqueId())) {
                if (AFK.afk.get(player.getUniqueId())) {
                    AFK.unAFK(player);
                } else {
                    AFK.afk(player);
                }
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("uninstall")) {
            if (player.isOp()) {
                AFK.uninstall();
            } else {
                player.sendMessage(vt + ChatColor.RED + "You must be an OP to run this command!");
            }
        }
        return false;
    }

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("uninstall");
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
