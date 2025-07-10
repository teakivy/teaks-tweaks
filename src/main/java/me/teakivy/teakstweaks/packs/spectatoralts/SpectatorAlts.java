package me.teakivy.teakstweaks.packs.spectatoralts;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.JsonManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
        super("spectator-alts", Material.ENDER_EYE);

        alts = new HashMap<>();
    }

    @Override
    public void init() {
        super.init();

        LinkedHashMap<String, Object> map = JsonManager.getFromFile("data/alts.json");
        if (map == null) {
            map = new LinkedHashMap<>();

            JsonManager.saveToFile(map, "data/alts.json");
        }

        for (String owner : map.keySet()) {
            List<String> alts = (List<String>) map.get(owner);
            for (String alt : alts) {
                SpectatorAlts.alts.put(UUID.fromString(alt), UUID.fromString(owner));
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

        LinkedHashMap<String, Object> map = JsonManager.getFromFile("data/alts.json");
        if (map == null) {
            map = new LinkedHashMap<>();
        }

        List<String> alts = (List<String>) map.get(owner.toString());
        if (alts == null) {
            alts = new ArrayList<>();
        }
        if (!alts.contains(alt.toString())) {
            alts.add(alt.toString());
        }
        map.put(owner.toString(), alts);

        JsonManager.saveToFile(map, "data/alts.json");
    }

    public static void removeAlt(UUID alt) {
        Bukkit.getOfflinePlayer(alt).setWhitelisted(false);
        alts.remove(alt);

        LinkedHashMap<String, Object> map = JsonManager.getFromFile("data/alts.json");
        if (map == null) {
            map = new LinkedHashMap<>();
        }

        for (String owner : map.keySet()) {
            List<String> alts = (List<String>) map.get(owner);
            if (alts.contains(alt.toString())) {
                alts.remove(alt.toString());
                map.put(owner, alts);
            }
        }

        JsonManager.saveToFile(map, "data/alts.json");

        if (Bukkit.getPlayer(alt) != null) {
            Bukkit.getPlayer(alt).kickPlayer("");
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
        if (!getConfig().getBoolean("force-gamemode")) return;
        if (!alts.containsKey(player.getUniqueId())) return;

        if (event.getNewGameMode() != getGameMode()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (getConfig().getBoolean("allow-player-teleport")) return;
        if (!alts.containsKey(player.getUniqueId())) return;

        for (Entity entity : event.getTo().getWorld().getNearbyEntities(event.getTo(), 10, 10, 10)) {
            if (!(entity instanceof Player)) continue;
            if (entity.getUniqueId().equals(alts.get(player.getUniqueId()))) return;
        }

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
            event.setCancelled(true);
        }
    }

    protected GameMode getGameMode() {
        return switch (Objects.requireNonNull(getConfig().getString("gamemode"))) {
            case "survival" -> GameMode.SURVIVAL;
            case "creative" -> GameMode.CREATIVE;
            case "adventure" -> GameMode.ADVENTURE;
            default -> GameMode.SPECTATOR;
        };
    }
}
