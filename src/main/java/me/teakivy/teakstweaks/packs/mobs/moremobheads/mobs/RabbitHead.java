package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.entity.EntityDeathEvent;

public class RabbitHead extends BaseMobHead {

    public RabbitHead() {
        super(EntityType.RABBIT, "rabbit", Sound.ENTITY_RABBIT_AMBIENT);

        addHeadTexture("brown", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2ZkNGY4NmNmNzQ3M2ZiYWU5M2IxZTA5MDQ4OWI2NGMwYmUxMjZjN2JiMTZmZmM4OGMwMDI0NDdkNWM3Mjc5NSJ9fX0");
        addHeadTexture("white", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU0MmQ3MTYwOTg3MTQ4YTVkOGUyMGU0NjliZDliM2MyYTM5NDZjN2ZiNTkyM2Y1NWI5YmVhZTk5MTg1ZiJ9fX0");
        addHeadTexture("black", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJiNDI1ZmYyYTIzNmFiMTljYzkzOTcxOTVkYjQwZjhmMTg1YjE5MWM0MGJmNDRiMjZlOTVlYWM5ZmI1ZWZhMyJ9fX0");
        addHeadTexture("black_white", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVmNzJhMjE5NWViZjQxMTdjNTA1NmNmZTJiNzM1N2VjNWJmODMyZWRlMTg1NmE3NzczZWU0MmEwZDBmYjNmMCJ9fX0");
        addHeadTexture("gold", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY3YjcyMjY1NmZkZWVjMzk5NzRkMzM5NWM1ZTE4YjQ3YzVlMjM3YmNlNWJiY2VkOWI3NTUzYWExNGI1NDU4NyJ9fX0");
        addHeadTexture("salt_pepper", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTIzODUxOWZmMzk4MTViMTZjNDA2MjgyM2U0MzE2MWZmYWFjOTY4OTRmZTA4OGIwMThlNmEyNGMyNmUxODFlYyJ9fX0");
        addHeadTexture("toast", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTFhNTdjM2QwYTliMTBlMTNmNjZkZjc0MjAwY2I4YTZkNDg0YzY3MjIyNjgxMmQ3NGUyNWY2YzAyNzQxMDYxNiJ9fX0");
        addHeadTexture("killer", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFkZDc2NzkyOWVmMmZkMmQ0M2U4NmU4NzQ0YzRiMGQ4MTA4NTM0NzEyMDFmMmRmYTE4Zjk2YTY3ZGU1NmUyZiJ9fX0");
    }

    @Override
    public boolean dropHead(EntityDeathEvent event) {
        Rabbit rabbit = (Rabbit) event.getEntity();

        String key = this.key;
        if (getRabbitKey(event).equals("killer")) key = "killer-rabbit";
        if (getRabbitKey(event).equals("toast")) key = "toast-rabbit";

        return MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance(key));
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        return this.textures.get(getRabbitKey(event));
    }

    @Override
    public String getName(EntityDeathEvent event) {
        return switch (getRabbitKey(event)) {
            case "brown" -> "Brown Rabbit";
            case "white" -> "White Rabbit";
            case "black" -> "Black Rabbit";
            case "black_white" -> "Black and White Rabbit";
            case "gold" -> "Gold Rabbit";
            case "salt_pepper" -> "Salt and Pepper Rabbit";
            case "toast" -> "Toast Rabbit";
            case "killer" -> "Killer Rabbit";
            default -> "Rabbit";
        };
    }

    private String getRabbitKey(EntityDeathEvent event) {
        Rabbit rabbit = (Rabbit) event.getEntity();

        if (rabbit.getCustomName() != null && rabbit.getCustomName().equalsIgnoreCase("Toast")) return "toast";

        return switch (rabbit.getRabbitType()) {
            case BLACK -> "black";
            case BLACK_AND_WHITE -> "black_white";
            case BROWN -> "brown";
            case GOLD -> "gold";
            case SALT_AND_PEPPER -> "salt_pepper";
            case WHITE -> "white";
            case THE_KILLER_BUNNY -> "killer";
        };
    }
}
