package me.teakivy.teakstweaks.packs;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.datamanager.DataManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
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

	protected static Main main = Main.getPlugin(Main.class);

	public ConfigurationSection config;

	public ItemStack item;

	public BasePack(String name, String path, PackType packType, Material material, String... description) {
        this.name = name;
		this.path = path;
		this.packType = packType;
		this.config = main.getConfig().getConfigurationSection("packs." + path);

		item = new ItemStack(material);

		List<Component> lore = new ArrayList<>();

		if (description.length > 0) {
			for (String line : description) {
				StringBuilder newLine = new StringBuilder();
				for (String word : line.split(" ")) {
					if (newLine.length() > 30) {
						lore.add(Component.text(ChatColor.GRAY + newLine.toString()));
						newLine = new StringBuilder();
					}
					newLine.append(word).append(" ");
				}
				lore.add(Component.text(ChatColor.GRAY + newLine.toString()));
				lore.add(Component.text(""));
			}
		}
		if (lore.size() >= 1) lore.remove(lore.size() - 1);

		if (config.getKeys(false).size() > 1) {
			lore.add(Component.text(""));
			lore.add(Component.text(packType.getColor() + "Config"));
		}

		for (String key : config.getKeys(false)) {
			if (key.equals("enabled")) continue;
			if (config.get(key).toString().startsWith("MemorySection")) {
				continue;
			}

			lore.add(Component.text("  " + ChatColor.GRAY + transformKey(key) + ": " + ChatColor.RESET + packType.getColor() + config.get(key)));
		}

		lore.add(Component.text(""));

		lore.add(Component.text(packType.getColor() + packType.getName()));

		item.lore(lore);

		item.editMeta(meta -> {
			meta.displayName(Component.text(ChatColor.RESET + packType.getColor().toString() + name));
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

	public DataManager getData() {
		return main.data;
	}

	public ConfigurationSection getConfig() {
		return config;
	}

	public ItemStack getItem() {
		return item;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
//		if (!path.equalsIgnoreCase("graves")) return;
//		event.getPlayer().getInventory().addItem(item);
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

}

