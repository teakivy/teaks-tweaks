package me.teakivy.teakstweaks.packs.teakstweaks.spectatoralts;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.datamanager.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.*;

public class SpectatorAlts extends BasePack {

    private static HashMap<UUID, UUID> alts;

    public SpectatorAlts() {
        super("spectator-alts", PackType.TEAKSTWEAKS, Material.ENDER_EYE);

        alts = new HashMap<>();
    }

    @Override
    public void init() {
        super.init();
        DataManager data = TeaksTweaks.getInstance().data;
        FileConfiguration config = data.getConfig();

        if (config.contains("alts")) {
            ConfigurationSection section = config.getConfigurationSection("alts");
            for (String owner : section.getKeys(false)) {
                for (String alt : section.getStringList(owner)) {
                    alts.put(UUID.fromString(alt), UUID.fromString(owner));
                }
            }
        }
    }

    public static List<UUID> getAlts(UUID owner) {
        List<UUID> ret = new ArrayList<>();
        for (UUID uuid : alts.keySet()) {
            if (alts.get(uuid).equals(owner)) {
                ret.add(uuid);
            }
        }
        return ret;
    }

    public static boolean isAlt(UUID uuid) {
        return alts.containsKey(uuid);
    }

    public static void addAlt(UUID owner, UUID alt) {
        Bukkit.getOfflinePlayer(alt).setWhitelisted(true);
        alts.put(alt, owner);

        DataManager data = TeaksTweaks.getInstance().data;

        ConfigurationSection section = data.getConfig().getConfigurationSection("alts");
        if (section == null) {
            section = data.getConfig().createSection("alts");
        }

        if (!section.contains(owner.toString())) {
            section.set(owner.toString(), new ArrayList<>());
        }

        List<String> alts = section.getStringList(owner.toString());
        alts.add(alt.toString());
        section.set(owner.toString(), alts);

        try {
            data.saveConfig();
        } catch (Exception ignored) {}
    }

    public static void removeAlt(UUID alt) {
        Bukkit.getOfflinePlayer(alt).setWhitelisted(false);
        alts.remove(alt);

        DataManager data = TeaksTweaks.getInstance().data;

        ConfigurationSection section = data.getConfig().getConfigurationSection("alts");
        if (section == null) {
            section = data.getConfig().createSection("alts");
        }

        for (String owner : section.getKeys(false)) {
            List<String> alts = section.getStringList(owner);
            alts.remove(alt.toString());
            section.set(owner, alts);
        }

        try {
            data.saveConfig();
        } catch (Exception ignored) {}

        if (Bukkit.getPlayer(alt) != null) {
            Bukkit.getPlayer(alt).kick();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!alts.containsKey(player.getUniqueId())) return;

        player.setGameMode(getGameMode());
    }

    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (!config.getBoolean("force-gamemode")) return;
        if (!alts.containsKey(player.getUniqueId())) return;

        if (event.getNewGameMode() != getGameMode()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (config.getBoolean("allow-player-teleport")) return;
        if (!alts.containsKey(player.getUniqueId())) return;

        for (Entity entity : event.getTo().getNearbyEntities(10, 10, 10)) {
            if (!(entity instanceof Player)) continue;
            if (entity.getUniqueId().equals(alts.get(player.getUniqueId()))) return;
        }

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
            event.setCancelled(true);
        }
    }

    protected GameMode getGameMode() {
        return switch (Objects.requireNonNull(config.getString("gamemode"))) {
            case "survival" -> GameMode.SURVIVAL;
            case "creative" -> GameMode.CREATIVE;
            case "adventure" -> GameMode.ADVENTURE;
            default -> GameMode.SPECTATOR;
        };
    }
}
