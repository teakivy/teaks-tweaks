package me.teakivy.vanillatweaks.Packs.ThunderShrine;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shrine implements Listener {

    Main main = Main.getPlugin(Main.class);
    FileConfiguration data = main.data.getConfig();

    public void registerShrines() {

    }

    public List<Location> getShrineLocations() {
        if (!data.contains("thunder-shrines")) return null;
        List<Location> locs = new ArrayList<>();
        for (String shrine : data.getConfigurationSection("thunder-shrines").getKeys(false)) {
            Location loc = new Location(Bukkit.getWorld(Objects.requireNonNull(data.getString("thunder-shrines." + shrine + ".world"))), data.getInt("thunder-shrines." + shrine + ".x"), data.getInt("thunder-shrines." + shrine + ".y"), data.getInt("thunder-shrines." + shrine + ".z"));
            locs.add(loc);
        }
        return locs;
    }

    public boolean isShrineLocation(Location loc) {
        String world = loc.getWorld().getName();
        int x = (int) Math.floor(loc.getX());
        int y = (int) Math.floor(loc.getY());
        int z = (int) Math.floor(loc.getZ());

        for (Location shrine : getShrineLocations()) {
            if (shrine.getWorld().getName().equalsIgnoreCase(world)) {
                if (shrine.getX() == x && shrine.getY() == y && shrine.getZ() == z) return true;
            }
        }
        return false;
    }

    public void saveShrineLocation(Location loc) throws IOException {
        String world = loc.getWorld().getName();
        int x = (int) Math.floor(loc.getX());
        int y = (int) Math.floor(loc.getY());
        int z = (int) Math.floor(loc.getZ());

        String path = "thunder-shrines." + world + x + y + z;
        data.set(path + ".world", world);
        data.set(path + ".x", x);
        data.set(path + ".y", y);
        data.set(path + ".z", z);
        main.data.saveConfig();
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
