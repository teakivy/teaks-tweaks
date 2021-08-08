package me.teakivy.vanillatweaks.Utils.Register;

import me.teakivy.vanillatweaks.Commands.*;
import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Experimental.ChunkLoaders.Loader;
import me.teakivy.vanillatweaks.Packs.Experimental.ConfettiCreepers.ConfettiCreeper;
import me.teakivy.vanillatweaks.Packs.Experimental.Elevators.Elevator;
import me.teakivy.vanillatweaks.Packs.Experimental.XPManagement.XPManagement;
import me.teakivy.vanillatweaks.Packs.Hermitcraft.Tag.Tag;
import me.teakivy.vanillatweaks.Packs.Hermitcraft.ThunderShrine.Shrine;
import me.teakivy.vanillatweaks.Packs.Hermitcraft.TreasureGems.Gems;
import me.teakivy.vanillatweaks.Packs.Hermitcraft.WanderingTrades.Trades;
import me.teakivy.vanillatweaks.Packs.Items.ArmoredElytra.ArmoredElytras;
import me.teakivy.vanillatweaks.Packs.Items.PlayerHeadDrops.HeadDrop;
import me.teakivy.vanillatweaks.Packs.Items.RotationWrench.Wrench;
import me.teakivy.vanillatweaks.Packs.Mobs.AntiCreeperGreif.AntiCreeper;
import me.teakivy.vanillatweaks.Packs.Mobs.AntiEndermanGrief.AntiEnderman;
import me.teakivy.vanillatweaks.Packs.Mobs.AntiGhastGrief.AntiGhast;
import me.teakivy.vanillatweaks.Packs.Mobs.CountMobDeaths.CountDeaths;
import me.teakivy.vanillatweaks.Packs.Mobs.DoubleShulkerShells.DoubleShulkers;
import me.teakivy.vanillatweaks.Packs.Mobs.DragonDrops.DragonDrops;
import me.teakivy.vanillatweaks.Packs.Mobs.LargerPhantoms.Phantoms;
import me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import me.teakivy.vanillatweaks.Packs.Mobs.SilenceMobs.Silencer;
import me.teakivy.vanillatweaks.Packs.Mobs.VillagerDeathMessages.VillagerDeath;
import me.teakivy.vanillatweaks.Packs.Survival.AFKDisplay.AFK;
import me.teakivy.vanillatweaks.Packs.Survival.CauldronConcrete.ConcreteConverter;
import me.teakivy.vanillatweaks.Packs.Survival.ClassicFishingLoot.Fishing;
import me.teakivy.vanillatweaks.Packs.Survival.CoordsHud.HUD;
import me.teakivy.vanillatweaks.Packs.Survival.CustomNetherPortals.NetherPortal;
import me.teakivy.vanillatweaks.Packs.Survival.DurabilityPing.DuraPing;
import me.teakivy.vanillatweaks.Packs.Survival.FastLeafDecay.Decay;
import me.teakivy.vanillatweaks.Packs.Survival.Graves.GraveEvents;
import me.teakivy.vanillatweaks.Packs.Survival.MultiplayerSleep.MultiplayerSleep;
import me.teakivy.vanillatweaks.Packs.Survival.PillagerTools.PillagerSpawning;
import me.teakivy.vanillatweaks.Packs.Survival.TrackRawStatistics.RawStats;
import me.teakivy.vanillatweaks.Packs.Survival.TrackStatistics.StatTracker;
import me.teakivy.vanillatweaks.Packs.Survival.UnlockAllRecipes.UnlockRecipes;
import me.teakivy.vanillatweaks.Packs.Survival.WorkstationHighlights.Highlighter;
import me.teakivy.vanillatweaks.Packs.Teleportation.Back.Back;
import me.teakivy.vanillatweaks.Packs.Utilities.CustomVillagerShops.CustomVillager;
import me.teakivy.vanillatweaks.Packs.Utilities.ItemAverages.ItemTracker;
import me.teakivy.vanillatweaks.Packs.Utilities.SpawningSpheres.Sphere;
import me.teakivy.vanillatweaks.Packs.Utilities.SpectatorConduitPower.ConduitPower;
import me.teakivy.vanillatweaks.Packs.Utilities.SpectatorNightVision.NightVision;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;

public class Register {

    static Main main = Main.getPlugin(Main.class);

