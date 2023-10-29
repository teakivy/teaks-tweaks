package me.teakivy.teakstweaks.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;

public class TagGameCommand extends AbstractCommand {

    public TagGameCommand() {
        super("tag", "taggame", "/taggame", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("TaggedTeam");

        if (args.length < 1) {
            if (!checkPermission(player, "give")) return;

            ItemStack tag = new ItemStack(Material.NAME_TAG);
            ItemMeta tagMeta = tag.getItemMeta();
            tagMeta.setDisplayName(getString("item_name"));
            tagMeta.setUnbreakable(true);
            tag.setItemMeta(tagMeta);

            player.getInventory().addItem(tag);

            player.addScoreboardTag("tag_it");

            if (team == null) {
                team = sb.registerNewTeam("TaggedTeam");
                team.setColor(ChatColor.RED);
            }

            team.addEntry(player.getName());
            player.sendMessage(getString("begun"));
            return;
        }

        if (args[0].equalsIgnoreCase("uninstall")) {
            if (!checkPermission(player, "uninstall")) return;

            if (team == null) return;
            team.unregister();
            player.sendMessage(getString("uninstalled"));
        }
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return List.of("uninstall");

        return null;
    }
}
