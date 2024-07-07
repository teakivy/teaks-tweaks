package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.TeaksTweaks;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MM {
    public static Audience sender(CommandSender sender) {
        BukkitAudiences bukkitAudiences = TeaksTweaks.getAdventure();
        return bukkitAudiences.sender(sender);
    }

    public static Audience player(Player player) {
        BukkitAudiences bukkitAudiences = TeaksTweaks.getAdventure();
        return bukkitAudiences.player(player);
    }

    public static String toString(Component component) {
        return BukkitComponentSerializer.legacy().serialize(component);
    }
}
