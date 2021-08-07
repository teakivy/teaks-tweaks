package me.teakivy.vanillatweaks.Utils.Logger;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;

public class Log {

    static Main main = Main.getPlugin(Main.class);

    public static void message(String msg) {
        Bukkit.getLogger().info(msg);
    }

}
