package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.Register.Register;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class TagGameCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public TagGameCommand() {
        super("taggame", "/taggame", "The classic game of Tag!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
                return true;
            }
            Player player = (Player) sender;

            if (!player.isOp()) {
                player.sendMessage(vt + ChatColor.RED + "You must be an OP to use this command!");
                return true;
            }

            if (args[0].equalsIgnoreCase("uninstall")) {
                player.sendMessage(vt + ChatColor.RED + "Tag Uninstalled!");
                Register.tag.uninstall();
                return true;
            }
        }

        if (!main.getConfig().getBoolean("packs.tag.enabled")) {
            sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
            return true;
        }
        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(vt + ChatColor.RED + "You must be an OP to use this command!");
            return true;
        }

        if (args.length < 1) {
            Register.tag.uninstall();
            ItemStack tag = new ItemStack(Material.NAME_TAG);
            ItemMeta tagMeta = tag.getItemMeta();
            tagMeta.setDisplayName(ChatColor.YELLOW + "Tag!");
            tagMeta.setUnbreakable(true);
            tag.setItemMeta(tagMeta);

            player.getInventory().addItem(tag);

            player.addScoreboardTag("vt_tag_it");

            Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
            if (sb.getTeam("TaggedTeam") == null) {
                Team taggedTeam = sb.registerNewTeam("TaggedTeam");
                taggedTeam.setColor(ChatColor.RED);
            }
            Team taggedTeam = sb.getTeam("TaggedTeam");
            taggedTeam.addEntry(player.getName());
            player.sendMessage(vt + ChatColor.YELLOW + "Let the games begin!");
            return true;
        }
        return false;
    }

    List<String> arguments1 = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments1.isEmpty()) {
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
