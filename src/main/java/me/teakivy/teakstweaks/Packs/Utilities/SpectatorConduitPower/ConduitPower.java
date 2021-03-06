package me.teakivy.teakstweaks.Packs.Utilities.SpectatorConduitPower;

import me.teakivy.teakstweaks.Packs.BasePack;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.potion.PotionEffectType;

public class ConduitPower extends BasePack {

    public ConduitPower() {
        super("Spectator Conduit Power", "spectator-conduit-power");
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
