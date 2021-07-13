package me.teakivy.vanillatweaks.Packs.MoreMobHeads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.MoreMobHeads.Mobs.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

public class MobHeads implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        if (!main.getConfig().getBoolean("packs.more-mob-heads.enabled")) return;

        Entity entity = event.getEntity();


        if (entity.getType().equals(EntityType.AXOLOTL)) Axolotl.onDeath(event);
        if (entity.getType().equals(EntityType.BAT)) Bat.onDeath(event);
        if (entity.getType().equals(EntityType.BEE)) Bee.onDeath(event);
        if (entity.getType().equals(EntityType.BLAZE)) Blaze.onDeath(event);
        if (entity.getType().equals(EntityType.CAT)) Cat.onDeath(event);
        if (entity.getType().equals(EntityType.CAVE_SPIDER)) CaveSpider.onDeath(event);
        if (entity.getType().equals(EntityType.CHICKEN)) Chicken.onDeath(event);
        if (entity.getType().equals(EntityType.COD)) Cod.onDeath(event);
        if (entity.getType().equals(EntityType.COW)) Cow.onDeath(event);
        if (entity.getType().equals(EntityType.CREEPER)) Creeper.onDeath(event);
        if (entity.getType().equals(EntityType.DOLPHIN)) Dolphin.onDeath(event);
        if (entity.getType().equals(EntityType.DONKEY)) Donkey.onDeath(event);
        if (entity.getType().equals(EntityType.DROWNED)) Drowned.onDeath(event);
        if (entity.getType().equals(EntityType.ELDER_GUARDIAN)) ElderGuardian.onDeath(event);
        if (entity.getType().equals(EntityType.ENDER_DRAGON)) EnderDragon.onDeath(event);
        if (entity.getType().equals(EntityType.ENDERMAN)) Enderman.onDeath(event);
        if (entity.getType().equals(EntityType.ENDERMITE)) Endermite.onDeath(event);
        if (entity.getType().equals(EntityType.EVOKER)) Evoker.onDeath(event);
        if (entity.getType().equals(EntityType.FOX)) Fox.onDeath(event);
        if (entity.getType().equals(EntityType.GHAST)) Ghast.onDeath(event);
        if (entity.getType().equals(EntityType.GLOW_SQUID)) GlowSquid.onDeath(event);
        if (entity.getType().equals(EntityType.GOAT)) Goat.onDeath(event);
        if (entity.getType().equals(EntityType.GUARDIAN)) Guardian.onDeath(event);
        if (entity.getType().equals(EntityType.HOGLIN)) Hoglin.onDeath(event);
        if (entity.getType().equals(EntityType.HORSE)) Horse.onDeath(event);
        if (entity.getType().equals(EntityType.HUSK)) Husk.onDeath(event);
        if (entity.getType().equals(EntityType.ILLUSIONER)) Illusioner.onDeath(event);
        if (entity.getType().equals(EntityType.IRON_GOLEM)) IronGolem.onDeath(event);
        if (entity.getType().equals(EntityType.LLAMA)) Llama.onDeath(event);
        if (entity.getType().equals(EntityType.MAGMA_CUBE)) MagmaCube.onDeath(event);
        if (entity.getType().equals(EntityType.MUSHROOM_COW)) Mooshroom.onDeath(event);
        if (entity.getType().equals(EntityType.MULE)) Mule.onDeath(event);
        if (entity.getType().equals(EntityType.OCELOT)) Ocelot.onDeath(event);
        if (entity.getType().equals(EntityType.PANDA)) Panda.onDeath(event);
        if (entity.getType().equals(EntityType.PARROT)) Parrot.onDeath(event);
        if (entity.getType().equals(EntityType.PHANTOM)) Phantom.onDeath(event);
        if (entity.getType().equals(EntityType.PIG)) Pig.onDeath(event);
        if (entity.getType().equals(EntityType.PIGLIN)) Piglin.onDeath(event);
        if (entity.getType().equals(EntityType.PIGLIN_BRUTE)) PiglinBrute.onDeath(event);
        if (entity.getType().equals(EntityType.PILLAGER)) Pillager.onDeath(event);
        if (entity.getType().equals(EntityType.POLAR_BEAR)) PolarBear.onDeath(event);
        if (entity.getType().equals(EntityType.PUFFERFISH)) Pufferfish.onDeath(event);
        if (entity.getType().equals(EntityType.RABBIT)) Rabbit.onDeath(event);
        if (entity.getType().equals(EntityType.RAVAGER)) Ravager.onDeath(event);
        if (entity.getType().equals(EntityType.SALMON)) Salmon.onDeath(event);
        if (entity.getType().equals(EntityType.SHULKER)) Shulker.onDeath(event);
        if (entity.getType().equals(EntityType.SILVERFISH)) Silverfish.onDeath(event);
        if (entity.getType().equals(EntityType.SKELETON_HORSE)) SkeletonHorse.onDeath(event);
        if (entity.getType().equals(EntityType.SLIME)) Slime.onDeath(event);
        if (entity.getType().equals(EntityType.SNOWMAN)) SnowGolem.onDeath(event);
        if (entity.getType().equals(EntityType.SPIDER)) Spider.onDeath(event);
        if (entity.getType().equals(EntityType.SQUID)) Squid.onDeath(event);
        if (entity.getType().equals(EntityType.STRAY)) Stray.onDeath(event);
        if (entity.getType().equals(EntityType.STRIDER)) Strider.onDeath(event);
        if (entity.getType().equals(EntityType.TRADER_LLAMA)) TraderLlama.onDeath(event);
        if (entity.getType().equals(EntityType.TROPICAL_FISH)) TropicalFish.onDeath(event);
        if (entity.getType().equals(EntityType.TURTLE)) Turtle.onDeath(event);
        if (entity.getType().equals(EntityType.VEX)) Vex.onDeath(event);
        if (entity.getType().equals(EntityType.VILLAGER)) Villager.onDeath(event);
        if (entity.getType().equals(EntityType.VINDICATOR)) Vindicator.onDeath(event);
        if (entity.getType().equals(EntityType.WANDERING_TRADER)) WanderingTrader.onDeath(event);
        if (entity.getType().equals(EntityType.WITCH)) Witch.onDeath(event);
        if (entity.getType().equals(EntityType.WITHER)) Wither.onDeath(event);
        if (entity.getType().equals(EntityType.WOLF)) Wolf.onDeath(event);
        if (entity.getType().equals(EntityType.ZOGLIN)) Zoglin.onDeath(event);
        if (entity.getType().equals(EntityType.ZOMBIE_HORSE)) ZombieHorse.onDeath(event);
        if (entity.getType().equals(EntityType.ZOMBIE_VILLAGER)) ZombieVillager.onDeath(event);
        if (entity.getType().equals(EntityType.ZOMBIFIED_PIGLIN)) ZombifiedPiglin.onDeath(event);
    }

    public static ItemStack getHead(String name, String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        GameProfile profile = new GameProfile(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), name);
        profile.getProperties().put("textures", new Property("textures", texture));
        Field field;
        try {
            field = headMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException x) {
            x.printStackTrace();
        }

        head.setItemMeta(headMeta);
        return head;
    }

    public static boolean dropChance(Player player, double chance, double lootingBonus) {
        Random rand = new Random();
        if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
            for (int i = 0; i < player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS); i++) {
                chance = chance + lootingBonus;
            }
        } else {
            if (player.getInventory().getItemInOffHand().getItemMeta() != null) {
                for (int i = 0; i < player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS); i++) {
                    chance = chance + lootingBonus;
                }
            }
        }
        if (chance > 1) chance = 1;
        double num = rand.nextDouble();
        return num < chance;
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }
}
