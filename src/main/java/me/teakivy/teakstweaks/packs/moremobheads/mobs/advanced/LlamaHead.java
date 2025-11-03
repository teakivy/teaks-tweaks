package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Llama;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class LlamaHead extends AdvancedMobHead {

    public LlamaHead() {
        super(EntityType.LLAMA, Sound.ENTITY_LLAMA_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Llama llama = (Llama) event.getEntity();
        return switch (llama.getColor()) {
            case WHITE -> "white_llama";
            case CREAMY -> "creamy_llama";
            case BROWN -> "brown_llama";
            case GRAY -> "gray_llama";
        };
    }

    protected void register() {
        super.register();
        List<String> keys = List.of("white_llama", "creamy_llama", "brown_llama", "gray_llama");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
