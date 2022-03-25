package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.Register;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeaksTweaksCommand extends AbstractCommand {

    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "TT" + ChatColor.GRAY + "] ";

    public TeaksTweaksCommand() {
        super("teakstweaks", "/teakstweaks", "Teak's Tweaks Main Command!", Arrays.asList("tweaks", "tt", "vt", "vanillatweaks"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("vt") || cmd.getName().equalsIgnoreCase("vanillatweaks")) {
            sender.sendMessage(vt + ChatColor.RED + "This command is deprecated, please use /teakstweaks instead!");
        }

        if (args.length < 1) {
            sendInfoMessage(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            sendInfoMessage(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("ver")) {
            sender.sendMessage(vt + ChatColor.GREEN + "v" + Main.getPlugin(Main.class).getDescription().getVersion());
            return true;
        }

        if (args[0].equalsIgnoreCase("support")) {
            sender.sendMessage(vt + ChatColor.GREEN + "https://discord.gg/wfP4SkZx6s");
            return true;
        }

        if (args[0].equalsIgnoreCase("update")) {
            sender.sendMessage(vt + ChatColor.WHITE + "You can check for updates at " + ChatColor.YELLOW + "https://www.spigotmc.org/resources/teaks-tweaks.94021/");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("teakstweaks.reload")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            Main.getPlugin(Main.class).reloadConfig();

            Register.unregisterAll();
            Register.registerAll();

            sender.sendMessage(vt + ChatColor.GREEN + "Config reloaded!");
            return true;
        }
        return false;
    }

    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("info");
            arguments.add("version");
            arguments.add("update");
            arguments.add("reload");
            arguments.add("support");
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }

    public void sendInfoMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "-----------------------------------------------------");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GOLD + "Teak's Tweaks " + ChatColor.YELLOW + "v" + Main.getPlugin(Main.class).getDescription().getVersion());
        sender.sendMessage("");
        sender.sendMessage(ChatColor.WHITE + "Author: " + ChatColor.GREEN + Main.getPlugin(Main.class).getDescription().getAuthors().get(0));
        sender.sendMessage(ChatColor.WHITE + "Config Version: " + ChatColor.GREEN + Main.getPlugin(Main.class).getConfig().getString("config.version"));
        sender.sendMessage(ChatColor.WHITE + "Config Generated: " + ChatColor.GREEN + Main.getPlugin(Main.class).getConfig().getString("config.plugin-version"));
        sender.sendMessage(ChatColor.WHITE + "Support Server: " + ChatColor.GREEN + "https://discord.gg/wfP4SkZx6s");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "-----------------------------------------------------");
    }
}
