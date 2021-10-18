package me.teakivy.teakstweaks.Commands.ArmorStands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Packs.Survival.CoordsHud.DisplayHud;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Set;

public class ArmorstandCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);

    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!main.getConfig().getBoolean("config.dev-mode")) return false;

        if (command.getName().equalsIgnoreCase("armorstand")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(vt + ChatColor.RED + "You must be a player to use this command!");
                return true;
            }

            Player player = (Player) sender;
            if (args.length < 1) {
                return true;
            }
            LivingEntity nearest = getNearestArmorStand(player);

            if (args[0].equalsIgnoreCase("check_target")) {

                if (nearest != null) {
                    nearest.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 3, 0, true, false));
                }
            }

            if (args[0].equalsIgnoreCase("style_show_base_plate")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {

                    if (stand != null) {
                        stand.setBasePlate(Boolean.parseBoolean(args[1]));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("style_show_arms")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {

                    if (stand != null) {
                        stand.setArms(Boolean.parseBoolean(args[1]));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("style_small_stand")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {

                    if (stand != null) {
                        stand.setSmall(Boolean.parseBoolean(args[1]));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("style_apply_gravity")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {

                    if (stand != null) {
                        stand.setGravity(Boolean.parseBoolean(args[1]));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("style_stand_visible")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {

                    if (stand != null) {
                        stand.setVisible(Boolean.parseBoolean(args[1]));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("style_display_name")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {

                    if (stand != null) {
                        stand.setCustomNameVisible(Boolean.parseBoolean(args[1]));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("nudge_stand_x")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("-8") || args[1].equalsIgnoreCase("-3") || args[1].equalsIgnoreCase("-1") || args[1].equalsIgnoreCase("+1") || args[1].equalsIgnoreCase("+3") || args[1].equalsIgnoreCase("+8")) {

                    if (stand != null) {
                        stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX() + (0.0625 * Float.parseFloat(args[1])), stand.getLocation().getY(), stand.getLocation().getZ()));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("nudge_stand_y")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("-8") || args[1].equalsIgnoreCase("-3") || args[1].equalsIgnoreCase("-1") || args[1].equalsIgnoreCase("+1") || args[1].equalsIgnoreCase("+3") || args[1].equalsIgnoreCase("+8")) {

                    if (stand != null) {
                        stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX(), stand.getLocation().getY() + (0.0625 * Float.parseFloat(args[1])), stand.getLocation().getZ()));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("nudge_stand_z")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("-8") || args[1].equalsIgnoreCase("-3") || args[1].equalsIgnoreCase("-1") || args[1].equalsIgnoreCase("+1") || args[1].equalsIgnoreCase("+3") || args[1].equalsIgnoreCase("+8")) {

                    if (stand != null) {
                        stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX(), stand.getLocation().getY(), stand.getLocation().getZ() + (0.0625 * Float.parseFloat(args[1]))));
                    }
                }
            }

            if (args[0].equalsIgnoreCase("nudge_facing_x")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("-8") || args[1].equalsIgnoreCase("-3") || args[1].equalsIgnoreCase("-1") || args[1].equalsIgnoreCase("+1") || args[1].equalsIgnoreCase("+3") || args[1].equalsIgnoreCase("+8")) {

                    if (stand != null) {
                        String direction = DisplayHud.getDirection(player.getLocation().getYaw());

                        if (direction.equalsIgnoreCase("N")) {
                            stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX() + (0.0625 * Float.parseFloat(args[1])), stand.getLocation().getY(), stand.getLocation().getZ()));
                        }
                        if (direction.equalsIgnoreCase("S")) {
                            stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX() - (0.0625 * Float.parseFloat(args[1])), stand.getLocation().getY(), stand.getLocation().getZ()));
                        }
                        if (direction.equalsIgnoreCase("E")) {
                            stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX(), stand.getLocation().getY(), stand.getLocation().getZ() + (0.0625 * Float.parseFloat(args[1]))));
                        }
                        if (direction.equalsIgnoreCase("W")) {
                            stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX(), stand.getLocation().getY(), stand.getLocation().getZ() - (0.0625 * Float.parseFloat(args[1]))));
                        }
                    }
                }
            }

            if (args[0].equalsIgnoreCase("nudge_facing_z")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("-8") || args[1].equalsIgnoreCase("-3") || args[1].equalsIgnoreCase("-1") || args[1].equalsIgnoreCase("+1") || args[1].equalsIgnoreCase("+3") || args[1].equalsIgnoreCase("+8")) {

                    if (stand != null) {
                        String direction = DisplayHud.getDirection(player.getLocation().getYaw());

                        if (direction.equalsIgnoreCase("N")) {
                            stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX(), stand.getLocation().getY(), stand.getLocation().getZ() - (0.0625 * Float.parseFloat(args[1]))));
                        }
                        if (direction.equalsIgnoreCase("S")) {
                            stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX(), stand.getLocation().getY(), stand.getLocation().getZ() + (0.0625 * Float.parseFloat(args[1]))));
                        }
                        if (direction.equalsIgnoreCase("E")) {
                            stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX() + (0.0625 * Float.parseFloat(args[1])), stand.getLocation().getY(), stand.getLocation().getZ()));
                        }
                        if (direction.equalsIgnoreCase("W")) {
                            stand.teleport(new Location(stand.getWorld(), stand.getLocation().getX() - (0.0625 * Float.parseFloat(args[1])), stand.getLocation().getY(), stand.getLocation().getZ()));
                        }
                    }
                }
            }

            if (args[0].equalsIgnoreCase("nudge_between")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (stand != null) {
                    stand.teleport(new Location(stand.getWorld(), (stand.getLocation().getX() + player.getLocation().getX()) / 2, (stand.getLocation().getY() + player.getLocation().getY()) / 2, (stand.getLocation().getZ() + player.getLocation().getZ()) / 2));
                }
            }

            if (args[0].equalsIgnoreCase("set_rotation_number")) {
                if (args.length < 2) return true;

                if (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("5") || args[1].equalsIgnoreCase("15") || args[1].equalsIgnoreCase("45")) {
                    Set<String> tags = player.getScoreboardTags();
                    tags.remove("vt_rotation_1");
                    tags.remove("vt_rotation_5");
                    tags.remove("vt_rotation_15");
                    tags.remove("vt_rotation_45");

                    tags.add("vt_rotation_" + args[1]);
                }
            }

            if (args[0].equalsIgnoreCase("rotate")) {
                if (args.length < 2) return true;
                ArmorStand stand = (ArmorStand) nearest;

                if (args[1].equalsIgnoreCase("clockwise") || args[1].equalsIgnoreCase("counterclockwise")) {

                    if (stand != null) {
                        if (args[1].equalsIgnoreCase("clockwise")) {
                            stand.setRotation(stand.getLocation().getYaw() + getRotationAmount(player), stand.getLocation().getPitch());
                        }
                        if (args[1].equalsIgnoreCase("counterclockwise")) {
                            stand.setRotation(stand.getLocation().getYaw() - getRotationAmount(player), stand.getLocation().getPitch());
                        }
                    }
                }
            }

