package me.teakivy.teakstweaks.Packs.Utilities.SpawningSpheres;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.DataManager.DataManager;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SphereData {

    Main main = Main.getPlugin(Main.class);
    DataManager dataManager = main.data;
    FileConfiguration data = dataManager.getConfig();

    public boolean isSphereUsed(Color color) {
        if (color == Color.RED) return data.getBoolean("spheres.red.in-use");
        if (color == Color.BLUE) return data.getBoolean("spheres.blue.in-use");
        if (color == Color.GREEN) return data.getBoolean("spheres.green.in-use");
        return true;
    }

    public Location getSphereLocation(Color color) {
        if (color == Color.RED) return data.getLocation("spheres.red.location");
        if (color == Color.BLUE) return data.getLocation("spheres.blue.location");
        if (color == Color.GREEN) return data.getLocation("spheres.green.location");
        return null;
    }

    public void setSphere(Color color, Location location) {
        String sec = "";
        if (color == Color.RED) sec = "spheres.red";
        if (color == Color.BLUE) sec = "spheres.blue";
        if (color == Color.GREEN) sec = "spheres.green";

        if (location == null) {
            data.set(sec + ".in-use", false);
            data.set(sec + ".location", null);
            return;
        }

        data.set(sec + ".in-use", true);
        data.set(sec + ".location", location);
    }
}
