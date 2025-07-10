package me.teakivy.teakstweaks.packs.moremobheads;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.moremobheads.mobs.*;
import me.teakivy.teakstweaks.utils.JsonManager;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MobHeads extends BasePack {
    private static HashMap<String, Object> chances = new HashMap<>();

    public MobHeads() {
        super("more-mob-heads", Material.ZOMBIE_HEAD);
    }

    @Override
    public List<CustomItem> registerItems() {
        // Registered in BaseMobHead # addHeadTexture
        return null;
    }

    @Override
    public void init() {
        super.init();
        new AllayHead();
        new ArmadilloHead();
        new AxolotlHead();
        new BatHead();
        new BeeHead();
        new BlazeHead();
        new BoggedHead();
        new BreezeHead();
        new CamelHead();
        new CatHead();
        new CaveSpiderHead();
        new ChargedCreeperHead();
        new ChickenHead();
        new CodHead();
        new CowHead();
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
        new HappyGhastHead();
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
        new PillagerHead();
        new PolarBearHead();
        new PufferfishHead();
        new RabbitHead();
        new RavagerHead();
        new SalmonHead();
        new SheepHead();
        new ShulkerHead();
        new SilverfishHead();
        new SkeletonHorseHead();
        new SlimeHead();
        new SnifferHead();
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
        new ZombieHorseHead();
        new ZombieVillagerHead();
        new ZombifiedPiglinHead();

        if (getConfig().getBoolean("use-base-heads")) {
            new CreeperHead();
            new PiglinHead();
            new SkeletonHead();
            new ZombieHead();

            MMHDatapackCreator.addNormalAdvancement("creeper_head", "Creeper Head", Material.CREEPER_HEAD);
            MMHDatapackCreator.addNormalAdvancement("piglin_head", "Piglin Head", Material.PIGLIN_HEAD);
            MMHDatapackCreator.addNormalAdvancement("skeleton_skull", "Skeleton Skull", Material.SKELETON_SKULL);
            MMHDatapackCreator.addNormalAdvancement("zombie_head", "Zombie Head", Material.ZOMBIE_HEAD);
        }

        CreakingHead.init();
        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(new CreakingHead(), TeaksTweaks.getInstance());

        loadJson();

        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), MMHDatapackCreator::createDataPack, 20);
    }

    public static boolean shouldDrop(Player player, String key) {
        if (Config.isDevMode()) return true;
        if (!chances.containsKey(key + ".chance") || !chances.containsKey(key + ".looting_bonus")) return false;
        double chance = (double) chances.get(key + ".chance");
        double lootingBonus = (double) chances.get(key + ".looting_bonus");
        Random rand = new Random();
        if (player == null) return false;
        if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
            for (int i = 0; i < player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOTING); i++) {
                chance = chance + lootingBonus;
            }
        } else {
            if (player.getInventory().getItemInOffHand().getItemMeta() != null) {
                for (int i = 0; i < player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(Enchantment.LOOTING); i++) {
                    chance = chance + lootingBonus;
                }
            }
        }
        if (chance > 1) chance = 1;
        double num = rand.nextDouble();
        return num < chance;
    }

    public static void loadJson() {
        JsonManager.saveToFile(
                JsonManager.updateJson(
                        JsonManager.getFromFile("data/mob_heads.json"),
                        JsonManager.getFromResource("data/mob_heads.json"),
                        true),
                "data/mob_heads.json");

        File file = new File(TeaksTweaks.getInstance().getDataFolder() + "/data/mob_heads.json");
        if (!file.exists()) TeaksTweaks.getInstance().saveResource("data/mob_heads.json", false);

        file = new File(TeaksTweaks.getInstance().getDataFolder() + "/data/mob_heads.json");

        if (!file.exists()) chances = new HashMap<>();

        try {
            chances =  TeaksTweaks.getGson().fromJson(new FileReader(file), HashMap.class);
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        chances = new HashMap<>();
    }
}