//            if (args[0].equalsIgnoreCase("point")) {
//                if (args.length < 3) return true;
//                ArmorStand stand = (ArmorStand) nearest;
//
//                if (args[1].equalsIgnoreCase("head")) {
//                    if (args[1])
//                }
//            }

        }
        return false;
    }

    private int getRotationAmount(Player player) {
        Set<String> tags = player.getScoreboardTags();

        if (tags.contains("vt_rotation_away")) {
            if (tags.contains("vt_rotation_1")) {
                return -1;
            }
            if (tags.contains("vt_rotation_5")) {
                return -5;
            }
            if (tags.contains("vt_rotation_45")) {
                return -45;
            }
            return -15;
        }

        if (tags.contains("vt_rotation_1")) {
            return 1;
        }
        if (tags.contains("vt_rotation_5")) {
            return 5;
        }
        if (tags.contains("vt_rotation_45")) {
            return 45;
        }
        return 15;
    }

    private LivingEntity getNearestArmorStand(Player player) {
        if (!main.getConfig().getBoolean("config.dev-mode")) return null;
        List<Entity> entities = player.getNearbyEntities(3, 3, 3);

        LivingEntity nearest = null;
        double distance = 1000000;

        for (Entity entity : entities) {
            if (entity.getType() == EntityType.ARMOR_STAND) {
                if (entity instanceof LivingEntity) {
                    if (entity.getLocation().distanceSquared(player.getLocation()) < distance) {
                        distance = entity.getLocation().distanceSquared(player.getLocation());
                        nearest = (LivingEntity) entity;
                    }
                }
            }
        }
        return nearest;
    }

}
