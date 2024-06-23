package me.teakivy.teakstweaks.packs.teakstweaks.quickcommands;

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
    }
}
