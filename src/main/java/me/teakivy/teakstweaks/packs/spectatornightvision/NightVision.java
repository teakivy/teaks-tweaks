package me.teakivy.teakstweaks.packs.spectatornightvision;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.MM;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends BasePack {

    public NightVision() {
        super("spectator-night-vision", PackType.UTILITIES, Material.ENDER_EYE);
    }

    @EventHandler
    public void changeGameMode(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                MM.player(player).sendMessage(getText("error.game_mode_changed"));
            }
        }
    }

}
