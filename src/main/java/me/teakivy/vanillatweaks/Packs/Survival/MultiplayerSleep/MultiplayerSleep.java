package me.teakivy.vanillatweaks.Packs.Survival.MultiplayerSleep;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.util.Objects;
import java.util.Random;

public class MultiplayerSleep implements Listener {

    int playersSleeping = 0;
    boolean sleptNight = false;
    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        if (!main.getConfig().getBoolean("packs.multiplayer-sleep.enabled")) return;
        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
            if (!event.getPlayer().isSleeping()) return;
            int tempCount = 0;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.isSleeping()) tempCount++;
            }
            playersSleeping = tempCount;

            checkSleep(event.getPlayer());
            immediateSleepDisplay(event.getPlayer(), event.getPlayer().getWorld(), true);

        }, 1L);
    }

    @EventHandler
    public void onSleepExit(PlayerBedLeaveEvent event) {
        if (!main.getConfig().getBoolean("packs.multiplayer-sleep.enabled")) return;
        event.setCancelled(sleptNight);
        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
            int tempCount = 0;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.isSleeping()) tempCount++;
            }
            playersSleeping = tempCount;
            immediateSleepDisplay(event.getPlayer(), event.getPlayer().getWorld(), false);
        }, 1L);
    }

    public void checkSleep(Player player) {
        int sleepingPercentage = main.getConfig().getInt("packs.multiplayer-sleep.sleeping-percentage");
        World world = player.getWorld();
        if (sleepingPercentage == 0) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, () -> {
                if (player.isSleeping()) {
                    sleep(player, world);
                }
            }, 5000);
        } else {
            int sleepingCount = 0;
            for (Player sleeping : Bukkit.getOnlinePlayers()) {
                if (sleeping.isSleeping()) sleepingCount++;
            }
            if (sleepingCount >= Math.ceil(getMaxSleepingPlayers() * (sleepingPercentage / 100)) ) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, () -> {
                    int sleepingCount2 = 0;
                    for (Player sleeping : Bukkit.getOnlinePlayers()) {
                        if (sleeping.isSleeping()) sleepingCount2++;
                    }
                    if (sleepingCount2 >= Math.ceil(getMaxSleepingPlayers() * (sleepingPercentage / 100)) ) {
                        sleep(player, world);
                    }
                }, 5000);
            }
        }
    }

    public void immediateSleepDisplay(Player player, World world, boolean bedEnter) {
        if (Objects.equals(main.getConfig().getString("packs.multiplayer-sleep.announce-type"), "chat") && main.getConfig().getBoolean("packs.multiplayer-sleep.immediate-chat-display") && bedEnter && player.isSleeping())
            Bukkit.broadcastMessage(MessageHandler.getMessage("pack.multiplayer-sleep.player-sleeping").replace("%player_name%", player.getName()));
        if (Objects.equals(main.getConfig().getString("packs.multiplayer-sleep.announce-type"), "actionbar") && world.getTime() > 13000)
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.YELLOW.toString() + playersSleeping + " of " + getMaxSleepingPlayers() + " player(s) asleep"));
                online.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(MessageHandler.getMessage("pack.multiplayer-sleep.percentage-message").replace("%sleeping%", String.valueOf(playersSleeping)).replace("%max_sleeping%", String.valueOf(getMaxSleepingPlayers()))));
            }
    }



    public int getMaxSleepingPlayers() {
        int max = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().getEnvironment() == World.Environment.NORMAL) max++;
        }
        return max;
    }

    public void sleep(Player player, World world) {
        Random rand = new Random();
        if (world.getTime() > 12500) {
            sleptNight = true;
            world.setTime(0);


            if (!main.getConfig().getBoolean("packs.multiplayer-sleep.always-reset-weather-cycle")) {
                if (world.hasStorm()) world.setClearWeatherDuration(rand.nextInt(156000) + 12000);
            } else {
                world.setClearWeatherDuration(rand.nextInt(156000) + 12000);
            }

            if (Objects.equals(main.getConfig().getString("packs.multiplayer-sleep.announce-type"), "chat") && !main.getConfig().getBoolean("packs.multiplayer-sleep.immediate-chat-display"))
                Bukkit.broadcastMessage(MessageHandler.getMessage("pack.multiplayer-sleep.player-sleeping").replace("%player_name%", player.getName()));


            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                sleptNight = false;
            }, 10L);
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
