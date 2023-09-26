package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoinAlert implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("teakstweaks.manage")) return;
        if (!Main.getInstance().getConfig().getBoolean("settings.alert-on-new-version")) return;
        if (!UpdateChecker.hasUpdate()) return;

        TextComponent text = new TextComponent(ChatColor.YELLOW + "There is a new version of Teak's Tweaks available!\nClick here to download " + ChatColor.GOLD + "Teak's Tweaks v" + UpdateChecker.getLatestVersion() + ChatColor.YELLOW + "!");
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GOLD + "Click here to download & install Teak's Tweaks v" + UpdateChecker.getLatestVersion() + "!")));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://modrinth.com/plugin/teaks-tweaks"));

        player.spigot().sendMessage(text);

        player.sendMessage("");
    }

}
