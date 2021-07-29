package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Teleportation.Back.Back;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class tpaCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    HashMap<Player, Player> requests = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("tpa")) {
            if (!main.getConfig().getBoolean("packs.tpa.enabled")) {
                sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(vt + "This command can only be ran by a Player!");
                return true;
            }
            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage(vt + ChatColor.RED + "Please specify who you would like to teleport to!");
                return true;
            }

            if (args[0].equalsIgnoreCase("confirm")) {
                if (args.length < 2) {
                    player.sendMessage(vt + ChatColor.RED + "Please specify who you would like to teleport!");
                    return true;
                }
                Player confirmant = Bukkit.getPlayer(args[1]);
                if (confirmant == null) {
                    player.sendMessage(vt + ChatColor.RED + "The player " + args[1] + " does not exist!");
                    return true;
                }
                if (!requests.containsKey(player)) return true;
                if (requests.get(player).getName().equals(confirmant.getName())) {
                    Back.backLoc.put(confirmant.getUniqueId(), confirmant.getLocation());
                    confirmant.teleport(player.getLocation());
                    requests.remove(player);
                    confirmant.sendMessage(vt + ChatColor.YELLOW + "Teleporting to " + player.getName() + "...");
                    player.sendMessage(vt + ChatColor.YELLOW + "Teleporting " + confirmant.getName() + " to you...");
                    return true;
                }
            }

            Player player2 = Bukkit.getPlayer(args[0]);

            if (player2 == null) {


                player.sendMessage(vt + ChatColor.RED + "The player " + args[1] + " does not exist!");
                return true;
            }
            String tpCommand = "/tpa confirm " + player.getName();

            TextComponent text = new TextComponent(vt + ChatColor.GOLD + player.getName() + ChatColor.YELLOW + " has requested to teleport to you!");
            text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GOLD + "Click To Accept!")));
            text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, tpCommand));

            requests.put(player2, player);

            player2.spigot().sendMessage(text);
            player.sendMessage(vt + ChatColor.YELLOW + "Teleport Request Sent.");

            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                if (requests.containsKey(player2)) {
                    player.sendMessage(vt + ChatColor.RED + "Teleport Request Cancelled!");
                    requests.remove(player2);
                }
            }, 60 * 20L);

        }
        return false;
    }

}
