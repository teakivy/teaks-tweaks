package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.survival.workstationhighlights.Highlighter;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkstationHighlightCommand extends AbstractCommand {

    static Main main = Main.getPlugin(Main.class);

    final String[] professionTypes;

    public WorkstationHighlightCommand() {
        super("workstation-highlights", MessageHandler.getCmdName("workstationhighlight"), MessageHandler.getCmdUsage("workstationhighlight"), MessageHandler.getCmdDescription("workstationhighlight"), MessageHandler.getCmdAliases("workstationhighlight"));

        professionTypes = new String[] {
            "ARMORER",
            "ARMOURER",
            "BUTCHER",
            "CARTOGRAPHER",
            "CLERIC",
            "FARMER",
            "FISHERMAN",
            "FLETCHER",
            "LEATHERWORKER",
            "LIBRARIAN",
            "MASON",
            "SHEPHERD",
            "TOOLSMITH",
            "WEAPONSMITH",
            "ANY"
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }

        String profession = "ANY";
        int radius = 3;

        if (args.length == 1) {
            if (!Arrays.toString(professionTypes).contains(args[0].toUpperCase())) {
                sender.sendMessage(ChatColor.RED + "Invalid profession type!");
                return true;
            }

            profession = args[0].toUpperCase();
        }

        if (args.length == 2) {
            if (!Arrays.toString(professionTypes).contains(args[0].toUpperCase())) {
                sender.sendMessage(ChatColor.RED + "Invalid profession type!");
                return true;
            }

            profession = args[0].toUpperCase();

            try {
                radius = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid radius!");
                return true;
            }
        }

        if (profession.equalsIgnoreCase("armourer")) profession = "ARMORER";

        if (radius < 1 || radius > 16) {
            sender.sendMessage(ChatColor.RED + "Radius must be between 1 and 16!");
            return true;
        }

        Player player = (Player) sender;
        Entity entity = null;
        double distance = Integer.MAX_VALUE;
        for (Entity e : player.getNearbyEntities(radius, radius, radius)) {
            if (e.getType() == EntityType.VILLAGER) {
                Villager villager = (Villager) e;
                if (villager.getProfession() == Villager.Profession.NONE) continue;
                if (villager.getProfession() == Villager.Profession.NITWIT) continue;

                if (!profession.equals("ANY") && !villager.getProfession().toString().equals(profession)) continue;

                double d = e.getLocation().distanceSquared(player.getLocation());
                if (d < distance) {
                    entity = e;
                    distance = d;
                }
            }
        }

        if (entity == null) {
            player.sendMessage(ChatColor.RED + "No villager workstation found nearby!");
            return true;
        }

        Villager villager = (Villager) entity;
        Location jobSite = villager.getMemory(MemoryKey.JOB_SITE);
        if (jobSite == null) {
            player.sendMessage(MessageHandler.getCmdMessage("workstationhighlight", "errpr.no-jobsite"));
            return true;
        }

        villager.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0, false, false, false));
        Highlighter.glowingBlock(jobSite, 200);
        createParticles(jobSite.add(.5, 1, .5), 200);
        player.sendMessage(MessageHandler.getCmdMessage("workstationhighlight", "jobsite-found")
                .replace("%x%", ((int) jobSite.getX()) + "")
                .replace("%y%", ((int) jobSite.getY()) + "")
                .replace("%z%", ((int) jobSite.getZ()) + "")
        );
        return false;
    }

    public static void createParticles(Location location, int length) {
        AreaEffectCloud e = (AreaEffectCloud) location.getWorld().spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
        e.setParticle(Particle.HEART);
        e.setRadius(.001F);
        e.setRadiusPerTick(0);
        e.setRadiusOnUse(0);
        e.setDuration(length);
        e.setWaitTime(10);
    }

List<String> arguments1 = new ArrayList<>();

@Override
public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

    if (arguments1.isEmpty()) {
        for (String s : professionTypes) {
            if (s.equalsIgnoreCase("armourer")) continue;
            arguments1.add(s.toLowerCase());
        }
    }

    List<String> result = new ArrayList<>();
    if (args.length == 1) {
        if ("armourer".startsWith(args[0].toLowerCase()) && args[0].contains("u")) result.add("armourer");

        for (String a : arguments1) {
            if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                result.add(a);
        }

        return result;
    }

    return null;
}
}
