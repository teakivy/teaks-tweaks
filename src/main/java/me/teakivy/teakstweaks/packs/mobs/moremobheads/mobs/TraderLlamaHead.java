package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TraderLlama;
import org.bukkit.event.entity.EntityDeathEvent;

public class TraderLlamaHead extends BaseMobHead {

    public TraderLlamaHead() {
        super(EntityType.TRADER_LLAMA, "trader_llama", Sound.ENTITY_LLAMA_SPIT);

        addHeadTexture("creamy", "Creamy Llama Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTg5YTJlYjE3NzA1ZmU3MTU0YWIwNDFlNWM3NmEwOGQ0MTU0NmEzMWJhMjBlYTMwNjBlM2VjOGVkYzEwNDEyYyJ9fX0");
        addHeadTexture("white", "White Llama Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzA4N2E1NTZkNGZmYTk1ZWNkMjg0NGYzNTBkYzQzZTI1NGU1ZDUzNWZhNTk2ZjU0MGQ3ZTc3ZmE2N2RmNDY5NiJ9fX0");
        addHeadTexture("brown", "Brown Llama Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQyNDc4MGIzYzVjNTM1MWNmNDlmYjViZjQxZmNiMjg5NDkxZGY2YzQzMDY4M2M4NGQ3ODQ2MTg4ZGI0Zjg0ZCJ9fX0");
        addHeadTexture("gray", "Gray Llama Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmU0ZDhhMGJjMTVmMjM5OTIxZWZkOGJlMzQ4MGJhNzdhOThlZTdkOWNlMDA3MjhjMGQ3MzNmMGEyZDYxNGQxNiJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        TraderLlama llama = (TraderLlama) event.getEntity();

        String key = switch (llama.getColor()) {
            case WHITE -> "white";
            case CREAMY -> "creamy";
            case BROWN -> "brown";
            case GRAY -> "gray";
        };

        return textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        TraderLlama llama = (TraderLlama) event.getEntity();

        String name = switch (llama.getColor()) {
            case WHITE -> "White";
            case CREAMY -> "Creamy";
            case BROWN -> "Brown";
            case GRAY -> "Gray";
        };

        return name + " Trader Llama";
    }
}
