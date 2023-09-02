package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeaksTweaksCommand extends AbstractCommand {

    public TeaksTweaksCommand() {
        super(null, "teakstweaks", "/teakstweaks", "Teak's Tweaks Main Command!", Arrays.asList("tweaks", "tt"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        enabled.clear();
        disabled.clear();

        if (args.length < 1) {
            sendInfoMessage(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            sendInfoMessage(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("ver")) {
            sender.sendMessage(ChatColor.GREEN + "v" + Main.getInstance().getDescription().getVersion());
            return true;
        }

        if (args[0].equalsIgnoreCase("support")) {
            sender.sendMessage(ChatColor.GREEN + "https://discord.gg/wfP4SkZx6s");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "You must specify a pack to enable!");
                return true;
            }
            if (args[1].equalsIgnoreCase("all")) {
                Main.getRegister().unregisterAll();
                Main.getRegister().registerAll(true);

                for (String pack : Main.getRegister().getAllPacks()) {
                    Main.getInstance().getConfig().set("packs." + pack + ".enabled", true);
                }
                Main.getInstance().saveConfig();

                sender.sendMessage(ChatColor.GREEN + "All packs enabled!");
                return true;
            }

            if (!Main.getRegister().getDisabledPacks().contains(args[1])) {
                sender.sendMessage(ChatColor.RED + args[1] + " cannot be enabled!");
                return true;
            }

            if (Main.getRegister().getDisabledPacks().contains(args[1])) {
                Main.getRegister().registerPack(args[1]);

                Main.getInstance().getConfig().set("packs." + args[1] + ".enabled", true);
                Main.getInstance().saveConfig();

                sender.sendMessage(ChatColor.GREEN + args[1] + " enabled!");
                return true;
            }

            sender.sendMessage(ChatColor.RED + args[1] + " cannot be enabled!");
            return true;
        }

        if (args[0].equalsIgnoreCase("disable")) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "You must specify a pack to disable!");
                return true;
            }
            if (args[1].equalsIgnoreCase("all")) {
                Main.getRegister().unregisterAll();

                for (String pack : Main.getRegister().getEnabledPacks()) {
                    Main.getInstance().getConfig().set("packs." + pack + ".enabled", false);
                }
                Main.getInstance().saveConfig();

                sender.sendMessage(ChatColor.GREEN + "All packs disabled!");
                return true;
            }

            if (!Main.getRegister().getEnabledPacks().contains(args[1])) {
                sender.sendMessage(ChatColor.RED + args[1] + " cannot be disabled!");
                return true;
            }

            if (Main.getRegister().getEnabledPacks().contains(args[1])) {
                Main.getRegister().unregisterPack(args[1]);

                Main.getInstance().getConfig().set("packs." + args[1] + ".enabled", true);
                Main.getInstance().saveConfig();


                sender.sendMessage(ChatColor.GREEN + args[1] + " disabled!");
                return true;
            }

            sender.sendMessage(ChatColor.RED + args[1] + " cannot be disabled!");
            return true;
        }


//        if (args[0].equalsIgnoreCase("reload")) {
//            if (!sender.hasPermission("teakstweaks.reload")) {
//                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
//                return true;
//            }
//            Main.getInstance().reloadConfig();
//            main.getRegister().registerAll();
//
//            sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
//            return true;
//        }
        return false;
    }

    List<String> arguments = new ArrayList<>();

    List<String> enabled = new ArrayList<>();
    List<String> disabled = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("info");
            arguments.add("version");
//            arguments.add("reload");
            arguments.add("support");
            arguments.add("enable");
            arguments.add("disable");
        }

        if (enabled.isEmpty()) {
            enabled.addAll(Main.getRegister().getEnabledPacks());
            enabled.add("all");
        }

        if (disabled.isEmpty()) {
            disabled.addAll(Main.getRegister().getDisabledPacks());
            disabled.add("all");
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("update")) {
            if ("confirm".startsWith(args[1].toLowerCase()))
                result.add("confirm");
            return result;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("disable")) {
            for (String a : enabled) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("enable")) {
            for (String a : disabled) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }

    public void sendInfoMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "-----------------------------------------------------");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GOLD + "Teak's Tweaks " + ChatColor.YELLOW + "v" + Main.getInstance().getDescription().getVersion());
        sender.sendMessage("");
        sender.sendMessage(ChatColor.WHITE + "Author: " + ChatColor.GREEN + Main.getInstance().getDescription().getAuthors().get(0));
        sender.sendMessage(ChatColor.WHITE + "Config Version: " + ChatColor.GREEN + Main.getInstance().getConfig().getString("config.version"));
        sender.sendMessage(ChatColor.WHITE + "Config Generated: " + ChatColor.GREEN + Main.getInstance().getConfig().getString("config.plugin-version"));
        if (Main.getInstance().getConfig().getBoolean("config.dev-mode")) {
            sender.sendMessage(ChatColor.WHITE + "Dev Mode: " + ChatColor.GREEN + "Enabled");
        }
        sender.sendMessage(ChatColor.WHITE + "Support Server: " + ChatColor.GREEN + "https://discord.gg/wfP4SkZx6s");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "-----------------------------------------------------");
    }
}
