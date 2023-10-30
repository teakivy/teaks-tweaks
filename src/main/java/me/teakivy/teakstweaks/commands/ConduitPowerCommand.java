package me.teakivy.teakstweaks.commands;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ConduitPowerCommand extends AbstractCommand {

    public ConduitPowerCommand() {
        super("spectator-conduit-power", "conduitpower", "/conduitpower", List.of("cp"), CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (player.getGameMode() != GameMode.SPECTATOR) {
            player.sendMessage(getError("wrong_gamemode"));
            return;
        }

        player.sendMessage(getString("toggled"));
        if (player.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
            player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, true, true));
    }
}
