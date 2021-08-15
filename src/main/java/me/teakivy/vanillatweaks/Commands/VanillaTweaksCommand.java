package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.ErrorType;
import me.teakivy.vanillatweaks.Utils.Register.Register;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class VanillaTweaksCommand extends AbstractCommand {

    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public VanillaTweaksCommand() {
        super("vanillatweaks", "/vanillatweaks", "Vanilla Tweaks Main Command!", Collections.singletonList("vt"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ErrorType.NOT_OP.m());
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(vt + ChatColor.RED + "Usage: /" + label + " reload");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            Main.getPlugin(Main.class).reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Main.getPlugin(Main.class).getConfig().getString("reload.message"))));
        }

        Register.unregisterAll();
        Register.registerAll();
        return false;
    }

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("reload");
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
