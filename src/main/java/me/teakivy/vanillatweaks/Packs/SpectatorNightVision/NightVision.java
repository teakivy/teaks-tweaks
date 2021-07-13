package me.teakivy.vanillatweaks.Packs.SpectatorNightVision;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.potion.PotionEffectType;

public class NightVision implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void changeGameMode(PlayerGameModeChangeEvent event) {
        if (!main.getConfig().getBoolean("packs.spectator-night-vision.enabled")) return;
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "Night Vision removed because of Game Mode change");
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
