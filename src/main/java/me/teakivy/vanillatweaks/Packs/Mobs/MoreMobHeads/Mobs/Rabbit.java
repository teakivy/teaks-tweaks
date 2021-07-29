package me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Rabbit {
    public static void onDeath(EntityDeathEvent event) {
        org.bukkit.entity.Rabbit rabbit = (org.bukkit.entity.Rabbit) event.getEntity();
        boolean rabbitNamed = rabbit.getCustomName() == null;
        if (rabbit.getRabbitType() == org.bukkit.entity.Rabbit.Type.THE_KILLER_BUNNY)
            if (!MobHeads.dropChance(event.getEntity().getKiller(), 1, 0)) return;
        else if (rabbitNamed) {
            if (rabbit.getCustomName().equals("Toast"))
                if (!MobHeads.dropChance(event.getEntity().getKiller(), 1, 0)) return;
            else {
                    if (!MobHeads.dropChance(event.getEntity().getKiller(), .26, .05)) return;
            }
        }
        else {if (!MobHeads.dropChance(event.getEntity().getKiller(), .26, .05)) return;}


        if (rabbit.getCustomName() != null) {
            if (rabbit.getCustomName().equals("Toast")) {
                event.getDrops().add(MobHeads.getHead("Toast", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTFhNTdjM2QwYTliMTBlMTNmNjZkZjc0MjAwY2I4YTZkNDg0YzY3MjIyNjgxMmQ3NGUyNWY2YzAyNzQxMDYxNiJ9fX0"));
                return;
            }
        }
        if (rabbit.getRabbitType() == org.bukkit.entity.Rabbit.Type.BROWN) event.getDrops().add(MobHeads.getHead("Brown Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2ZkNGY4NmNmNzQ3M2ZiYWU5M2IxZTA5MDQ4OWI2NGMwYmUxMjZjN2JiMTZmZmM4OGMwMDI0NDdkNWM3Mjc5NSJ9fX0"));
        if (rabbit.getRabbitType() == org.bukkit.entity.Rabbit.Type.WHITE) event.getDrops().add(MobHeads.getHead("White Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU0MmQ3MTYwOTg3MTQ4YTVkOGUyMGU0NjliZDliM2MyYTM5NDZjN2ZiNTkyM2Y1NWI5YmVhZTk5MTg1ZiJ9fX0"));
        if (rabbit.getRabbitType() == org.bukkit.entity.Rabbit.Type.BLACK) event.getDrops().add(MobHeads.getHead("Black Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJiNDI1ZmYyYTIzNmFiMTljYzkzOTcxOTVkYjQwZjhmMTg1YjE5MWM0MGJmNDRiMjZlOTVlYWM5ZmI1ZWZhMyJ9fX0"));
        if (rabbit.getRabbitType() == org.bukkit.entity.Rabbit.Type.BLACK_AND_WHITE) event.getDrops().add(MobHeads.getHead("Black and White Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVmNzJhMjE5NWViZjQxMTdjNTA1NmNmZTJiNzM1N2VjNWJmODMyZWRlMTg1NmE3NzczZWU0MmEwZDBmYjNmMCJ9fX0"));
        if (rabbit.getRabbitType() == org.bukkit.entity.Rabbit.Type.GOLD) event.getDrops().add(MobHeads.getHead("Gold Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY3YjcyMjY1NmZkZWVjMzk5NzRkMzM5NWM1ZTE4YjQ3YzVlMjM3YmNlNWJiY2VkOWI3NTUzYWExNGI1NDU4NyJ9fX0"));
        if (rabbit.getRabbitType() == org.bukkit.entity.Rabbit.Type.SALT_AND_PEPPER) event.getDrops().add(MobHeads.getHead("Salt and Pepper Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTIzODUxOWZmMzk4MTViMTZjNDA2MjgyM2U0MzE2MWZmYWFjOTY4OTRmZTA4OGIwMThlNmEyNGMyNmUxODFlYyJ9fX0"));
        if (rabbit.getRabbitType() == org.bukkit.entity.Rabbit.Type.THE_KILLER_BUNNY) event.getDrops().add(MobHeads.getHead("The Killer Bunny", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFkZDc2NzkyOWVmMmZkMmQ0M2U4NmU4NzQ0YzRiMGQ4MTA4NTM0NzEyMDFmMmRmYTE4Zjk2YTY3ZGU1NmUyZiJ9fX0"));
    }
}
