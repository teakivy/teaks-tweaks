package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Material;
import org.bukkit.inventory.MenuType;

import java.util.List;

public class QuickCommands extends BasePack {

    public QuickCommands() {
        super("quick-commands", Material.COMMAND_BLOCK);
    }

    @Override
    public void init() {
        super.init();

        if (getConfig().getBoolean("gamemode")) {
            new AQuickCommand("gmc", List.of("gamemode creative")).register();
            new AQuickCommand("gms", List.of("gamemode survival")).register();
            new AQuickCommand("gma", List.of("gamemode adventure")).register();
            new AQuickCommand("gmsp", List.of("gamemode spectator")).register();
        }

        if (getConfig().getBoolean("reply")) {
            new ReplyQuickCommand().register();
        }

        if (getConfig().getBoolean("feed")) {
            new FeedQuickCommand().register();
        }

        if (getConfig().getBoolean("heal")) {
            new HealQuickCommand().register();
        }

        if (getConfig().getBoolean("fly")) {
            new FlyQuickCommand().register();
        }

        if (getConfig().getBoolean("enderchest")) {
            new EnderChestQuickCommand().register();
        }

        if (getConfig().getBoolean("craftingtable")) {
            new MenuQuickCommand("craftingtable", List.of("craft", "workbench", "wb"), Permission.COMMAND_CRAFTINGTABLE, MenuType.CRAFTING).register();
        }

        if (getConfig().getBoolean("anvil")) {
            new MenuQuickCommand("anvil", Permission.COMMAND_ANVIL, MenuType.ANVIL).register();
        }

        if (getConfig().getBoolean("cartographytable")) {
            new MenuQuickCommand("cartographytable", List.of("cartography"), Permission.COMMAND_CARTOGRAPHYTABLE, MenuType.CARTOGRAPHY_TABLE).register();
        }

        if (getConfig().getBoolean("grindstone")) {
            new MenuQuickCommand("grindstone", Permission.COMMAND_GRINDSTONE, MenuType.GRINDSTONE).register();
        }

        if (getConfig().getBoolean("loom")) {
            new MenuQuickCommand("loom", Permission.COMMAND_LOOM, MenuType.LOOM).register();
        }

        if (getConfig().getBoolean("smithingtable")) {
            new MenuQuickCommand("smithingtable", List.of("smithing"), Permission.COMMAND_SMITHINGTABLE, MenuType.SMITHING).register();
        }
    }
}
