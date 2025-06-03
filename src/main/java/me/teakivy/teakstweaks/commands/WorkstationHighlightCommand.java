package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.workstationhighlights.Highlighter;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkstationHighlightCommand extends AbstractCommand {

    private static final String[] professionTypes = new String[] {
            "any",
            "armorer",
            "butcher",
            "cartographer",
            "cleric",
            "farmer",
            "fisherman",
            "fletcher",
            "leatherworker",
            "librarian",
            "mason",
            "shepherd",
            "toolsmith",
            "weaponsmith"
    };

    public WorkstationHighlightCommand() {
        super(CommandType.PLAYER_ONLY, "workstation-highlights", "workstationhighlight", Permission.COMMAND_WORKSTATIONHIGHLIGHT, Arg.optional("profession", "clear"), Arg.optional("radius"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        String profession = professionTypes[0];
        int radius = 3;

        if (event.hasArgs(1) && event.getArg(0).equalsIgnoreCase("clear")) {
            Highlighter.clear();
            sendMessage("cleared");
            return;
        }

        if (event.hasArgs(1)) {
            if (!Arrays.toString(professionTypes).contains(event.getArg(0))) {
                sendError("invalid_profession");
                return;
            }

            profession = event.getArg(0);
        }

        if (event.hasArgs(2)) {
            try {
                radius = Integer.parseInt(event.getArg(1));
            } catch (NumberFormatException e) {
                sendError("invalid_radius");
                return;
            }
        }

        if (radius < 1 || radius > 16) {
            sendError("error.radius_out_of_bounds");
            return;
        }

        Entity entity = null;
        double distance = Integer.MAX_VALUE;
        for (Entity e : player.getNearbyEntities(radius, radius, radius)) {
            if (e.getType() == EntityType.VILLAGER) {
                Villager villager = (Villager) e;
                if (villager.getProfession() == Villager.Profession.NONE) continue;
                if (villager.getProfession() == Villager.Profession.NITWIT) continue;

                if (!profession.equals("any") && !villager.getProfession().toString().toLowerCase().equals(profession)) continue;

                double d = e.getLocation().distanceSquared(player.getLocation());
                if (d < distance) {
                    entity = e;
                    distance = d;
                }
            }
        }

        if (entity == null) {
           sendError("no_workstations_found");
            return;
        }

        Villager villager = (Villager) entity;
        Location jobSite = villager.getMemory(MemoryKey.JOB_SITE);
        if (jobSite == null) {
            sendError("no_job_site");
            return;
        }

        villager.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0, false, false, false));
        Highlighter.glowingBlock(jobSite, 200);
        createParticles(jobSite.add(.5, 1, .5));
        sendMessage("found", insert("x", jobSite.getBlockX()), insert("y", jobSite.getBlockY()), insert("z", jobSite.getBlockZ()));
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        List<String> arg1 = new ArrayList<>(Arrays.asList(professionTypes));
        arg1.add("clear");
        if (event.isArg(0)) return arg1;
        if (event.isArg(1)) return List.of("[radius]");

        return null;
    }

    private void createParticles(Location location) {
        AreaEffectCloud e = (AreaEffectCloud) location.getWorld().spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
        e.setParticle(Particle.HEART);
        e.setRadius(.001F);
        e.setRadiusPerTick(0);
        e.setRadiusOnUse(0);
        e.setDuration(200);
        e.setWaitTime(10);
    }
}
