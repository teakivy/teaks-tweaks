package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class WitherHead extends BaseMobHead {

    public WitherHead() {
        super(EntityType.WITHER, "wither", Sound.ENTITY_WITHER_SPAWN);

        addHeadTexture("normal", "Wither Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRhMTA4MjhmNjNiN2VjZGVmZDc2N2IzMjQ1ZmJkYWExM2MzZWMwYzZiMTM3NzRmMWVlOGQzMDdjMDM0YzM4MyJ9fX0");
        addHeadTexture("projectile", "Wither Projectile", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjM3YzU4MTRhOTJmOGVjMGY2YWU5OTMzYWJlOTU0MmUxNjUxOTA3NjhlNzYwNDc4NTQzYWViZWVkNDAyN2MyNyJ9fX0");
        addHeadTexture("blue_projectile", "Blue Wither Projectile", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDM2ODJiMDYyMDNiOWRlNGMyODU0MTA3MWEyNmNkYzM0MGRkMjVkNGMzNzJiNzAyM2VjMmY0MTIwMjFkNjJmNyJ9fX0");
    }

    @Override
    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() != entity) return;
        if (!dropHead(event)) return;


        event.getDrops().add(getNormalHead());

        Random rand = new Random();
        int gen = rand.nextInt(4);
        switch (gen) {
            case 0 -> event.getDrops().add(createHead("Wither Projectile", this.textures.get("projectile")));
            case 1 -> event.getDrops().add(createHead("Blue Wither Projectile", this.textures.get("blue_projectile")));
        }
    }

    public ItemStack getNormalHead() {
        return createHead("Wither Head", this.textures.get("normal"));
    }
}
