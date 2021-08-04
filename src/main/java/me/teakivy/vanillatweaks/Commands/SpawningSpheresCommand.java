package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Utilities.SpawningSpheres.Sphere;
import me.teakivy.vanillatweaks.Packs.Utilities.SpawningSpheres.SphereData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class SpawningSpheresCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("ss") || command.getName().equalsIgnoreCase("spawningspheres") || command.getName().equalsIgnoreCase("spawningsphere")) {
            if (!main.getConfig().getBoolean("packs.spawning-spheres.enabled")) {
                sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("[VT] You must be a player to run this command!");
                return true;
            }
            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage(vt + ChatColor.RED + "Please specify an action!");
                return true;
            }

            SphereData sData = new SphereData();

            if (args[0].equalsIgnoreCase("create")) {
                if (args.length < 2) {
                    player.sendMessage(vt + "Please specify a sphere color!");
                    return true;
                }

                if (args[1].equalsIgnoreCase("red")) {
                    if (checkSphere(Color.RED, player, sData, true)) return true;
                    Sphere.spawnSphere(player.getLocation(), Color.RED);
                    player.sendMessage(vt + ChatColor.GREEN + "Summoned the " + ChatColor.RED + "Red" + ChatColor.GREEN + " sphere!");
                    sData.setSphere(Color.RED, player.getLocation());
                }

                if (args[1].equalsIgnoreCase("blue")) {
                    if (checkSphere(Color.BLUE, player, sData, true)) return true;
                    Sphere.spawnSphere(player.getLocation(), Color.BLUE);
                    player.sendMessage(vt + ChatColor.GREEN + "Summoned the " + ChatColor.AQUA + "Blue" + ChatColor.GREEN + " sphere!");
                    sData.setSphere(Color.BLUE, player.getLocation());
                }

                if (args[1].equalsIgnoreCase("green")) {
                    if (checkSphere(Color.GREEN, player, sData, true)) return true;
                    Sphere.spawnSphere(player.getLocation(), Color.GREEN);
                    player.sendMessage(vt + ChatColor.GREEN + "Summoned the " + ChatColor.DARK_GREEN + "Green" + ChatColor.GREEN + " sphere!");
                    sData.setSphere(Color.GREEN, player.getLocation());
                }
            }

            if (args[0].equalsIgnoreCase("remove")) {
                if (args.length < 2) {
                    player.sendMessage(vt + "Please specify a sphere color!");
                    return true;
                }

                if (args[1].equalsIgnoreCase("red")) {
                    if (checkSphere(Color.RED, player, sData, false)) return true;
                    removeSphere(Color.RED, player, sData);
                    sData.setSphere(Color.RED, null);
                }

                if (args[1].equalsIgnoreCase("blue")) {
                    if (checkSphere(Color.BLUE, player, sData, false)) return true;
                    removeSphere(Color.BLUE, player, sData);
                    sData.setSphere(Color.BLUE, null);
                }

                if (args[1].equalsIgnoreCase("green")) {
                    if (checkSphere(Color.GREEN, player, sData, false)) return true;
                    removeSphere(Color.GREEN, player, sData);
                    sData.setSphere(Color.GREEN, null);
                }

            }

            if (args[0].equalsIgnoreCase("tp") || args[0].equalsIgnoreCase("teleport")) {
                if (args.length < 2) {
                    player.sendMessage(vt + "Please specify a sphere color!");
                    return true;
                }

                if (args[1].equalsIgnoreCase("red")) {
                    if (checkSphere(Color.RED, player, sData, false)) return true;
                    player.teleport(sData.getSphereLocation(Color.RED));
                    player.sendMessage(vt + ChatColor.GREEN + "Teleported to the " + ChatColor.RED + "Red" + ChatColor.GREEN + " Sphere!");
                }

                if (args[1].equalsIgnoreCase("blue")) {
                    if (checkSphere(Color.BLUE, player, sData, false)) return true;
                    player.teleport(sData.getSphereLocation(Color.BLUE));
                    player.sendMessage(vt + ChatColor.GREEN + "Teleported to the " + ChatColor.AQUA + "Blue" + ChatColor.GREEN + " Sphere!");
                }

                if (args[1].equalsIgnoreCase("green")) {
                    if (checkSphere(Color.GREEN, player, sData, false)) return true;
                    player.teleport(sData.getSphereLocation(Color.GREEN));
                    player.sendMessage(vt + ChatColor.GREEN + "Teleported to the " + ChatColor.DARK_GREEN + "Green" + ChatColor.GREEN + " Sphere!");
                }
            }

        }

        return false;
    }

    private void removeSphere(Color color, Player player, SphereData data) {
        Location sLoc = data.getSphereLocation(color);
        Location pLoc = player.getLocation();
        long tpDelay = 60L;
        long startDelay = 20L;
        if (color == Color.RED) {
            player.teleport(sLoc);
            player.addScoreboardTag("vt_despawning_sphere");
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                List<Entity> entityList = (List<Entity>) Objects.requireNonNull(sLoc.getWorld()).getNearbyEntities(sLoc, 150, 150, 150);
                int count = 0;
                for (Entity entity : entityList) {
                    if (entity.getScoreboardTags().contains("vt_spawning_sphere") && entity.getScoreboardTags().contains("vt_red_sphere")) {
                        entity.remove();
                    }
                    count++;
                    if (count == entityList.size()) {
                        count = 0;
                        entityList = (List<Entity>) Objects.requireNonNull(sLoc.getWorld()).getNearbyEntities(sLoc, 150, 150, 150);
                        for (Entity entity1 : entityList) {
                            if (entity1.getScoreboardTags().contains("vt_spawning_sphere") && entity1.getScoreboardTags().contains("vt_red_sphere")) {
                                entity1.remove();
                            }
                            count++;
                            if (count == entityList.size()) {
                                player.teleport(pLoc);
                                player.sendMessage(vt + ChatColor.GREEN + "Removed the " + ChatColor.RED + "Red" + ChatColor.GREEN + " sphere!");
                                player.removeScoreboardTag("vt_despawning_sphere");
                            }
                        }
                    }
                }
            }, startDelay + tpDelay);
        }
        if (color == Color.BLUE) {
            player.teleport(sLoc);
            player.addScoreboardTag("vt_despawning_sphere");
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                List<Entity> entityList = (List<Entity>) Objects.requireNonNull(sLoc.getWorld()).getNearbyEntities(sLoc, 150, 150, 150);
                int count = 0;
                for (Entity entity : entityList) {
                    if (entity.getScoreboardTags().contains("vt_spawning_sphere") && entity.getScoreboardTags().contains("vt_blue_sphere")) {
                        entity.remove();
                    }
                    count++;
                    if (count == entityList.size()) {
                        count = 0;
                        entityList = (List<Entity>) Objects.requireNonNull(sLoc.getWorld()).getNearbyEntities(sLoc, 150, 150, 150);
                        for (Entity entity1 : entityList) {
                            if (entity1.getScoreboardTags().contains("vt_spawning_sphere") && entity1.getScoreboardTags().contains("vt_blue_sphere")) {
                                entity1.remove();
                            }
                            count++;
                            if (count == entityList.size()) {
                                player.teleport(pLoc);
                                player.sendMessage(vt + ChatColor.GREEN + "Removed the " + ChatColor.AQUA + "Blue" + ChatColor.GREEN + " sphere!");
                                player.removeScoreboardTag("vt_despawning_sphere");
                            }
                        }
                    }
                }
            }, startDelay + tpDelay);
        }
        if (color == Color.GREEN) {
            player.teleport(sLoc);
            player.addScoreboardTag("vt_despawning_sphere");
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                List<Entity> entityList = (List<Entity>) Objects.requireNonNull(sLoc.getWorld()).getNearbyEntities(sLoc, 150, 150, 150);
                int count = 0;
                for (Entity entity : entityList) {
                    if (entity.getScoreboardTags().contains("vt_spawning_sphere") && entity.getScoreboardTags().contains("vt_green_sphere")) {
                        entity.remove();
                    }
                    count++;
                    if (count == entityList.size()) {
                        count = 0;
                        entityList = (List<Entity>) Objects.requireNonNull(sLoc.getWorld()).getNearbyEntities(sLoc, 150, 150, 150);
                        for (Entity entity1 : entityList) {
                            if (entity1.getScoreboardTags().contains("vt_spawning_sphere") && entity1.getScoreboardTags().contains("vt_green_sphere")) {
                                entity1.remove();
                            }
                            count++;
                            if (count == entityList.size()) {
                                player.teleport(pLoc);
                                player.sendMessage(vt + ChatColor.GREEN + "Removed the " + ChatColor.DARK_GREEN + "Green" + ChatColor.GREEN + " sphere!");
                                player.removeScoreboardTag("vt_despawning_sphere");
                            }
                        }
                    }
                }
            }, startDelay + tpDelay);
        }
        player.removeScoreboardTag("vt_despawning_sphere");
    }

    private boolean checkSphere(Color color, Player player, SphereData data, boolean creating) {
        if (creating) {
            if (color == Color.RED) {
                if (data.isSphereUsed(color)) {
                    player.sendMessage(vt + ChatColor.RED + "The " + ChatColor.DARK_RED + "Red" + ChatColor.RED + " sphere is already in use!");
                    return true;
                }
                return false;
            }
            if (color == Color.BLUE) {
                if (data.isSphereUsed(color)) {
                    player.sendMessage(vt + ChatColor.RED + "The " + ChatColor.AQUA + "Blue" + ChatColor.RED + " sphere is already in use!");
                    return true;
                }
                return false;
            }
            if (color == Color.GREEN) {
                if (data.isSphereUsed(color)) {
                    player.sendMessage(vt + ChatColor.RED + "The " + ChatColor.DARK_GREEN + "Green" + ChatColor.RED + " sphere is already in use!");
                    return true;
                }
                return false;
            }
        } else {
            if (color == Color.RED) {
                if (!data.isSphereUsed(color)) {
                    player.sendMessage(vt + ChatColor.RED + "The " + ChatColor.DARK_RED + "Red" + ChatColor.RED + " sphere is not in use!");
                    return true;
                }
                return false;
            }
            if (color == Color.BLUE) {
                if (!data.isSphereUsed(color)) {
                    player.sendMessage(vt + ChatColor.RED + "The " + ChatColor.AQUA + "Blue" + ChatColor.RED + " sphere is not in use!");
                    return true;
                }
                return false;
            }
            if (color == Color.GREEN) {
                if (!data.isSphereUsed(color)) {
                    player.sendMessage(vt + ChatColor.RED + "The " + ChatColor.DARK_GREEN + "Green" + ChatColor.RED + " sphere is not in use!");
                    return true;
                }
                return false;
            }
        }
        return true;
    }
}
