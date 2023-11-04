package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.Arg;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TagGameCommand extends AbstractCommand {

    public TagGameCommand() {
        super(CommandType.PLAYER_ONLY, "tag", "taggame", Arg.optional("uninstall"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("TaggedTeam");

        if (!event.hasArgs()) {
            if (!checkPermission("give")) return;

            ItemStack tag = new ItemStack(Material.NAME_TAG);
            ItemMeta tagMeta = tag.getItemMeta();
            tagMeta.setDisplayName(getString("item_name"));
            tagMeta.setUnbreakable(true);
            tag.setItemMeta(tagMeta);

            Player player = event.getPlayer();

            player.getInventory().addItem(tag);
            player.addScoreboardTag("tag_it");

            if (team == null) {
                team = sb.registerNewTeam("TaggedTeam");
                team.color(NamedTextColor.RED);
            }

            team.addEntry(player.getName());
            sendMessage("begun");
            return;
        }

        if (event.isArg(0, "uninstall")) {
            if (!checkPermission("uninstall")) return;

            if (team == null) return;
            team.unregister();
            sendMessage("uninstalled");
        }
    }
}
