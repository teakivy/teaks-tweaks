package me.teakivy.teakstweaks.packs.moremobheads.types;

public record TexturedHead(
        String key,
        String name,
        String texture,
        double chance,
        double looting_bonus
) implements HeadEntry {}
