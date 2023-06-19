package me.teakivy.teakstweaks.packs.teleportation.homes;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HomesPack extends BasePack {

    private static List<Home> homes = new ArrayList<>();
    private static final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public HomesPack() {
        super("Homes", "homes", PackType.TELEPORTATION, Material.RECOVERY_COMPASS, "Allows you to set homes ('/home set <name>') and teleport back to them ('/home <name>')");
    }

    @Override
    public void init() {
        super.init();
        for (Player player : main.getServer().getOnlinePlayers()) {
            loadHomes(player);
            loadLegacyHomes(player);
        }
    }

    public static boolean setHome(Player player, String name, Location loc) {
        if (getHome(player, name) != null) return false;

        PersistentDataContainer data = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(main, "homes");
        String homeString = data.get(key, PersistentDataType.STRING);
        homeString = homeString + "," + name;
        data.set(key, PersistentDataType.STRING, homeString);

        NamespacedKey key2 = new NamespacedKey(main, "home." + name);

        Home home = new Home(name, player.getUniqueId(), loc);

        data.set(key2, PersistentDataType.STRING, home.getLocString());

        homes.add(home);

        return true;
    }

    public static boolean removeHome(Player player, String name) {
        Home home = getHome(player, name);

        if (home == null) return false;

        home.delete();
        homes.remove(home);

        return true;
    }

    public static Home getHome(Player player, String name) {
        for (Home home : homes) {
            if (home.getName().equalsIgnoreCase(name) && home.getOwner().equals(player.getUniqueId())) {
                return home;
            }
        }

        return null;
    }

    public static List<Home> getHomes(Player player) {
        List<Home> playerHomes = new ArrayList<>();

        for (Home home : homes) {
            if (home.getOwner().equals(player.getUniqueId())) {
                playerHomes.add(home);
            }
        }

        return playerHomes;
    }

    public static boolean onCooldown(Player player) {
        return getCooldown(player) > 0;
    }

    public static int getCooldown(Player player) {
        long cooldownTime = Main.getInstance().getConfig().getInt("packs.homes.teleport-cooldown") * 1000L;
        if (!cooldowns.containsKey(player.getUniqueId())) {
            return 0;
        }

        long timeLeft = cooldowns.get(player.getUniqueId()) + cooldownTime - System.currentTimeMillis();
        return (int) timeLeft / 1000;
    }

    public static void loadHomes(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(main, "homes");
        String homeString = data.get(key, PersistentDataType.STRING);

        if (homeString == null) return;

        String[] homes = homeString.split(",");

        for (String home : homes) {
            NamespacedKey key2 = new NamespacedKey(main, "home." + home);
            String locString = data.get(key2, PersistentDataType.STRING);
            if (locString == null) continue;

            Home home1 = new Home(home, player.getUniqueId(), locString);
            HomesPack.homes.add(home1);
        }
    }

    public static void unloadHomes(Player player) {
        List<Home> playerHomes = getHomes(player);

        for (Home home : playerHomes) {
            homes.remove(home);
        }
    }

    public static void loadLegacyHomes(Player player) {
        FileConfiguration ldata = main.data.getConfig();

        if (!ldata.contains("homes." + player.getUniqueId())) return;

        for (String home : ldata.getConfigurationSection("homes." + player.getUniqueId()).getKeys(false)) {
            String world = ldata.getString("homes." + player.getUniqueId() + "." + home + ".world");
            double x = ldata.getDouble("homes." + player.getUniqueId() + "." + home + ".x");
            double y = ldata.getDouble("homes." + player.getUniqueId() + "." + home + ".y");
            double z = ldata.getDouble("homes." + player.getUniqueId() + "." + home + ".z");

            Location loc = new Location(main.getServer().getWorld(world), x, y, z);

            setHome(player, home, loc);
        }

        ldata.set("homes." + player.getUniqueId(), null);
        try {
            main.data.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        loadHomes(event.getPlayer());
        loadLegacyHomes(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        unloadHomes(event.getPlayer());
    }

}
