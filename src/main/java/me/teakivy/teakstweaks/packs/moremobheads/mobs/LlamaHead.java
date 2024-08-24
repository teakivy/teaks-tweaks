package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Llama;
import org.bukkit.event.entity.EntityDeathEvent;

public class LlamaHead extends BaseMobHead {

    public LlamaHead() {
        super(EntityType.LLAMA, "llama", Sound.ENTITY_LLAMA_AMBIENT);

        addHeadTexture("creamy", "Creamy Llama Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ2N2ZkNGJmZjI5MzI2OWNiOTA4OTc0ZGNhODNjMzM0ODVlNDM1ZWQ1YThlMWRiZDY1MjFjNjE2ODcxNDAifX19");
        addHeadTexture("white", "White Llama Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODAyNzdlNmIzZDlmNzgxOWVmYzdkYTRiNDI3NDVmN2FiOWE2M2JhOGYzNmQ2Yjg0YTdhMjUwYzZkMWEzNThlYiJ9fX0");
        addHeadTexture("brown", "Brown Llama Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJiMWVjZmY3N2ZmZTNiNTAzYzMwYTU0OGViMjNhMWEwOGZhMjZmZDY3Y2RmZjM4OTg1NWQ3NDkyMTM2OCJ9fX0");
        addHeadTexture("gray", "Gray Llama Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2YyNGU1NmZkOWZmZDcxMzNkYTZkMWYzZTJmNDU1OTUyYjFkYTQ2MjY4NmY3NTNjNTk3ZWU4MjI5OWEifX19");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Llama llama = (Llama) event.getEntity();

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
        Llama llama = (Llama) event.getEntity();

        String name = switch (llama.getColor()) {
            case WHITE -> "White";
            case CREAMY -> "Creamy";
            case BROWN -> "Brown";
            case GRAY -> "Gray";
        };

        return name + " Llama";
    }
}
