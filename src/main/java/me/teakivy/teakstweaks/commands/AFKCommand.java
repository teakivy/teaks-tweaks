package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.afkdisplay.AFK;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.command.TabCompleteEvent;
import org.bukkit.entity.Player;

import java.util.List;

public class AFKCommand extends AbstractCommand {

    public AFKCommand() {
        super("afk-display", "afk", "[uninstall]", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (event.getArgsLength() == 1 && event.getArg(0).equals("uninstall")) {
            if (!checkPermission("uninstall")) return;

            AFK.uninstall();
        }

        if (!getConfig().getBoolean("packs.afk-display.allow-afk-command")) {
            sendError(ErrorType.COMMAND_DISABLED);
            return;
        }

        if (!checkPermission("toggle")) return;

        if (AFK.afk.containsKey(player.getUniqueId())) {
            if (AFK.afk.get(player.getUniqueId())) {
                AFK.unAFK(player);
                return;
            }

            AFK.afk(player, true);
        }
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.getArgsLength() == 1) return List.of("uninstall");

        return null;
    }
}
