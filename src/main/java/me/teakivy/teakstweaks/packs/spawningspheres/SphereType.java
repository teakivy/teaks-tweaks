package me.teakivy.teakstweaks.packs.spawningspheres;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public enum SphereType {
    RED,
    BLUE,
    GREEN;

    /**
     * Gets the color name of the sphere
     * @return the color name of the sphere (red, blue, green)
     */
    public String getColorName() {
        return switch (this) {
            case RED -> "red";
            case BLUE -> "blue";
            case GREEN -> "green";
        };
    }

    /**
     * Gets the main material of the sphere
     * @return the main material of the sphere
     */
    public Material getMaterial() {
        return switch (this) {
            case RED -> Material.RED_CONCRETE;
            case BLUE -> Material.BLUE_CONCRETE;
            case GREEN -> Material.GREEN_CONCRETE;
        };
    }

    /**
     * Gets the sub material of the sphere
     * @return the sub material of the sphere
     */
    public Material getSubMaterial() {
        return switch (this) {
            case RED -> Material.ORANGE_CONCRETE;
            case BLUE -> Material.CYAN_CONCRETE;
            case GREEN -> Material.LIME_CONCRETE;
        };
    }

    /**
     * Gets the chat color of the sphere
     * @return the chat color of the sphere
     */
    public TextColor getColor() {
        return switch (this) {
            case RED -> TextColor.color(0xFF5555);
            case BLUE -> TextColor.color(0x5555FF);
            case GREEN -> TextColor.color(0x55FF55);
        };
    }

    public Component getName() {
        Component name = switch (this) {
            case RED -> Component.translatable("spawning_spheres.sphere.red");
            case BLUE -> Component.translatable("spawning_spheres.sphere.blue");
            case GREEN -> Component.translatable("spawning_spheres.sphere.green");
        };

        return name.color(getColor());
    }

    public Team getTeam() {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        return switch (this) {
            case RED -> {
                if (sb.getTeam("sphere_red") == null) {
                    Team tTeam = sb.registerNewTeam("sphere_red");
                    tTeam.setColor(ChatColor.RED);
                }
                yield sb.getTeam("sphere_red");
            }
            case BLUE -> {
                if (sb.getTeam("sphere_blue") == null) {
                    Team tTeam = sb.registerNewTeam("sphere_blue");
                    tTeam.setColor(ChatColor.BLUE);
                }
                yield sb.getTeam("sphere_blue");
            }
            case GREEN -> {
                if (sb.getTeam("sphere_green") == null) {
                    Team tTeam = sb.registerNewTeam("sphere_green");
                    tTeam.setColor(ChatColor.GREEN);
                }
                yield sb.getTeam("sphere_green");
            }
        };
    }

    public static SphereType getSphereType(String name) {
        return switch (name.toLowerCase()) {
            case "red" -> RED;
            case "blue" -> BLUE;
            case "green" -> GREEN;
            default -> null;
        };
    }
}
