package me.teakivy.teakstweaks.packs.mobs.moremobheads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

public class MobHeads extends BasePack {

    public MobHeads() {
        super("More Mob Heads", "more-mob-heads", PackType.MOBS, Material.ZOMBIE_HEAD, "Adds a chance to receive a mob head upon killing it.");

        new AllayHead();
        new AxolotlHead();
        new BatHead();
        new BeeHead();
        new BlazeHead();
        new CatHead();
        new CaveSpiderHead();
        new ChargedCreeperHead();
        new ChickenHead();
        new CodHead();
        new CowHead();
        new CreeperHead();
        new DolphinHead();
        new DonkeyHead();
        new DrownedHead();
        new ElderGuardianHead();
        new EnderDragonHead();
        new EndermanHead();
        new EndermiteHead();
        new EvokerHead();
        new FoxHead();
        new FrogHead();
        new GhastHead();
        new GlowSquidHead();
        new GoatHead();
        new GuardianHead();
        new HoglinHead();
        new HorseHead();
        new HuskHead();
        new IllusionerHead();
        new IronGolemHead();
        new LlamaHead();
        new MagmaCubeHead();
        new MooshroomHead();
        new MuleHead();
        new OcelotHead();
        new PandaHead();
        new ParrotHead();
        new PhantomHead();
        new PigHead();
        new PiglinBruteHead();
        new PiglinHead();
        new PillagerHead();
        new PolarBearHead();
        new PufferfishHead();
        new RabbitHead();
        new RavagerHead();
        new SalmonHead();
        new SheepHead();
        new ShulkerHead();
        new SilverfishHead();
        new SkeletonHead();
        new SkeletonHorseHead();
        new SlimeHead();
        new SnowGolemHead();
        new SpiderHead();
        new SquidHead();
        new StrayHead();
        new StriderHead();
        new TadpoleHead();
        new TraderLlamaHead();
        new TropicalFishHead();
        new TurtleHead();
        new VexHead();
        new VillagerHead();
        new VindicatorHead();
        new WanderingTraderHead();
        new WardenHead();
        new WitchHead();
        new WitherHead();
        new WolfHead();
        new ZoglinHead();
        new ZombieHead();
        new ZombieHorseHead();
        new ZombieVillagerHead();
        new ZombifiedPiglinHead();
    }

//    @EventHandler
//    public void onMobDeath(EntityDeathEvent event) {
//        if (event.getEntity().getKiller() == null) return;
//
//        Entity entity = event.getEntity();
//
//        String entityName = entity.getType().toString();
//
//
//        switch (entityName) {
//            case "BEE" -> BeeHead.onDeath(event);
//            case "CAT" -> CatHead.onDeath(event);
//            case "COD" -> CodHead.onDeath(event);
//            case "COW" -> CowHead.onDeath(event);
//            case "CREEPER" -> ChargedCreeperHead.onDeath(event);
//            case "DOLPHIN" -> DolphinHead.onDeath(event);
//            case "DONKEY" -> DonkeyHead.onDeath(event);
//            case "DROWNED" -> DrownedHead.onDeath(event);
//            case "ELDER_GUARDIAN" -> ElderGuardianHead.onDeath(event);
//            case "ENDER_DRAGON" -> EnderDragonHead.onDeath(event);
//            case "ENDERMAN" -> EndermanHead.onDeath(event);
//            case "ENDERMITE" -> EndermiteHead.onDeath(event);
//            case "EVOKER" -> EvokerHead.onDeath(event);
//            case "FOX" -> FoxHead.onDeath(event);
//            case "FROG" -> FrogHead.onDeath(event);
//            case "GHAST" -> GhastHead.onDeath(event);
//            case "GLOW_SQUID" -> GlowSquidHead.onDeath(event);
//            case "GOAT" -> GoatHead.onDeath(event);
//            case "GUARDIAN" -> GuardianHead.onDeath(event);
//            case "HOGLIN" -> HoglinHead.onDeath(event);
//            case "HORSE" -> HorseHead.onDeath(event);
//            case "HUSK" -> HuskHead.onDeath(event);
//            case "ILLUSIONER" -> IllusionerHead.onDeath(event);
//            case "IRON_GOLEM" -> IronGolemHead.onDeath(event);
//            case "LLAMA" -> LlamaHead.onDeath(event);
//            case "MAGMA_CUBE" -> MagmaCubeHead.onDeath(event);
//            case "MUSHROOM_COW" -> MooshroomHead.onDeath(event);
//            case "MULE" -> MuleHead.onDeath(event);
//            case "OCELOT" -> OcelotHead.onDeath(event);
//            case "PANDA" -> PandaHead.onDeath(event);
//            case "PARROT" -> ParrotHead.onDeath(event);
//            case "PHANTOM" -> PhantomHead.onDeath(event);
//            case "PIG" -> PigHead.onDeath(event);
//            case "PIGLIN" -> PiglinHead.onDeath(event);
//            case "PIGLIN_BRUTE" -> PiglinBruteHead.onDeath(event);
//            case "PILLAGER" -> PillagerHead.onDeath(event);
//            case "POLAR_BEAR" -> PolarBearHead.onDeath(event);
//            case "PUFFERFISH" -> PufferfishHead.onDeath(event);
//            case "RABBIT" -> RabbitHead.onDeath(event);
//            case "RAVAGER" -> RavagerHead.onDeath(event);
//            case "SALMON" -> SalmonHead.onDeath(event);
//            case "SHEEP" -> SheepHead.onDeath(event);
//            case "SHULKER" -> ShulkerHead.onDeath(event);
//            case "SILVERFISH" -> SilverfishHead.onDeath(event);
//            case "SKELETON_HORSE" -> SkeletonHorseHead.onDeath(event);
//            case "SLIME" -> SlimeHead.onDeath(event);
//            case "SNOWMAN" -> SnowGolemHead.onDeath(event);
//            case "SPIDER" -> SpiderHead.onDeath(event);
//            case "SQUID" -> SquidHead.onDeath(event);
//            case "STRAY" -> StrayHead.onDeath(event);
//            case "STRIDER" -> StriderHead.onDeath(event);
//            case "TADPOLE" -> TadpoleHead.onDeath(event);
//            case "TRADER_LLAMA" -> TraderLlamaHead.onDeath(event);
//            case "TROPICAL_FISH" -> TropicalFishHead.onDeath(event);
//            case "TURTLE" -> TurtleHead.onDeath(event);
//            case "VEX" -> VexHead.onDeath(event);
//            case "VILLAGER" -> VillagerHead.onDeath(event);
//            case "VINDICATOR" -> VindicatorHead.onDeath(event);
//            case "WANDERING_TRADER" -> WanderingTraderHead.onDeath(event);
//            case "WARDEN" -> WardenHead.onDeath(event);
//            case "WITCH" -> WitchHead.onDeath(event);
//            case "WITHER" -> WitherHead.onDeath(event);
//            case "WOLF" -> WolfHead.onDeath(event);
//            case "ZOGLIN" -> ZoglinHead.onDeath(event);
//            case "ZOMBIE_HORSE" -> ZombieHorseHead.onDeath(event);
//            case "ZOMBIE_VILLAGER" -> ZombieVillagerHead.onDeath(event);
//            case "ZOMBIFIED_PIGLIN" -> ZombifiedPiglinHead.onDeath(event);
//        }
//
//        // Base Heads
//        if (getConfig().getBoolean("use-base-heads")) {
//            switch (entityName) {
//                case "SKELETON" -> SkeletonHead.onDeath(event);
//                case "ZOMBIE" -> ZombieHead.onDeath(event);
//                case "CREEPER" -> CreeperHead.onDeath(event);
//            }
//        }
//    }

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
