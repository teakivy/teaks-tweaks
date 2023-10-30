package me.teakivy.teakstweaks.packs.survival.durabilityping;

public enum DuraPingOption {
    PING_FOR_HAND_ITEMS,
    PING_FOR_ARMOR_ITEMS,
    PING_WITH_SOUND,
    DISPLAY;

    public String toString() {
        return this.name().toLowerCase();
    }

    public String getScoreboardTag() {
        return "dp_" + this;
    }

    public static DuraPingOption fromString(String string) {
        try {
            return valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
