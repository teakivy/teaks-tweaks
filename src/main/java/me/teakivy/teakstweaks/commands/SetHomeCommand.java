package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;

public class SetHomeCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    FileConfiguration data = main.data.getConfig();

    public SetHomeCommand() {
        super(MessageHandler.getCmdName("sethome"), MessageHandler.getCmdUsage("sethome"), MessageHandler.getCmdDescription("sethome"), MessageHandler.getCmdAliases("sethome"));
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


        if (!sender.hasPermission("teakstweaks.homes.manage")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(MessageHandler.getCmdMessage("sethome", "error.missing-home-name"));
            return true;
        }

        String name = args[0].toLowerCase();

        if (data.contains("homes." + player.getUniqueId() + "." + name)) {
            player.sendMessage(MessageHandler.getCmdMessage("sethome", "error.home-already-exists").replace("%name%", name));
            return true;
        }
        if (main.getConfig().getInt("packs.homes.max-homes") > 0) {
            if (data.getConfigurationSection("homes." + player.getUniqueId()) != null) {
                if (Objects.requireNonNull(data.getConfigurationSection("homes." + player.getUniqueId())).getKeys(false).stream().count() >= main.getConfig().getInt("packs.homes.max-homes")) {
                    player.sendMessage(MessageHandler.getCmdMessage("sethome", "error.max-homes-reached").replace("%amount%", main.getConfig().getInt("packs.homes.max-homes") + ""));
                    return true;
                }
            }
        }

        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        String world = player.getWorld().getName();

        data.set("homes." + player.getUniqueId() + "." + name + ".world", world);
        data.set("homes." + player.getUniqueId() + "." + name + ".x", x);
        data.set("homes." + player.getUniqueId() + "." + name + ".y", y);
        data.set("homes." + player.getUniqueId() + "." + name + ".z", z);
        try {
            main.data.saveConfig();
            player.sendMessage(MessageHandler.getCmdMessage("sethome", "set-home").replace("%name%", name));
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage(MessageHandler.getCmdMessage("sethome", "error.cant-set-home").replace("%name%", name));
        }
        return true;
    }
}
