package me.teakivy.teakstweaks.Utils;

import me.teakivy.teakstweaks.Commands.*;
import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Packs.Experimental.ChunkLoaders.Loader;
import me.teakivy.teakstweaks.Packs.Experimental.ConfettiCreepers.ConfettiCreeper;
import me.teakivy.teakstweaks.Packs.Experimental.Elevators.Elevator;
import me.teakivy.teakstweaks.Packs.Experimental.XPManagement.XPManagement;
import me.teakivy.teakstweaks.Packs.Hermitcraft.Tag.Tag;
import me.teakivy.teakstweaks.Packs.Hermitcraft.ThunderShrine.Shrine;
import me.teakivy.teakstweaks.Packs.Hermitcraft.TreasureGems.Gems;
import me.teakivy.teakstweaks.Packs.Hermitcraft.WanderingTrades.Trades;
import me.teakivy.teakstweaks.Packs.Items.ArmoredElytra.ArmoredElytras;
import me.teakivy.teakstweaks.Packs.Items.PlayerHeadDrops.HeadDrop;
import me.teakivy.teakstweaks.Packs.Items.RotationWrench.Wrench;
import me.teakivy.teakstweaks.Packs.Mobs.AntiCreeperGreif.AntiCreeper;
import me.teakivy.teakstweaks.Packs.Mobs.AntiEndermanGrief.AntiEnderman;
import me.teakivy.teakstweaks.Packs.Mobs.AntiGhastGrief.AntiGhast;
import me.teakivy.teakstweaks.Packs.Mobs.BatMembranes.BatMembranes;
import me.teakivy.teakstweaks.Packs.Mobs.CountMobDeaths.CountDeaths;
import me.teakivy.teakstweaks.Packs.Mobs.DoubleShulkerShells.DoubleShulkers;
import me.teakivy.teakstweaks.Packs.Mobs.DragonDrops.DragonDrops;
import me.teakivy.teakstweaks.Packs.Mobs.LargerPhantoms.Phantoms;
import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import me.teakivy.teakstweaks.Packs.Mobs.SilenceMobs.Silencer;
import me.teakivy.teakstweaks.Packs.Mobs.VillagerDeathMessages.VillagerDeath;
import me.teakivy.teakstweaks.Packs.Survival.AFKDisplay.AFK;
import me.teakivy.teakstweaks.Packs.Survival.CauldronConcrete.ConcreteConverter;
import me.teakivy.teakstweaks.Packs.Survival.ClassicFishingLoot.Fishing;
import me.teakivy.teakstweaks.Packs.Survival.CoordsHud.HUD;
import me.teakivy.teakstweaks.Packs.Survival.CustomNetherPortals.NetherPortal;
import me.teakivy.teakstweaks.Packs.Survival.DurabilityPing.DuraPing;
import me.teakivy.teakstweaks.Packs.Survival.FastLeafDecay.Decay;
import me.teakivy.teakstweaks.Packs.Survival.Graves.GraveEvents;
import me.teakivy.teakstweaks.Packs.Survival.MultiplayerSleep.MultiSleep;
import me.teakivy.teakstweaks.Packs.Survival.PillagerTools.PillagerSpawning;
import me.teakivy.teakstweaks.Packs.Survival.TrackRawStatistics.RawStats;
import me.teakivy.teakstweaks.Packs.Survival.TrackStatistics.StatTracker;
import me.teakivy.teakstweaks.Packs.Survival.UnlockAllRecipes.UnlockRecipes;
import me.teakivy.teakstweaks.Packs.Survival.WorkstationHighlights.Highlighter;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.BetterFoliage.BetterFoliage;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.ChatColors.ChatColors;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.EditSigns.EditSigns;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.FixedItemFrames.FixedItemFrames;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.InstantDeepslate.InstantDeepslate;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.InvisibleItemFrames.InvisibleItemFrames;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.KeepSmall.KeepSmall;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.LecternReset.LecternReset;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.SlimeCream.SlimeCream;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.StairChairs.StairChairs;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.Sudoku.Sudoku;
import me.teakivy.teakstweaks.Packs.TeaksTweaks.UnstickyPistons.UnstickyPistons;
import me.teakivy.teakstweaks.Packs.Teleportation.Back.Back;
import me.teakivy.teakstweaks.Packs.Utilities.CustomVillagerShops.CustomVillager;
import me.teakivy.teakstweaks.Packs.Utilities.ItemAverages.ItemTracker;
import me.teakivy.teakstweaks.Packs.Utilities.SpawningSpheres.Sphere;
import me.teakivy.teakstweaks.Packs.Utilities.SpectatorConduitPower.ConduitPower;
import me.teakivy.teakstweaks.Packs.Utilities.SpectatorNightVision.NightVision;

