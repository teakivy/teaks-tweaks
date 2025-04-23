package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDeathEvent;

import static org.bukkit.entity.Wolf.Variant.*;

public class WolfHead extends BaseMobHead {

    public WolfHead() {
        super(EntityType.WOLF, "wolf", Sound.ENTITY_WOLF_CUTE_PANT);

        addHeadTexture("ashen_wolf", "Ashen Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzIzODRjNWNmMTg5NDhiODNhODk1NDhkYmE1YTk5NDVlZGVlZmM1ZTk2NTRjNWQ2ZDM4YWUxMGE1ZDUwMmU3NSJ9fX0");
        addHeadTexture("angry_ashen_wolf", "Angry Ashen Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NiYzMwNjZkMzFjNDM5MDM1MDM4ZmQ2ODc1ZDVkYmVlYzM5NjhjMWI4MDA2ZmZiZmI1ZjY3NGQ3NmM4OWNkZSJ9fX0");

        addHeadTexture("black_wolf", "Black Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzVhNjZhNDJiMjVmODIyYTdlMTZhMjE4NzUyOGQxYTJlMjk0YTAxZDlmODUwNjcxYjk0Yzk1NzQyYmI0OTE2ZSJ9fX0");
        addHeadTexture("angry_black_wolf", "Angry Black Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVlZDQ3ZGVkMjcwOGIxM2Q5MmViNTBmYjY4ZThjMWUxMWIzOWEwY2Q0NWIzOTM3MmVlYWQ4NzJjNDllZWFlYiJ9fX0");

        addHeadTexture("chestnut_wolf", "Chestnut Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I5YjBkNDg0NDIyMDRmZjZmZDM5ZmEwNzQxNjcxMThlOWMwNjZjZGUzODg4OTc3ZDBmNjAzNmUxZDhhNjllZSJ9fX0");
        addHeadTexture("angry_chestnut_wolf", "Angry Chestnut Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg0YjI4ZjIzMmIxNGE1OWI2Y2I3NzU3MzIzOTc0ZWE1MDJiMWJjYjk4NGRlYTMwMDkzZWMyMWVkMmFkZTMxMiJ9fX0");

        addHeadTexture("pale_wolf", "Pale Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWVlMzNjMmRjMDdkNzZiNGYwM2U2NjQyN2EwOGNiYTJlODE3OWQwNzVhZTY0YjljZTE1MGFhNDIwOWM1YWYzOSJ9fX0");
        addHeadTexture("angry_pale_wolf", "Angry Pale Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg4N2E0Mjc4NzkwMGU2NzE2ZmE0NjJmYmFkOGRlYjU1MjZiOTQzOTg3OTc0MTRmMDNmNjAxM2VmODg1YTFkYiJ9fX0");

        addHeadTexture("rusty_wolf", "Rusty Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjM0NTVmNjA0OGE2ODA5OGNkMjc2MzRlMzE0NmM4MWM4MjY5YWVlZmNmMGFmZjkxY2M5NzZlZmEwYmFhMTE0NiJ9fX0");
        addHeadTexture("angry_rusty_wolf", "Angry Rusty Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDFmMWMzMmU1MjU4ZjNkOGY4ZDE4MWZiMzBkZjYxZTA2OTNlNTVkNTM4YTEzZWVhYmRmNjMwMGYzODA4M2FkYyJ9fX0");

        addHeadTexture("snowy_wolf", "Snowy Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVjYWRhYjUwYWE4ZDQxZmE5YjM2OWEyZjg0Zjk3NDU2YmU3OTAwYjIyMGVjZTNiOTVlOGEwMDk2ODY2MGQ1In19fQ");
        addHeadTexture("angry_snowy_wolf", "Angry Snowy Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJiN2MyODZjMjMwODI2YjI5ZTdmZDM3ZjI1NzNiOTAxNWM0MjJiYzM4ZmViMTRkOWEzMTdjNjg1NWFkYTNmNiJ9fX0");

        addHeadTexture("spotted_wolf", "Spotted Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTczYjlhNjQzMWFmMjZiY2IzMTgyNmViNmZkOWY0YjM1Yjk0N2JhNTg4MmM2ZTRhYTkzNTg4NjMzZjdiOGQ5ZCJ9fX0");
        addHeadTexture("angry_spotted_wolf", "Angry Spotted Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc4NmI3MzkzNDhhYTg1MDJlYTE4NWRmYjE0YmY1YWIwMWUyOWUwODJkMWZlYjg2MTNiM2ZlOTNlMGRlYmQ4ZSJ9fX0");

        addHeadTexture("striped_wolf", "Striped Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2ZjNTJmYjNjZGZjNmFlYjAwZTY3YzFiN2E1OWQ4ZDMyMGRmNDQ2NTZjN2FmNjgyNGIxM2NhNjA3OTJhYTdkNyJ9fX0");
        addHeadTexture("angry_striped_wolf", "Angry Striped Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ2MGMyNTQ4OGIwNjcyNzY2OWE2OTE1ZDFkYWRhYTlhN2QyODMxYjQ2MGJlZTMwZTVkYTQwNzg3NDcwNTAwMSJ9fX0");

        addHeadTexture("woods_wolf", "Woods Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQxMmFiMTc2NDdiNjljOTQyMTc2OTU3MmFjNjc0ZGUxOTkxMjRjMjg0YjllZDFmNjVhMjg1YzM4Y2QyYTUwNCJ9fX0");
        addHeadTexture("angry_woods_wolf", "Angry Woods Wolf Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMwNTgzZGJhOGVhNjE0MzA1ZGIwMTBiYWJkYzViYjQ0ZTlhMjAwMzMxMWIzOTlkODk2NWU3NzJkZDAxOTFmYiJ9fX0");

    }

    @Override
    public Sound getSound(EntityDeathEvent event) {
        if (event == null) return this.sound;
        Wolf wolf = (Wolf) event.getEntity();
        if (wolf.isAngry()) return Sound.ENTITY_WOLF_GROWL;
        return this.sound;
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Wolf wolf = (Wolf) event.getEntity();
        String key = "";
        if (wolf.isAngry()) key = "angry_";
        key += wolf.getVariant().toString().toLowerCase().replace("minecraft:", "").replace("_wolf", "") + "_wolf";

        return this.textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Wolf wolf = (Wolf) event.getEntity();
        String key = "";
        if (wolf.isAngry()) key = "angry_";
        key += wolf.getVariant().toString().toLowerCase().replace("minecraft:", "").replace("_wolf", "") + "_wolf";

        return toTitleCase(key.replace("_", " "));
    }

    private String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toUpperCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
