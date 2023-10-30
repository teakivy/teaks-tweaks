package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * For a How-To on how to use AbstractCommand see this post @ http://forums.bukkit.org/threads/195990/
 *
 * @author Goblom
 */
public abstract class AbstractCommand implements CommandExecutor, TabExecutor {

    protected final String parentPack;

    protected final String command;
    protected final String description;
    protected final String permission;
    protected final List<String> alias;
    protected final String usage;
    protected final String permMessage;

    protected static CommandMap cmap;
    protected final CommandType commandType;

    protected int cooldownTime;
    protected HashMap<UUID, Long> cooldownMap;

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param usage The command usage
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, String usage, CommandType commandType) {
        this(parentPack, command, usage, Translatable.get(command + ".command_description"), null, null, commandType);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param usage The command usage
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, String usage, List<String> alias, CommandType commandType) {
        this(parentPack, command, usage, Translatable.get(command + ".command_description"), null, alias, commandType);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param usage The command usage
     * @param description The command description
     * @param aliases The command aliases
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, String usage, String description, List<String> aliases, CommandType commandType) {
        this(parentPack, command, usage, description, null, aliases, commandType);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param usage The command usage
     * @param description The command description
     * @param permissionMessage The permission message
     * @param aliases The command aliases
     * @param commandType The command type
     */
    public AbstractCommand(String parentPack, String command, String usage, String description, String permissionMessage, List<String> aliases, CommandType commandType) {
        this.parentPack = parentPack;
        this.command = command.toLowerCase();
        this.usage = usage;
        this.description = description;
        this.permMessage = permissionMessage;
        this.alias = aliases;
        this.permission = "teakstweaks." + parentPack + ".command." + command;
        this.commandType = commandType;

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
        if (this.usage != null) cmd.setUsage(this.usage);
        if (this.permMessage != null) cmd.setPermissionMessage(this.permMessage);
        getCommandMap().register("", cmd);
        cmd.setExecutor(this);

        PluginCommand command = getCommand(this.command, TeaksTweaks.getInstance());

        if (alias != null) command.setAliases(this.alias);
        getCommandMap().register(TeaksTweaks.getInstance().getDescription().getName(), command);

        Logger.info(get("startup.register.command").replace("%command%", this.command));
    }

    private PluginCommand getCommand(String name, Plugin plugin) {
        PluginCommand command = null;

        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);

            command = c.newInstance(name, plugin);
        } catch (Exception e) {
            Logger.error("Failed to create command " + name + " for plugin " + plugin.getName());
        }

        return command;
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
        if (checkPermission(sender)) return true;

        if (commandType == CommandType.PLAYER_ONLY) {
            Player player = checkPlayer(sender);
            if (player == null) return true;

            playerCommand(player, args);
            return true;
        }

        command(sender, args);
        return true;
    }

    /**
     * Command Executor
     * @param sender The command sender
     * @param args The command arguments
     */
    public void command(CommandSender sender, String[] args) {
        sender.sendMessage(getUsage());
    }

    /**
     * Player Command Executor
     * @param player The command sender
     * @param args The command arguments
     */
    public void playerCommand(Player player, String[] args) {
        player.sendMessage(getUsage());
    }

    /**
     * Check if the sender has permission
     * @param sender The command sender
     * @param permission The permission to check
     * @return false if the sender does not have permission
     */
    public boolean checkPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(this.permission + "." + permission)) return true;

        sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
        return false;
    }

    /**
     * Check if the sender has permission without sending a message
     * @param sender The command sender
     * @param permission The permission to check
     * @return false if the sender does not have permission
     */
    public boolean silentCheckPermission(CommandSender sender, String permission) {
        return sender.hasPermission(this.permission + "." + permission);
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
        List<String> result = tabComplete(sender, args);
        if (result == null) result = tabComplete(args);
        return getArgsList(args[args.length - 1], result);
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

    public List<String> tabComplete(String[] args) {
        return null;
    }

    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) return null;
        return tabComplete((Player) sender, args);
    }

    public List<String> tabComplete(Player player, String[] args) {
        return null;
    }

    public Player checkPlayer(CommandSender sender) {
        if (sender instanceof Player) return (Player) sender;

        sender.sendMessage(ErrorType.NOT_PLAYER.m());
        return null;
    }

    public boolean checkPermission(CommandSender sender) {
        if (sender.hasPermission(permission)) return false;

        sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
        return true;
    }

    /**
     * Get a string from the language file within the command's section
     * @param key The key to get
     * @return The string
     */
    public String getString(String key) {
        return Translatable.get(command + "." + key);
    }

    /**
     * Get an error string from the language file within the command's section
     * @param key The key to get
     * @return The string
     */
    public String getError(String key) {
        return Translatable.get(command + ".error." + key);
    }

    /**
     * Get a string from the language file
     * @param key The key to get
     * @return The string
     */
    public static String get(String key) {
        return Translatable.get(key);
    }

    /**
     * Get the command usage message
     * @return The usage message
     */
    public String getUsage() {
        return get("plugin.commands.usage").replace("%command_usage%", this.usage);
    }

    public void sendUsage(CommandSender sender) {
        sender.sendMessage(getUsage());
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
}
