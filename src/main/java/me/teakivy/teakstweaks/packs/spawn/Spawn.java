package me.teakivy.teakstweaks.packs.spawn;

import me.teakivy.teakstweaks.commands.SpawnCommand;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;

public class Spawn extends BasePack {

        public Spawn() {
            super("spawn", PackType.TELEPORTATION, Material.COMPASS, new SpawnCommand());
        }
}
