package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.ThunderShrine.Shrine;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ShrineCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("shrine")) {
            if (!main.getConfig().getBoolean("packs.thunder-shrine.enabled")) {
                sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(vt + "This command can only be ran by a Player!");
                return true;
            }
            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage(vt + ChatColor.RED + "Please specify an action!");
                return true;
            }

            if (args[0].equalsIgnoreCase("create")) {
                try {
                    Location loc = player.getLocation();
                    String world = loc.getWorld().getName();
                    int x = (int) Math.floor(loc.getX());
                    int y = (int) Math.floor(loc.getY());
                    int z = (int) Math.floor(loc.getZ());
                    Shrine.createShrine(player.getLocation());
                    player.sendMessage(vt + ChatColor.GREEN + "A Thunder Shrine has been created at " + ChatColor.GOLD + "XYZ: " + ChatColor.YELLOW + x + " " + y + " " + z + ChatColor.GREEN + " in" + ChatColor.GOLD + " World: " + ChatColor.YELLOW + world);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("remove")) {
                Entity shrine = null;
                for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
                    if (Shrine.getShrines().contains(entity)) {
                        shrine = entity;
                    }
                }

                if (shrine == null) {
                    player.sendMessage(vt + ChatColor.RED + "Could not find any shrines nearby!");
                    return true;
                }

                shrine.remove();
                TextComponent uuid = new TextComponent(vt + ChatColor.GREEN + "Removed the shrine: " + ChatColor.GOLD + shrine.getUniqueId().toString());
                uuid.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.YELLOW + "Click to teleport!")));
                uuid.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + shrine.getLocation().getX() + " " + shrine.getLocation().getY() + " " + shrine.getLocation().getZ()));
                player.spigot().sendMessage(uuid);
                return true;
            }

            if (args[0].equalsIgnoreCase("uninstall")) {
                for (Entity shrine : Shrine.getShrines()) {
                    shrine.remove();
                }
                player.sendMessage(vt + ChatColor.GREEN + "All Shrines have been removed!");
                return true;
            }

        }
        return false;
    }

}
