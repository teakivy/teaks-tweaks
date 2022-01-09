package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Packs.Utilities.SpawningSpheres.Sphere;
import me.teakivy.teakstweaks.Packs.Utilities.SpawningSpheres.SphereData;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpawningSpheresCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public SpawningSpheresCommand() {
        super(MessageHandler.getCmdName("spawningspheres"), MessageHandler.getCmdUsage("spawningspheres"), MessageHandler.getCmdDescription("spawningspheres"), MessageHandler.getCmdAliases("spawningspheres"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.spawning-spheres.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ErrorType.MISSING_ACTION.m());
            return true;
        }

        SphereData sData = new SphereData();

        if (args[0].equalsIgnoreCase("create")) {
            if (args.length < 2) {
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.missing-color"));
                return true;
            }

            if (!sender.hasPermission("teakstweaks.spawningspheres.create")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }

            if (args[1].equalsIgnoreCase("red")) {
                if (checkSphere(Color.RED, player, sData, true)) return true;
                Sphere.spawnSphere(player.getLocation(), Color.RED);
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-summoned").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.red")));
                sData.setSphere(Color.RED, player.getLocation());
            }

            if (args[1].equalsIgnoreCase("blue")) {
                if (checkSphere(Color.BLUE, player, sData, true)) return true;
                Sphere.spawnSphere(player.getLocation(), Color.BLUE);
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-summoned").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.blue")));
                sData.setSphere(Color.BLUE, player.getLocation());
            }

            if (args[1].equalsIgnoreCase("green")) {
                if (checkSphere(Color.GREEN, player, sData, true)) return true;
                Sphere.spawnSphere(player.getLocation(), Color.GREEN);
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-summoned").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.green")));
                sData.setSphere(Color.GREEN, player.getLocation());
            }
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (args.length < 2) {
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.missing-color"));
                return true;
            }

            if (!sender.hasPermission("teakstweaks.spawningspheres.remove")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
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
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.missing-color"));
                return true;
            }

            if (!sender.hasPermission("teakstweaks.spawningspheres.teleport")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }

            if (args[1].equalsIgnoreCase("red")) {
                if (checkSphere(Color.RED, player, sData, false)) return true;
                player.teleport(sData.getSphereLocation(Color.RED));
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-teleport").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.red")));
            }

            if (args[1].equalsIgnoreCase("blue")) {
                if (checkSphere(Color.BLUE, player, sData, false)) return true;
                player.teleport(sData.getSphereLocation(Color.BLUE));
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-teleport").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.blue")));
            }

            if (args[1].equalsIgnoreCase("green")) {
                if (checkSphere(Color.GREEN, player, sData, false)) return true;
                player.teleport(sData.getSphereLocation(Color.GREEN));
                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-teleport").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.green")));
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
                                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-removed").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.red")));
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
                                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-removed").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.blue")));
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
                                player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "sphere-removed").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.green")));
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
                    player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.sphere-in-use").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.red")));
                    return true;
                }
                return false;
            }
            if (color == Color.BLUE) {
                if (data.isSphereUsed(color)) {
                    player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.sphere-in-use").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.blue")));
                    return true;
                }
                return false;
            }
            if (color == Color.GREEN) {
                if (data.isSphereUsed(color)) {
                    player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.sphere-in-use").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.green")));
                    return true;
                }
                return false;
            }
        } else {
            if (color == Color.RED) {
                if (!data.isSphereUsed(color)) {
                    player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.sphere-not-in-use").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.red")));
                    return true;
                }
                return false;
            }
            if (color == Color.BLUE) {
                if (!data.isSphereUsed(color)) {
                    player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.sphere-not-in-use").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.blue")));
                    return true;
                }
                return false;
            }
            if (color == Color.GREEN) {
                if (!data.isSphereUsed(color)) {
                    player.sendMessage(MessageHandler.getCmdMessage("spawningspheres", "error.sphere-not-in-use").replace("%color%", MessageHandler.getCmdMessage("spawningspheres", "sphere.green")));
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    List<String> arguments1 = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        arguments1.add("create");
        arguments1.add("remove");
        arguments1.add("teleport");
        arguments1.add("tp");

        arguments2.add("red");
        arguments2.add("blue");
        arguments2.add("green");

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2) {
            for (String a : arguments2) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}
