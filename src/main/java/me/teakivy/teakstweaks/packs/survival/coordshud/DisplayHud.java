package me.teakivy.teakstweaks.packs.survival.coordshud;

import me.teakivy.teakstweaks.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class DisplayHud {

    private static World world;

    static String hudMessage;

    public static void init() {
        hudMessage = "&6XYZ: &f%x% %y% %z%  &6%direction_abbreviated%      %world_time%";
        world =  Bukkit.getWorld(Main.getInstance().getConfig().getString("packs.coords-hud.time-world"));
        if (world == null) world = Bukkit.getWorlds().get(0);
    }

    public static void showHud(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            String msg = ChatColor.translateAlternateColorCodes('&', hudMessage);
            Location loc = player.getLocation().getBlock().getLocation();
            if (Main.getInstance().getConfig().getBoolean("packs.coords-hud.use-player-position")) {
                loc = player.getLocation();
            }
            msg = msg.replace("%x%", ((int) loc.getX()) + "");
            msg = msg.replace("%y%", ((int) loc.getY()) + "");
            msg = msg.replace("%z%", ((int) loc.getZ()) + "");

            Location playerLocation = player.getLocation();
            float playerDirection = playerLocation.getYaw();
            String directionAbbr = getDirectionAbbr(playerDirection);
            String worldTime = getWorldTime();

            msg = msg.replace("%direction_abbreviated%", directionAbbr);
            msg = msg.replace("%world_time%", worldTime);

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
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
