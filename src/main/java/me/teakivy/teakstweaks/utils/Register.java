package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.commands.*;
import me.teakivy.teakstweaks.commands.DeleteHomeCommand;
import me.teakivy.teakstweaks.commands.DurabilityPingCommand;
import me.teakivy.teakstweaks.commands.KillBoatsCommand;
import me.teakivy.teakstweaks.commands.PortalCommand;
import me.teakivy.teakstweaks.commands.TestCommand;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.alwaysdrop.AlwaysDrop;
import me.teakivy.teakstweaks.packs.alwaysactivebeacons.AlwaysActiveBeacons;
import me.teakivy.teakstweaks.packs.chunkloaders.ChunkLoaders;
import me.teakivy.teakstweaks.packs.collectiblebuddingamethyst.CollectibleBuddingAmethyst;
import me.teakivy.teakstweaks.packs.confetticreepers.ConfettiCreepers;
import me.teakivy.teakstweaks.packs.disposal.Disposal;
import me.teakivy.teakstweaks.packs.elevators.Elevators;
import me.teakivy.teakstweaks.packs.huskdropssand.HuskDropsSand;
import me.teakivy.teakstweaks.packs.miniblocks.MiniBlocks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.musicdiscengraver.MusicDiscEngraver;
import me.teakivy.teakstweaks.packs.paintingpicker.PaintingPicker;
import me.teakivy.teakstweaks.packs.sawmill.Sawmill;
import me.teakivy.teakstweaks.packs.transferablepets.TransferablePets;
import me.teakivy.teakstweaks.packs.wanderingtraderannouncements.WanderingTraderAnnouncements;
import me.teakivy.teakstweaks.packs.xpmanagement.XPManagement;
import me.teakivy.teakstweaks.packs.thundershrine.ThunderShrine;
import me.teakivy.teakstweaks.packs.wanderingtrades.WanderingTrades;
import me.teakivy.teakstweaks.packs.armoredelytra.ArmoredElytra;
import me.teakivy.teakstweaks.packs.playerheaddrops.PlayerHeadDrops;
import me.teakivy.teakstweaks.packs.rotationwrench.RotationWrench;
import me.teakivy.teakstweaks.packs.anticreepergrief.AntiCreeperGrief;
import me.teakivy.teakstweaks.packs.antiendermangrief.AntiEndermanGrief;
import me.teakivy.teakstweaks.packs.antighastgrief.AntiGhastGrief;
import me.teakivy.teakstweaks.packs.batmembranes.BatMembranes;
import me.teakivy.teakstweaks.packs.countmobdeaths.CountMobDeaths;
import me.teakivy.teakstweaks.packs.doubleshulkershells.DoubleShulkerShells;
import me.teakivy.teakstweaks.packs.dragondrops.DragonDrops;
import me.teakivy.teakstweaks.packs.largerphantoms.LargerPhantoms;
import me.teakivy.teakstweaks.packs.silencemobs.SilenceMobs;
import me.teakivy.teakstweaks.packs.villagerdeathmessages.VillagerDeathMessages;
import me.teakivy.teakstweaks.packs.afkdisplay.AFKDisplay;
import me.teakivy.teakstweaks.packs.cauldronconcrete.CauldronConcrete;
import me.teakivy.teakstweaks.packs.cauldronmud.CauldronMud;
import me.teakivy.teakstweaks.packs.classicfishingloot.ClassicFishingLoot;
import me.teakivy.teakstweaks.packs.coordshud.CoordsHud;
import me.teakivy.teakstweaks.packs.customnetherportals.CustomNetherPortals;
import me.teakivy.teakstweaks.packs.durabilityping.DurabilityPing;
import me.teakivy.teakstweaks.packs.fastleafdecay.FastLeafDecay;
import me.teakivy.teakstweaks.packs.graves.Graves;
import me.teakivy.teakstweaks.packs.netherportalcoords.NetherPortalCoords;
import me.teakivy.teakstweaks.packs.realtimeclock.RealTimeClock;
import me.teakivy.teakstweaks.packs.trackrawstatistics.TrackRawStatistics;
import me.teakivy.teakstweaks.packs.trackstatistics.TrackStatistics;
import me.teakivy.teakstweaks.packs.unlockallrecipes.UnlockAllRecipes;
import me.teakivy.teakstweaks.packs.workstationhighlights.WorkstationHighlights;
import me.teakivy.teakstweaks.packs.chatcolors.ChatColors;
import me.teakivy.teakstweaks.packs.dirttograss.DirtToGrass;
import me.teakivy.teakstweaks.packs.fixeditemframes.FixedItemFrames;
import me.teakivy.teakstweaks.packs.instamine.InstaMine;
import me.teakivy.teakstweaks.packs.invisibleitemframes.InvisibleItemFrames;
import me.teakivy.teakstweaks.packs.keepsmall.KeepSmall;
import me.teakivy.teakstweaks.packs.lecternreset.LecternReset;
import me.teakivy.teakstweaks.packs.quickcommands.QuickCommands;
import me.teakivy.teakstweaks.packs.slimecream.SlimeCream;
import me.teakivy.teakstweaks.packs.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.packs.stairchairs.StairChairs;
import me.teakivy.teakstweaks.packs.sudoku.Sudoku;
import me.teakivy.teakstweaks.packs.unstickypistons.UnstickyPistons;
import me.teakivy.teakstweaks.packs.back.Back;
import me.teakivy.teakstweaks.packs.homes.Homes;
import me.teakivy.teakstweaks.packs.spawn.Spawn;
import me.teakivy.teakstweaks.packs.tpa.TPA;
import me.teakivy.teakstweaks.packs.itemaverages.ItemAverages;
import me.teakivy.teakstweaks.packs.killboats.KillBoats;
import me.teakivy.teakstweaks.packs.spawningspheres.SpawningSpheres;
import me.teakivy.teakstweaks.packs.spectatorconduitpower.SpectatorConduitPower;
import me.teakivy.teakstweaks.packs.spectatornightvision.SpectatorNightVision;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.config.Config;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Register {

    private final Map<String, BasePack> packs;
    private final List<String> enabledPacks;

    /**
     * Creates a new Register object
     */
    public Register() {
        enabledPacks = new ArrayList<>();

        packs = Stream.of(new AntiCreeperGrief(),
            new AntiEndermanGrief(),
            new AntiGhastGrief(),
            new DoubleShulkerShells(),
            new DragonDrops(),
            new LargerPhantoms(),
            new MoreMobHeads(),
            new DurabilityPing(),
            new CauldronConcrete(),
            new CauldronMud(),
            new CountMobDeaths(),
            new QuickCommands(),
            new SpectatorAlts(),
            new PlayerHeadDrops(),
            new SilenceMobs(),
            new SpectatorConduitPower(),
            new SpectatorNightVision(),
            new UnlockAllRecipes(),
            new VillagerDeathMessages(),
            new WanderingTrades(),
            new XPManagement(),
            new ConfettiCreepers(),
            new Back(),
            new AFKDisplay(),
            new ThunderShrine(),
            new WorkstationHighlights(),
            new ChunkLoaders(),
            new FastLeafDecay(),
            new Elevators(),
            new RotationWrench(),
            new ArmoredElytra(),
            new ClassicFishingLoot(),
            new CustomNetherPortals(),
            new ItemAverages(),
            new TrackStatistics(),
            new TrackRawStatistics(),
            new Graves(),
            new CoordsHud(),
            new SpawningSpheres(),
            new KeepSmall(),
            new ChatColors(),
            new LecternReset(),
            new Sudoku(),
            new StairChairs(),
            new UnstickyPistons(),
            new SlimeCream(),
            new InvisibleItemFrames(),
            new DirtToGrass(),
            new FixedItemFrames(),
            new InstaMine(),
            new BatMembranes(),
            new NetherPortalCoords(),
            new RealTimeClock(),
            new Spawn(),
            new Homes(),
            new TPA(),
            new KillBoats(),
            new AlwaysDrop(),
            new HuskDropsSand(),
            new CollectibleBuddingAmethyst(),
            new MiniBlocks(),
            new WanderingTraderAnnouncements(),
            new Sawmill(),
            new PaintingPicker(),
            new Disposal(),
            new AlwaysActiveBeacons(),
            new MusicDiscEngraver(),
            new TransferablePets()
        ).collect(Collectors.toMap(BasePack::getPath, x -> x));

    }

    /**
     * Registers all packs in the config
     */
    public void registerAll() {
        registerAll(false);
    }

    /**
     * Registers all packs in the config
     * @param bypassEnabled If true, will register all packs regardless of if they are enabled or not
     */
    public void registerAll(boolean bypassEnabled) {
        unregisterAll();
        for (String pack : Objects.requireNonNull(Config.get().getConfigurationSection("packs")).getKeys(false)) {
            if (Config.isPackEnabled(pack) || bypassEnabled || Config.isDevMode()) {
                registerPack(pack);
            }
        }
    }

    /**
     * Unregisters all packs
     */
    public void unregisterAll() {
        unregisterAll(false);
    }

    /**
     * Unregisters all packs
     * @param bypassEnabled If true, will unregister all packs regardless of if they are enabled or not
     */
    public void unregisterAll(boolean bypassEnabled) {
        TeaksTweaks.getInstance().clearPacks();
        for (String pack : Objects.requireNonNull(Config.get().getConfigurationSection("packs")).getKeys(false)) {
            if (Config.isPackEnabled(pack) || bypassEnabled) {
                unregisterPack(pack);
            }
        }
    }

    /**
     * Unregisters a pack
     * @param pack The pack to unregister
     */
    public void unregisterPack(String pack) {
        BasePack pk = packs.get(pack);
        if (pk != null) pk.unregister();

        enabledPacks.remove(pack);
    }

    /**
     * Registers a pack
     * @param pack The pack to register
     */
    public void registerPack(String pack) {
        BasePack pk = packs.get(pack);
        if (pk != null) pk.init();

        enabledPacks.add(pack);
    }

    /**
     * Registers all commands
     */
    public static void registerCommands() {

        AbstractCommand[] commands = {
                new TeaksTweaksCommand(),
                new AFKCommand(),
                new ConduitPowerCommand(),
                new MechanicsCommand(),
                new NightVisionCommand(),
                new SudokuCommand(),
                new DisposalCommand(),
                new PortalCommand(),
                new CoordsHudCommand(),
                new RealTimeClockCommand(),
                new TestCommand(),
                new KillBoatsCommand(),
                new ShrineCommand(),
                new ItemAveragesCommand(),
                new SpawnCommand(),
                new BackCommand(),
                new GraveCommand(),
                new TPACommand(),
                new TPAHereCommand(),
                new SetHomeCommand(),
                new DeleteHomeCommand(),
                new HomeCommand(),
                new SpawningSpheresCommand(),
                new WorkstationHighlightCommand(),
                new AltsCommand(),
                new DurabilityPingCommand(),
        };

        for (me.teakivy.teakstweaks.utils.command.AbstractCommand command : commands) {
            command.register();
        }
    }

    /**
     * Gets all packs
     * @return A set of all packs
     */
    public Set<String> getAllPacks() {
        return Objects.requireNonNull(Config.get().getConfigurationSection("packs")).getKeys(false);
    }

    /**
     * Gets all enabled packs
     * @return A list of all enabled packs
     */
    public List<String> getEnabledPacks() {
        return enabledPacks;
    }

    /**
     * Gets all disabled packs
     * @return A list of all disabled packs
     */
    public List<String> getDisabledPacks() {
        List<String> disabledPacks = new ArrayList<>();
        for (String pack : getAllPacks()) {
            if (!enabledPacks.contains(pack)) {
                disabledPacks.add(pack);
            }
        }
        return disabledPacks;
    }

    /**
     * Gets a pack
     * @param pack The pack to get
     * @return The pack
     */
    public BasePack getPack(String pack) {
        return packs.get(pack);
    }

}
