package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.coordshud.HUD;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import org.bukkit.entity.Player;

import java.util.List;

public class CoordsHudCommand extends AbstractCommand {

    public CoordsHudCommand() {
        super("coords-hud", "coordshud", "/coordshud", List.of("ch"), CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (!checkPermission(player, "toggle")) return;

        if (getConfig().getBoolean("packs.coords-hud.force-enable")) {
            player.sendMessage(ErrorType.COMMAND_DISABLED.m());
            return;
        }

        HUD.setEnabled(player, !HUD.isEnabled(player));

        player.sendMessage(getText("toggled"));
    }
}