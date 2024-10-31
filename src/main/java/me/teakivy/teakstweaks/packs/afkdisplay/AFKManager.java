package me.teakivy.teakstweaks.packs.afkdisplay;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AFKManager {

    // this all for saving AFK status
    private static final Map<UUID, Boolean> afkStatusMap = new HashMap<>();

    public static boolean isAfk(OfflinePlayer player) {
        return afkStatusMap.getOrDefault(player.getUniqueId(), false);
    }

    public static void setAfkStatus(Player player, boolean isAfk) {
        afkStatusMap.put(player.getUniqueId(), isAfk);
        Bukkit.getPluginManager().callEvent(new AFKStatusChangeEvent(player, isAfk));
    }
}
