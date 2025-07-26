package me.teakivy.teakstweaks.packs.moremobheads.types;

public record MaterialHead(
        String key,
        String material,
        double chance,
        double looting_bonus
) implements HeadEntry {}
