package me.teakivy.vanillatweaks.Packs.CoordsHud;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class DisplayHud{


    public static void showHud(Player player) {
        String display = ChatColor.GOLD + "XYZ: " + ChatColor.WHITE + (int) Math.floor(player.getLocation().getX()) + " " + (int) Math.floor(player.getLocation().getY()) + " " + (int) Math.floor(player.getLocation().getZ()) + "  " + ChatColor.GOLD + getDirection(player) + "      " + getWorldTime(player);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(display));
    }


    public static String getDirection(Player player) {
        float direction = player.getLocation().getYaw();
        String directionString = "NA";

        if (direction <= -135 || direction >= 135) directionString = "N";
        if (direction <= 135 && direction >= 45) directionString = "W";
        if (direction >= -45 && direction <= 45) directionString = "S";
        if (direction <= -45 && direction >= -135) directionString = "E";

        return directionString;
    }

    public static String getWorldTime(Player player) {
        World world = Bukkit.getWorlds().get(0);
        long ticks = world.getTime();
        int hours = (int) Math.floor((ticks / 1000) + 6) % 24;
        int minutes = (int) Math.floor((ticks % 1000 / 10) * 0.6);
        return String.format("%02d:%02d", hours, minutes);
    }

}
