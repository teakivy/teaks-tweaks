package me.teakivy.teakstweaks.utils.command;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
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

    protected final String parentPack;

    protected final String command;
    protected final String description;
    protected final String permission;
    protected final List<String> alias;

    protected final Arg[] args;
    protected final String translationKey;

    protected static CommandMap cmap;
    protected final CommandType commandType;

    protected int cooldownTime;
    protected HashMap<UUID, Long> cooldownMap;


    private CommandSender sender;
    private Player player;

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, CommandType commandType) {
        this(parentPack, command, null, Translatable.getLegacy(command + ".command_description"), null, null, commandType, null);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param commandType The command type
     * @param translationKey The translation key
     */
    public AbstractCommand(String parentPack, String command, CommandType commandType, String translationKey) {
        this(parentPack, command, null, Translatable.getLegacy(command + ".command_description"), null, null, commandType, translationKey);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param args The command arguments
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, String args, CommandType commandType) {
        this(parentPack, command, args, Translatable.getLegacy(command + ".command_description"), null, null, commandType, null);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param args The command arguments
     * @param commandType The command type
     * @param translationKey The translation key
     */
    public AbstractCommand(String parentPack, String command, String args, CommandType commandType, String translationKey) {
        this(parentPack, command, args, Translatable.getLegacy(command + ".command_description"), null, null, commandType, translationKey);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param alias The command aliases
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, List<String> alias, CommandType commandType) {
        this(parentPack, command, null, Translatable.getLegacy(command + ".command_description"), null, alias, commandType, null);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param alias The command aliases
     * @param commandType The command type
     * @param translationKey The translation key
     */
    public AbstractCommand(String parentPack, String command, List<String> alias, CommandType commandType, String translationKey) {
        this(parentPack, command, null, Translatable.getLegacy(command + ".command_description"), null, alias, commandType, translationKey);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param args The command arguments
     * @param alias The command aliases
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, String args, List<String> alias, CommandType commandType) {
        this(parentPack, command, args, Translatable.getLegacy(command + ".command_description"), null, alias, commandType, null);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param args The command arguments
     * @param alias The command aliases
     * @param commandType The command type
     * @param translationKey The translation key
     */
    public AbstractCommand(String parentPack, String command, String args, List<String> alias, CommandType commandType, String translationKey) {
        this(parentPack, command, args, Translatable.getString(command + ".command_description"), null, alias, commandType, translationKey);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param args The command arguments
     * @param description The command description
     * @param aliases The command aliases
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, String args, String description, List<String> aliases, CommandType commandType) {
        this(parentPack, command, args, description, null, aliases, commandType, null);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param args The command arguments
     * @param description The command description
     * @param aliases The command aliases
     * @param commandType The command type
     * @param translationKey The translation key
     */
    public AbstractCommand(String parentPack, String command, String args, String description, List<String> aliases, CommandType commandType, String translationKey) {
        this(parentPack, command, args, description, null, aliases, commandType, translationKey);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param args The command arguments
     * @param description The command description
     * @param permissionMessage The permission message
     * @param aliases The command aliases
     * @param commandType The command type
     * @param translationKey The translation key
     */
    public AbstractCommand(String parentPack, String command, String args, String description, String permissionMessage, List<String> aliases, CommandType commandType, String translationKey) {
        this.parentPack = parentPack;
        this.command = command.toLowerCase();
        this.description = description;
        this.alias = aliases;
        this.permission = "teakstweaks." + parentPack + ".command." + command;
        this.commandType = commandType;
        this.translationKey = translationKey == null ? command : translationKey;

        this.cooldownTime = 0;
        this.cooldownMap = new HashMap<>();
        this.args = new Arg[]{};
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     */
    public AbstractCommand(CommandType type, String parentPack, String command) {
        this(type, parentPack, command, null, null, null, new Arg[]{});
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, Arg... args) {
        this(type, parentPack, command, null, null, null, args);
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param aliases The command aliases
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, List<String> aliases, Arg... args) {
        this(type, parentPack, command, null, aliases, null, args);
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param aliases The command aliases
     * @param translationKey The translation key
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, List<String> aliases, String translationKey, Arg... args) {
        this(type, parentPack, command, null, aliases, translationKey, args);
    }

    /**
     * Create a new AbstractCommand
     * @param type The command type
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param description The command description
     * @param aliases The command aliases
     * @param translationKey The translation key
     * @param args The command arguments
     */
    public AbstractCommand(CommandType type, String parentPack, String command, String description, List<String> aliases, String translationKey, Arg... args) {
        this.parentPack = parentPack;
        this.command = command.toLowerCase();
        this.args = args;
        this.description = description == null ? Translatable.getString(command + ".command_description") : description;
        this.alias = aliases;
        this.permission = "teakstweaks." + parentPack + ".command." + command;
        this.commandType = type;
        this.translationKey = translationKey == null ? command : translationKey;

        this.cooldownTime = 0;
        this.cooldownMap = new HashMap<>();
    }

    /**
     * Register the command
     */
    public void register() {
        if (this.command.equals("mechanics") && !getConfig().getBoolean("settings.mechanics-command")) return;
        if (this.command.equals("test") && !TeaksTweaks.isDevMode()) return;

        if (this.parentPack != null && !TeaksTweaks.getPackConfig(parentPack).getBoolean("enabled")) return;

        ReflectCommand cmd = new ReflectCommand(this.command);
        if (this.alias != null) cmd.setAliases(this.alias);
        if (this.description != null) cmd.setDescription(this.description);
        cmd.setUsage(getUsageString());
        getCommandMap().register("", cmd);
        cmd.setExecutor(this);

        Bukkit.getCommandMap().getKnownCommands().put(this.command, cmd);

        if (this.alias != null) {
            for (String alias : this.alias) {
                Bukkit.getCommandMap().getKnownCommands().put(alias, cmd);
            }
        }

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
        if (checkPermission()) return true;

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
     * @param sender The command sender
     * @param args The command arguments
     */
    @Deprecated
    public void command(CommandSender sender, String[] args) {
        sender.sendMessage(getUsage());
    }

    /**
     * Player Command Executor
     * @param player The command sender
     * @param args The command arguments
     */
    @Deprecated
    public void playerCommand(Player player, String[] args) {
        player.sendMessage(getUsage());
    }


    /**
     * Command Executor
     * @param event The command event
     */
    public void command(CommandEvent event) {
        sender.sendMessage(getUsage());
    }

    /**
     * Player Command Executor
     * @param event The command event
     */
    public void playerCommand(PlayerCommandEvent event) {
        sender.sendMessage(getUsage());
    }

    /**
     * Check if the sender has permission
     * @param sender The command sender
     * @param permission The permission to check
     * @return false if the sender does not have permission
     */
    @Deprecated
    public boolean checkPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(this.permission + "." + permission)) return true;

        sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
        return false;
    }


    /**
     * Check if the sender has permission
     * @param permission The permission to check
     * @return false if the sender does not have permission
     */
    public boolean checkPermission(String permission) {
        if (sender.hasPermission(this.permission + "." + permission)) return true;

        sendError(ErrorType.MISSING_COMMAND_PERMISSION);
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

    @Deprecated
    public List<String> tabComplete(String[] args) {
        return null;
    }

    @Deprecated
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) return null;
        return tabComplete((Player) sender, args);
    }

    @Deprecated
    public List<String> tabComplete(Player player, String[] args) {
        return null;
    }

    public Player checkPlayer() {
        if (sender instanceof Player) return (Player) sender;

        sender.sendMessage(ErrorType.NOT_PLAYER.m());
        return null;
    }

    public boolean checkPermission() {
        if (sender.hasPermission(permission)) return false;

        sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
        return true;
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

    @Deprecated
    public String getString(String key, TagResolver... resolvers) {
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

    @Deprecated
    public void sendUsage(CommandSender sender) {
        sendMessage(getUsage());
    }

    public void sendUsage() {
        sendMessage(getUsage());
    }

    public ConfigurationSection getPackConfig() {
        return TeaksTweaks.getInstance().getConfig().getConfigurationSection("packs." + parentPack);
    }

    public FileConfiguration getConfig() {
        return TeaksTweaks.getInstance().getConfig();
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

    public int getCooldown() {
        if (!this.cooldownMap.containsKey(player.getUniqueId())) return 0;
        return (int) ((this.cooldownMap.get(player.getUniqueId()) + (this.cooldownTime * 1000L) - System.currentTimeMillis()) / 1000L);
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
