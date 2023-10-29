package me.teakivy.teakstweaks.commands;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class NightVisionCommand extends AbstractCommand {

    public NightVisionCommand() {
        super("spectator-night-vision", "nightvision", "/nightvision", List.of("nv"), CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (player.getGameMode() != GameMode.SPECTATOR) {
            player.sendMessage(getError("wrong_gamemode"));
            return;
        }

        player.sendMessage(getString("toggled"));
        if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, true, true));
    }
}