    public static AntiCreeper antiCreeper = new AntiCreeper();
    public static AntiEnderman antiEnderman = new AntiEnderman();
    public static AntiGhast antiGhast = new AntiGhast();
    public static ConcreteConverter concreteConverter = new ConcreteConverter();
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
    public static ConfettiCreeper confettiCreeper = new ConfettiCreeper();
    public static Back back = new Back();
    public static AFK afk = new AFK();
    public static Shrine shrine = new Shrine();
    public static Phantoms phantoms = new Phantoms();
    public static Highlighter highlighter = new Highlighter();
    public static Loader loader = new Loader();
    public static Decay decay = new Decay();
    public static PillagerSpawning pillagerSpawning = new PillagerSpawning();
    public static Elevator elevator = new Elevator();
    public static Wrench wrench = new Wrench();
    public static Gems gems = new Gems();
    public static ArmoredElytras armoredElytras = new ArmoredElytras();
    public static Fishing classicFishing = new Fishing();
    public static NetherPortal portal = new NetherPortal();
    public static ItemTracker itemTracker = new ItemTracker();
    public static StatTracker statTracker = new StatTracker();
    public static RawStats rawStats = new RawStats();
    public static GraveEvents graves = new GraveEvents();
    public static HUD hud = new HUD();
    public static CustomVillager customVillager = new CustomVillager();
    public static Sphere sphere = new Sphere();

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

