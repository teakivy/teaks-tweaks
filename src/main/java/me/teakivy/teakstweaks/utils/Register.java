package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.commands.*;
import me.teakivy.teakstweaks.packs.experimental.chunkloaders.Loader;
import me.teakivy.teakstweaks.packs.experimental.confetticreepers.ConfettiCreeper;
import me.teakivy.teakstweaks.packs.experimental.elevators.Elevator;
import me.teakivy.teakstweaks.packs.experimental.xpmanagement.XPManagement;
import me.teakivy.teakstweaks.packs.hermitcraft.tag.Tag;
import me.teakivy.teakstweaks.packs.hermitcraft.thundershrine.Shrine;
import me.teakivy.teakstweaks.packs.hermitcraft.treasuregems.Gems;
import me.teakivy.teakstweaks.packs.hermitcraft.wanderingtrades.Trades;
import me.teakivy.teakstweaks.packs.items.armoredelytra.ArmoredElytras;
import me.teakivy.teakstweaks.packs.items.playerheaddrops.HeadDrop;
import me.teakivy.teakstweaks.packs.items.rotationwrench.Wrench;
import me.teakivy.teakstweaks.packs.mobs.anticreepergrief.AntiCreeper;
import me.teakivy.teakstweaks.packs.mobs.antiendermangrief.AntiEnderman;
import me.teakivy.teakstweaks.packs.mobs.antighastgrief.AntiGhast;
import me.teakivy.teakstweaks.packs.mobs.batmembranes.BatMembranes;
import me.teakivy.teakstweaks.packs.mobs.countmobdeaths.CountDeaths;
import me.teakivy.teakstweaks.packs.mobs.doubleshulkershells.DoubleShulkers;
import me.teakivy.teakstweaks.packs.mobs.dragondrops.DragonDrops;
import me.teakivy.teakstweaks.packs.mobs.largerphantoms.Phantoms;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import me.teakivy.teakstweaks.packs.mobs.silencemobs.Silencer;
import me.teakivy.teakstweaks.packs.mobs.villagerdeathmessages.VillagerDeath;
import me.teakivy.teakstweaks.packs.survival.afkdisplay.AFK;
import me.teakivy.teakstweaks.packs.survival.cauldronconcrete.ConcreteConverter;
import me.teakivy.teakstweaks.packs.survival.classicfishingloot.Fishing;
import me.teakivy.teakstweaks.packs.survival.coordshud.HUD;
import me.teakivy.teakstweaks.packs.survival.customnetherportals.NetherPortal;
import me.teakivy.teakstweaks.packs.survival.durabilityping.DuraPing;
import me.teakivy.teakstweaks.packs.survival.fastleafdecay.Decay;
import me.teakivy.teakstweaks.packs.survival.graves.GraveEvents;
import me.teakivy.teakstweaks.packs.survival.multiplayersleep.MultiSleep;
import me.teakivy.teakstweaks.packs.survival.pillagertools.PillagerSpawning;
import me.teakivy.teakstweaks.packs.survival.trackrawstatistics.RawStats;
import me.teakivy.teakstweaks.packs.survival.trackstatistics.StatTracker;
import me.teakivy.teakstweaks.packs.survival.unlockallrecipes.UnlockRecipes;
import me.teakivy.teakstweaks.packs.survival.workstationhighlights.Highlighter;
import me.teakivy.teakstweaks.packs.teakstweaks.betterfoliage.BetterFoliage;
import me.teakivy.teakstweaks.packs.teakstweaks.chatcolors.ChatColors;
import me.teakivy.teakstweaks.packs.teakstweaks.editsigns.EditSigns;
import me.teakivy.teakstweaks.packs.teakstweaks.fixeditemframes.FixedItemFrames;
import me.teakivy.teakstweaks.packs.teakstweaks.instantdeepslate.InstantDeepslate;
import me.teakivy.teakstweaks.packs.teakstweaks.invisibleitemframes.InvisibleItemFrames;
import me.teakivy.teakstweaks.packs.teakstweaks.keepsmall.KeepSmall;
import me.teakivy.teakstweaks.packs.teakstweaks.lecternreset.LecternReset;
import me.teakivy.teakstweaks.packs.teakstweaks.sleepyspidereggs.SleepySpiderEggs;
import me.teakivy.teakstweaks.packs.teakstweaks.slimecream.SlimeCream;
import me.teakivy.teakstweaks.packs.teakstweaks.stairchairs.StairChairs;
import me.teakivy.teakstweaks.packs.teakstweaks.sudoku.Sudoku;
import me.teakivy.teakstweaks.packs.teakstweaks.unstickypistons.UnstickyPistons;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.packs.utilities.customvillagershops.CustomVillager;
import me.teakivy.teakstweaks.packs.utilities.itemaverages.ItemTracker;
import me.teakivy.teakstweaks.packs.utilities.spawningspheres.Sphere;
import me.teakivy.teakstweaks.packs.utilities.spectatorconduitpower.ConduitPower;
import me.teakivy.teakstweaks.packs.utilities.spectatornightvision.NightVision;

