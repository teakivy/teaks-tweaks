package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Packs.Teleportation.Back.Back;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class HomeCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    HashMap<UUID, Long> cooldown = new HashMap<>();

    FileConfiguration data = main.data.getConfig();

    public HomeCommand() {
        super(MessageHandler.getCmdName("home"), MessageHandler.getCmdUsage("home"), MessageHandler.getCmdDescription("home"), MessageHandler.getCmdAliases("home"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.homes.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(MessageHandler.getCmdMessage("home", "error.missing-home"));
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {

            if (args.length < 2) {
                player.sendMessage(MessageHandler.getCmdMessage("home", "error.missing-home-name"));
                return true;
            }
            if (!sender.hasPermission("vanillatweaks.homes.manage")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            String name = args[1].toLowerCase();

            if (data.contains("homes." + player.getUniqueId() + "." + name)) {
                player.sendMessage(MessageHandler.getCmdMessage("home", "error.home-already-exists").replace("%name%", name));
                return true;
            }
            if (main.getConfig().getInt("packs.homes.max-homes") > 0) {
                if (data.getConfigurationSection("homes." + player.getUniqueId()) != null) {
                    if ((long) data.getConfigurationSection("homes." + player.getUniqueId()).getKeys(false).size() >= main.getConfig().getInt("packs.homes.max-homes")) {
                        player.sendMessage(MessageHandler.getCmdMessage("home", "error.max-homes-reached").replace("%amount%", "" + main.getConfig().getInt("packs.homes.max-homes")));
                        return true;
                    }
                }
            }

            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            String world = player.getWorld().getName();


            main.data.getConfig().set("homes." + player.getUniqueId() + "." + name + ".world", world);
            main.data.getConfig().set("homes." + player.getUniqueId() + "." + name + ".x", x);
            main.data.getConfig().set("homes." + player.getUniqueId() + "." + name + ".y", y);
            main.data.getConfig().set("homes." + player.getUniqueId() + "." + name + ".z", z);
            try {
                main.data.saveConfig();
                player.sendMessage(MessageHandler.getCmdMessage("home", "set-home").replace("%name%", name));
            } catch (IOException e) {
                e.printStackTrace();
                player.sendMessage(MessageHandler.getCmdMessage("home", "error.cant-set-home").replace("%name%", name));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("remove")) {
            if (args.length < 2) {
                player.sendMessage(MessageHandler.getCmdMessage("home", "error.missing-home-name").replace("%name%", args[0].toLowerCase()));
                return true;
            }
            if (!sender.hasPermission("vanillatweaks.homes.manage")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            String name = args[1].toLowerCase();

            if (!data.contains("homes." + player.getUniqueId() + "." + name)) {
                player.sendMessage(MessageHandler.getCmdMessage("home", "error.home-doesnt-exist").replace("%name%", name));
                return true;
            }

            main.data.getConfig().set("homes." + player.getUniqueId() + "." + name, null);
            player.sendMessage(MessageHandler.getCmdMessage("home", "removed-home").replace("%name%", name));
            return true;
        }

        if (!data.contains("homes." + player.getUniqueId())) {
            player.sendMessage(MessageHandler.getCmdMessage("home", "error.no-homes-yet"));
            return true;
        }

        if (main.getConfig().getInt("packs.homes.teleport-cooldown") > 0) {
            if (cooldown.containsKey(player.getUniqueId())) {
                if (cooldown.get(player.getUniqueId()) + (main.getConfig().getInt("packs.homes.teleport-cooldown") * 1000L) > System.currentTimeMillis()) {
                    player.sendMessage(MessageHandler.getCmdMessage("home", "error.on-cooldown").replace("%time%", main.getConfig().getInt("packs.homes.teleport-cooldown") + ""));
                    return true;
                }
            }
            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
        }
        if (main.getConfig().getInt("packs.homes.teleport-delay") == 0) {

            if (!sender.hasPermission("vanillatweaks.homes.teleport")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (data.contains("homes." + player.getUniqueId() + "." + args[0].toLowerCase())) {
                player.sendMessage(MessageHandler.getCmdMessage("home", "teleporting-to-home").replace("%name%", args[0].toLowerCase()));
                Back.backLoc.put(player.getUniqueId(), player.getLocation());
                player.teleport(new Location(Bukkit.getWorld(data.getString("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".world")), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".x"), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".y"), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".z")));
            } else {
                player.sendMessage(MessageHandler.getCmdMessage("home", "error.home-doesnt-exist").replace("%name%", args[0].toLowerCase()));
            }
            return true;
        } else if (main.getConfig().getInt("packs.homes.teleport-delay") > 0) {
            Location loc = player.getLocation();

            if (!sender.hasPermission("vanillatweaks.homes.teleport")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            if (data.contains("homes." + player.getUniqueId() + "." + args[0].toLowerCase())) {
                player.sendMessage(MessageHandler.getCmdMessage("home", "teleporting-to-home").replace("%name%", args[0].toLowerCase()));
                Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                    if (player.getLocation().getX() == loc.getX() && player.getLocation().getY() == loc.getY() && player.getLocation().getZ() == loc.getZ()) {
                        Back.backLoc.put(player.getUniqueId(), player.getLocation());
                        player.teleport(new Location(Bukkit.getWorld(data.getString("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".world")), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".x"), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".y"), data.getDouble("homes." + player.getUniqueId() + "." + args[0].toLowerCase() + ".z")));
                    } else {
                        player.sendMessage(MessageHandler.getCmdMessage("home", "error.player-moved"));
                    }
                }, main.getConfig().getInt("packs.homes.teleport-delay") * 20L);
            } else {
                player.sendMessage(MessageHandler.getCmdMessage("home", "error.home-doesnt-exist").replace("%name%", args[0].toLowerCase()));
            }
            return true;


        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        List<String> arguments1 = new ArrayList<String>();
        List<String> arguments2 = new ArrayList<String>();

        Player player = (Player) sender;

        arguments1.add("set");
        arguments1.add("remove");
        if (data.getConfigurationSection("homes." + player.getUniqueId()) != null) {
            arguments1.addAll(data.getConfigurationSection("homes." + player.getUniqueId()).getKeys(false));
        }

        if (data.getConfigurationSection("homes." + player.getUniqueId()) != null) {
            arguments2.addAll(data.getConfigurationSection("homes." + player.getUniqueId()).getKeys(false));
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2) {
            for (String a : arguments2) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}
