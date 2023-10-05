package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.lang.Translatable;
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

        TextComponent text = new TextComponent(Translatable.get("startup.update.join_alert").replace("%version%", UpdateChecker.getLatestVersion()));
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Translatable.get("startup.update.join_alert.hover").replace("%version%", UpdateChecker.getLatestVersion()))));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Translatable.get("plugin.url")));

        player.spigot().sendMessage(text);

        player.sendMessage("");
    }

}
