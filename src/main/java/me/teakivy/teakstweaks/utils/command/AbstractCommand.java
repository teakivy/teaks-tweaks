package me.teakivy.teakstweaks.utils.command;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.commands.TeaksTweaksCommand;
import me.teakivy.teakstweaks.utils.config.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.translation.Argument;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.intellij.lang.annotations.Subst;

import java.util.List;

public abstract class AbstractCommand {
    private final String parentPack;
    private final String translationKey;
    private final List<String> aliases;

    /**
     * Set up the command
     * @param parentPack The pack this command belongs to
     * @param translationKey The translation key for this command
     */
    public AbstractCommand(String parentPack, String translationKey) {
        this(parentPack, translationKey, List.of());
    }

    /**
     * Set up the command
     * @param parentPack The pack this command belongs to
     * @param translationKey The translation key for this command
     * @param aliases Command aliases
     */
    public AbstractCommand(String parentPack, String translationKey, List<String> aliases) {
        this.parentPack = parentPack;
        this.translationKey = translationKey;
        this.aliases = aliases;
    }

    public abstract LiteralCommandNode<CommandSourceStack> getCommand();

    /**
     * Get the parent pack of this command
     * @return The parent pack
     */
    public String getParentPack() {
        return parentPack;
    }

    /**
     * Get the translation key for this command
     * @return The translation key
     */
    public String getTranslationKey() {
        return translationKey;
    }

    /**
     * Get the aliases for this command
     * @return The aliases
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * Get a translated text component
     * @param key The translation key
     * @param args Arguments for the translation
     * @return The translated component
     */
    public Component getText(String key, ComponentLike... args) {
        return Component.translatable(translationKey + "." + key, args);
    }

    /**
     * Get a translated error component
     * @param key The translation key
     * @param args Arguments for the translation
     * @return The translated component
     */
    public Component getError(String key, ComponentLike... args) {
        return Component.translatable(translationKey + ".error." + key, args);
    }

    public Component get(String key, ComponentLike... args) {
        return Component.translatable(key, args);
    }

    /**
     * Get the config section for the parent pack
     * @return The config section
     */
    public ConfigurationSection getPackConfig() {
        return Config.getPackConfig(parentPack);
    }

    /**
     * Get the main config section
     * @return The main config section
     */
    public ConfigurationSection getConfig() {
        return Config.get();
    }

    /**
     * Create an argument for MiniMessage
     * @param key The key to replace
     * @param value The value to replace with
     * @return The argument
     */
    public ComponentLike insert(@Subst("") String key, Object value) {
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

    public JavaPlugin getPlugin() {
        return TeaksTweaks.getInstance();
    }

    public void register() {
        if (parentPack != null && !Config.isPackEnabled(parentPack)) return;
        getPlugin().getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(this.getCommand(), aliases);
        });
    }
}
