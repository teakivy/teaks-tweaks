package me.teakivy.teakstweaks.packs.spectatornightvision;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends BasePack {

    public NightVision() {
        super("spectator-night-vision", Material.ENDER_EYE);
    }

    @EventHandler
    public void changeGameMode(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(getText("error.game_mode_changed"));
            }
        }

        if (!getConfig().getBoolean("auto-enable")) return;
        if (!event.getNewGameMode().equals(GameMode.SPECTATOR)) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, false, true));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!getConfig().getBoolean("auto-enable")) return;
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SPECTATOR)) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, false, true));
    }
}