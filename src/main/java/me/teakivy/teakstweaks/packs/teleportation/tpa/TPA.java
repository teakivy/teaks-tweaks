package me.teakivy.teakstweaks.packs.teleportation.tpa;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;

public class TPA extends BasePack {

    public TPA() {
        super("TPA", "tpa", PackType.TELEPORTATION, Material.BEACON, "Enter '/tpa <player>' to request to teleport to another player.");
    }
}
