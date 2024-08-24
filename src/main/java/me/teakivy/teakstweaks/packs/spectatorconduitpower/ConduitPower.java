package me.teakivy.teakstweaks.packs.spectatorconduitpower;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.MM;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.potion.PotionEffectType;

public class ConduitPower extends BasePack {

    public ConduitPower() {
        super("spectator-conduit-power", PackType.UTILITIES, Material.HEART_OF_THE_SEA);
    }

    @EventHandler
    public void changeGameMode(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (player.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
                player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
                MM.player(player).sendMessage(getText("error.game_mode_changed"));
            }
        }
    }
}
