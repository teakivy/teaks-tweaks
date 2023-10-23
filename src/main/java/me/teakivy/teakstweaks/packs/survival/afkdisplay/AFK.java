package me.teakivy.teakstweaks.packs.survival.afkdisplay;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AFK extends BasePack {

    public AFK() {
        super("afk-display", PackType.SURVIVAL, Material.BELL);
    }

    @Override
    public void init() {
        super.init();
        register();
    }

    public static HashMap<UUID, Boolean> afk = new HashMap<>();
    static HashMap<UUID, Long> lastMove = new HashMap<>();

    static int afkTimer;

    static long afkMinutes;
    static long kickAfter;

    static Team afkTeam;

    public void register() {
        registerTeam();

        afkMinutes = getConfig().getInt("afk-after");
        kickAfter = getConfig().getInt("kick-after");

        if (getConfig().getBoolean("display-afk-badge")) {
            afkTeam.setPrefix(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getConfig().getString("afk-badge"))) + " ");
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

        afkTimer = Bukkit.getScheduler().runTaskTimer(teaksTweaks, () -> {
            afk.forEach((uuid, isAFK) -> {
                Player player = Bukkit.getOfflinePlayer(uuid).getPlayer();
                if (!isAFK) {
                    if (player != null) {
                        long currentTime = System.currentTimeMillis();
                        if (lastMove.containsKey(uuid)) {
                            if (lastMove.get(uuid) + (afkMinutes * 60 * 1000) < currentTime) {
                                afk(player, false);
                            }
                        }
                    }
                } else {
                    if (player != null) {
                        if (kickAfter == 0) {
                            player.kickPlayer(getConfig().getString("kick-message"));
                        }
                        if (kickAfter > 0) {
                            if (lastMove.get(uuid) + (afkMinutes * 60 * 1000) + (kickAfter * 60 * 1000) < System.currentTimeMillis()) {
                                player.kickPlayer(getConfig().getString("kick-message"));
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

    public static void afk(Player player, boolean fromCommand) {
        if (!fromCommand && lastMove.get(player.getUniqueId()) +  + (afkMinutes * 60 * 1000) > System.currentTimeMillis()) return;
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
            if (teaksTweaks.getConfig().getBoolean("packs.afk-display.message.display-to-self")) {
                player.sendMessage(Translatable.get("afk_display.self_now_afk"));
            }
            if (teaksTweaks.getConfig().getBoolean("packs.afk-display.message.display-to-everyone")) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (player1.getUniqueId() != player.getUniqueId()) {
                        player1.sendMessage(Translatable.get("afk_display.other_now_afk").replace("%player%", player.getName()));
                    }
                }
            }
            if (teaksTweaks.getConfig().getBoolean("packs.afk-display.message.display-to-console")) {
                Logger.info(ChatColor.stripColor(Translatable.get("afk_display.other_now_afk").replace("%player%", player.getName())));
            }
        } else {
            if (teaksTweaks.getConfig().getBoolean("packs.afk-display.message.display-to-self")) {
                player.sendMessage(Translatable.get("afk_display.self_not_afk"));
            }
            if (teaksTweaks.getConfig().getBoolean("packs.afk-display.message.display-to-everyone")) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (player1.getUniqueId() != player.getUniqueId()) {
                        player1.sendMessage(Translatable.get("afk_display.other_not_afk").replace("%player%", player.getName()));
                    }
                }
            }
            if (teaksTweaks.getConfig().getBoolean("packs.afk-display.message.display-to-console")) {
                Logger.info(ChatColor.stripColor(Translatable.get("afk_display.other_not_afk").replace("%player%", player.getName())));
            }
        }
    }

    @Override
    public void unregister() {
        super.unregister();
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
