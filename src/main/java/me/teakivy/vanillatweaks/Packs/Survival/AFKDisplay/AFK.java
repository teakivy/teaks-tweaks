package me.teakivy.vanillatweaks.Packs.Survival.AFKDisplay;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.Logger.Log;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AFK implements Listener {

    static Main main = Main.getPlugin(Main.class);

    public static HashMap<UUID, Boolean> afk = new HashMap<>();
    static HashMap<UUID, Long> lastMove = new HashMap<>();

    static int afkTimer;

    static long afkMinutes;
    static long kickAfter;

    static Team afkTeam;

    public static void register() {
        registerTeam();

        afkMinutes = main.getConfig().getInt("packs.afk-display.afk-after");
        kickAfter = main.getConfig().getInt("packs.afk-display.kick-after");

        if (main.getConfig().getBoolean("packs.afk-display.display-afk-badge")) {
            afkTeam.setPrefix(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.getConfig().getString("packs.afk-display.afk-badge"))) + " ");
        } else {
            afkTeam.setPrefix("");
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!afk.containsKey(player.getUniqueId())) {
                afk.put(player.getUniqueId(), false);
            }
            if (!lastMove.containsKey(player.getUniqueId())) {
                lastMove.put(player.getUniqueId(), System.currentTimeMillis());
            }
        }

        afkTimer = Bukkit.getScheduler().runTaskTimer(main, () -> {
            afk.forEach((uuid, isAFK) -> {
                Player player = Bukkit.getOfflinePlayer(uuid).getPlayer();
                if (!isAFK) {
                    if (player != null) {
                        long currentTime = System.currentTimeMillis();
                        if (lastMove.containsKey(uuid)) {
                            if (lastMove.get(uuid) + (afkMinutes * 60 * 1000) < currentTime) {
                                afk(player);
                            }
                        }
                    }
                } else {
                    if (player != null) {
                        if (kickAfter == 0) {
                            player.kickPlayer(main.getConfig().getString("packs.afk-display.kick-message"));
                        }
                        if (kickAfter > 0) {
                            if (lastMove.get(uuid) + (afkMinutes * 60 * 1000) + (kickAfter * 60 * 1000) < System.currentTimeMillis()) {
                                player.kickPlayer(main.getConfig().getString("packs.afk-display.kick-message"));
                            }
                        }
                    }
                }
            });
        }, 20L, 20L).getTaskId();
    }

    public static void registerTeam() {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        if (sb.getTeam("AFK") == null) {
            Team afk = sb.registerNewTeam("AFK");
            afk.setColor(ChatColor.GRAY);
        }
        afkTeam = sb.getTeam("AFK");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        afk.put(player.getUniqueId(), false);
        lastMove.put(player.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam("AFK") == null) registerTeam();
        if (afk.get(uuid) || afkTeam.getEntries().contains(player.getName())) {
            unAFK(player);
            return;
        }
        lastMove.put(uuid, System.currentTimeMillis());
    }

    public static void afk(Player player) {
        afkTeam.addEntry(player.getName());
        afk.put(player.getUniqueId(), true);
        displayAFKMessage(player, true);
    }

    public static void unAFK(Player player) {
        afkTeam.removeEntry(player.getName());
        afk.put(player.getUniqueId(), false);
        displayAFKMessage(player, false);
    }

    public static void displayAFKMessage(Player player, Boolean isAFK) {
        if (isAFK) {
            if (main.getConfig().getBoolean("packs.afk-display.message.display-to-self")) {
                player.sendMessage(MessageHandler.getMessage("pack.afk-display.self-now-afk"));
            }
            if (main.getConfig().getBoolean("packs.afk-display.message.display-to-everyone")) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (player1.getUniqueId() != player.getUniqueId()) {
                        player1.sendMessage(MessageHandler.getMessage("pack.afk-display.other-now-afk").replace("%player_name%", player.getName()));
                    }
                }
            }
            if (main.getConfig().getBoolean("packs.afk-display.message.display-to-console")) {
                Log.message(MessageHandler.getMessage("pack.afk-display.console-now-afk").replace("%player_name%", player.getName()));
            }
        } else {
            if (main.getConfig().getBoolean("packs.afk-display.message.display-to-self")) {
                player.sendMessage(MessageHandler.getMessage("pack.afk-display.self-not-afk"));
            }
            if (main.getConfig().getBoolean("packs.afk-display.message.display-to-everyone")) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (player1.getUniqueId() != player.getUniqueId()) {
                        player1.sendMessage(MessageHandler.getMessage("pack.afk-display.other-not-afk").replace("%player_name%", player.getName()));
                    }
                }
            }
            if (main.getConfig().getBoolean("packs.afk-display.message.display-to-console")) {
                Log.message(MessageHandler.getMessage("pack.afk-display.console-not-afk").replace("%player_name%", player.getName()));
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTask(afkTimer);
    }

    public static void uninstall() {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team afkTeam = sb.getTeam("AFK");
        if (afkTeam != null) {
            afkTeam.unregister();
        }
    }


}
