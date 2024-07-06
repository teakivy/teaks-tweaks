package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.commands.*;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.experimental.chunkloaders.Loader;
import me.teakivy.teakstweaks.packs.experimental.confetticreepers.ConfettiCreeper;
import me.teakivy.teakstweaks.packs.experimental.elevators.Elevator;
import me.teakivy.teakstweaks.packs.experimental.xpmanagement.XPManagement;
import me.teakivy.teakstweaks.packs.hermitcraft.tag.Tag;
import me.teakivy.teakstweaks.packs.hermitcraft.thundershrine.Shrine;
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
import me.teakivy.teakstweaks.packs.survival.cauldronmud.MudConverter;
import me.teakivy.teakstweaks.packs.survival.classicfishingloot.Fishing;
import me.teakivy.teakstweaks.packs.survival.coordshud.HUD;
import me.teakivy.teakstweaks.packs.survival.customnetherportals.NetherPortal;
import me.teakivy.teakstweaks.packs.survival.durabilityping.DuraPing;
import me.teakivy.teakstweaks.packs.survival.fastleafdecay.Decay;
import me.teakivy.teakstweaks.packs.survival.graves.GraveEvents;
import me.teakivy.teakstweaks.packs.survival.netherportalcoords.NetherPortalCoords;
import me.teakivy.teakstweaks.packs.survival.pillagertools.PillagerSpawning;
import me.teakivy.teakstweaks.packs.survival.realtimeclock.RealTimeClock;
import me.teakivy.teakstweaks.packs.survival.trackrawstatistics.RawStats;
import me.teakivy.teakstweaks.packs.survival.trackstatistics.StatTracker;
import me.teakivy.teakstweaks.packs.survival.unlockallrecipes.UnlockRecipes;
import me.teakivy.teakstweaks.packs.survival.workstationhighlights.Highlighter;
import me.teakivy.teakstweaks.packs.teakstweaks.chatcolors.ChatColors;
import me.teakivy.teakstweaks.packs.teakstweaks.dirttograss.DirtToGrass;
import me.teakivy.teakstweaks.packs.teakstweaks.fixeditemframes.FixedItemFrames;
import me.teakivy.teakstweaks.packs.teakstweaks.instantdeepslate.InstantDeepslate;
import me.teakivy.teakstweaks.packs.teakstweaks.invisibleitemframes.InvisibleItemFrames;
import me.teakivy.teakstweaks.packs.teakstweaks.keepsmall.KeepSmall;
import me.teakivy.teakstweaks.packs.teakstweaks.lecternreset.LecternReset;
import me.teakivy.teakstweaks.packs.teakstweaks.quickcommands.QuickCommands;
import me.teakivy.teakstweaks.packs.teakstweaks.sleepyspidereggs.SleepySpiderEggs;
import me.teakivy.teakstweaks.packs.teakstweaks.slimecream.SlimeCream;
import me.teakivy.teakstweaks.packs.teakstweaks.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.packs.teakstweaks.stairchairs.StairChairs;
import me.teakivy.teakstweaks.packs.teakstweaks.sudoku.Sudoku;
import me.teakivy.teakstweaks.packs.teakstweaks.unstickypistons.UnstickyPistons;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.packs.teleportation.spawn.Spawn;
import me.teakivy.teakstweaks.packs.teleportation.tpa.TPA;
import me.teakivy.teakstweaks.packs.utilities.itemaverages.ItemTracker;
import me.teakivy.teakstweaks.packs.utilities.killboats.KillBoats;
import me.teakivy.teakstweaks.packs.utilities.spawningspheres.SpheresPack;
import me.teakivy.teakstweaks.packs.utilities.spectatorconduitpower.ConduitPower;
import me.teakivy.teakstweaks.packs.utilities.spectatornightvision.NightVision;
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
            new Tag(),
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
            new PillagerSpawning(),
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
            new InstantDeepslate(),
            new BatMembranes(),
            new SleepySpiderEggs(),
            new NetherPortalCoords(),
            new RealTimeClock(),
            new Spawn(),
            new HomesPack(),
            new TPA(),
            new KillBoats()
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
            new TagGameCommand(),
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
