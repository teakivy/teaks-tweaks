package me.teakivy.teakstweaks.packs.moremobheads;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.BasicMobHead;
import me.teakivy.teakstweaks.packs.moremobheads.abstractions.MaterialMobHead;
import me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced.*;
import me.teakivy.teakstweaks.packs.moremobheads.mobs.special.ChargedCreeperHead;
import me.teakivy.teakstweaks.packs.moremobheads.mobs.special.CreakingHead;
import me.teakivy.teakstweaks.packs.moremobheads.mobs.special.WitherHead;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;

public class HeadRegister {

    public static void registerAll() {
        if (Config.getBoolean("packs.more-mob-heads.use-base-heads") || Config.isDevMode()) registerMaterialHeads();
        registerBasicHeads();
        registerAdvancedHeads();
        registerSpecialHeads();
    }

    private static void registerBasicHeads() {
        new BasicMobHead("allay", EntityType.ALLAY, Sound.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM);
        new BasicMobHead("armadillo", EntityType.ARMADILLO, Sound.ENTITY_ARMADILLO_AMBIENT);
        new BasicMobHead("bat", EntityType.BAT, Sound.ENTITY_BAT_AMBIENT);
        new BasicMobHead("blaze", EntityType.BLAZE, Sound.ENTITY_BLAZE_SHOOT);
        new BasicMobHead("bogged", EntityType.BOGGED, Sound.ENTITY_BOGGED_AMBIENT);
        new BasicMobHead("breeze", EntityType.BREEZE, Sound.ENTITY_BREEZE_CHARGE);
        new BasicMobHead("camel", EntityType.CAMEL, Sound.ENTITY_CAMEL_AMBIENT);
        new BasicMobHead("cave_spider", EntityType.CAVE_SPIDER, Sound.ENTITY_SPIDER_AMBIENT);
        new BasicMobHead("cod", EntityType.COD, Sound.ENTITY_COD_FLOP);
        new BasicMobHead("dolphin", EntityType.DOLPHIN, Sound.ENTITY_DOLPHIN_PLAY);
        new BasicMobHead("donkey", EntityType.DONKEY, Sound.ENTITY_DONKEY_AMBIENT);
        new BasicMobHead("drowned", EntityType.DROWNED, Sound.ENTITY_DROWNED_AMBIENT);
        new BasicMobHead("elder_guardian", EntityType.ELDER_GUARDIAN, Sound.ENTITY_ELDER_GUARDIAN_CURSE);
        new BasicMobHead("enderman", EntityType.ENDERMAN, Sound.ENTITY_ENDERMAN_SCREAM);
        new BasicMobHead("endermite", EntityType.ENDERMITE, Sound.ENTITY_ENDERMITE_AMBIENT);
        new BasicMobHead("evoker", EntityType.EVOKER, Sound.ENTITY_EVOKER_PREPARE_WOLOLO);
        new BasicMobHead("ghast", EntityType.GHAST, Sound.ENTITY_GHAST_SCREAM);
        new BasicMobHead("glow_squid", EntityType.GLOW_SQUID, Sound.ENTITY_GLOW_SQUID_AMBIENT);
        new BasicMobHead("guardian", EntityType.GUARDIAN, Sound.ENTITY_GUARDIAN_ATTACK);
        new BasicMobHead("happy_ghast", EntityType.HAPPY_GHAST, Sound.ENTITY_HAPPY_GHAST_AMBIENT);
        new BasicMobHead("hoglin", EntityType.HOGLIN, Sound.ENTITY_HOGLIN_ATTACK);
        new BasicMobHead("husk", EntityType.HUSK, Sound.ENTITY_HUSK_AMBIENT);
        new BasicMobHead("illusioner", EntityType.ILLUSIONER, Sound.ENTITY_ILLUSIONER_CAST_SPELL);
        new BasicMobHead("iron_golem", EntityType.IRON_GOLEM, Sound.ENTITY_IRON_GOLEM_HURT);
        new BasicMobHead("magma_cube", EntityType.MAGMA_CUBE, Sound.ENTITY_MAGMA_CUBE_SQUISH);
        new BasicMobHead("mule", EntityType.MULE, Sound.ENTITY_MULE_AMBIENT);
        new BasicMobHead("ocelot", EntityType.OCELOT, Sound.ENTITY_OCELOT_AMBIENT);
        new BasicMobHead("phantom", EntityType.PHANTOM, Sound.ENTITY_PHANTOM_BITE);
        new BasicMobHead("piglin_brute", EntityType.PIGLIN_BRUTE, Sound.ENTITY_PIGLIN_BRUTE_AMBIENT);
        new BasicMobHead("pillager", EntityType.PILLAGER, Sound.ENTITY_PILLAGER_CELEBRATE);
        new BasicMobHead("polar_bear", EntityType.POLAR_BEAR, Sound.ENTITY_POLAR_BEAR_AMBIENT);
        new BasicMobHead("pufferfish", EntityType.PUFFERFISH, Sound.ENTITY_PUFFER_FISH_BLOW_UP);
        new BasicMobHead("ravager", EntityType.RAVAGER, Sound.ENTITY_RAVAGER_ROAR);
        new BasicMobHead("salmon", EntityType.SALMON, Sound.ENTITY_SALMON_AMBIENT);
        new BasicMobHead("shulker", EntityType.SHULKER, Sound.ENTITY_SHULKER_SHOOT);
        new BasicMobHead("silverfish", EntityType.SILVERFISH, Sound.ENTITY_SILVERFISH_AMBIENT);
        new BasicMobHead("skeleton_horse", EntityType.SKELETON_HORSE, Sound.ENTITY_LIGHTNING_BOLT_IMPACT);
        new BasicMobHead("slime", EntityType.SLIME, Sound.ENTITY_SLIME_JUMP);
        new BasicMobHead("sniffer", EntityType.SNIFFER, Sound.ENTITY_SNIFFER_HAPPY);
        new BasicMobHead("snow_golem", EntityType.SNOW_GOLEM, Sound.ENTITY_SNOW_GOLEM_AMBIENT);
        new BasicMobHead("spider", EntityType.SPIDER, Sound.ENTITY_SPIDER_AMBIENT);
        new BasicMobHead("squid", EntityType.SQUID, Sound.ENTITY_SQUID_SQUIRT);
        new BasicMobHead("stray", EntityType.STRAY, Sound.ENTITY_STRAY_AMBIENT);
        new BasicMobHead("tadpole", EntityType.TADPOLE, Sound.ENTITY_TADPOLE_FLOP);
        new BasicMobHead("tropical_fish", EntityType.TROPICAL_FISH, Sound.ENTITY_TROPICAL_FISH_AMBIENT);
        new BasicMobHead("turtle", EntityType.TURTLE, Sound.ENTITY_TURTLE_LAY_EGG);
        new BasicMobHead("vex", EntityType.VEX, Sound.ENTITY_VEX_CHARGE);
        new BasicMobHead("vindicator", EntityType.VINDICATOR, Sound.ENTITY_VINDICATOR_AMBIENT);
        new BasicMobHead("wandering_trader", EntityType.WANDERING_TRADER, Sound.ENTITY_WANDERING_TRADER_AMBIENT);
        new BasicMobHead("warden", EntityType.WARDEN, Sound.ENTITY_WARDEN_EMERGE);
        new BasicMobHead("witch", EntityType.WITCH, Sound.ENTITY_WITCH_AMBIENT);
        new BasicMobHead("zoglin", EntityType.ZOGLIN, Sound.ENTITY_ZOGLIN_AMBIENT);
        new BasicMobHead("zombie_horse", EntityType.ZOMBIE_HORSE, Sound.ENTITY_ZOMBIE_HORSE_AMBIENT);
        new BasicMobHead("zombified_piglin", EntityType.ZOMBIFIED_PIGLIN, Sound.ENTITY_ZOMBIFIED_PIGLIN_ANGRY);
    }

