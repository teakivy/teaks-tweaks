package me.teakivy.vanillatweaks.Packs.Survival.CoordsHud;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class DisplayHud {

    private static World world = Bukkit.getWorlds().get(0);

    public static void showHud(Player player) {
        String display = ChatColor.GOLD + "XYZ: " + ChatColor.WHITE;

        Location playerLocation = player.getLocation();
        float playerDirection = playerLocation.getYaw();

        display += ((int) playerLocation.getX()) + " " + ((int) playerLocation.getY()) + " " + ((int) playerLocation.getZ()) + "  ";
        display += ChatColor.GOLD + getDirection(playerDirection) + "      " + getWorldTime();

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(display));
    }

    public static String getDirection(float direction) {
        if (direction <= -135 || direction >= 135) return "N";
        if (direction <= 135 && direction >= 45) return "W";
        if (direction >= -45 && direction <= 45) return "S";
        if (direction <= -45 && direction >= -135) return "E";

        return "N/A";
    }

    public static String getWorldTime() {
        long ticks = world.getTime();
        int hours = (int) (((ticks / 1000) + 6) % 24);
        int minutes = (int) ((ticks % 1000 / 10) * 0.6);
        return String.format("%02d:%02d", hours, minutes);
    }
}
