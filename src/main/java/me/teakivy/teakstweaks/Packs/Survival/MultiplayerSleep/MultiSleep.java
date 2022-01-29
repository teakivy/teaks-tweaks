package me.teakivy.teakstweaks.Packs.Survival.MultiplayerSleep;

import me.teakivy.teakstweaks.Packs.BasePack;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.util.Objects;
import java.util.Random;

public class MultiSleep extends BasePack {
    int sleeping = 0;
    int sleepingPercentage;
    BossBar bar;

    public MultiSleep() {
        super("Multiplayer Sleep", "multiplayer-sleep");
        this.sleepingPercentage = getConfig().getInt("sleeping-percentage");
        this.bar = Bukkit.getServer().createBossBar("0 of 0 player(s) asleep", BarColor.WHITE, BarStyle.SOLID);
    }

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        Bukkit.getScheduler().runTaskLater(main, () -> {
        sleeping++;
        if (event.isCancelled() || !event.getPlayer().isSleeping()) {
            sleeping--;
            return;
        }


        if (Objects.equals(getConfig().getString("announce-type"), "bossbar")  && sleeping > 0) {
            bar.setTitle(sleeping + " of " + getMaxSleeping() + " player(s) asleep");
            bar.setColor(getBossBarColor());
            bar.setProgress((100.0/getMaxSleeping() * sleeping) / 100);
            bar.setVisible(true);

            for (Player online : Bukkit.getOnlinePlayers()) {
                bar.addPlayer(online);
            }
        }
        if (canSleep()) {
            if (Objects.equals(getConfig().getString("announce-type"), "actionbar")) {
                actionbarMessage();
            }
            triggerSleep(event.getPlayer());
        }
        }, 1L);
    }

    @EventHandler
    public void onSleepEnd(PlayerBedLeaveEvent event) {
        sleeping--;
        if (event.isCancelled()) sleeping++;


        if (Objects.equals(getConfig().getString("announce-type"), "actionbar")) {
            actionbarMessage();
        }

        if (Objects.equals(getConfig().getString("announce-type"), "bossbar") && sleeping > 0) {
            bar.setTitle(sleeping + " of " + getMaxSleeping() + " player(s) asleep");
            bar.setColor(getBossBarColor());
            bar.setProgress((100.0/getMaxSleeping() * sleeping) / 100);
            bar.setVisible(true);

            for (Player online : Bukkit.getOnlinePlayers()) {
                bar.addPlayer(online);
            }
        }

        if (Objects.equals(getConfig().getString("announce-type"), "bossbar") && sleeping <= 0) {
            for (Player player : bar.getPlayers()) {
                bar.removePlayer(player);
            }
        }

        Bukkit.getScheduler().runTaskLater(main, () -> {
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(" "));
        }, 1L);
    }

    public void sleepMessage(Player player) {
        Bukkit.broadcastMessage(MessageHandler.getMessage("pack.multiplayer-sleep.player-sleeping").replace("%player_name%", player.getName()));
    }

    public void actionbarMessage() {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            if (sleeping <= 0) return;
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(MessageHandler.getMessage("pack.multiplayer-sleep.percentage-message").replace("%sleeping%", String.valueOf(sleeping)).replace("%max_sleeping%", String.valueOf(getMaxSleeping()))));
            }
        }, 1L);
    }

    public boolean canSleep() {
        int sleepAvaliable = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getEnvironment() == World.Environment.NORMAL) {
                sleepAvaliable++;
            }
        }

        if (sleeping >= 1 && sleepingPercentage <= 0) return true;
        return 100 / sleepAvaliable * sleeping >= sleepingPercentage;
    }

    public BarColor getBossBarColor() {
        String cs = getConfig().getString("boss-bar-color");
        BarColor color = BarColor.WHITE;
        if (cs == null) return color;
        cs = cs.toLowerCase();
        switch (cs) {
            case "blue" -> color = BarColor.BLUE;
            case "green" -> color = BarColor.GREEN;
            case "pink" -> color = BarColor.PINK;
            case "purple" -> color = BarColor.PURPLE;
            case "red" -> color = BarColor.RED;
            case "yellow" -> color = BarColor.YELLOW;
        }
        return color;
    }

    public int getMaxSleeping() {
        int sleepAvaliable = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getEnvironment() == World.Environment.NORMAL) {
                sleepAvaliable++;
            }
        }

        if (sleepingPercentage <= 0) return 1;
        return sleepAvaliable / (100 / sleepingPercentage);
    }

    public void triggerSleep(Player player) {
        Random rand = new Random();
        Bukkit.getScheduler().runTaskLater(main, () -> {
            if (!canSleep()) return;
            for (World world : Bukkit.getWorlds()) {
                if (world.getEnvironment() == World.Environment.NORMAL) {
                    world.setTime(1000);

                    if (!getConfig().getBoolean("always-reset-weather-cycle")) {
                        if (world.hasStorm()) world.setClearWeatherDuration(rand.nextInt(156000) + 12000);
                    } else {
                        world.setClearWeatherDuration(rand.nextInt(156000) + 12000);
                    }
                }
            }
            if (Objects.equals(getConfig().getString("announce-type"), "chat") && !getConfig().getBoolean("immediate-chat-display")) {
                sleepMessage(player);
            }
        }, 5 * 20L);
    }

    public void log() {
        int sleepAvaliable = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getEnvironment() == World.Environment.NORMAL) {
                sleepAvaliable++;
            }
        }

        System.out.println(sleepAvaliable);
        System.out.println(sleeping);
        System.out.println(100 / sleepAvaliable * sleeping);
        System.out.println(sleepingPercentage);
    }

}
