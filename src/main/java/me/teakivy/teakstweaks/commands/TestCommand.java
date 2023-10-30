package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {

    public TestCommand() {
        super(null, "test", "/test", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        player.sendMessage(Translatable.get("stair_chairs.chair_created", Placeholder.parsed("name", player.getName())));
    }
}
