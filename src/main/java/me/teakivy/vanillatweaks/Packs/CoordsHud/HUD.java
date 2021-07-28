package me.teakivy.vanillatweaks.Packs.CoordsHud;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HUD {
    static Main main = Main.getPlugin(Main.class);

    static boolean running = false;

    public static void startHUD() {
        running = true;
        doHUD();
    }

    public static void doHUD() {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            System.out.println(Main.chEnabled);
            for (UUID uuid : Main.chEnabled) {
                Player player = Bukkit.getPlayer(uuid);
                if (player == null) continue;
                if (player.isOnline()) DisplayHud.showHud(player);
            }
            if (running) doHUD();
        }, 1L);
    }

    public static void stopHUD() {
        running = false;
    }
}
