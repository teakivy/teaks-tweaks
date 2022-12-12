package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.survival.workstationhighlights.Highlighter;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;

public class WorkstationHighlightCommand extends AbstractCommand {

    static Main main = Main.getPlugin(Main.class);

    public WorkstationHighlightCommand() {
        super("workstation-highlights", MessageHandler.getCmdName("workstationhighlight"), MessageHandler.getCmdUsage("workstationhighlight"), MessageHandler.getCmdDescription("workstationhighlight"), MessageHandler.getCmdAliases("workstationhighlight"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!main.getConfig().getBoolean("packs.workstation-highlights.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }


        if (!sender.hasPermission("teakstweaks.workstationhighlights.execute")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
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
            player.sendMessage(MessageHandler.getCmdMessage("workstationhighlight", "errpr.no-villager"));
            return true;
        }

        Villager villager = (Villager) entity;
        Location jobSite = villager.getMemory(MemoryKey.JOB_SITE);
        if (jobSite == null) {
            player.sendMessage(MessageHandler.getCmdMessage("workstationhighlight", "errpr.no-jobsite"));
            return true;
        }

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
}
