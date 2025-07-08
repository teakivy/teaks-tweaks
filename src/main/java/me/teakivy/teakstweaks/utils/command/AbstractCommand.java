package me.teakivy.teakstweaks.utils.command;

import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.intellij.lang.annotations.Subst;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * For a How-To on how to use AbstractCommand see this post @ http://forums.bukkit.org/threads/195990/
 *
 * @author Goblom
 * @author TeakIvy
 */
public abstract class AbstractCommand implements CommandExecutor, TabExecutor {

    private final String parentPack;

    private final String command;
    private final String description;
    private final Permission permission;
    private final List<String> alias;

    private final Arg[] args;
    private final String translationKey;

    private static CommandMap cmap;
    private final CommandType commandType;

    private int cooldownTime;
    private final HashMap<UUID, Long> cooldownMap;


    private CommandSender sender;
    private Player player;

    public AbstractCommand(CommandType type, String command, Permission permission, List<String> aliases, Arg... args) {
        this(type, null, command, permission, aliases, args);
    }

    public AbstractCommand(CommandType type, String command, Permission permission, Arg... args) {
        this(type, null, command, permission, args);
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param permission The command permission
     */
    public AbstractCommand(CommandType type, String parentPack, String command, Permission permission) {
        this(type, parentPack, command, permission, null, null, null, new Arg[]{});
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param permission The command permission
     * @param translationKey The translation key
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, Permission permission, String translationKey, Arg... args) {
        this(type, parentPack, command, permission, null, null, translationKey, args);
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param permission The command permission
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, Permission permission, Arg... args) {
        this(type, parentPack, command, permission, null, null, null, args);
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param permission The command permission
     * @param aliases The command aliases
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, Permission permission, List<String> aliases, Arg... args) {
        this(type, parentPack, command, permission, null, aliases, null, args);
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param permission The command permission
     * @param aliases The command aliases
     * @param translationKey The translation key
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, Permission permission, List<String> aliases, String translationKey, Arg... args) {
        this(type, parentPack, command, permission, null, aliases, translationKey, args);
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param permission The command permission
     * @param description The command description
     * @param aliases The command aliases
     * @param translationKey The translation key
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, Permission permission, String description, List<String> aliases, String translationKey, Arg... args) {
        this.parentPack = parentPack;
        this.command = command.toLowerCase();
        this.args = args;
        this.translationKey = translationKey == null ? command : translationKey;
        this.description = description == null ? Translatable.getString(this.translationKey + ".command_description") : description;
        this.alias = aliases;
        this.permission = permission;
        this.commandType = type;

        this.cooldownTime = 0;
        this.cooldownMap = new HashMap<>();
    }

    /**
     * Register the command
     */
    public void register() {
        if (this.command.equals("mechanics") && !Config.getBoolean("settings.mechanics-command")) return;
        if (this.command.equals("test") && !Config.isDevMode()) return;

        if (this.parentPack != null && !Config.getPackConfig(parentPack).getBoolean("enabled")) return;

        ReflectCommand cmd = new ReflectCommand(this.command);
        if (this.alias != null) cmd.setAliases(this.alias);
        if (this.description != null) cmd.setDescription(this.description);
        cmd.setUsage(getUsageString());
        getCommandMap().register("", cmd);
        cmd.setExecutor(this);

        getCommandMap().register("teakstweaks", cmd);
        Logger.info(get("startup.register.command", Placeholder.parsed("command", this.command)));
    }

    /**
     * Get the bukkit command map
     * @return The command map
     */
    final CommandMap getCommandMap() {
        if (cmap == null) {
            try {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            } catch (Exception e) { e.printStackTrace(); }
        } else if (cmap != null) { return cmap; }
        return getCommandMap();
    }

    /**
     * Reflection Commands
     */
    private final class ReflectCommand extends Command {
        private AbstractCommand exe = null;

        /**
         * Create a new ReflectCommand instance
         * @param command The command name
         */
        private ReflectCommand(String command) { super(command); }

        /**
         * Set the executor for the command
         * @param exe The executor
         */
        public void setExecutor(AbstractCommand exe) { this.exe = exe; }

        /**
         * Execute the command
         * @param sender The command sender
         * @param commandLabel The command label
         * @param args The command arguments
         * @return Whether or not the command was successful
         */
        @Override public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) { return exe.onCommand(sender, this, commandLabel, args); }
            return false;
        }

