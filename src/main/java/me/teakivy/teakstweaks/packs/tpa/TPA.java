package me.teakivy.teakstweaks.packs.tpa;

import me.teakivy.teakstweaks.commands.TPACommand;
import me.teakivy.teakstweaks.commands.TPAHereCommand;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.packs.back.Back;
import me.teakivy.teakstweaks.utils.MM;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TPA extends BasePack {

    public TPA() {
        super("tpa", PackType.TELEPORTATION, Material.BEACON, new TPACommand(), new TPAHereCommand());
    }
}