public class Register {

    static Main main = Main.getPlugin(Main.class);

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

    public void registerAll() {
        unregisterAll();
        for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false)) {
            if (main.getConfig().getBoolean("packs." + pack + ".enabled")) {
                registerPack(pack);
            }
        }
    }

    public void unregisterAll() {
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
        }
    }
    public static void registerCommands() {


        new TeaksTweaksCommand().register();

        if (main.getConfig().getBoolean("commands.portal.enabled") &&
                main.getConfig().getBoolean("packs.nether-portal-coords.enabled")) {
            new PortalCommand().register();
        }

        if (main.getConfig().getBoolean("commands.coordshud.enabled") &&
                main.getConfig().getBoolean("packs.coords-hud.enabled")) {
            new CoordsHudCommand().register();
        }

        if (main.getConfig().getBoolean("commands.nightvision.enabled") &&
                main.getConfig().getBoolean("packs.spectator-night-vision.enabled")) {
            new NightVisionCommand().register();
        }

        if (main.getConfig().getBoolean("commands.conduitpower.enabled") &&
                main.getConfig().getBoolean("packs.spectator-conduit-power.enabled")) {
            new ConduitPowerCommand().register();
        }

        if (main.getConfig().getBoolean("commands.killboats.enabled") &&
                main.getConfig().getBoolean("packs.kill-boats.enabled")) {
            new KillBoatsCommand().register();
        }

        if (main.getConfig().getBoolean("commands.test.enabled")) {
            new TestCommand().register();
        }

        if (main.getConfig().getBoolean("commands.realtimeclock.enabled") &&
                main.getConfig().getBoolean("packs.real-time-clock.enabled")) {
            new RealTimeClockCommand().register();
        }

        if (main.getConfig().getBoolean("commands.spawn.enabled") &&
                main.getConfig().getBoolean("packs.spawn.enabled")) {
            new SpawnCommand().register();
        }

        if (main.getConfig().getBoolean("commands.tpa.enabled") &&
                main.getConfig().getBoolean("packs.tpa.enabled")) {
            new TPACommand().register();
        }

        if (main.getConfig().getBoolean("commands.home.enabled") &&
                main.getConfig().getBoolean("packs.homes.enabled")) {
            new HomeCommand().register();
        }

        if (main.getConfig().getBoolean("commands.durabilityping.enabled") &&
                main.getConfig().getBoolean("packs.durability-ping.enabled")) {
            new DurabilityPingCommand().register();
        }

        if (main.getConfig().getBoolean("commands.taggame.enabled") &&
                main.getConfig().getBoolean("packs.tag.enabled")) {
            new TagGameCommand().register();
        }

        if (main.getConfig().getBoolean("commands.back.enabled") &&
                main.getConfig().getBoolean("packs.back.enabled")) {
            new BackCommand().register();
        }

        if (main.getConfig().getBoolean("commands.afk.enabled") &&
                main.getConfig().getBoolean("packs.afk-display.enabled")) {
            new AFKCommand().register();
        }

        if (main.getConfig().getBoolean("commands.shrine.enabled")
                && main.getConfig().getBoolean("packs.thunder-shrine.enabled")) {
            new ShrineCommand().register();
        }

        if (main.getConfig().getBoolean("commands.workstationhighlights.enabled") &&
                main.getConfig().getBoolean("packs.workstation-highlights.enabled")) {
            new WorkstationHighlightCommand().register();
        }

        if (main.getConfig().getBoolean("commands.sethome.enabled") &&
                main.getConfig().getBoolean("packs.homes.enabled")) {
            new SetHomeCommand().register();
        }

        if (main.getConfig().getBoolean("commands.itemaverages.enabled") &&
                main.getConfig().getBoolean("packs.item-averages.enabled")) {
            new ItemAveragesCommand().register();
        }

        if (main.getConfig().getBoolean("commands.grave.enabled") &&
                main.getConfig().getBoolean("packs.graves.enabled")) {
            new GraveCommand().register();
        }

        if (main.getConfig().getBoolean("commands.spawningspheres.enabled") &&
                main.getConfig().getBoolean("packs.spawning-spheres.enabled")) {
            new SpawningSpheresCommand().register();
        }

        if (main.getConfig().getBoolean("commands.gem.enabled") &&
                main.getConfig().getBoolean("packs.treasure-gem.enabled")) {
            new GemCommand().register();
        }

        if (main.getConfig().getBoolean("commands.sudoku.enabled") &&
                main.getConfig().getBoolean("packs.sudoku.enabled")) {
            new SudokuCommand().register();
        }

        if (main.getConfig().getBoolean("commands.packlist.enabled")) {
            new PackListCommand().register();
        }
    }

}
