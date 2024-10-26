package me.teakivy.teakstweaks.packs.killboats;

import me.teakivy.teakstweaks.commands.KillBoatsCommand;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;

public class KillBoats extends BasePack {

        public KillBoats() {
            super("kill-boats", PackType.UTILITIES, Material.OAK_BOAT, new KillBoatsCommand());
        }
}
