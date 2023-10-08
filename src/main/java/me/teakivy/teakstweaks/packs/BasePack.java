package me.teakivy.teakstweaks.packs;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.datamanager.DataManager;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BasePack implements Listener {
	public String name;
	public String path;
	public PackType packType;
	public String permission;

	protected static Main main = Main.getPlugin(Main.class);

	public ConfigurationSection config;

	public ItemStack item;

	public String translatableKey;

	public BasePack(String name, String path, PackType packType, Material material, String... description) {
		this.translatableKey = path.replaceAll("-", "_");
		this.name = name;
		this.path = path;
		this.packType = packType;
		this.config = main.getConfig().getConfigurationSection("packs." + path);
		this.permission = "teakstweaks." + path;

		item = new ItemStack(material);

		List<String> lore = new ArrayList<>();
		for (String line : description) {
			StringBuilder newLine = new StringBuilder();
			for (String word : line.split(" ")) {
				if (newLine.length() > 30) {
					lore.add(ChatColor.GRAY + newLine.toString());
					newLine = new StringBuilder();
				}
				newLine.append(word).append(" ");
			}
			lore.add(ChatColor.GRAY + newLine.toString());
			lore.add("");
		}
		if (lore.size() >= 1) lore.remove(lore.size() - 1);

		if (config.getKeys(false).size() > 1) {
			lore.add("");
			lore.add(packType.getColor() + "Config");
		}

		for (String key : config.getKeys(false)) {
			if (key.equals("enabled")) continue;
			if (config.get(key).toString().startsWith("MemorySection")) {
				continue;
			}

			lore.add("  " + ChatColor.GRAY + transformKey(key) + ": " + ChatColor.RESET + packType.getColor() + config.get(key));
		}

		lore.add("");

		lore.add(packType.getColor() + packType.getName());

		item.setLore(lore);

		item.editMeta(meta -> {
			meta.setDisplayName(ChatColor.RESET + packType.getColor().toString() + name);
		});
	}


	public BasePack(String path, PackType packType, Material material) {
		this.translatableKey = path.replaceAll("-", "_");
        this.name = Translatable.get(this.translatableKey + ".name");
		this.path = path;
		this.packType = packType;
		this.config = main.getConfig().getConfigurationSection("packs." + path);
		this.permission = "teakstweaks." + path;

		String[] description = Translatable.get(this.translatableKey + ".description").split("\n");

		item = new ItemStack(material);

		List<String> lore = new ArrayList<>();
		for (String line : description) {
			StringBuilder newLine = new StringBuilder();
			for (String word : line.split(" ")) {
				if (newLine.length() > 30) {
					lore.add(ChatColor.GRAY + newLine.toString());
					newLine = new StringBuilder();
				}
				newLine.append(word).append(" ");
			}
			lore.add(ChatColor.GRAY + newLine.toString());
			lore.add("");
		}
		if (lore.size() >= 1) lore.remove(lore.size() - 1);

		if (config.getKeys(false).size() > 1) {
			lore.add("");
			lore.add(packType.getColor() + "Config");
		}

		for (String key : config.getKeys(false)) {
			if (key.equals("enabled")) continue;
			if (config.get(key).toString().startsWith("MemorySection")) {
				continue;
			}

			lore.add("  " + ChatColor.GRAY + transformKey(key) + ": " + ChatColor.RESET + packType.getColor() + config.get(key));
		}

		lore.add("");

		lore.add(packType.getColor() + packType.getName());

		item.setLore(lore);

		item.editMeta(meta -> {
			meta.setDisplayName(ChatColor.RESET + packType.getColor().toString() + name);
		});
    }

	public void init() {
		registerEvents(this);
		main.addPack(name);
		Logger.log(Logger.LogLevel.INFO, "Registered Pack: " + packType.getColor() + name);
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

	public static DataManager getData() {
		return main.data;
	}

	public static ConfigurationSection getDataConfig() {
		return main.data.getConfig();
	}

	public ConfigurationSection getConfig() {
		return config;
	}

	public ItemStack getItem() {
		return item;
	}

	public boolean hasPermission(Player player) {
		return player.hasPermission(permission);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		// TODO
	}

	private String transformKey(String key) {
		String[] words = key.split("-");
		StringBuilder newKey = new StringBuilder();
		for (String word : words) {
			if (word.equals("xp")) {
				newKey.append("XP");
				continue;
			}
			newKey.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
		}
		return newKey.toString().trim();
	}

	protected String getString(String key) {
		return Translatable.get(translatableKey + "." + key);
	}

}

