package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.lang.reflect.Field;
import java.util.List;

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

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param usage The command usage
     * @param description The command description
     */
    public AbstractCommand(String parentPack, String command, String usage, String description) {
        this(parentPack, command, usage, description, null, null);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param usage The command usage
     * @param description The command description
     * @param aliases The command aliases
     */
    public AbstractCommand(String parentPack, String command, String usage, String description, List<String> aliases) {
        this(parentPack, command, usage, description, null, aliases);
    }

    /**
     * Create a new AbstractCommand
     * @param parentPack The pack this command belongs to
     * @param command The command name
     * @param usage The command usage
     * @param description The command description
     * @param permissionMessage The permission message
     * @param aliases The command aliases
     */
    public AbstractCommand(String parentPack, String command, String usage, String description, String permissionMessage, List<String> aliases) {
        this.parentPack = parentPack;
        this.command = command.toLowerCase();
        this.usage = usage;
        this.description = description;
        this.permMessage = permissionMessage;
        this.alias = aliases;
        this.permission = "teakstweaks." + parentPack + ".command." + command;
    }

    /**
     * Register the command
     */
    public void register() {
        if (this.command.equalsIgnoreCase("mechanics") &&
                !TeaksTweaks.getInstance().getConfig().getBoolean("settings.mechanics-command")) return;

        if (this.parentPack != null &&
                (!this.parentPack.equalsIgnoreCase("test")
                        && !TeaksTweaks.getPackConfig(parentPack).getBoolean("enabled")))
            return;

        ReflectCommand cmd = new ReflectCommand(this.command);
        if (this.alias != null) cmd.setAliases(this.alias);
        if (this.description != null) cmd.setDescription(this.description);
        if (this.usage != null) cmd.setUsage(this.usage);
        if (this.permMessage != null) cmd.setPermissionMessage(this.permMessage);
        getCommandMap().register("", cmd);
        cmd.setExecutor(this);

        Bukkit.getCommandMap().getKnownCommands().put(this.command, cmd);

        if (this.alias != null) {
            for (String alias : this.alias) {
                Bukkit.getCommandMap().getKnownCommands().put(alias, cmd);
            }
        }

        Logger.info(get("startup.register.command").replace("%command%", this.command));
    }

    /**
     * Get the bukkit command map
     * @return
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
    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    /**
     * Tab Completer
     * @param sender The command sender
     * @param cmd The command
     * @param label The command label
     * @param args The command arguments
     * @return A list of possible tab completions
     */
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
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
}
