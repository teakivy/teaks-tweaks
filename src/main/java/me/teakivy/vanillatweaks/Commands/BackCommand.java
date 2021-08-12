package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Teleportation.Back.Back;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BackCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    HashMap<UUID, Long> cooldown = new HashMap<>();

    public BackCommand() {
        super(MessageHandler.getCmdName("back"), MessageHandler.getCmdUsage("back"), MessageHandler.getCmdDescription("back"), MessageHandler.getCmdAliases("back"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.back.enabled")) {
            sender.sendMessage(vt + MessageHandler.getMessage("plugin.error.pack-not-enabled"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(vt + MessageHandler.getMessage("plugin.error.not-player"));
            return true;
        }
        Player player = (Player) sender;

        if (main.getConfig().getInt("packs.back.teleport-cooldown") > 0) {
            if (cooldown.containsKey(player.getUniqueId())) {
                if (cooldown.get(player.getUniqueId()) + (main.getConfig().getInt("packs.back.teleport-cooldown") * 1000L) > System.currentTimeMillis()) {
                    player.sendMessage(vt + ChatColor.RED + "You must wait " + main.getConfig().getInt("packs.back.teleport-cooldown") + " seconds between uses of /back!");
                    return true;
                }
            }
            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
        }

        if (!Back.backLoc.containsKey(player.getUniqueId())) {
            player.sendMessage(vt + ChatColor.RED + "You have nowhere to go back to!");
            return true;
        }
        if (main.getConfig().getInt("packs.back.teleport-delay") == 0) {
            player.sendMessage(vt + ChatColor.YELLOW + "Teleporting Back...");
            Back.tpBack(player);
        } else if (main.getConfig().getInt("packs.back.teleport-delay") > 0) {
            Location loc = player.getLocation();
            player.sendMessage(vt + ChatColor.YELLOW + "Teleporting to world spawn...");
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                if (player.getLocation().getX() == loc.getX() && player.getLocation().getY() == loc.getY() && player.getLocation().getZ() == loc.getZ()) {
                    Back.tpBack(player);
                } else {
                    player.sendMessage(vt + ChatColor.RED + "You must stand still to teleport!");
                }
            }, main.getConfig().getInt("packs.back.teleport-delay") * 20L);
        }
        return false;
    }
}
