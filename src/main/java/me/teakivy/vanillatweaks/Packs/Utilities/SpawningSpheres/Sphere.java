package me.teakivy.vanillatweaks.Packs.Utilities.SpawningSpheres;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class Sphere implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("vt_despawning_sphere")) event.setCancelled(true);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

    public static void spawnSphere(Location loc, Color color) {
        ItemStack colorStack = new ItemStack(Material.AIR);
        if (color == Color.RED) colorStack = new ItemStack(Material.RED_CONCRETE);
        if (color == Color.BLUE) colorStack = new ItemStack(Material.CYAN_CONCRETE);
        if (color == Color.GREEN) colorStack = new ItemStack(Material.GREEN_CONCRETE);
        String sColor = "";
        if (color == Color.RED) sColor = "red";
        if (color == Color.BLUE) sColor = "blue";
        if (color == Color.GREEN) sColor = "green";

        Team team = getTeam(color);
        summonStand(loc, colorStack, true, team, true, sColor);

        int radius = 24;
        for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
            double curRadius = Math.sin(i);
            double y = Math.cos(i) * radius;
            for (double a = 0; a < Math.PI * 2; a+= Math.PI / 10) {
                double x = Math.cos(a) * curRadius * radius;
                double z = Math.sin(a) * curRadius * radius;
                loc.add(x, y, z);
                summonStand(loc, colorStack, true, team, false, sColor);
                loc.subtract(x, y, z);
            }
        }

        if (color == Color.RED) colorStack = new ItemStack(Material.ORANGE_CONCRETE);
        if (color == Color.BLUE) colorStack = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
        if (color == Color.GREEN) colorStack = new ItemStack(Material.LIME_CONCRETE);
        team = getCombinationTeam(color);
        radius = 128;
        for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
            double curRadius = Math.sin(i);
            double y = Math.cos(i) * radius;
            for (double a = 0; a < Math.PI * 2; a+= Math.PI / 10) {
                double x = Math.cos(a) * curRadius * radius;
                double z = Math.sin(a) * curRadius * radius;
                loc.add(x, y, z);
                summonStand(loc, colorStack, true, team, false, sColor);
                loc.subtract(x, y, z);
            }
        }
    }

    private static void summonStand(Location loc, ItemStack head, boolean glowing, Team team, boolean center, String color) {
        ArmorStand stand = (ArmorStand) Objects.requireNonNull(loc.getWorld()).spawnEntity(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), 0, 0), EntityType.ARMOR_STAND);
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setSmall(true);
        if (glowing) {
            stand.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 999999, 0));
        }
        if (center) {
            stand.setCustomName(MessageHandler.getMessage("pack.spawning-spheres.center-stand"));
            stand.setCustomNameVisible(true);
        }
        stand.setMarker(true);
        stand.addScoreboardTag("vt_" + color + "_sphere");
        stand.addScoreboardTag("vt_spawning_sphere");
        Objects.requireNonNull(stand.getEquipment()).setHelmet(head);

        if (team != null) {
            team.addEntry(stand.getUniqueId().toString());
        }
    }

    private static Team getTeam(Color color) {
        Scoreboard sb = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        Team team = null;
        if (color == Color.RED) {
            if (sb.getTeam("vt_sphere_red") == null) {
                Team tTeam = sb.registerNewTeam("vt_sphere_red");
                tTeam.setColor(ChatColor.RED);
            }
            team = sb.getTeam("vt_sphere_red");
        }
        if (color == Color.ORANGE) {
            if (sb.getTeam("vt_sphere_orange") == null) {
                Team tTeam = sb.registerNewTeam("vt_sphere_orange");
                tTeam.setColor(ChatColor.GOLD);
            }
            team = sb.getTeam("vt_sphere_orange");
        }
        if (color == Color.BLUE) {
            if (sb.getTeam("vt_sphere_cyan") == null) {
                Team tTeam = sb.registerNewTeam("vt_sphere_cyan");
                tTeam.setColor(ChatColor.DARK_AQUA);
            }
            team = sb.getTeam("vt_sphere_cyan");
        }
        if (color == Color.AQUA) {
            if (sb.getTeam("vt_sphere_blue") == null) {
                Team tTeam = sb.registerNewTeam("vt_sphere_blue");
                tTeam.setColor(ChatColor.AQUA);
            }
            team = sb.getTeam("vt_sphere_blue");
        }
        if (color == Color.LIME) {
            if (sb.getTeam("vt_sphere_lime") == null) {
                Team tTeam = sb.registerNewTeam("vt_sphere_lime");
                tTeam.setColor(ChatColor.GREEN);
            }
            team = sb.getTeam("vt_sphere_lime");
        }
        if (color == Color.GREEN) {
            if (sb.getTeam("vt_sphere_green") == null) {
                Team tTeam = sb.registerNewTeam("vt_sphere_green");
                tTeam.setColor(ChatColor.DARK_GREEN);
            }
            team = sb.getTeam("vt_sphere_green");
        }
        return team;
    }

    private static Team getCombinationTeam(Color color) {
        if (color == Color.RED) {
            return getTeam(Color.ORANGE);
        }
        if (color == Color.BLUE) {
            return getTeam(Color.AQUA);
        }
        if (color == Color.GREEN) {
            return getTeam(Color.LIME);
        }
        return getTeam(color);
    }
}
