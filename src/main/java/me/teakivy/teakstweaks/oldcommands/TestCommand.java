package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.utils.oldcommand.AbstractCommand;
import me.teakivy.teakstweaks.utils.oldcommand.CommandType;
import me.teakivy.teakstweaks.utils.oldcommand.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;

public class TestCommand extends AbstractCommand {

    public TestCommand() {
        super(CommandType.PLAYER_ONLY, "test", Permission.TEST);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        sendMessage("test", insert("name", event.getPlayer().getName()));
    }
}
