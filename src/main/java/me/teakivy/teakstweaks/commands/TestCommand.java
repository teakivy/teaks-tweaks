package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.log.PasteBookUploader;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

import java.io.IOException;

public class TestCommand extends AbstractCommand {

    public TestCommand() {
        super(CommandType.PLAYER_ONLY, "test", Permission.COMMAND_TEAKSTWEAKS);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        sendMessage("test", insert("name", event.getPlayer().getName()));

        try {
            String reportUrl = PasteBookUploader.uploadText("Hello, World!", "Teak's Tweaks Test");
            event.getPlayer().sendMessage(reportUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
