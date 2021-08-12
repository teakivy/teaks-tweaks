package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Hermitcraft.ThunderShrine.Shrine;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShrineCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public ShrineCommand() {
        super(MessageHandler.getCmdName("shrine"), MessageHandler.getCmdUsage("shrine"), MessageHandler.getCmdDescription("shrine"), MessageHandler.getCmdAliases("shrine"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.thunder-shrine.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }
        if (!sender.isOp()) {
            sender.sendMessage(vt + ChatColor.RED + "You must be OP to use this command!");
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
        return false;
    }

    List<String> arguments1 = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments1.isEmpty()) {
            arguments1.add("create");
            arguments1.add("remove");
            arguments1.add("uninstall");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return null;
    }
}
