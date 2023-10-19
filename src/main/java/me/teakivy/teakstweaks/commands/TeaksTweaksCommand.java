package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.lang.Translatable;
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
            sender.sendMessage(getString("version").replace("%version%", Main.getInstance().getDescription().getVersion()));
            return true;
        }

        if (args[0].equalsIgnoreCase("support")) {
            sender.sendMessage(getString("support").replace("%discord%", get("plugin.discord")));
            return true;
        }

        if (args[0].equalsIgnoreCase("update")) {
            sender.sendMessage(getString("update").replace("%url%", get("plugin.url")));
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage(getString("error.enable.specify_pack"));
                return true;
            }
            if (args[1].equalsIgnoreCase("all")) {
                Main.getRegister().unregisterAll();
                Main.getRegister().registerAll(true);

                for (String pack : Main.getRegister().getAllPacks()) {
                    Main.getInstance().getConfig().set("packs." + pack + ".enabled", true);
                }
                Main.getInstance().saveConfig();

                sender.sendMessage(getString("enable.all"));
                return true;
            }

            if (!Main.getRegister().getDisabledPacks().contains(args[1])) {
                sender.sendMessage(getString("error.enable.cannot_enable").replace("%pack%", args[1]));
                return true;
            }

            if (Main.getRegister().getDisabledPacks().contains(args[1])) {
                Main.getRegister().registerPack(args[1]);

                Main.getInstance().getConfig().set("packs." + args[1] + ".enabled", true);
                Main.getInstance().saveConfig();

                sender.sendMessage(getString("enable.success").replace("%pack%", args[1]));
                return true;
            }

            sender.sendMessage(getString("error.enable.cannot_enable").replace("%pack%", args[1]));
            return true;
        }

        if (args[0].equalsIgnoreCase("disable")) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage(getString("error.disable.specify_pack"));
                return true;
            }
            if (args[1].equalsIgnoreCase("all")) {
                Main.getRegister().unregisterAll();

                for (String pack : Main.getRegister().getEnabledPacks()) {
                    Main.getInstance().getConfig().set("packs." + pack + ".enabled", false);
                }
                Main.getInstance().saveConfig();

                sender.sendMessage(getString("disable.all"));
                return true;
            }

            if (!Main.getRegister().getEnabledPacks().contains(args[1])) {
                sender.sendMessage(getString("error.disable.cannot_disable").replace("%pack%", args[1]));
                return true;
            }

            if (Main.getRegister().getEnabledPacks().contains(args[1])) {
                Main.getRegister().unregisterPack(args[1]);

                Main.getInstance().getConfig().set("packs." + args[1] + ".enabled", true);
                Main.getInstance().saveConfig();


                sender.sendMessage(getString("disable.success").replace("%pack%", args[1]));
                return true;
            }

            sender.sendMessage(getString("error.disable.cannot_disable").replace("%pack%", args[1]));
            return true;
        }

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
            arguments.add("update");
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
        sender.sendMessage(getString("info.dashed_line"));
        sender.sendMessage("");
        sender.sendMessage(getString("info.title").replace("%version%", Main.getInstance().getDescription().getVersion()));
        sender.sendMessage("");
        sender.sendMessage(getString("info.author").replace("%author%", get("plugin.author")));
        sender.sendMessage(getString("info.config_version").replace("%config_version%", Main.getInstance().getConfig().getString("config.version")));
        sender.sendMessage(getString("info.config_generated").replace("%config_generated%", Main.getInstance().getConfig().getString("config.plugin-version")));
        if (Main.getInstance().getConfig().getBoolean("config.dev-mode")) {
            sender.sendMessage(getString("info.dev_mode_enabled"));
        }
        sender.sendMessage(getString("info.support").replace("%discord%", get("plugin.discord")));
        sender.sendMessage("");
        sender.sendMessage(getString("info.dashed_line"));
    }

    @Override
    public String getString(String key) {
        return Translatable.get("teakstweakscommand." + key);
    }
}
