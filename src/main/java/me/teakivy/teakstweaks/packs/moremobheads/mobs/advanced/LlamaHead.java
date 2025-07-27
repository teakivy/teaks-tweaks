package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Llama;
import org.bukkit.event.entity.EntityDeathEvent;

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
}
