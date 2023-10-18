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

        if (config.getBoolean("gamemode")) {
            new AQuickCommand("gmc", "Change your gamemode to creative", "", true, null, List.of("gamemode creative")).register();
            new AQuickCommand("gms", "Change your gamemode to survival", "", true, null, List.of("gamemode survival")).register();
            new AQuickCommand("gma", "Change your gamemode to adventure", "", true, null, List.of("gamemode adventure")).register();
            new AQuickCommand("gmsp", "Change your gamemode to spectator", "", true, null, List.of("gamemode spectator")).register();
        }

        if (config.getBoolean("reply")) {
            new ReplyQuickCommand().register();
        }
    }
}
