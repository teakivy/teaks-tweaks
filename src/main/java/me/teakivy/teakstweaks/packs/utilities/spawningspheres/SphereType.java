package me.teakivy.teakstweaks.packs.utilities.spawningspheres;

import me.teakivy.teakstweaks.utils.lang.Translatable;
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
    public ChatColor getChatColor() {
        return switch (this) {
            case RED -> ChatColor.RED;
            case BLUE -> ChatColor.BLUE;
            case GREEN -> ChatColor.GREEN;
        };
    }

    public String getName() {
        return switch (this) {
            case RED -> getChatColor() + Translatable.get("spawning_spheres.sphere.red");
            case BLUE -> getChatColor() + Translatable.get("spawning_spheres.sphere.blue");
            case GREEN -> getChatColor() + Translatable.get("spawning_spheres.sphere.green");
        };
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
