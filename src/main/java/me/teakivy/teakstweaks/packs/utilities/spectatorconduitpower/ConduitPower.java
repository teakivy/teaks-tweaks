package me.teakivy.teakstweaks.packs.utilities.spectatorconduitpower;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.potion.PotionEffectType;

public class ConduitPower extends BasePack {

    public ConduitPower() {
        super("Spectator Conduit Power", "spectator-conduit-power", PackType.UTILITIES, Material.HEART_OF_THE_SEA, "Easily toggle conduit power when in spectator.", "Command: /cp");
    }

    @EventHandler
    public void changeGameMode(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (player.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
                player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
                player.sendMessage(MessageHandler.getMessage("pack.spectator-conduit-power.game-mode-change"));
            }
        }
    }
}
