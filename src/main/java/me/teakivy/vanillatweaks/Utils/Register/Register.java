package me.teakivy.vanillatweaks.Utils.Register;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.AntiCreeperGreif.AntiCreeper;
import me.teakivy.vanillatweaks.Packs.AntiEndermanGrief.AntiEnderman;
import me.teakivy.vanillatweaks.Packs.AntiGhastGrief.AntiGhast;
import me.teakivy.vanillatweaks.Packs.CauldronConcrete.ConcreteConverter;
import me.teakivy.vanillatweaks.Packs.CoordsHud.DisplayHud;
import me.teakivy.vanillatweaks.Packs.CountMobDeaths.CountDeaths;
import me.teakivy.vanillatweaks.Packs.DoubleShulkerShells.DoubleShulkers;
import me.teakivy.vanillatweaks.Packs.DragonDrops.DragonDrops;
import me.teakivy.vanillatweaks.Packs.DurabilityPing.DuraPing;
import me.teakivy.vanillatweaks.Packs.MoreMobHeads.MobHeads;
import me.teakivy.vanillatweaks.Packs.MultiplayerSleep.MultiplayerSleep;
import me.teakivy.vanillatweaks.Packs.PlayerHeadDrops.HeadDrop;
import me.teakivy.vanillatweaks.Packs.SilenceMobs.Silencer;
import me.teakivy.vanillatweaks.Packs.SpectatorConduitPower.ConduitPower;
import me.teakivy.vanillatweaks.Packs.SpectatorNightVision.NightVision;
import me.teakivy.vanillatweaks.Packs.Tag.Tag;
import me.teakivy.vanillatweaks.Packs.UnlockAllRecipes.UnlockRecipes;
import me.teakivy.vanillatweaks.Packs.VillagerDeathMessages.VillagerDeath;
import me.teakivy.vanillatweaks.Packs.WanderingTrades.Trades;
import me.teakivy.vanillatweaks.Packs.XPManagement.XPManagement;

public class Register {

    static Main main = Main.getPlugin(Main.class);

    public static AntiCreeper antiCreeper = new AntiCreeper();
    public static AntiEnderman antiEnderman = new AntiEnderman();
    public static AntiGhast antiGhast = new AntiGhast();
    public static ConcreteConverter concreteConverter = new ConcreteConverter();
    public static DisplayHud displayHud = new DisplayHud();
    public static CountDeaths countDeaths = new CountDeaths();
    public static DoubleShulkers doubleShulkers = new DoubleShulkers();
    public static DragonDrops dragonDrops = new DragonDrops();
    public static DuraPing duraPing = new DuraPing();
    public static MobHeads mobHeads = new MobHeads();
    public static MultiplayerSleep multiplayerSleep = new MultiplayerSleep();
    public static HeadDrop headDrop = new HeadDrop();
    public static Silencer silencer = new Silencer();
    public static ConduitPower conduitPower = new ConduitPower();
    public static NightVision nightVision = new NightVision();
    public static Tag tag = new Tag();
    public static UnlockRecipes unlockRecipes = new UnlockRecipes();
    public static VillagerDeath villagerDeath = new VillagerDeath();
    public static Trades trades = new Trades();
    public static XPManagement xpManagement = new XPManagement();

