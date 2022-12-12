package me.teakivy.teakstweaks.packs;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.datamanager.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class BasePack implements Listener {
	public String name;
	public String path;

	protected static Main main = Main.getPlugin(Main.class);

	public ConfigurationSection config;

	public BasePack(String name, String path) {
        this.name = name;
		this.path = path;
		this.config = main.getConfig().getConfigurationSection("packs." + path);
    }

	public void init() {
		registerEvents(this);
		main.addPack(name);
		Logger.log(Logger.LogLevel.INFO, "Registered Pack: " + name);
	}

	public void registerEvents(Listener listener) {
        HandlerList.unregisterAll(listener);
        Bukkit.getServer().getPluginManager().registerEvents(listener, main);
    }

	public void unregister() {
		HandlerList.unregisterAll(this);
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public DataManager getData() {
		return main.data;
	}

	public ConfigurationSection getConfig() {
		return config;
	}

}
