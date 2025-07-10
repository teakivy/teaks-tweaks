package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.Material;

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
            new CraftingTableQuickCommand().register();
        }

        if (getConfig().getBoolean("anvil")) {
            new AnvilQuickCommand().register();
        }

        if (getConfig().getBoolean("cartographytable")) {
            new CartographyTableQuickCommand().register();
        }

        if (getConfig().getBoolean("grindstone")) {
            new GrindstoneQuickCommand().register();
        }

        if (getConfig().getBoolean("loom")) {
            new LoomQuickCommand().register();
        }

        if (getConfig().getBoolean("smithingtable")) {
            new SmithingTableQuickCommand().register();
        }
    }
}
