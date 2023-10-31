package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.workstationhighlights.Highlighter;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        super("workstation-highlights", "workstationhighlight", "/workstationhighlight", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        String profession = professionTypes[0];
        int radius = 3;

        if (args.length >= 1) {
            if (!Arrays.toString(professionTypes).contains(args[0])) {
                player.sendMessage(getString("error.invalid_profession"));
                return;
            }

            profession = args[0];
        }

        if (args.length == 2) {
            try {
                radius = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(getString("error.invalid_radius"));
                return;
            }
        }

        if (radius < 1 || radius > 16) {
            player.sendMessage(getString("error.radius_out_of_bounds"));
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
            player.sendMessage(getString("error.no_workstations_found"));
            return;
        }

        Villager villager = (Villager) entity;
        Location jobSite = villager.getMemory(MemoryKey.JOB_SITE);
        if (jobSite == null) {
            player.sendMessage(getString("error.no_job_site"));
            return;
        }

        villager.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0, false, false, false));
        Highlighter.glowingBlock(jobSite, 200);
        createParticles(jobSite.add(.5, 1, .5));
        player.sendMessage(getString("found")
                .replace("%x%", ((int) jobSite.getX()) + "")
                .replace("%y%", ((int) jobSite.getY()) + "")
                .replace("%z%", ((int) jobSite.getZ()) + "")
        );
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return Arrays.asList(professionTypes);
        if (args.length == 2) return List.of("[radius]");

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
