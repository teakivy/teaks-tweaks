package me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Head;
import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Panda {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("panda"))) return;
        org.bukkit.entity.Panda panda = (org.bukkit.entity.Panda) event.getEntity();

        if (panda.getMainGene() == org.bukkit.entity.Panda.Gene.AGGRESSIVE) event.getDrops().add(MobHeads.getHead("Aggressive Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTU0NmU0MzZkMTY2YjE3ZjA1MjFiZDg1MzhlYTEzY2Q2ZWUzYjVkZjEwMmViMzJlM2U0MjVjYjI4NWQ0NDA2MyJ9fX0"));
        if (panda.getMainGene() == org.bukkit.entity.Panda.Gene.LAZY) event.getDrops().add(MobHeads.getHead("Lazy Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTg3ZjFmNWRiMmUyNGRmNGRhYWVkNDY4NWQ2YWVlNWRlYjdjZGQwMjk2MzBmMDA3OWMxZjhlMWY5NzQxYWNmZCJ9fX0"));
        if (panda.getMainGene() == org.bukkit.entity.Panda.Gene.PLAYFUL) event.getDrops().add(MobHeads.getHead("Playful Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNhZGQ0YmYzYzRjYWNlOTE2NjgwZTFmZWY5MGI1ZDE2YWQ2NjQzOTUxNzI1NjY4YmE2YjQ5OTZiNjljYTE0MCJ9fX0"));
        if (panda.getMainGene() == org.bukkit.entity.Panda.Gene.WORRIED) event.getDrops().add(MobHeads.getHead("Worried Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4NmZkMWJmOGNiY2UyM2JjMDhmYjkwNjkxNzE3NjExYWRkYzg1YWI4MjNiNzcxNGFlYzk4YTU2NjBlZmYxNSJ9fX0"));
        if (panda.getMainGene() == org.bukkit.entity.Panda.Gene.BROWN) event.getDrops().add(MobHeads.getHead("Brown Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ1ZjZkNjEyNjcyODY3MWI0NGMxYzc3NWY5OTYxNzQyNGUzMzYxMWI1ZDMxYWQyYWNmZjI4MDRlYjk2ZWIwNiJ9fX0"));
        if (panda.getMainGene() == org.bukkit.entity.Panda.Gene.WEAK) event.getDrops().add(MobHeads.getHead("Weak Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2M1NmEzNTVmYmUwZTJmYmQyOGU4NWM0ZDgxNWZmYTVkMWY5ZDVmODc5OGRiYzI1OWZmODhjNGFkZGIyMDJhZSJ9fX0"));
        if (panda.getMainGene() == org.bukkit.entity.Panda.Gene.NORMAL) event.getDrops().add(MobHeads.getHead("Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkZjQ3ZTAxNWQ1YzFjNjhkNzJiZTExYmI2NTYzODBmYzZkYjUzM2FhYjM4OTQxYTkxYjFkM2Q1ZTM5NjQ5NyJ9fX0"));
    }
}
