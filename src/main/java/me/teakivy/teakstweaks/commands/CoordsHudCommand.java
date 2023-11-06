package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.coordshud.HUD;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;

import java.util.List;

public class CoordsHudCommand extends AbstractCommand {

    public CoordsHudCommand() {
        super(CommandType.PLAYER_ONLY, "coords-hud", "coordshud", List.of("ch"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (!checkPermission("toggle")) return;

        if (getPackConfig().getBoolean("force-enable")) {
            sendError(ErrorType.COMMAND_DISABLED);
            return;
        }

        HUD.setEnabled(event.getPlayer(), !HUD.isEnabled(event.getPlayer()));

        sendMessage("toggled");
    }
}