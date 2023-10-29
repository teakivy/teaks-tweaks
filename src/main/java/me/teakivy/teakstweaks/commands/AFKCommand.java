package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.afkdisplay.AFK;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.entity.Player;

import java.util.List;

public class AFKCommand extends AbstractCommand {

    public AFKCommand() {
        super("afk-display", "afk", "/afk [uninstall]", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length == 1 && args[0].equals("uninstall")) {
            if (!checkPermission(player, "uninstall")) return;

            AFK.uninstall();
        }

        if (!getConfig().getBoolean("packs.afk-display.allow-afk-command")) {
            player.sendMessage(ErrorType.COMMAND_DISABLED.m());
            return;
        }

        if (!checkPermission(player, "toggle")) return;

        if (AFK.afk.containsKey(player.getUniqueId())) {
            if (AFK.afk.get(player.getUniqueId())) {
                AFK.unAFK(player);
                return;
            }

            AFK.afk(player, true);
        }
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return List.of("uninstall");

        return null;
    }
}
