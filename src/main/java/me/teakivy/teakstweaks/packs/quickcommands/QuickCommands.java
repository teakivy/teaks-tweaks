package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.quickcommands.base.MenuQuickCommand;
import me.teakivy.teakstweaks.packs.quickcommands.base.TargetedQuickCommand;
import me.teakivy.teakstweaks.packs.quickcommands.commands.EnderChestQuickCommand;
import me.teakivy.teakstweaks.packs.quickcommands.commands.ReplyQuickCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class QuickCommands extends BasePack {

    public QuickCommands() {
        super(TTPack.QUICK_COMMANDS, Material.COMMAND_BLOCK);
    }

    @Override
    public void init() {
        super.init();

        if (getConfig().getBoolean("gamemode")) {
            registerGamemodeCommand("gms", GameMode.SURVIVAL, Permission.COMMAND_GMS);
            registerGamemodeCommand("gmc", GameMode.CREATIVE, Permission.COMMAND_GMC);
            registerGamemodeCommand("gma", GameMode.ADVENTURE, Permission.COMMAND_GMA);
            registerGamemodeCommand("gmsp", GameMode.SPECTATOR, Permission.COMMAND_GMSP);
        }

        if (getConfig().getBoolean("reply")) new ReplyQuickCommand().register();
        if (getConfig().getBoolean("enderchest")) new EnderChestQuickCommand().register();

        registerIfEnabled("feed", Permission.COMMAND_FEED, p -> {
            p.setFoodLevel(20);
            p.setSaturation(20);
        });

        registerIfEnabled("heal", Permission.COMMAND_HEAL, p -> {
            p.setHealth(p.getHealthScale());
        });

        registerIfEnabled("fly", Permission.COMMAND_FLY, p -> {
            p.setAllowFlight(!p.getAllowFlight());
        });

        registerMenuCommands(Map.of(
                "craftingtable", new MenuMeta(List.of("craft", "workbench", "wb"), Permission.COMMAND_CRAFTINGTABLE, MenuType.CRAFTING),
                "anvil",         new MenuMeta(List.of(), Permission.COMMAND_ANVIL, MenuType.ANVIL),
                "cartographytable", new MenuMeta(List.of("cartography"), Permission.COMMAND_CARTOGRAPHYTABLE, MenuType.CARTOGRAPHY_TABLE),
                "grindstone",    new MenuMeta(List.of(), Permission.COMMAND_GRINDSTONE, MenuType.GRINDSTONE),
                "loom",          new MenuMeta(List.of(), Permission.COMMAND_LOOM, MenuType.LOOM),
                "smithingtable", new MenuMeta(List.of("smithing"), Permission.COMMAND_SMITHINGTABLE, MenuType.SMITHING)
        ));
    }

    private void registerGamemodeCommand(String name, GameMode mode, Permission permission) {
        new TargetedQuickCommand(name, permission, player -> player.setGameMode(mode)).register();
    }

    private void registerIfEnabled(String key, Permission permission, Consumer<Player> action) {
        if (getConfig().getBoolean(key)) {
            new TargetedQuickCommand(key, permission, action).register();
        }
    }

    private void registerMenuCommands(Map<String, MenuMeta> commands) {
        for (Map.Entry<String, MenuMeta> entry : commands.entrySet()) {
            String key = entry.getKey();
            MenuMeta meta = entry.getValue();
            if (getConfig().getBoolean(key)) {
                new MenuQuickCommand(key, meta.aliases(), meta.permission(), meta.menuType()).register();
            }
        }
    }

    private record MenuMeta(List<String> aliases, Permission permission, MenuType menuType) {}
}
