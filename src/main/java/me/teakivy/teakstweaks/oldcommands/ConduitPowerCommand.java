package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.utils.oldcommand.AbstractCommand;
import me.teakivy.teakstweaks.utils.oldcommand.CommandType;
import me.teakivy.teakstweaks.utils.oldcommand.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ConduitPowerCommand extends AbstractCommand {

    public ConduitPowerCommand() {
        super(CommandType.PLAYER_ONLY, "spectator-conduit-power", "conduitpower", Permission.COMMAND_CONDUITPOWER, List.of("cp"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.SPECTATOR) {
            sendError("wrong_gamemode");
            return;
        }

        sendMessage("toggled");
        if (player.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
            player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, false, true));
    }
}
