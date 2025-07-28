package me.teakivy.teakstweaks.packs.coordshud;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.config.Config;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CoordsHudDisplay {

    private static World world;

    static String hudMessage;

    public static void init() {
        hudMessage = Config.getPackConfig("coords-hud").getString("hud-message");
        world =  Bukkit.getWorld(Config.getString("packs.coords-hud.time-world"));
        if (world == null) world = Bukkit.getWorlds().get(0);
    }

    public static void showHud(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(TeaksTweaks.getInstance(), () -> {
            Location loc = player.getLocation().getBlock().getLocation();
            if (Config.getBoolean("packs.coords-hud.use-player-position")) {
                loc = player.getLocation();
            }

            Location playerLocation = player.getLocation();
            float playerDirection = playerLocation.getYaw();
            String directionAbbr = getDirectionAbbr(playerDirection);
            String worldTime = getWorldTime();

            player.sendActionBar(MiniMessage.miniMessage().deserialize(
                    hudMessage,
                    Placeholder.parsed("x", loc.getBlockX() + ""),
                    Placeholder.parsed("y", loc.getBlockY() + ""),
                    Placeholder.parsed("z", loc.getBlockZ() + ""),
                    Placeholder.parsed("direction", directionAbbr),
                    Placeholder.parsed("world_time", worldTime)));
        });
    }

    public static String getDirectionAbbr(float direction) {
        if (direction >= 135 || direction <= -135) return "N";
        if (direction >= -135 && direction <= -45) return "E";
        if (direction >= -45 && direction <= 45) return "S";
        if (direction >= 45 && direction <= 135) return "W";

        return "X";
    }

    public static String getWorldTime() {
        long ticks = world.getTime();
        int hours = (int) (((ticks / 1000) + 6) % 24);
        int minutes = (int) ((ticks % 1000 / 10) * 0.6);
        return String.format("%02d:%02d", hours, minutes);
    }

    public static String getWorld(Player player) {
        return player.getWorld().getName();
    }
}
