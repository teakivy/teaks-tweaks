package me.teakivy.teakstweaks.utils.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.translation.Argument;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.intellij.lang.annotations.Subst;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractCommand {
    private final TTPack parentPack;
    private final String translationKey;
    private final List<String> aliases;
    private TTCommand command;

    private int cooldownTime;
    private final HashMap<UUID, Long> cooldownMap;

    /**
     * Set up the command
     * @param command The command object
     * @param translationKey The translation key for this command
     */
    public AbstractCommand(TTCommand command, String translationKey) {
        this(command, translationKey, List.of());
    }

    /**
     * Set up the command
     * @param translationKey The translation key for this command
     */
    public AbstractCommand(TTPack parentPack, String translationKey) {
        this(parentPack, translationKey, List.of());
    }

    /**
     * Set up the command
     * @param translationKey The translation key for this command
     */
    public AbstractCommand(TTPack parentPack, String translationKey, List<String> aliases) {
        this.parentPack = parentPack;
        this.translationKey = translationKey;
        this.aliases = aliases;

        cooldownMap = new HashMap<>();
        cooldownTime = 0;
    }

    /**
     * Set up the command
     * @param command The command object
     * @param translationKey The translation key for this command
     * @param aliases Command aliases
     */
    public AbstractCommand(TTCommand command, String translationKey, List<String> aliases) {
        this.parentPack = command.getParentPack();
        this.translationKey = translationKey;
        this.aliases = aliases;
        this.command = command;

        cooldownMap = new HashMap<>();
        cooldownTime = 0;
    }

    public abstract LiteralCommandNode<CommandSourceStack> getCommand();

    /**
     * Get the parent pack of this command
     * @return The parent pack
     */
    public TTPack getParentPack() {
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
        return Config.getPackConfig(parentPack.getKey());
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
        if (parentPack != null && !Config.isPackEnabled(parentPack.getKey()) && !Config.isDevMode()) return;
        getPlugin().getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(this.getCommand(), aliases);
        });
    }

    public boolean isEnabled() {
        return parentPack == null || Config.isPackEnabled(parentPack.getKey()) || Config.isDevMode();
    }

    protected Command<CommandSourceStack> playerOnly(Function<CommandContext<CommandSourceStack>, Integer> function) {
        return context -> {
            if (!(context.getSource().getSender() instanceof Player)) {
                context.getSource().getSender().sendMessage(ErrorType.NOT_PLAYER.m());
                return Command.SINGLE_SUCCESS;
            }
            return function.apply(context);
        };
    }

    protected Player checkPlayer(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getSender() instanceof Player)) {
            ctx.getSource().getSender().sendMessage(ErrorType.NOT_PLAYER.m());
            return null;
        }
        return (Player) ctx.getSource().getSender();
    }

    protected Predicate<CommandSourceStack> perm(Permission permission) {
        return source -> source.getSender().hasPermission(permission.getPermission());
    }

    public void setCooldownTime(int time) {
        this.cooldownTime = time;
    }

    public int getCooldownTime() {
        return this.cooldownTime;
    }

    public void setCooldown(Player player) {
        this.cooldownMap.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public boolean isOnCooldown(Player player) {
        if (!this.cooldownMap.containsKey(player.getUniqueId())) return false;
        return this.cooldownMap.get(player.getUniqueId()) + (this.cooldownTime * 1000L) > System.currentTimeMillis();
    }

    public int getCooldown(Player player) {
        if (!this.cooldownMap.containsKey(player.getUniqueId())) return 0;
        return (int) ((this.cooldownMap.get(player.getUniqueId()) + (this.cooldownTime * 1000L) - System.currentTimeMillis()) / 1000L);
    }

    public static SuggestionProvider<CommandSourceStack> createSuggestions(List<String> suggestions) {
        return (CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) -> {
            builder.restart();
            String remaining = builder.getRemainingLowerCase();
            for (String suggestion : suggestions) {
                if (suggestion.toLowerCase().startsWith(remaining)) {
                    builder.suggest(suggestion);
                }
            }
            return builder.buildFuture();
        };
    }
}