public class Register {

    private final AntiCreeper antiCreeper = new AntiCreeper();
    private final AntiEnderman antiEnderman = new AntiEnderman();
    private final AntiGhast antiGhast = new AntiGhast();
    private final DoubleShulkers doubleShulkers = new DoubleShulkers();
    private final DragonDrops dragonDrops = new DragonDrops();
    private final Phantoms phantoms = new Phantoms();
    private final MobHeads mobHeads = new MobHeads();
    private final DuraPing duraPing = new DuraPing();
    private final ConcreteConverter concreteConverter = new ConcreteConverter();
    private final CountDeaths countDeaths = new CountDeaths();
    private final MultiSleep multiplayerSleep = new MultiSleep();
    private final HeadDrop headDrop = new HeadDrop();
    private final Silencer silencer = new Silencer();
    private final ConduitPower conduitPower = new ConduitPower();
    private final NightVision nightVision = new NightVision();
    private final Tag tag = new Tag();
    private final UnlockRecipes unlockRecipes = new UnlockRecipes();
    private final VillagerDeath villagerDeath = new VillagerDeath();
    private final Trades trades = new Trades();
    private final XPManagement xpManagement = new XPManagement();
    private final ConfettiCreeper confettiCreeper = new ConfettiCreeper();
    private final Back back = new Back();
    private final AFK afk = new AFK();
    private final Shrine shrine = new Shrine();
    private final Highlighter highlighter = new Highlighter();
    private final Loader loader = new Loader();
    private final Decay decay = new Decay();
    private final PillagerSpawning pillagerSpawning = new PillagerSpawning();
    private final Elevator elevator = new Elevator();
    private final Wrench wrench = new Wrench();
    private final Gems gems = new Gems();
    private final ArmoredElytras armoredElytras = new ArmoredElytras();
    private final Fishing classicFishing = new Fishing();
    private final NetherPortal portal = new NetherPortal();
    private final ItemTracker itemTracker = new ItemTracker();
    private final StatTracker statTracker = new StatTracker();
    private final RawStats rawStats = new RawStats();
    private final GraveEvents graves = new GraveEvents();
    private final HUD hud = new HUD();
    private final CustomVillager customVillager = new CustomVillager();
    private final Sphere sphere = new Sphere();
    private final KeepSmall keepSmall = new KeepSmall();
    private final ChatColors chatColors = new ChatColors();
    private final EditSigns editableSigns = new EditSigns();
    private final LecternReset lecternReset = new LecternReset();
    private final Sudoku sudoku = new Sudoku();
    private final StairChairs stairChairs = new StairChairs();
    private final UnstickyPistons unstickyPistons = new UnstickyPistons();
    private final SlimeCream slimeCream = new SlimeCream();
    private final InvisibleItemFrames invisibleItemFrames = new InvisibleItemFrames();
    private final BetterFoliage betterFoliage = new BetterFoliage();
    private final FixedItemFrames fixedItemFrames = new FixedItemFrames();
    private final InstantDeepslate instantDeepslate = new InstantDeepslate();
    private final BatMembranes batMembranes = new BatMembranes();
    private final SleepySpiderEggs sleepySpiderEggs = new SleepySpiderEggs();

