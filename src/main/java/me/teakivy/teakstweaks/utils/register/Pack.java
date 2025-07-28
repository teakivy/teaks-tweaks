package me.teakivy.teakstweaks.utils.register;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.afkdisplay.AFK;
import me.teakivy.teakstweaks.packs.alwaysactivebeacons.AlwaysActiveBeacons;
import me.teakivy.teakstweaks.packs.alwaysdrop.AlwaysDrop;
import me.teakivy.teakstweaks.packs.anticreepergrief.AntiCreeper;
import me.teakivy.teakstweaks.packs.antiendermangrief.AntiEnderman;
import me.teakivy.teakstweaks.packs.antighastgrief.AntiGhast;
import me.teakivy.teakstweaks.packs.armoredelytra.ArmoredElytras;
import me.teakivy.teakstweaks.packs.back.Back;
import me.teakivy.teakstweaks.packs.batmembranes.BatMembranes;
import me.teakivy.teakstweaks.packs.cauldronconcrete.ConcreteConverter;
import me.teakivy.teakstweaks.packs.chatcolors.ChatColors;
import me.teakivy.teakstweaks.packs.chunkloaders.Loader;
import me.teakivy.teakstweaks.packs.classicfishingloot.Fishing;
import me.teakivy.teakstweaks.packs.collectiblebuddingamethyst.CollectibleBuddingAmethyst;
import me.teakivy.teakstweaks.packs.confetticreepers.ConfettiCreeper;
import me.teakivy.teakstweaks.packs.coordshud.HUD;
import me.teakivy.teakstweaks.packs.countmobdeaths.CountDeaths;
import me.teakivy.teakstweaks.packs.customnetherportals.NetherPortal;
import me.teakivy.teakstweaks.packs.dirttograss.DirtToGrass;
import me.teakivy.teakstweaks.packs.disposal.Disposal;
import me.teakivy.teakstweaks.packs.doubleshulkershells.DoubleShulkers;
import me.teakivy.teakstweaks.packs.dragondrops.DragonDrops;
import me.teakivy.teakstweaks.packs.durabilityping.DuraPing;
import me.teakivy.teakstweaks.packs.elevators.Elevator;
import me.teakivy.teakstweaks.packs.fastleafdecay.Decay;
import me.teakivy.teakstweaks.packs.fixeditemframes.FixedItemFrames;
import me.teakivy.teakstweaks.packs.graves.GraveEvents;
import me.teakivy.teakstweaks.packs.homes.HomesPack;
import me.teakivy.teakstweaks.packs.huskdropssand.HuskDropsSand;
import me.teakivy.teakstweaks.packs.instamine.InstaMine;
import me.teakivy.teakstweaks.packs.invisibleitemframes.InvisibleItemFrames;
import me.teakivy.teakstweaks.packs.itemaverages.ItemTracker;
import me.teakivy.teakstweaks.packs.keepsmall.KeepSmall;
import me.teakivy.teakstweaks.packs.killboats.KillBoats;
import me.teakivy.teakstweaks.packs.largerphantoms.Phantoms;
import me.teakivy.teakstweaks.packs.lecternreset.LecternReset;
import me.teakivy.teakstweaks.packs.miniblocks.MiniBlocks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.musicdiscengraver.MusicDiscEngraver;
import me.teakivy.teakstweaks.packs.netherportalcoords.NetherPortalCoords;
import me.teakivy.teakstweaks.packs.paintingpicker.PaintingPicker;
import me.teakivy.teakstweaks.packs.playerheaddrops.HeadDrop;
import me.teakivy.teakstweaks.packs.quickcommands.QuickCommands;
import me.teakivy.teakstweaks.packs.realtimeclock.RealTimeClock;
import me.teakivy.teakstweaks.packs.rotationwrench.Wrench;
import me.teakivy.teakstweaks.packs.sawmill.Sawmill;
import me.teakivy.teakstweaks.packs.silencemobs.Silencer;
import me.teakivy.teakstweaks.packs.slimecream.SlimeCream;
import me.teakivy.teakstweaks.packs.spawn.Spawn;
import me.teakivy.teakstweaks.packs.spawningspheres.SpheresPack;
import me.teakivy.teakstweaks.packs.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.packs.spectatorconduitpower.ConduitPower;
import me.teakivy.teakstweaks.packs.spectatornightvision.NightVision;
import me.teakivy.teakstweaks.packs.stairchairs.StairChairs;
import me.teakivy.teakstweaks.packs.sudoku.Sudoku;
import me.teakivy.teakstweaks.packs.thundershrine.Shrine;
import me.teakivy.teakstweaks.packs.tpa.TPA;
import me.teakivy.teakstweaks.packs.trackrawstatistics.RawStats;
import me.teakivy.teakstweaks.packs.transferablepets.TransferablePets;
import me.teakivy.teakstweaks.packs.unlockallrecipes.UnlockRecipes;
import me.teakivy.teakstweaks.packs.unstickypistons.UnstickyPistons;
import me.teakivy.teakstweaks.packs.villagerdeathmessages.VillagerDeath;
import me.teakivy.teakstweaks.packs.wanderingtraderannouncements.WanderingTraderAnnouncements;
import me.teakivy.teakstweaks.packs.wanderingtrades.Trades;
import me.teakivy.teakstweaks.packs.workstationhighlights.Highlighter;
import me.teakivy.teakstweaks.packs.xpmanagement.XPManagement;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public enum Pack {
    AFK_DISPLAY("afk-display", new AFK()),
    ALWAYS_ACTIVE_BEACONS("always-active-beacons", new AlwaysActiveBeacons()),
    ALWAYS_DROP("always-drop", new AlwaysDrop()),
    ANTI_CREEPER_GRIEF("anti-creeper-grief", new AntiCreeper()),
    ANTI_ENDERMAN_GRIEF("anti-enderman-grief", new AntiEnderman()),
    ANTI_GHAST_GRIEF("anti-ghast-grief", new AntiGhast()),
    ARMORED_ELYTRA("armored-elytra", new ArmoredElytras()),
    BACK("back", new Back()),
    BAT_MEMBRANES("bat-membranes", new BatMembranes()),
    CAULDRON_CONCRETE("cauldron-concrete", new ConcreteConverter()),
    CHAT_COLORS("chat-colors", new ChatColors()),
    CHUNK_LOADERS("chunk-loaders", new Loader()),
    CLASSIC_FISHING_LOOT("classic-fishing-loot", new Fishing()),
    COLLECTIBLE_BUDDING_AMETHYST("collectible-budding-amethyst", new CollectibleBuddingAmethyst()),
    CONFETTI_CREEPERS("confetti-creepers", new ConfettiCreeper()),
    COORDS_HUD("coords-hud", new HUD()),
    COUNT_MOB_DEATHS("count-mob-deaths", new CountDeaths()),
    CUSTOM_NETHER_PORTALS("custom-nether-portals", new NetherPortal()),
    DIRT_TO_GRASS("dirt-to-grass", new DirtToGrass()),
    DISPOSAL("disposal", new Disposal()),
    DOUBLE_SHULKER_SHELLS("double-shulker-shells", new DoubleShulkers()),
    DRAGON_DROPS("dragon-drops", new DragonDrops()),
    DURABILITY_PING("durability-ping", new DuraPing()),
    ELEVATORS("elevators", new Elevator()),
    FAST_LEAF_DECAY("fast-leaf-decay", new Decay()),
    FIXED_ITEM_FRAMES("fixed-item-frames", new FixedItemFrames()),
    GRAVES("graves", new GraveEvents()),
    HOMES("homes", new HomesPack()),
    HUSK_DROPS_SAND("husk-drops-sand", new HuskDropsSand()),
    INSTA_MINE("insta-mine", new InstaMine()),
    INVISIBLE_ITEM_FRAMES("invisible-item-frames", new InvisibleItemFrames()),
    ITEM_AVERAGES("item-averages", new ItemTracker()),
    KEEP_SMALL("keep-small", new KeepSmall()),
    KILL_BOATS("kill-boats", new KillBoats()),
    LARGER_PHANTOMS("larger-phantoms", new Phantoms()),
    LECTERN_RESET("lectern-reset", new LecternReset()),
    MINI_BLOCKS("mini-blocks", new MiniBlocks()),
    MORE_MOB_HEADS("more-mob-heads", new MoreMobHeads()),
    MUSIC_DISC_ENGRAVER("music-disc-engraver", new MusicDiscEngraver()),
    NETHER_PORTAL_COORDS("nether-portal-coords", new NetherPortalCoords()),
    PAINTING_PICKER("painting-picker", new PaintingPicker()),
    PLAYER_HEAD_DROPS("player-head-drops", new HeadDrop()),
    QUICK_COMMANDS("quick-commands", new QuickCommands()),
    REAL_TIME_CLOCK("real-time-clock", new RealTimeClock()),
    ROTATION_WRENCH("rotation-wrench", new Wrench()),
    SAWMILL("sawmill", new Sawmill()),
    SILENCE_MOBS("silence-mobs", new Silencer()),
    SLIME_CREAM("slime-cream", new SlimeCream()),
    SPAWN("spawn", new Spawn()),
    SPAWNING_SPHERES("spawning-spheres", new SpheresPack()),
    SPECTATOR_ALTS("spectator-alts", new SpectatorAlts()),
    SPECTATOR_CONDUIT_POWER("spectator-conduit-power", new ConduitPower()),
    SPECTATOR_NIGHT_VISION("spectator-night-vision", new NightVision()),
    STAIR_CHAIRS("stair-chairs", new StairChairs()),
    SUDOKU("sudoku", new Sudoku()),
    THUNDER_SHRINE("thunder-shrine", new Shrine()),
    TPA("tpa", new TPA()),
    TRACK_RAW_STATISTICS("track-raw-statistics", new RawStats()),
    TRANSFERABLE_PETS("transferable-pets", new TransferablePets()),
    UNLOCK_ALL_RECIPES("unlock-all-recipes", new UnlockRecipes()),
    UNSTICKY_PISTONS("unsticky-pistons", new UnstickyPistons()),
    VILLAGER_DEATH_MESSAGES("villager-death-messages", new VillagerDeath()),
    WANDERING_TRADER_ANNOUNCEMENTS("wandering-trader-announcements", new WanderingTraderAnnouncements()),
    WANDERING_TRADES("wandering-trades", new Trades()),
    WORKSTATION__HIGHLIGHTS("workstation-highlights", new Highlighter()),
    XP_MANAGEMENT("xp-management", new XPManagement());

    private final String key;
    private final BasePack pack;

    Pack(String key, BasePack pack) {
        this.key = key;
        this.pack = pack;
    }

    public String getKey() {
        return key;
    }

    public BasePack getPack() {
        return pack;
    }

    public static Pack fromKey(String key) {
        for (Pack pack : values()) {
            if (pack.getKey().equalsIgnoreCase(key)) {
                return pack;
            }
        }
        return null;
    }

    public ConfigurationSection getConfig() {
        return pack.getConfig();
    }

    public void register() {
        if (pack.isRegistered()) return;
        if (!isEnabled() && !Config.isDevMode()) return;
        pack.init();
    }

    public boolean isEnabled() {
        return pack.getConfig().getBoolean("enabled", false);
    }

    public ItemStack getItem() {
        return pack.getItem();
    }
}