        if (pack.equalsIgnoreCase("coords-hud")) {
            HUD.stopHUD();
            hud.unregister();
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

        if (pack.equalsIgnoreCase("confetti-creepers")) {
            confettiCreeper.unregister();
        }

        if (pack.equalsIgnoreCase("back")) {
            back.unregister();
        }

        if (pack.equalsIgnoreCase("afk-display")) {
            afk.unregister();
        }

        if (pack.equalsIgnoreCase("thunder-shrine")) {
            shrine.unregister();
        }

        if (pack.equalsIgnoreCase("larger-phantoms")) {
            phantoms.unregister();
        }

        if (pack.equalsIgnoreCase("workstation-highlights")) {
            highlighter.unregister();
        }

        if (pack.equalsIgnoreCase("chunk-loaders")) {
            loader.unregister();
        }

        if (pack.equalsIgnoreCase("fast-leaf-decay")) {
            decay.unregister();
        }

        if (pack.equalsIgnoreCase("pillager-tools")) {
            pillagerSpawning.unregister();
        }

        if (pack.equalsIgnoreCase("elevators")) {
            elevator.unregister();
        }

        if (pack.equalsIgnoreCase("rotation-wrench")) {
            wrench.unregister();
        }

        if (pack.equalsIgnoreCase("treasure-gems")) {
            gems.unregister();
        }

        if (pack.equalsIgnoreCase("armored-elytra")) {
            armoredElytras.unregister();
        }

        if (pack.equalsIgnoreCase("classic-fishing-loot")) {
            classicFishing.unregister();
        }

        if (pack.equalsIgnoreCase("custom-nether-portals")) {
            portal.unregister();
        }

        if (pack.equalsIgnoreCase("item-averages")) {
            itemTracker.unregister();
        }

        if (pack.equalsIgnoreCase("track-statistics")) {
            statTracker.unregister();
        }

        if (pack.equalsIgnoreCase("graves")) {
            graves.unregister();
        }

        if (pack.equalsIgnoreCase("custom-villager-shops")) {
            customVillager.unregister();
        }

        if (pack.equalsIgnoreCase("spawning-spheres")) {
            sphere.unregister();
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

        if (pack.equalsIgnoreCase("coords-hud")) {
            HUD.startHUD();
            main.getServer().getPluginManager().registerEvents(hud, main);
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
            xpManagement.registerRecipe();
        }

        if (pack.equalsIgnoreCase("confetti-creepers")) {
            main.getServer().getPluginManager().registerEvents(confettiCreeper, main);
        }

        if (pack.equalsIgnoreCase("back")) {
            main.getServer().getPluginManager().registerEvents(back, main);
        }

        if (pack.equalsIgnoreCase("afk-display")) {
            main.getServer().getPluginManager().registerEvents(afk, main);
            AFK.register();
        }

        if (pack.equalsIgnoreCase("thunder-shrine")) {
            main.getServer().getPluginManager().registerEvents(shrine, main);
            shrine.register();
        }

        if (pack.equalsIgnoreCase("larger-phantoms")) {
            main.getServer().getPluginManager().registerEvents(phantoms, main);
        }

        if (pack.equalsIgnoreCase("workstation-highlights")) {
            main.getServer().getPluginManager().registerEvents(highlighter, main);
        }

        if (pack.equalsIgnoreCase("chunk-loaders")) {
            main.getServer().getPluginManager().registerEvents(loader, main);
        }

        if (pack.equalsIgnoreCase("fast-leaf-decay")) {
            main.getServer().getPluginManager().registerEvents(decay, main);
        }

        if (pack.equalsIgnoreCase("pillager-tools")) {
            main.getServer().getPluginManager().registerEvents(pillagerSpawning, main);
        }

        if (pack.equalsIgnoreCase("elevators")) {
            main.getServer().getPluginManager().registerEvents(elevator, main);
            Elevator.register();
        }

        if (pack.equalsIgnoreCase("rotation-wrench")) {
            main.getServer().getPluginManager().registerEvents(wrench, main);
            Wrench.register();
        }

        if (pack.equalsIgnoreCase("treasure-gems")) {
            main.getServer().getPluginManager().registerEvents(gems, main);
        }

        if (pack.equalsIgnoreCase("armored-elytra")) {
            main.getServer().getPluginManager().registerEvents(armoredElytras, main);
        }

        if (pack.equalsIgnoreCase("classic-fishing-loot")) {
            main.getServer().getPluginManager().registerEvents(classicFishing, main);
        }

        if (pack.equalsIgnoreCase("custom-nether-portals")) {
            portal.registerThis();
            main.getServer().getPluginManager().registerEvents(portal, main);
        }

        if (pack.equalsIgnoreCase("item-averages")) {
            main.getServer().getPluginManager().registerEvents(itemTracker, main);
        }

        if (pack.equalsIgnoreCase("track-statistics")) {
            statTracker.register();
            main.getServer().getPluginManager().registerEvents(statTracker, main);
        }

        if (pack.equalsIgnoreCase("track-raw-statistics")) {
            rawStats.register();
        }

        if (pack.equalsIgnoreCase("graves")) {
            main.getServer().getPluginManager().registerEvents(graves, main);
        }

        if (pack.equalsIgnoreCase("custom-villager-shops")) {
            main.getServer().getPluginManager().registerEvents(customVillager, main);
        }

        if (pack.equalsIgnoreCase("spawning-spheres")) {
            main.getServer().getPluginManager().registerEvents(sphere, main);
        }
    }
    public static void registerCommands() {


        new VanillaTweaksCommand().register();

        if (main.getConfig().getBoolean("commands.portal.enabled")) {
            new PortalCommand().register();
        }

        if (main.getConfig().getBoolean("commands.coordshud.enabled")) {
            new CoordsHudCommand().register();
        }

        if (main.getConfig().getBoolean("commands.nightvision.enabled")) {
            new NightVisionCommand().register();
        }

        if (main.getConfig().getBoolean("commands.conduitpower.enabled")) {
            new ConduitPowerCommand().register();
        }

        if (main.getConfig().getBoolean("commands.killboats.enabled")) {
            new KillBoatsCommand().register();
        }

        if (main.getConfig().getBoolean("commands.test.enabled")) {
            new TestCommand().register();
        }

        if (main.getConfig().getBoolean("commands.realtimeclock.enabled")) {
            new RealTimeClockCommand().register();
        }

        if (main.getConfig().getBoolean("commands.spawn.enabled")) {
            new SpawnCommand().register();
        }

        if (main.getConfig().getBoolean("commands.tpa.enabled")) {
            new TPACommand().register();
        }

        if (main.getConfig().getBoolean("commands.home.enabled")) {
            new HomeCommand().register();
        }

        if (main.getConfig().getBoolean("commands.durabilityping.enabled")) {
            new DurabilityPingCommand().register();
        }

        if (main.getConfig().getBoolean("commands.taggame.enabled")) {
            new TagGameCommand().register();
        }

        if (main.getConfig().getBoolean("commands.back.enabled")) {
            new BackCommand().register();
        }

        if (main.getConfig().getBoolean("commands.afk.enabled")) {
            new AFKCommand().register();
        }

        if (main.getConfig().getBoolean("commands.shrine.enabled")) {
            new ShrineCommand().register();
        }

        if (main.getConfig().getBoolean("commands.workstationhighlights.enabled")) {
            new WorkstationHighlightCommand().register();
        }

        if (main.getConfig().getBoolean("commands.sethome.enabled")) {
            new SetHomeCommand().register();
        }

        if (main.getConfig().getBoolean("commands.itemaverages.enabled")) {
            new ItemAveragesCommand().register();
        }

        if (main.getConfig().getBoolean("commands.grave.enabled")) {
            new GraveCommand().register();
        }

        if (main.getConfig().getBoolean("commands.spawningspheres.enabled")) {
            new SpawningSpheresCommand().register();
        }

        if (main.getConfig().getBoolean("commands.gem.enabled")) {
            new GemCommand().register();
        }


    }

    public static void registerCommand(BukkitCommand command) {
        registerCommand(command, null);
    }

    public static void registerCommand(Command command) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(command.getName(), command);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerCommand(BukkitCommand command, TabCompleter tabCompleter) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(command.getName(), command);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