    public void registerAll() {
        Main main = Main.getInstance();
        unregisterAll();
        for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false)) {
            if (main.getConfig().getBoolean("packs." + pack + ".enabled")) {
                registerPack(pack);
            }
        }
    }

    public void unregisterAll() {
        Main main = Main.getInstance();
        main.clearPacks();
        for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false)) {
            if (!main.getConfig().getBoolean("packs." + pack + ".enabled")) {
                unregisterPack(pack);
            }
        }
    }

    public void unregisterPack(String pack) {

        switch (pack) {
            case "player-head-drops" -> headDrop.unregister();
            case "anti-creeper-grief" -> antiCreeper.unregister();
            case "anti-enderman-grief" -> antiEnderman.unregister();
            case "anti-ghast-grief" -> antiGhast.unregister();
            case "double-shulker-shells" -> doubleShulkers.unregister();
            case "dragon-drops" -> dragonDrops.unregister();
            case "larger-phantoms" -> phantoms.unregister();
            case "more-mob-heads" -> mobHeads.unregister();
            case "silence-mobs" -> silencer.unregister();
            case "count-mob-deaths" -> countDeaths.unregister();
            case "villager-death-messages" -> villagerDeath.unregister();
            case "coords-hud" -> hud.unregister();
            case "cauldron-concrete" -> concreteConverter.unregister();
            case "durability-ping" -> duraPing.unregister();
            case "multiplayer-sleep" -> multiplayerSleep.unregister();
            case "spectator-conduit-power" -> conduitPower.unregister();
            case "spectator-night-vision" -> nightVision.unregister();
            case "tag" -> tag.unregister();
            case "unlock-all-recipes" -> unlockRecipes.unregister();
            case "wandering-trades" -> trades.unregister();
            case "xp-management" -> xpManagement.unregister();
            case "confetti-creepers" -> confettiCreeper.unregister();
            case "back" -> back.unregister();
            case "afk-display" -> afk.unregister();
            case "thunder-shrine" -> shrine.unregister();
            case "workstation-highlights" -> highlighter.unregister();
            case "chunk-loaders" -> loader.unregister();
            case "fast-leaf-decay" -> decay.unregister();
            case "pillager-tools" -> pillagerSpawning.unregister();
            case "elevators" -> elevator.unregister();
            case "rotation-wrench" -> wrench.unregister();
            case "treasure-gems" -> gems.unregister();
            case "armored-elytra" -> armoredElytras.unregister();
            case "classic-fishing-loot" -> classicFishing.unregister();
            case "custom-nether-portals" -> portal.unregister();
            case "item-averages" -> itemTracker.unregister();
            case "track-statistics" -> statTracker.unregister();
            case "track-raw-statistics" -> rawStats.unregister();
            case "graves" -> graves.unregister();
            case "custom-villager-shops" -> customVillager.unregister();
            case "spawning-spheres" -> sphere.unregister();
            case "keep-small" -> keepSmall.unregister();
            case "chat-colors" -> chatColors.unregister();
            case "editable-signs" -> editableSigns.unregister();
            case "lectern-reset" -> lecternReset.unregister();
            case "sudoku" -> sudoku.unregister();
            case "stair-chairs" -> stairChairs.unregister();
            case "unsticky-pistons" -> unstickyPistons.unregister();
            case "slime-cream" -> slimeCream.unregister();
            case "invisible-item-frames" -> invisibleItemFrames.unregister();
            case "better-foliage" -> betterFoliage.unregister();
            case "fixed-item-frames" -> fixedItemFrames.unregister();
            case "instant-deepslate" -> instantDeepslate.unregister();
            case "bat-membranes" -> batMembranes.unregister();
            case "sleepy-spider-eggs" -> sleepySpiderEggs.unregister();
        }
    }

    public void registerPack(String pack) {
        switch (pack) {
            case "player-head-drops" -> headDrop.init();
            case "anti-creeper-grief" -> antiCreeper.init();
            case "anti-enderman-grief" -> antiEnderman.init();
            case "anti-ghast-grief" -> antiGhast.init();
            case "double-shulker-shells" -> doubleShulkers.init();
            case "dragon-drops" -> dragonDrops.init();
            case "larger-phantoms" -> phantoms.init();
            case "more-mob-heads" -> mobHeads.init();
            case "silence-mobs" -> silencer.init();
            case "count-mob-deaths" -> countDeaths.init();
            case "villager-death-messages" -> villagerDeath.init();
            case "coords-hud" -> hud.init();
            case "cauldron-concrete" -> concreteConverter.init();
            case "durability-ping" -> duraPing.init();
            case "multiplayer-sleep" -> multiplayerSleep.init();
            case "spectator-conduit-power" -> conduitPower.init();
            case "spectator-night-vision" -> nightVision.init();
            case "tag" -> tag.init();
            case "unlock-all-recipes" -> unlockRecipes.init();
            case "wandering-trades" -> trades.init();
            case "xp-management" -> xpManagement.init();
            case "confetti-creepers" -> confettiCreeper.init();
            case "back" -> back.init();
            case "afk-display" -> afk.init();
            case "thunder-shrine" -> shrine.init();
            case "workstation-highlights" -> highlighter.init();
            case "chunk-loaders" -> loader.init();
            case "fast-leaf-decay" -> decay.init();
            case "pillager-tools" -> pillagerSpawning.init();
            case "elevators" -> elevator.init();
            case "rotation-wrench" -> wrench.init();
            case "treasure-gems" -> gems.init();
            case "armored-elytra" -> armoredElytras.init();
            case "classic-fishing-loot" -> classicFishing.init();
            case "custom-nether-portals" -> portal.init();
            case "item-averages" -> itemTracker.init();
            case "track-statistics" -> statTracker.init();
            case "track-raw-statistics" -> rawStats.init();
            case "graves" -> graves.init();
            case "custom-villager-shops" -> customVillager.init();
            case "spawning-spheres" -> sphere.init();
            case "keep-small" -> keepSmall.init();
            case "chat-colors" -> chatColors.init();
            case "editable-signs" -> editableSigns.init();
            case "lectern-reset" -> lecternReset.init();
            case "sudoku" -> sudoku.init();
            case "stair-chairs" -> stairChairs.init();
            case "unsticky-pistons" -> unstickyPistons.init();
            case "slime-cream" -> slimeCream.init();
            case "invisible-item-frames" -> invisibleItemFrames.init();
            case "better-foliage" -> betterFoliage.init();
            case "fixed-item-frames" -> fixedItemFrames.init();
            case "instant-deepslate" -> instantDeepslate.init();
            case "bat-membranes" -> batMembranes.init();
            case "sleepy-spider-eggs" -> sleepySpiderEggs.init();
        }
    }
    public static void registerCommands() {
        AbstractCommand[] cmds = {
            new TeaksTweaksCommand(),
            new PortalCommand(),
            new CoordsHudCommand(),
            new NightVisionCommand(),
            new ConduitPowerCommand(),
            new KillBoatsCommand(),
            new TestCommand(),
            new RealTimeClockCommand(),
            new SpawnCommand(),
            new TPACommand(),
            new HomeCommand(),
            new DurabilityPingCommand(),
            new TagGameCommand(),
            new BackCommand(),
            new AFKCommand(),
            new ShrineCommand(),
            new WorkstationHighlightCommand(),
            new SetHomeCommand(),
            new ItemAveragesCommand(),
            new GraveCommand(),
            new SpawningSpheresCommand(),
            new GemCommand(),
            new SudokuCommand(),
            new PackListCommand()
        };

        for (AbstractCommand cmd : cmds) {
            cmd.register();
        }
    }

}
