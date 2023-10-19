package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.Sound;
import org.bukkit.entity.Axolotl;
import org.bukkit.event.entity.EntityDeathEvent;

public class AxolotlHead extends BaseMobHead {

    public AxolotlHead() {
        super(org.bukkit.entity.EntityType.AXOLOTL, "axolotl", Sound.ENTITY_AXOLOTL_IDLE_AIR);

        addHeadTexture("lucy", "Lucy Axolotl Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY3ZTE1ZWFiNzMwNjRiNjY4MGQxZGI5OGJhNDQ1ZWQwOTE0YmEzNWE3OTk5OTdjMGRhMmIwM2ZmYzNhODgyNiJ9fX0");
        addHeadTexture("wild", "Wild Axolotl Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDdjZjAyNzQ5OThiZjVhN2YzOGIzNzAzNmUxNTRmMTEyZmEyZTI4YmFkNDBkNWE3Yzk0NzY1ZmU0ZjUyMjExZSJ9fX0");
        addHeadTexture("gold", "Gold Axolotl Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTU4NTYwMTE1ZmFhZDExNjE5YjNkNTVkZTc5ZWYyYTA1M2Y0NzhhNjcxOTRiYmU5MjQ3ZWRlYTBiYzk4ZTgzNCJ9fX0");
        addHeadTexture("cyan", "Cyan Axolotl Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODUxMTk2ZDQzOTMwNjU5ZDcxN2UxYjZhMDQ2YTA4ZDEyMjBmY2I0ZTMxYzQ4NTZiYzMzZTc1NTE5ODZlZjFkIn19fQ");
        addHeadTexture("blue", "Blue Axolotl Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjhmZDEwYjBmZWY0NTk1OTYwYjFmNjQxOTNiYzhhMTg2NWEyZDJlZDQ4YjJlMmNlMDNkOTk0NTYzMDI3ZGY5NSJ9fX0");
    }

    @Override
    public boolean dropHead(EntityDeathEvent event) {
        Axolotl axolotl = (Axolotl) event.getEntity();

        String key = this.key;
        if (axolotl.getVariant() == Axolotl.Variant.BLUE) key = "blue_axolotl";

        return MobHeads.shouldDrop(event.getEntity().getKiller(), key);
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Axolotl axolotl = (Axolotl) event.getEntity();

        switch (axolotl.getVariant()) {
            case WILD -> {
                return this.textures.get("wild");
            }
            case GOLD -> {
                return this.textures.get("gold");
            }
            case CYAN -> {
                return this.textures.get("cyan");
            }
            case BLUE -> {
                return this.textures.get("blue");
            }
        }

        return this.textures.get("lucy");
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Axolotl axolotl = (Axolotl) event.getEntity();

        switch (axolotl.getVariant()) {
            case LUCY -> {
                return "Lucy Axolotl";
            }
            case WILD -> {
                return "Wild Axolotl";
            }
            case GOLD -> {
                return "Gold Axolotl";
            }
            case CYAN -> {
                return "Cyan Axolotl";
            }
            case BLUE -> {
                return "Blue Axolotl";
            }
        }

        return "Axolotl";
    }
}
