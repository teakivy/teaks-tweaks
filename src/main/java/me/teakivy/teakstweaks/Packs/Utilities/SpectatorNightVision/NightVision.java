package me.teakivy.teakstweaks.Packs.Utilities.SpectatorNightVision;

import me.teakivy.teakstweaks.Packs.BasePack;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends BasePack {

    public NightVision() {
        super("Spectator Night Vision", "spectator-night-vision");
    }

    @EventHandler
    public void changeGameMode(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(MessageHandler.getMessage("pack.spectator-conduit-power.game-mode-change"));
            }
        }
    }

}
