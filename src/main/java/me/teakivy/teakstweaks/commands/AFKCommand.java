package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.afkdisplay.AFK;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.*;
import org.bukkit.entity.Player;

public class AFKCommand extends AbstractCommand {

    public AFKCommand() {
        super(CommandType.PLAYER_ONLY, "afk-display", "afk", Arg.optional("uninstall"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (event.isArgsSize(1) && event.isArg(0, "uninstall")) {
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
}
