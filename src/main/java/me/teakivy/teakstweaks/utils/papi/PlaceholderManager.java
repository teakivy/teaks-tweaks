package me.teakivy.teakstweaks.utils.papi;

import me.teakivy.teakstweaks.TeaksTweaks;
import org.bukkit.Bukkit;

// honestly i just take it from another plugin xd
// it must be checking what PAPI is enabled, and for placeholder registration

public class PlaceholderManager {

    private AfkPapi afkPapi;
    private boolean hasPapi;

    public PlaceholderManager(TeaksTweaks plugin) {
        this.hasPapi = false;
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            this.afkPapi = new AfkPapi(plugin);
            this.hasPapi = true;
        }
    }

    public void load() {
        if (afkPapi != null) {
            afkPapi.register();
            Bukkit.getLogger().info("TeaksTweaks PAPI registered successfully.");
        }
    }

    public void unload() {
        if (afkPapi != null) {
            afkPapi.unregister();
            Bukkit.getLogger().info("TeaksTweaks PAPI unregistered successfully.");
        }
    }

}
