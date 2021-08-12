package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Survival.AFKDisplay.AFK;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AFKCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = MessageHandler.getMessage("plugin.message-prefix");

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public AFKCommand() {
        super(MessageHandler.getCmdName("afk"), MessageHandler.getCmdUsage("afk"), MessageHandler.getCmdDescription("afk"), MessageHandler.getCmdAliases("afk"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.afk-display.enabled")) {
            sender.sendMessage(vt + MessageHandler.getMessage("plugin.error.pack-not-enabled"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(vt + MessageHandler.getMessage("plugin.error.not-player"));
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            if (!main.getConfig().getBoolean("packs.afk-display.allow-afk-command")) {
                sender.sendMessage(vt + MessageHandler.getMessage("plugin.error.command-disabled"));
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
                sender.sendMessage(vt + MessageHandler.getMessage("plugin.error.no-op"));
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
