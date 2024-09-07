package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;

import java.util.List;

public class QuickCommands extends BasePack {

    public QuickCommands() {
        super("quick-commands", PackType.TEAKSTWEAKS, Material.COMMAND_BLOCK);
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

        if (getConfig().getBoolean("workbench")) {
            new CraftingTableQuickCommand().register();
        }
    }
}
