package me.teakivy.teakstweaks.packs.survival.netherportalcoords;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;

public class NetherPortalCoords extends BasePack {

    public NetherPortalCoords() {
        super("Nether Portal Coords", "nether-portal-coords", PackType.SURVIVAL, Material.OBSIDIAN, "Adds a command that calculates where a nether portal must be placed in the other dimension. Useful for syncing up nether portals.", "Command: /portal");
    }
}
