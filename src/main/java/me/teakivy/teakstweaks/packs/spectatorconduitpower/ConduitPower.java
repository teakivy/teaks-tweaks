package me.teakivy.teakstweaks.packs.spectatorconduitpower;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConduitPower extends BasePack {

    public ConduitPower() {
        super("spectator-conduit-power", Material.HEART_OF_THE_SEA);
    }

    @EventHandler
    public void changeGameMode(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (player.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
                player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
                player.sendMessage(getText("error.game_mode_changed"));
            }
        }

        if (!getConfig().getBoolean("auto-enable")) return;
        if (!event.getNewGameMode().equals(GameMode.SPECTATOR)) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, false, true));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!getConfig().getBoolean("auto-enable")) return;
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SPECTATOR)) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, false, true));
    }
}
