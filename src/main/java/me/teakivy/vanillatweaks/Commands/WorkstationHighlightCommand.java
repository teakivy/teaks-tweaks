package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Survival.WorkstationHighlights.Highlighter;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;

import java.util.Arrays;

public class WorkstationHighlightCommand extends AbstractCommand {

    static Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public WorkstationHighlightCommand() {
        super("workstationhighlight", "/workstationhighlight", "Villager Workstations!", Arrays.asList("wh", "workstation"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!main.getConfig().getBoolean("packs.workstation-highlights.enabled")) {
            sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;
        Entity entity = null;
        double distance = Integer.MAX_VALUE;
        for (Entity e : player.getNearbyEntities(3, 3, 3)) {
            if (e.getType() == EntityType.VILLAGER) {
                double d = e.getLocation().distanceSquared(player.getLocation());
                if (d < distance) {
                    entity = e;
                    distance = d;
                }
            }
        }

        if (entity == null) {
            player.sendMessage(vt + ChatColor.RED + "Could not find a Villager nearby!");
            return true;
        }

        Villager villager = (Villager) entity;
        Location jobSite = villager.getMemory(MemoryKey.JOB_SITE);
        if (jobSite == null) {
            player.sendMessage(vt + ChatColor.RED + "The nearest Villager does not have a job site!");
            return true;
        }

        Highlighter.glowingBlock(jobSite, 200);
        createParticles(jobSite.add(.5, 1, .5), 200);
        player.sendMessage(vt + ChatColor.YELLOW + "The workstation is located at " + ChatColor.GOLD + "[XYZ]: " + jobSite.getX() + " " + jobSite.getY() + " " + jobSite.getZ());
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
}
