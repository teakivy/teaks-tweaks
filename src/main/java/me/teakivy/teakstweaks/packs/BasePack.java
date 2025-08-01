package me.teakivy.teakstweaks.packs;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import me.teakivy.teakstweaks.utils.lang.TranslationManager;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.metrics.CustomMetrics;
import me.teakivy.teakstweaks.utils.recipe.RecipeManager;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.translation.Argument;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.intellij.lang.annotations.Subst;

import java.util.ArrayList;
import java.util.List;

public class BasePack implements Listener {
	private final String name;
	private final String path;
	private final String translatableKey;
	private final ItemStack item;
	private final TTPack pack;

	private boolean registered = false;

	/**
	 * Set up the pack
	 * @param pack Pack value
	 * @param material Material for the item
	 */
	public BasePack(TTPack pack, Material material) {
		this.pack = pack;
		this.path = pack.getKey();
		this.translatableKey = this.path.replaceAll("-", "_");
        this.name = TranslationManager.getString(Config.getLanguage(), this.translatableKey + ".name");

		String[] description = TranslationManager.getString(Config.getLanguage(), this.translatableKey + ".description").split("<newline>");

		item = new ItemStack(material);

		List<String> lore = new ArrayList<>();
		for (String line : description) {
			StringBuilder newLine = new StringBuilder();
			for (String word : line.split(" ")) {
				if (newLine.length() > 30) {
					lore.add("<gray>" + newLine);
					newLine = new StringBuilder();
				}
				newLine.append(word).append(" ");
			}
			lore.add("<gray>" + newLine);
			lore.add(" ");
		}
		if (!lore.isEmpty()) lore.removeLast();

		if (getConfig().getKeys(false).size() > 1) {
			lore.add(" ");
			lore.add("<gold>Config");
		}

		for (String key : getConfig().getKeys(false)) {
			if (key.equals("enabled")) continue;
			if (getConfig().get(key).toString().startsWith("MemorySection")) continue;
			if (getConfig().isList(key) && getConfig().getList(key).size() > 3) {
				lore.add("  <gray>" + transformKey(key) + ": <reset><gold><italic>[" + getConfig().getList(key).size() + " Items]");
				continue;
			}

			lore.add("  <gray>" + transformKey(key) + ": <reset><gold>" + getConfig().get(key));
		}

		List<Component> loreComponents = new ArrayList<>();
		for (String line : lore) {
			loreComponents.add(MiniMessage.miniMessage().deserialize("<reset>" + line).decoration(TextDecoration.ITALIC, false));
		}

		ItemMeta meta = item.getItemMeta();
		meta.lore(loreComponents);
		meta.displayName(MiniMessage.miniMessage().deserialize("<gold>" + name).decoration(TextDecoration.ITALIC, false));
		item.setItemMeta(meta);
    }

	/**
	 * Initialize the pack
	 */
	public void init() {
		if (this.registered) return;
		this.registered = true;
		registerEvents(this);

		List<CustomItem> customItems = registerItems();
		if (customItems != null) {
			for (CustomItem customItem : customItems) {
				customItem.register();
			}
		}

		getPlugin().addPack(name);
		Logger.info(Component.text(TranslationManager.getString(Config.getLanguage(), "startup.register.pack").replace("<name>", name)));

		CustomMetrics.addPackEnabled(name);
	}

	public boolean isRegistered() {
		return this.registered;
	}

	public TTPack getPack() {
		return this.pack;
	}

	/**
	 * Register all custom items
	 */
	public List<CustomItem> registerItems() {
		return new ArrayList<>();
	}


	/**
	 * Unregister & reregister all event handlers
	 * @param listener Listener class
	 */
	public void registerEvents(Listener listener) {
        HandlerList.unregisterAll(listener);
        Bukkit.getServer().getPluginManager().registerEvents(listener, getPlugin());
    }

	/**
	 * Unregister all EventHandlers
	 */
	public void unregister() {
		HandlerList.unregisterAll(this);
		RecipeManager.unregister(path);
	}

	/**
	 * Get the name of the pack
	 * @return Pack name
	 */
	public String getName() {
		return name;
	}

	public TeaksTweaks getPlugin() {
		return TeaksTweaks.getInstance();
	}

	/**
	 * Get the pack's config path
	 * @return [pack]
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Get the config section for the pack
	 * @return config.packs.[pack]
	 */
	public ConfigurationSection getConfig() {
		return Config.getPackConfig(path);
	}

	/**
	 * Register a recipe
	 * @param recipe Recipe
	 */
	public void addRecipe(Recipe recipe) {
		RecipeManager.register(path, recipe);
	}

	/**
	 * Get the item shown in the mechanics gui
	 * @return ItemStack
	 */
	public ItemStack getItem() {
		return item;
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

	/**
	 * Get the translatable string from [pack].[key]
	 * @param key Translatable key
	 * @return Translated & colored string
	 */
	protected String getString(String key) {
		return TranslationManager.getString(Config.getLanguage(), translatableKey + "." + key);
	}

	protected Component getText(String key, ComponentLike... resolvers) {
		return Component.translatable(translatableKey + "." + key, resolvers);
	}



	/**
	 * Create an argument for MiniMessage
	 * @param key The key to replace
	 * @param value The value to replace with
	 * @return The argument
	 */
	public static ComponentLike insert(@Subst("") String key, Object value) {
		return Argument.component(key, Component.text(value.toString()));
	}

	/**
	 * Create an argument for MiniMessage
	 * @param key The key to replace
	 * @param value The value to replace with
	 * @return The argument
	 */
	public ComponentLike insert(@Subst("") String key, Component value) {
		return Argument.component(key, value);
	}

	public static Component newText(String text, TagResolver... resolvers) {
		return MiniMessage.miniMessage().deserialize(text, resolvers);
	}
}