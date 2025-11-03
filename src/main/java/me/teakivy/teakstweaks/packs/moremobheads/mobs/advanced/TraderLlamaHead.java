package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TraderLlama;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class TraderLlamaHead extends AdvancedMobHead {

    public TraderLlamaHead() {
        super(EntityType.TRADER_LLAMA, Sound.ENTITY_LLAMA_SPIT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        TraderLlama traderLlama = (TraderLlama) event.getEntity();
        return switch (traderLlama.getColor()) {
            case WHITE -> "white_trader_llama";
            case CREAMY -> "creamy_trader_llama";
            case BROWN -> "brown_trader_llama";
            case GRAY -> "gray_trader_llama";
        };
    }

    protected void register() {
        super.register();
        List<String> keys = List.of("white_trader_llama", "creamy_trader_llama", "brown_trader_llama", "gray_trader_llama");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
