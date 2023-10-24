package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SpawningSphere;
import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SphereType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {

    public TestCommand() {
        super("test", "test", "/test", "Teak's Tweaks Testing Command.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        new SpawningSphere(player.getLocation(), SphereType.RED).createSphere();


        player.sendMessage(getString("test"));
        return false;
    }

    @Override
    public void register() {
        if (TeaksTweaks.getInstance().getConfig().getBoolean("config.dev-mode")) {
            super.register();
        }
    }
}
