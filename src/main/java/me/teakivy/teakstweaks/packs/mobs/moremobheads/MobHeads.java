package me.teakivy.teakstweaks.packs.mobs.moremobheads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

public class MobHeads extends BasePack {

    public MobHeads() {
        super("More Mob Heads", "more-mob-heads", PackType.MOBS, Material.ZOMBIE_HEAD);
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;

        Entity entity = event.getEntity();

        String entityName = entity.getType().toString();


        switch (entityName) {
            case "ALLAY" -> Allay.onDeath(event);
            case "AXOLOTL" -> Axolotl.onDeath(event);
            case "BAT" -> Bat.onDeath(event);
            case "BEE" -> Bee.onDeath(event);
            case "BLAZE" -> Blaze.onDeath(event);
            case "CAT" -> Cat.onDeath(event);
            case "CAVE_SPIDER" -> CaveSpider.onDeath(event);
            case "CHICKEN" -> Chicken.onDeath(event);
            case "COD" -> Cod.onDeath(event);
            case "COW" -> Cow.onDeath(event);
            case "CREEPER" -> ChargedCreeper.onDeath(event);
            case "DOLPHIN" -> Dolphin.onDeath(event);
            case "DONKEY" -> Donkey.onDeath(event);
            case "DROWNED" -> Drowned.onDeath(event);
            case "ELDER_GUARDIAN" -> ElderGuardian.onDeath(event);
            case "ENDER_DRAGON" -> EnderDragon.onDeath(event);
            case "ENDERMAN" -> Enderman.onDeath(event);
            case "ENDERMITE" -> Endermite.onDeath(event);
            case "EVOKER" -> Evoker.onDeath(event);
            case "FOX" -> Fox.onDeath(event);
            case "FROG" -> Frog.onDeath(event);
            case "GHAST" -> Ghast.onDeath(event);
            case "GLOW_SQUID" -> GlowSquid.onDeath(event);
            case "GOAT" -> Goat.onDeath(event);
            case "GUARDIAN" -> Guardian.onDeath(event);
            case "HOGLIN" -> Hoglin.onDeath(event);
            case "HORSE" -> Horse.onDeath(event);
            case "HUSK" -> Husk.onDeath(event);
            case "ILLUSIONER" -> Illusioner.onDeath(event);
            case "IRON_GOLEM" -> IronGolem.onDeath(event);
            case "LLAMA" -> Llama.onDeath(event);
            case "MAGMA_CUBE" -> MagmaCube.onDeath(event);
            case "MUSHROOM_COW" -> Mooshroom.onDeath(event);
            case "MULE" -> Mule.onDeath(event);
            case "OCELOT" -> Ocelot.onDeath(event);
            case "PANDA" -> Panda.onDeath(event);
            case "PARROT" -> Parrot.onDeath(event);
            case "PHANTOM" -> Phantom.onDeath(event);
            case "PIG" -> Pig.onDeath(event);
            case "PIGLIN" -> Piglin.onDeath(event);
            case "PIGLIN_BRUTE" -> PiglinBrute.onDeath(event);
            case "PILLAGER" -> Pillager.onDeath(event);
            case "POLAR_BEAR" -> PolarBear.onDeath(event);
            case "PUFFERFISH" -> Pufferfish.onDeath(event);
            case "RABBIT" -> Rabbit.onDeath(event);
            case "RAVAGER" -> Ravager.onDeath(event);
            case "SALMON" -> Salmon.onDeath(event);
            case "SHEEP" -> Sheep.onDeath(event);
            case "SHULKER" -> Shulker.onDeath(event);
            case "SILVERFISH" -> Silverfish.onDeath(event);
            case "SKELETON_HORSE" -> SkeletonHorse.onDeath(event);
            case "SLIME" -> Slime.onDeath(event);
            case "SNOWMAN" -> SnowGolem.onDeath(event);
            case "SPIDER" -> Spider.onDeath(event);
            case "SQUID" -> Squid.onDeath(event);
            case "STRAY" -> Stray.onDeath(event);
            case "STRIDER" -> Strider.onDeath(event);
            case "TADPOLE" -> Tadpole.onDeath(event);
            case "TRADER_LLAMA" -> TraderLlama.onDeath(event);
            case "TROPICAL_FISH" -> TropicalFish.onDeath(event);
            case "TURTLE" -> Turtle.onDeath(event);
            case "VEX" -> Vex.onDeath(event);
            case "VILLAGER" -> Villager.onDeath(event);
            case "VINDICATOR" -> Vindicator.onDeath(event);
            case "WANDERING_TRADER" -> WanderingTrader.onDeath(event);
            case "WARDEN" -> Warden.onDeath(event);
            case "WITCH" -> Witch.onDeath(event);
            case "WITHER" -> Wither.onDeath(event);
            case "WOLF" -> Wolf.onDeath(event);
            case "ZOGLIN" -> Zoglin.onDeath(event);
            case "ZOMBIE_HORSE" -> ZombieHorse.onDeath(event);
            case "ZOMBIE_VILLAGER" -> ZombieVillager.onDeath(event);
            case "ZOMBIFIED_PIGLIN" -> ZombifiedPiglin.onDeath(event);
        }

        // Base Heads
        if (getConfig().getBoolean("use-base-heads")) {
            switch (entityName) {
                case "SKELETON" -> Skeleton.onDeath(event);
                case "ZOMBIE" -> Zombie.onDeath(event);
                case "CREEPER" -> Creeper.onDeath(event);
            }
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
}
