package me.teakivy.teakstweaks.packs.teakstweaks.sudoku;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.MM;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Sudoku extends BasePack {

    public Sudoku() {
        super("sudoku", PackType.TEAKSTWEAKS, Material.GOLDEN_SWORD);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!event.getEntity().getScoreboardTags().contains("sudoku-message")) return;
        event.setDeathMessage(MM.toString(getText("death_message", insert("player", event.getEntity().getName()))));
        event.getEntity().getScoreboardTags().remove("sudoku-message");
    }
}
