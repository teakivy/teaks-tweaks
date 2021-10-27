package me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Mobs.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
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


        if (entity.getType().toString().equals("AXOLOTL")) Axolotl.onDeath(event);
        if (entity.getType().toString().equals("BAT")) Bat.onDeath(event);
        if (entity.getType().toString().equals("BEE")) Bee.onDeath(event);
        if (entity.getType().toString().equals("BLAZE")) Blaze.onDeath(event);
        if (entity.getType().toString().equals("CAT")) Cat.onDeath(event);
        if (entity.getType().toString().equals("CAVE_SPIDER")) CaveSpider.onDeath(event);
        if (entity.getType().toString().equals("CHICKEN")) Chicken.onDeath(event);
        if (entity.getType().toString().equals("COD")) Cod.onDeath(event);
        if (entity.getType().toString().equals("COW")) Cow.onDeath(event);
        if (entity.getType().toString().equals("CREEPER")) Creeper.onDeath(event);
        if (entity.getType().toString().equals("DOLPHIN")) Dolphin.onDeath(event);
        if (entity.getType().toString().equals("DONKEY")) Donkey.onDeath(event);
        if (entity.getType().toString().equals("DROWNED")) Drowned.onDeath(event);
        if (entity.getType().toString().equals("ELDER_GUARDIAN")) ElderGuardian.onDeath(event);
        if (entity.getType().toString().equals("ENDER_DRAGON")) EnderDragon.onDeath(event);
        if (entity.getType().toString().equals("ENDERMAN")) Enderman.onDeath(event);
        if (entity.getType().toString().equals("ENDERMITE")) Endermite.onDeath(event);
        if (entity.getType().toString().equals("EVOKER")) Evoker.onDeath(event);
        if (entity.getType().toString().equals("FOX")) Fox.onDeath(event);
        if (entity.getType().toString().equals("GHAST")) Ghast.onDeath(event);
        if (entity.getType().toString().equals("GLOW_SQUID")) GlowSquid.onDeath(event);
        if (entity.getType().toString().equals("GOAT")) Goat.onDeath(event);
        if (entity.getType().toString().equals("GUARDIAN")) Guardian.onDeath(event);
        if (entity.getType().toString().equals("HOGLIN")) Hoglin.onDeath(event);
        if (entity.getType().toString().equals("HORSE")) Horse.onDeath(event);
        if (entity.getType().toString().equals("HUSK")) Husk.onDeath(event);
        if (entity.getType().toString().equals("ILLUSIONER")) Illusioner.onDeath(event);
        if (entity.getType().toString().equals("IRON_GOLEM")) IronGolem.onDeath(event);
        if (entity.getType().toString().equals("LLAMA")) Llama.onDeath(event);
        if (entity.getType().toString().equals("MAGMA_CUBE")) MagmaCube.onDeath(event);
        if (entity.getType().toString().equals("MUSHROOM_COW")) Mooshroom.onDeath(event);
        if (entity.getType().toString().equals("MULE")) Mule.onDeath(event);
        if (entity.getType().toString().equals("OCELOT")) Ocelot.onDeath(event);
        if (entity.getType().toString().equals("PANDA")) Panda.onDeath(event);
        if (entity.getType().toString().equals("PARROT")) Parrot.onDeath(event);
        if (entity.getType().toString().equals("PHANTOM")) Phantom.onDeath(event);
        if (entity.getType().toString().equals("PIG")) Pig.onDeath(event);
        if (entity.getType().toString().equals("PIGLIN")) Piglin.onDeath(event);
        if (entity.getType().toString().equals("PIGLIN_BRUTE")) PiglinBrute.onDeath(event);
        if (entity.getType().toString().equals("PILLAGER")) Pillager.onDeath(event);
        if (entity.getType().toString().equals("POLAR_BEAR")) PolarBear.onDeath(event);
        if (entity.getType().toString().equals("PUFFERFISH")) Pufferfish.onDeath(event);
        if (entity.getType().toString().equals("RABBIT")) Rabbit.onDeath(event);
        if (entity.getType().toString().equals("RAVAGER")) Ravager.onDeath(event);
        if (entity.getType().toString().equals("SALMON")) Salmon.onDeath(event);
        if (entity.getType().toString().equals("SHULKER")) Shulker.onDeath(event);
        if (entity.getType().toString().equals("SILVERFISH")) Silverfish.onDeath(event);
        if (entity.getType().toString().equals("SKELETON_HORSE")) SkeletonHorse.onDeath(event);
        if (entity.getType().toString().equals("SLIME")) Slime.onDeath(event);
        if (entity.getType().toString().equals("SNOWMAN")) SnowGolem.onDeath(event);
        if (entity.getType().toString().equals("SPIDER")) Spider.onDeath(event);
        if (entity.getType().toString().equals("SQUID")) Squid.onDeath(event);
        if (entity.getType().toString().equals("STRAY")) Stray.onDeath(event);
        if (entity.getType().toString().equals("STRIDER")) Strider.onDeath(event);
        if (entity.getType().toString().equals("TRADER_LLAMA")) TraderLlama.onDeath(event);
        if (entity.getType().toString().equals("TROPICAL_FISH")) TropicalFish.onDeath(event);
        if (entity.getType().toString().equals("TURTLE")) Turtle.onDeath(event);
        if (entity.getType().toString().equals("VEX")) Vex.onDeath(event);
        if (entity.getType().toString().equals("VILLAGER")) Villager.onDeath(event);
        if (entity.getType().toString().equals("VINDICATOR")) Vindicator.onDeath(event);
        if (entity.getType().toString().equals("WANDERING_TRADER")) WanderingTrader.onDeath(event);
        if (entity.getType().toString().equals("WITCH")) Witch.onDeath(event);
        if (entity.getType().toString().equals("WITHER")) Wither.onDeath(event);
        if (entity.getType().toString().equals("WOLF")) Wolf.onDeath(event);
        if (entity.getType().toString().equals("ZOGLIN")) Zoglin.onDeath(event);
        if (entity.getType().toString().equals("ZOMBIE_HORSE")) ZombieHorse.onDeath(event);
        if (entity.getType().toString().equals("ZOMBIE_VILLAGER")) ZombieVillager.onDeath(event);
        if (entity.getType().toString().equals("ZOMBIFIED_PIGLIN")) ZombifiedPiglin.onDeath(event);
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

    public static boolean dropChance(Player player, double[] chances) {
        double chance = chances[0];
        double lootingBonus = chances[1];
        Random rand = new Random();
        if (player == null) return false;
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