    private static void registerAdvancedHeads() {
        new AxolotlHead();
        new BeeHead();
        new CatHead();
        new ChickenHead();
        new CowHead();
        new FoxHead();
        new FrogHead();
        new GoatHead();
        new HorseHead();
        new LlamaHead();
        new MooshroomHead();
        new PandaHead();
        new ParrotHead();
        new PigHead();
        new RabbitHead();
        new SheepHead();
        new StriderHead();
        new TraderLlamaHead();
        new VillagerHead();
        new WolfHead();
        new ZombieVillagerHead();
    }

    private static void registerMaterialHeads() {
        new MaterialMobHead("zombie", EntityType.ZOMBIE, Material.ZOMBIE_HEAD);
        new MaterialMobHead("skeleton", EntityType.SKELETON, Material.SKELETON_SKULL);
        new MaterialMobHead("creeper", EntityType.CREEPER, Material.CREEPER_HEAD);
        new MaterialMobHead("dragon", EntityType.ENDER_DRAGON, Material.DRAGON_HEAD);
        new MaterialMobHead("piglin", EntityType.PIGLIN, Material.PIGLIN_HEAD);
    }

    private static void registerSpecialHeads() {
        new ChargedCreeperHead();
        new CreakingHead();
        new WitherHead();

    }
}
