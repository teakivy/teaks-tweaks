package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeaksTweaksCommand extends AbstractCommand {

    Main main = Main.getInstance();

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

        if (args[0].equalsIgnoreCase("update")) {
            if (!sender.hasPermission("teakstweaks.manage")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "This command will download the latest version of Teak's Tweaks and reload the plugin.");
                sender.sendMessage(ChatColor.RED + "If you are sure you want to do this, please run " + ChatColor.GOLD + "/teakstweaks update confirm");
                return true;
            }

            if (!args[1].equalsIgnoreCase("confirm")) {
                sender.sendMessage(ChatColor.RED + "This command will download the latest version of Teak's Tweaks and reload the plugin.");
                sender.sendMessage(ChatColor.RED + "If you are sure you want to do this, please run /teakstweaks update confirm");
                return true;
            }

            try {
                Logger.log(Logger.LogLevel.INFO, "Starting Teak's Tweaks update sequence. This may take up to a minute.", true);

                UpdateChecker.update();
            } catch (IOException | InvalidPluginException | InvalidDescriptionException e) {
                sender.sendMessage(ChatColor.RED + "An error occurred while updating Teak's Tweaks!");
                throw new RuntimeException(e);
            }

            return false;
        }


        if (args[0].equalsIgnoreCase("devupdate")) {
            if (!sender.hasPermission("teakstweaks.manage")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "This command will download the latest version of Teak's Tweaks from the Dev Branch and reload the plugin.");
                sender.sendMessage(ChatColor.RED + "If you are sure you want to do this, please run " + ChatColor.GOLD + "/teakstweaks devupdate confirm");
                return true;
            }

            if (!args[1].equalsIgnoreCase("confirm")) {
                sender.sendMessage(ChatColor.RED + "This command will download the latest version of Teak's Tweaks from the Dev Branch and reload the plugin.");
                sender.sendMessage(ChatColor.RED + "If you are sure you want to do this, please run /teakstweaks devupdate confirm");
                return true;
            }

            try {
                Logger.log(Logger.LogLevel.INFO, "Starting Teak's Tweaks update sequence. This may take up to a minute.", true);

                File pluginsFolder = new File("plugins");
                File teaksTweaksFolder = new File(pluginsFolder, "TeaksTweaks");

                if (!teaksTweaksFolder.exists()) {
                    teaksTweaksFolder.mkdir();
                }

                File devFile = new File(teaksTweaksFolder, "dev.txt");

                if (!devFile.exists()) {
                    devFile.createNewFile();
                }

                FileWriter writer = new FileWriter(devFile);
                writer.write("true");
                writer.close();

                UpdateChecker.update();
            } catch (IOException | InvalidPluginException | InvalidDescriptionException e) {
                sender.sendMessage(ChatColor.RED + "An error occurred while updating Teak's Tweaks!");
                throw new RuntimeException(e);
            }

            return false;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            if (!sender.hasPermission("teakstweaks.manage")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "You must specify a pack to enable!");
                return true;
            }
            if (args[1].equalsIgnoreCase("all")) {
                main.getRegister().unregisterAll();
                main.getRegister().registerAll(true);

                for (String pack : main.getRegister().getAllPacks()) {
                    main.getConfig().set("packs." + pack + ".enabled", true);
                }
                main.saveConfig();

                sender.sendMessage(ChatColor.GREEN + "All packs enabled!");
                return true;
            }

            if (!main.getRegister().getDisabledPacks().contains(args[1])) {
                sender.sendMessage(ChatColor.RED + args[1] + " cannot be enabled!");
                return true;
            }

            if (main.getRegister().getDisabledPacks().contains(args[1])) {
                main.getRegister().registerPack(args[1]);

                main.getConfig().set("packs." + args[1] + ".enabled", true);
                main.saveConfig();

                sender.sendMessage(ChatColor.GREEN + args[1] + " enabled!");
                return true;
            }

            sender.sendMessage(ChatColor.RED + args[1] + " cannot be enabled!");
            return true;
        }

        if (args[0].equalsIgnoreCase("disable")) {
            if (!sender.hasPermission("teakstweaks.manage")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "You must specify a pack to disable!");
                return true;
            }
            if (args[1].equalsIgnoreCase("all")) {
                main.getRegister().unregisterAll();

                for (String pack : main.getRegister().getEnabledPacks()) {
                    main.getConfig().set("packs." + pack + ".enabled", false);
                }
                main.saveConfig();

                sender.sendMessage(ChatColor.GREEN + "All packs disabled!");
                return true;
            }

            if (!main.getRegister().getEnabledPacks().contains(args[1])) {
                sender.sendMessage(ChatColor.RED + args[1] + " cannot be disabled!");
                return true;
            }

            if (main.getRegister().getEnabledPacks().contains(args[1])) {
                main.getRegister().unregisterPack(args[1]);

                main.getConfig().set("packs." + args[1] + ".enabled", true);
                main.saveConfig();


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
            arguments.add("devupdate");
            arguments.add("update");
//            arguments.add("reload");
            arguments.add("support");
            arguments.add("enable");
            arguments.add("disable");
        }

        if (enabled.isEmpty()) {
            enabled.addAll(main.getRegister().getEnabledPacks());
            enabled.add("all");
        }

        if (disabled.isEmpty()) {
            disabled.addAll(main.getRegister().getDisabledPacks());
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
        if (main.getConfig().getBoolean("config.dev-mode")) {
            sender.sendMessage(ChatColor.WHITE + "Dev Mode: " + ChatColor.GREEN + "Enabled");
        }
        sender.sendMessage(ChatColor.WHITE + "Support Server: " + ChatColor.GREEN + "https://discord.gg/wfP4SkZx6s");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "-----------------------------------------------------");
    }
}
