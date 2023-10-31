package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ConduitPowerCommand extends AbstractCommand {

    public ConduitPowerCommand() {
        super("spectator-conduit-power", "conduitpower", List.of("cp"), CommandType.PLAYER_ONLY);
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

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, true, true));
    }
}
