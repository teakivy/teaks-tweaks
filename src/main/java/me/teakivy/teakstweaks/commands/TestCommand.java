package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class TestCommand extends AbstractCommand {

    public TestCommand() {
        super(CommandType.PLAYER_ONLY, "test", Permission.TEST);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        sendMessage("test", insert("name", event.getPlayer().getName()));
    }
}