    public static void registerAll() {
        for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false)) {
            if (main.getConfig().getBoolean("packs." + pack + ".enabled")) {
                registerPack(pack);
            }
        }
    }

    public static void unregisterAll() {
        for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false)) {
            if (!main.getConfig().getBoolean("packs." + pack + ".enabled")) {
                unregisterPack(pack);
            }
        }
    }

    public static void unregisterPack(String pack) {

        if (pack.equalsIgnoreCase("anti-creeper-grief")) {
            antiCreeper.unregister();
        }

        if (pack.equalsIgnoreCase("anti-enderman-grief")) {
            antiEnderman.unregister();
        }

        if (pack.equalsIgnoreCase("anti-ghast-grief")) {
            antiGhast.unregister();
        }

        if (pack.equalsIgnoreCase("cauldron-concrete")) {
            concreteConverter.unregister();
        }

        if (pack.equalsIgnoreCase("coordinates-hud")) {
            displayHud.unregister();
        }

        if (pack.equalsIgnoreCase("count-mob-deaths")) {
            countDeaths.unregister();
        }

        if (pack.equalsIgnoreCase("double-shulker-shells")) {
            doubleShulkers.unregister();
        }

        if (pack.equalsIgnoreCase("dragon-drops")) {
            dragonDrops.unregister();
        }

        if (pack.equalsIgnoreCase("durability-ping")) {
            duraPing.unregister();
        }

        if (pack.equalsIgnoreCase("more-mob-heads")) {
            mobHeads.unregister();
        }

        if (pack.equalsIgnoreCase("multiplayer-sleep")) {
            multiplayerSleep.unregister();
        }

        if (pack.equalsIgnoreCase("player-head-drops")) {
            headDrop.unregister();
        }

        if (pack.equalsIgnoreCase("silence-mobs")) {
            silencer.unregister();
        }

        if (pack.equalsIgnoreCase("spectator-conduit-power")) {
            conduitPower.unregister();
        }

        if (pack.equalsIgnoreCase("spectator-night-vision")) {
            nightVision.unregister();
        }

        if (pack.equalsIgnoreCase("tag")) {
            tag.unregister();
        }

        if (pack.equalsIgnoreCase("unlock-all-recipes")) {
            unlockRecipes.unregister();
        }

        if (pack.equalsIgnoreCase("villager-death-messages")) {
            villagerDeath.unregister();
        }

        if (pack.equalsIgnoreCase("wandering-trades")) {
            trades.unregister();
        }

        if (pack.equalsIgnoreCase("xp-management")) {
            xpManagement.unregister();
        }

    }

    public static void registerPack(String pack) {
        if (pack.equalsIgnoreCase("anti-creeper-grief")) {
            main.getServer().getPluginManager().registerEvents(antiCreeper, main);
        }

        if (pack.equalsIgnoreCase("anti-enderman-grief")) {
            main.getServer().getPluginManager().registerEvents(antiEnderman, main);
        }

        if (pack.equalsIgnoreCase("anti-ghast-grief")) {
            main.getServer().getPluginManager().registerEvents(antiGhast, main);
        }

        if (pack.equalsIgnoreCase("cauldron-concrete")) {
            main.getServer().getPluginManager().registerEvents(concreteConverter, main);
        }

        if (pack.equalsIgnoreCase("coordinates-hud")) {
            main.getServer().getPluginManager().registerEvents(displayHud, main);
        }

        if (pack.equalsIgnoreCase("count-mob-deaths")) {
            main.getServer().getPluginManager().registerEvents(countDeaths, main);
        }

        if (pack.equalsIgnoreCase("double-shulker-shells")) {
            main.getServer().getPluginManager().registerEvents(doubleShulkers, main);
        }

        if (pack.equalsIgnoreCase("dragon-drops")) {
            main.getServer().getPluginManager().registerEvents(dragonDrops, main);
        }

        if (pack.equalsIgnoreCase("durability-ping")) {
            main.getServer().getPluginManager().registerEvents(duraPing, main);
        }

        if (pack.equalsIgnoreCase("more-mob-heads")) {
            main.getServer().getPluginManager().registerEvents(mobHeads, main);
        }

        if (pack.equalsIgnoreCase("multiplayer-sleep")) {
            main.getServer().getPluginManager().registerEvents(multiplayerSleep, main);
        }

        if (pack.equalsIgnoreCase("player-head-drops")) {
            main.getServer().getPluginManager().registerEvents(headDrop, main);
        }

        if (pack.equalsIgnoreCase("silence-mobs")) {
            main.getServer().getPluginManager().registerEvents(silencer, main);
        }

        if (pack.equalsIgnoreCase("spectator-conduit-power")) {
            main.getServer().getPluginManager().registerEvents(conduitPower, main);
        }

        if (pack.equalsIgnoreCase("spectator-night-vision")) {
            main.getServer().getPluginManager().registerEvents(nightVision, main);
        }

        if (pack.equalsIgnoreCase("tag")) {
            main.getServer().getPluginManager().registerEvents(tag, main);
        }

        if (pack.equalsIgnoreCase("unlock-all-recipes")) {
            main.getServer().getPluginManager().registerEvents(unlockRecipes, main);
        }

        if (pack.equalsIgnoreCase("villager-death-messages")) {
            main.getServer().getPluginManager().registerEvents(villagerDeath, main);
        }

        if (pack.equalsIgnoreCase("wandering-trades")) {
            main.getServer().getPluginManager().registerEvents(trades, main);
        }

        if (pack.equalsIgnoreCase("xp-management")) {
            main.getServer().getPluginManager().registerEvents(xpManagement, main);
        }
    }

}
