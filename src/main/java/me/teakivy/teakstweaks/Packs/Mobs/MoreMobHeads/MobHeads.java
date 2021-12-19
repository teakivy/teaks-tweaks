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

        String entityName = entity.getType().toString();


        if (entityName.equals("BAT")) Bat.onDeath(event);
        if (entityName.equals("AXOLOTL")) Axolotl.onDeath(event);
        if (entityName.equals("BEE")) Bee.onDeath(event);
        if (entityName.equals("BLAZE")) Blaze.onDeath(event);
        if (entityName.equals("CAT")) Cat.onDeath(event);
        if (entityName.equals("CAVE_SPIDER")) CaveSpider.onDeath(event);
        if (entityName.equals("CHICKEN")) Chicken.onDeath(event);
        if (entityName.equals("COD")) Cod.onDeath(event);
        if (entityName.equals("COW")) Cow.onDeath(event);
        if (entityName.equals("CREEPER")) ChargedCreeper.onDeath(event);
        if (entityName.equals("DOLPHIN")) Dolphin.onDeath(event);
        if (entityName.equals("DONKEY")) Donkey.onDeath(event);
        if (entityName.equals("DROWNED")) Drowned.onDeath(event);
        if (entityName.equals("ELDER_GUARDIAN")) ElderGuardian.onDeath(event);
        if (entityName.equals("ENDER_DRAGON")) EnderDragon.onDeath(event);
        if (entityName.equals("ENDERMAN")) Enderman.onDeath(event);
        if (entityName.equals("ENDERMITE")) Endermite.onDeath(event);
        if (entityName.equals("EVOKER")) Evoker.onDeath(event);
        if (entityName.equals("FOX")) Fox.onDeath(event);
        if (entityName.equals("GHAST")) Ghast.onDeath(event);
        if (entityName.equals("GLOW_SQUID")) GlowSquid.onDeath(event);
        if (entityName.equals("GOAT")) Goat.onDeath(event);
        if (entityName.equals("GUARDIAN")) Guardian.onDeath(event);
        if (entityName.equals("HOGLIN")) Hoglin.onDeath(event);
        if (entityName.equals("HORSE")) Horse.onDeath(event);
        if (entityName.equals("HUSK")) Husk.onDeath(event);
        if (entityName.equals("ILLUSIONER")) Illusioner.onDeath(event);
        if (entityName.equals("IRON_GOLEM")) IronGolem.onDeath(event);
        if (entityName.equals("LLAMA")) Llama.onDeath(event);
        if (entityName.equals("MAGMA_CUBE")) MagmaCube.onDeath(event);
        if (entityName.equals("MUSHROOM_COW")) Mooshroom.onDeath(event);
        if (entityName.equals("MULE")) Mule.onDeath(event);
        if (entityName.equals("OCELOT")) Ocelot.onDeath(event);
        if (entityName.equals("PANDA")) Panda.onDeath(event);
        if (entityName.equals("PARROT")) Parrot.onDeath(event);
        if (entityName.equals("PHANTOM")) Phantom.onDeath(event);
        if (entityName.equals("PIG")) Pig.onDeath(event);
        if (entityName.equals("PIGLIN")) Piglin.onDeath(event);
        if (entityName.equals("PIGLIN_BRUTE")) PiglinBrute.onDeath(event);
        if (entityName.equals("PILLAGER")) Pillager.onDeath(event);
        if (entityName.equals("POLAR_BEAR")) PolarBear.onDeath(event);
        if (entityName.equals("PUFFERFISH")) Pufferfish.onDeath(event);
        if (entityName.equals("RABBIT")) Rabbit.onDeath(event);
        if (entityName.equals("RAVAGER")) Ravager.onDeath(event);
        if (entityName.equals("SALMON")) Salmon.onDeath(event);
        if (entityName.equals("SHULKER")) Shulker.onDeath(event);
        if (entityName.equals("SILVERFISH")) Silverfish.onDeath(event);
        if (entityName.equals("SKELETON_HORSE")) SkeletonHorse.onDeath(event);
        if (entityName.equals("SLIME")) Slime.onDeath(event);
        if (entityName.equals("SNOWMAN")) SnowGolem.onDeath(event);
        if (entityName.equals("SPIDER")) Spider.onDeath(event);
        if (entityName.equals("SQUID")) Squid.onDeath(event);
        if (entityName.equals("STRAY")) Stray.onDeath(event);
        if (entityName.equals("STRIDER")) Strider.onDeath(event);
        if (entityName.equals("TRADER_LLAMA")) TraderLlama.onDeath(event);
        if (entityName.equals("TROPICAL_FISH")) TropicalFish.onDeath(event);
        if (entityName.equals("TURTLE")) Turtle.onDeath(event);
        if (entityName.equals("VEX")) Vex.onDeath(event);
        if (entityName.equals("VILLAGER")) Villager.onDeath(event);
        if (entityName.equals("VINDICATOR")) Vindicator.onDeath(event);
        if (entityName.equals("WANDERING_TRADER")) WanderingTrader.onDeath(event);
        if (entityName.equals("WITCH")) Witch.onDeath(event);
        if (entityName.equals("WITHER")) Wither.onDeath(event);
        if (entityName.equals("WOLF")) Wolf.onDeath(event);
        if (entityName.equals("ZOGLIN")) Zoglin.onDeath(event);
        if (entityName.equals("ZOMBIE_HORSE")) ZombieHorse.onDeath(event);
        if (entityName.equals("ZOMBIE_VILLAGER")) ZombieVillager.onDeath(event);
        if (entityName.equals("ZOMBIFIED_PIGLIN")) ZombifiedPiglin.onDeath(event);

        // Base Heads
        if (main.getConfig().getBoolean("packs.more-mob-heads.use-base-heads")) {
            if (entity.getType().toString().equals("SKELETON")) Skeleton.onDeath(event);
            if (entity.getType().toString().equals("ZOMBIE")) Zombie.onDeath(event);
            if (entity.getType().toString().equals("CREEPER")) Creeper.onDeath(event);
        }
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
