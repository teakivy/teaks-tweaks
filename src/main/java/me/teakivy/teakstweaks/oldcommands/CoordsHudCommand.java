package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.packs.coordshud.HUD;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.oldcommand.AbstractCommand;
import me.teakivy.teakstweaks.utils.oldcommand.CommandType;
import me.teakivy.teakstweaks.utils.oldcommand.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;

import java.util.List;

public class CoordsHudCommand extends AbstractCommand {

    public CoordsHudCommand() {
        super(CommandType.PLAYER_ONLY, "coords-hud", "coordshud", Permission.COMMAND_COORDSHUD, List.of("ch"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (getPackConfig().getBoolean("force-enable")) {
            sendError(ErrorType.COMMAND_DISABLED);
            return;
        }

        HUD.setEnabled(event.getPlayer(), !HUD.isEnabled(event.getPlayer()));

        sendMessage("toggled");
    }
}