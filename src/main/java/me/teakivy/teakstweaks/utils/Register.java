package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.commands.*;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.alwaysdrop.AlwaysDrop;
import me.teakivy.teakstweaks.packs.alwaysactivebeacons.AlwaysActiveBeacons;
import me.teakivy.teakstweaks.packs.chunkloaders.Loader;
import me.teakivy.teakstweaks.packs.collectiblebuddingamethyst.CollectibleBuddingAmethyst;
import me.teakivy.teakstweaks.packs.confetticreepers.ConfettiCreeper;
import me.teakivy.teakstweaks.packs.disposal.Disposal;
import me.teakivy.teakstweaks.packs.elevators.Elevator;
import me.teakivy.teakstweaks.packs.huskdropssand.HuskDropsSand;
import me.teakivy.teakstweaks.packs.miniblocks.MiniBlocks;
import me.teakivy.teakstweaks.packs.musicdiscengraver.MusicDiscEngraver;
import me.teakivy.teakstweaks.packs.paintingpicker.PaintingPicker;
import me.teakivy.teakstweaks.packs.sawmill.Sawmill;
import me.teakivy.teakstweaks.packs.wanderingtraderannouncements.WanderingTraderAnnouncements;
import me.teakivy.teakstweaks.packs.xpmanagement.XPManagement;
import me.teakivy.teakstweaks.packs.thundershrine.Shrine;
import me.teakivy.teakstweaks.packs.wanderingtrades.Trades;
import me.teakivy.teakstweaks.packs.armoredelytra.ArmoredElytras;
import me.teakivy.teakstweaks.packs.playerheaddrops.HeadDrop;
import me.teakivy.teakstweaks.packs.rotationwrench.Wrench;
import me.teakivy.teakstweaks.packs.anticreepergrief.AntiCreeper;
import me.teakivy.teakstweaks.packs.antiendermangrief.AntiEnderman;
import me.teakivy.teakstweaks.packs.antighastgrief.AntiGhast;
import me.teakivy.teakstweaks.packs.batmembranes.BatMembranes;
import me.teakivy.teakstweaks.packs.countmobdeaths.CountDeaths;
import me.teakivy.teakstweaks.packs.doubleshulkershells.DoubleShulkers;
import me.teakivy.teakstweaks.packs.dragondrops.DragonDrops;
import me.teakivy.teakstweaks.packs.largerphantoms.Phantoms;
import me.teakivy.teakstweaks.packs.moremobheads.MobHeads;
import me.teakivy.teakstweaks.packs.silencemobs.Silencer;
import me.teakivy.teakstweaks.packs.villagerdeathmessages.VillagerDeath;
import me.teakivy.teakstweaks.packs.afkdisplay.AFK;
import me.teakivy.teakstweaks.packs.cauldronconcrete.ConcreteConverter;
import me.teakivy.teakstweaks.packs.cauldronmud.MudConverter;
import me.teakivy.teakstweaks.packs.classicfishingloot.Fishing;
import me.teakivy.teakstweaks.packs.coordshud.HUD;
import me.teakivy.teakstweaks.packs.customnetherportals.NetherPortal;
import me.teakivy.teakstweaks.packs.durabilityping.DuraPing;
import me.teakivy.teakstweaks.packs.fastleafdecay.Decay;
import me.teakivy.teakstweaks.packs.graves.GraveEvents;
import me.teakivy.teakstweaks.packs.netherportalcoords.NetherPortalCoords;
import me.teakivy.teakstweaks.packs.realtimeclock.RealTimeClock;
import me.teakivy.teakstweaks.packs.trackrawstatistics.RawStats;
import me.teakivy.teakstweaks.packs.trackstatistics.StatTracker;
import me.teakivy.teakstweaks.packs.unlockallrecipes.UnlockRecipes;
import me.teakivy.teakstweaks.packs.workstationhighlights.Highlighter;
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
import me.teakivy.teakstweaks.packs.homes.HomesPack;
import me.teakivy.teakstweaks.packs.spawn.Spawn;
import me.teakivy.teakstweaks.packs.tpa.TPA;
import me.teakivy.teakstweaks.packs.itemaverages.ItemTracker;
import me.teakivy.teakstweaks.packs.killboats.KillBoats;
import me.teakivy.teakstweaks.packs.spawningspheres.SpheresPack;
import me.teakivy.teakstweaks.packs.spectatorconduitpower.ConduitPower;
import me.teakivy.teakstweaks.packs.spectatornightvision.NightVision;
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

        packs = Stream.of(new AntiCreeper(),
            new AntiEnderman(),
            new AntiGhast(),
            new DoubleShulkers(),
            new DragonDrops(),
            new Phantoms(),
            new MobHeads(),
            new DuraPing(),
            new ConcreteConverter(),
            new MudConverter(),
            new CountDeaths(),
            new QuickCommands(),
            new SpectatorAlts(),
            new HeadDrop(),
            new Silencer(),
            new ConduitPower(),
            new NightVision(),
            new UnlockRecipes(),
            new VillagerDeath(),
            new Trades(),
            new XPManagement(),
            new ConfettiCreeper(),
            new Back(),
            new AFK(),
            new Shrine(),
            new Highlighter(),
            new Loader(),
            new Decay(),
            new Elevator(),
            new Wrench(),
            new ArmoredElytras(),
            new Fishing(),
            new NetherPortal(),
            new ItemTracker(),
            new StatTracker(),
            new RawStats(),
            new GraveEvents(),
            new HUD(),
            new SpheresPack(),
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
            new HomesPack(),
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
            new MusicDiscEngraver()
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
            if (Config.isPackEnabled(pack) || bypassEnabled) {
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
        AbstractCommand[] cmds = {
            new TeaksTweaksCommand(),
            new TestCommand(),
            new MechanicsCommand(),
            new PortalCommand(),
            new CoordsHudCommand(),
            new NightVisionCommand(),
            new ConduitPowerCommand(),
            new KillBoatsCommand(),
            new RealTimeClockCommand(),
            new SpawnCommand(),
            new TPACommand(),
            new HomeCommand(),
            new DurabilityPingCommand(),
            new BackCommand(),
            new AFKCommand(),
            new ShrineCommand(),
            new WorkstationHighlightCommand(),
            new SetHomeCommand(),
            new ItemAveragesCommand(),
            new GraveCommand(),
            new SpawningSpheresCommand(),
            new SudokuCommand(),
            new PackListCommand(),
            new DeleteHomeCommand(),
            new AltsCommand(),
            new TPAHereCommand(),
            new DisposalCommand(),
        };

        for (AbstractCommand cmd : cmds) {
            cmd.register();
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