        /**
         * Tab complete the command
         * @param sender The command sender
         * @param alais The command alias
         * @param args The command arguments
         * @return A list of possible tab completions
         */
        @Override  public List<String> tabComplete(CommandSender sender, String alais, String[] args) {
            if (exe != null) { return exe.onTabComplete(sender, this, alais, args); }
            return null;
        }
    }

    /**
     * Command Executor
     * @param sender The command sender
     * @param cmd The command
     * @param label The command label
     * @param args The command arguments
     * @return Whether or not the command was successful
     */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        int requiredArgs = 0;
        for (Arg arg : this.args) {
            if (arg == null) continue;
            if (arg.isRequired()) requiredArgs++;
        }
        if (args.length < requiredArgs) {
            sender.sendMessage(getUsage());
            return true;
        }

        this.sender = sender;
        if (!checkPermission()) return true;

        if (commandType == CommandType.PLAYER_ONLY) {
            this.player = checkPlayer();
            if (this.player == null) return true;

            playerCommand(new PlayerCommandEvent(player, args));
            this.player = null;
            this.sender = null;
            return true;
        }

        command(new CommandEvent(sender, args));
        this.sender = null;
        return true;
    }


    /**
     * Command Executor
     * @param event The command event
     */
    public void command(CommandEvent event) {
        this.sender.sendMessage(getUsage());
    }

    /**
     * Player Command Executor
     * @param event The command event
     */
    public void playerCommand(PlayerCommandEvent event) {
        this.sender.sendMessage(getUsage());
    }


    /**
     * Check if the sender has permission
     * @param permission The permission to check
     * @return false if the sender does not have permission
     */
    public boolean checkPermission(Permission permission) {
        return checkPermission(permission, false);
    }
    /**
     * Check if the sender has permission
     * @param permission The permission to check
     * @param silent Whether or not to send an error message
     * @return false if the sender does not have permission
     */
    public boolean checkPermission(Permission permission, boolean silent) {
        if (permission.check(this.sender)) return true;

        if (!silent) sendError(ErrorType.MISSING_COMMAND_PERMISSION);
        return false;
    }

    /**
     * Tab Completer
     * @param sender The command sender
     * @param cmd The command
     * @param label The command label
     * @param args The command arguments
     * @return A list of possible tab completions
     */
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return getArgsList(args[args.length - 1], tabComplete(new TabCompleteEvent(sender, args)));
    }

    /**
     * Get a list of possible tab completions
     * @param arg The argument to complete
     * @param options The possible options
     * @return A list of possible tab completions
     */
    public List<String> getArgsList(String arg, List<String> options) {
        List<String> result = new ArrayList<>();
        if (options == null) return result;
        for (String option : options) {
            if (option == null) continue;
            if (option.toLowerCase().startsWith(arg.toLowerCase())) {
                result.add(option);
            }
        }
        return result;
    }

    /**
     * Tab Completer
     * @param event The tab complete event
     * @return A list of possible tab completions
     */
    public List<String> tabComplete(TabCompleteEvent event) {
        for (int i = 0; i < args.length; i++) {
            Arg arg = args[i];
            if (arg == null) continue;
            if (event.getArgsLength() - 1 == i) {
                return List.of(arg.getOptions());
            }
        }
        return null;
    }

    public Player checkPlayer() {
        if (sender instanceof Player) return (Player) sender;

        this.sender.sendMessage(ErrorType.NOT_PLAYER.m());
        return null;
    }

    public boolean checkPermission() {
        if (permission == null) return true;
        return permission.check(sender);
    }

    /**
     * Get a string from the language file within the command's section
     * @param key The key to get
     * @return The string
     */
    public Component getText(String key, TagResolver... resolvers) {
        return Translatable.get(translationKey + "." + key, resolvers);
    }

    public String getString(String key) {
        return Translatable.getString(translationKey + "." + key);
    }

    /**
     * Get an error string from the language file within the command's section
     * @param key The key to get
     * @return The string
     */
    public Component getError(String key, TagResolver... resolvers) {
        return Translatable.get(translationKey + ".error." + key, resolvers);
    }

    public Component newText(String text, TagResolver... resolvers) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(text, resolvers);
    }

    /**
     * Get a string from the language file
     * @param key The key to get
     * @return The string
     */
    public static Component get(String key, TagResolver... resolvers) {
        return Translatable.get(key, resolvers);
    }

    public String getUsageString() {
        StringBuilder usage = new StringBuilder("/" + command);
        if (args != null) {
            for (Arg arg : args) {
                usage.append(" ").append(arg.toString());
            }
        }

        return usage.toString();
    }

    /**
     * Get the command usage message
     * @return The usage message
     */
    public Component getUsage() {
        return get("plugin.commands.usage", Placeholder.parsed("command_usage", getUsageString()));
    }

    public void sendUsage() {
        sendMessage(getUsage());
    }

    public ConfigurationSection getPackConfig() {
        return Config.getPackConfig(parentPack);
    }

    public ConfigurationSection getConfig() {
        return Config.get();
    }

    public void setCooldownTime(int time) {
        this.cooldownTime = time;
    }

    public int getCooldownTime() {
        return this.cooldownTime;
    }

    @Deprecated
    public void setCooldown(Player player) {
        this.cooldownMap.put(player.getUniqueId(), System.currentTimeMillis());
    }

    @Deprecated
    public boolean isOnCooldown(Player player) {
        if (!this.cooldownMap.containsKey(player.getUniqueId())) return false;
        return this.cooldownMap.get(player.getUniqueId()) + (this.cooldownTime * 1000L) > System.currentTimeMillis();
    }

    @Deprecated
    public int getCooldown(Player player) {
        if (!this.cooldownMap.containsKey(player.getUniqueId())) return 0;
        return (int) ((this.cooldownMap.get(player.getUniqueId()) + (this.cooldownTime * 1000L) - System.currentTimeMillis()) / 1000L);
    }

    public void setCooldown() {
        this.cooldownMap.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public boolean isOnCooldown() {
        if (!this.cooldownMap.containsKey(player.getUniqueId())) return false;
        return this.cooldownMap.get(player.getUniqueId()) + (this.cooldownTime * 1000L) > System.currentTimeMillis();
    }

    public void sendMessage(String key, TagResolver... resolvers) {
        this.sender.sendMessage(getText(key, resolvers));
    }

    public void sendError(String key, TagResolver... resolvers) {
        this.sender.sendMessage(getError(key, resolvers));
    }

    public void sendError(ErrorType errorType) {
        this.sender.sendMessage(errorType.m());
    }

    public void sendMessage(Component message) {
        this.sender.sendMessage(message);
    }

    public void sendString(String message) {
        this.sender.sendMessage(newText(message));
    }

    public void sendText(String message, TagResolver... resolvers) {
        this.sender.sendMessage(newText(message, resolvers));
    }

    public TagResolver.Single insert(@Subst("") String key, String value) {
        return Placeholder.parsed(key, value);
    }
    public TagResolver.Single insert(@Subst("") String key, int value) {
        return Placeholder.parsed(key, value + "");
    }

    public TagResolver.Single insert(@Subst("") String key, Component value) {
        return Placeholder.component(key, value);
    }
}
