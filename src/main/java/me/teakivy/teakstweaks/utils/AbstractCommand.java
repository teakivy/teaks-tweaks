package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.Main;
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

    public AbstractCommand(String parentPack, String command) {
        this(parentPack, command, null, null, null, null);
    }

    public AbstractCommand(String parentPack, String command, String usage) {
        this(parentPack, command, usage, null, null, null);
    }

    public AbstractCommand(String parentPack, String command, String usage, String description) {
        this(parentPack, command, usage, description, null, null);
    }

    public AbstractCommand(String parentPack, String command, String usage, String description, String permissionMessage) {
        this(parentPack, command, usage, description, permissionMessage, null);
    }

    public AbstractCommand(String parentPack, String command, String usage, String description, List<String> aliases) {
        this(parentPack, command, usage, description, null, aliases);
    }

    public AbstractCommand(String parentPack, String command, String usage, String description, String permissionMessage, List<String> aliases) {
        this.parentPack = parentPack;
        this.command = command.toLowerCase();
        this.usage = usage;
        this.description = description;
        this.permMessage = permissionMessage;
        this.alias = aliases;
        this.permission = "teakstweaks." + parentPack + ".command." + command;
    }

    public void register() {
        if (this.command.equalsIgnoreCase("mechanics") &&
                !Main.getInstance().getConfig().getBoolean("settings.mechanics-command")) return;

        if (this.parentPack != null &&
                (!this.parentPack.equalsIgnoreCase("test")
                        && !Main.getPackConfig(parentPack).getBoolean("enabled")))
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

        Logger.log(Logger.LogLevel.INFO, "Registered Command: /" + command);
    }

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

    private final class ReflectCommand extends Command {
        private AbstractCommand exe = null;
        protected ReflectCommand(String command) { super(command); }
        public void setExecutor(AbstractCommand exe) { this.exe = exe; }
        @Override public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) { return exe.onCommand(sender, this, commandLabel, args); }
            return false;
        }

        @Override  public List<String> tabComplete(CommandSender sender, String alais, String[] args) {
            if (exe != null) { return exe.onTabComplete(sender, this, alais, args); }
            return null;
        }
    }

    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }

    public String getString(String key) {
        return Translatable.get(parentPack + "." + key);
    }

    public static String get(String key) {
        return Translatable.get(key);
    }

    public String getUsage() {
        return get("commands.usage").replace("command_usage", this.usage);
    }
}
