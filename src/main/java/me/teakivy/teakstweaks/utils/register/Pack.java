package me.teakivy.teakstweaks.utils.register;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.afkdisplay.AFKDisplay;
import me.teakivy.teakstweaks.packs.alwaysactivebeacons.AlwaysActiveBeacons;
import me.teakivy.teakstweaks.packs.alwaysdrop.AlwaysDrop;
import me.teakivy.teakstweaks.packs.anticreepergrief.AntiCreeperGrief;
import me.teakivy.teakstweaks.packs.antiendermangrief.AntiEndermanGrief;
import me.teakivy.teakstweaks.packs.antighastgrief.AntiGhastGrief;
import me.teakivy.teakstweaks.packs.armoredelytra.ArmoredElytra;
import me.teakivy.teakstweaks.packs.back.Back;
import me.teakivy.teakstweaks.packs.batmembranes.BatMembranes;
import me.teakivy.teakstweaks.packs.cauldronconcrete.CauldronConcrete;
import me.teakivy.teakstweaks.packs.cauldronmud.CauldronMud;
import me.teakivy.teakstweaks.packs.chatcolors.ChatColors;
import me.teakivy.teakstweaks.packs.chunkloaders.ChunkLoaders;
import me.teakivy.teakstweaks.packs.classicfishingloot.ClassicFishingLoot;
import me.teakivy.teakstweaks.packs.collectiblebuddingamethyst.CollectibleBuddingAmethyst;
import me.teakivy.teakstweaks.packs.confetticreepers.ConfettiCreepers;
import me.teakivy.teakstweaks.packs.coordshud.CoordsHud;
import me.teakivy.teakstweaks.packs.countmobdeaths.CountMobDeaths;
import me.teakivy.teakstweaks.packs.customnetherportals.CustomNetherPortals;
import me.teakivy.teakstweaks.packs.dirttograss.DirtToGrass;
import me.teakivy.teakstweaks.packs.disposal.Disposal;
import me.teakivy.teakstweaks.packs.doubleshulkershells.DoubleShulkerShells;
import me.teakivy.teakstweaks.packs.dragondrops.DragonDrops;
import me.teakivy.teakstweaks.packs.durabilityping.DurabilityPing;
import me.teakivy.teakstweaks.packs.elevators.Elevators;
import me.teakivy.teakstweaks.packs.fastleafdecay.FastLeafDecay;
import me.teakivy.teakstweaks.packs.fixeditemframes.FixedItemFrames;
import me.teakivy.teakstweaks.packs.graves.Graves;
import me.teakivy.teakstweaks.packs.homes.Homes;
import me.teakivy.teakstweaks.packs.huskdropssand.HuskDropsSand;
import me.teakivy.teakstweaks.packs.instamine.InstaMine;
import me.teakivy.teakstweaks.packs.invisibleitemframes.InvisibleItemFrames;
import me.teakivy.teakstweaks.packs.itemaverages.ItemAverages;
import me.teakivy.teakstweaks.packs.keepsmall.KeepSmall;
import me.teakivy.teakstweaks.packs.killboats.KillBoats;
import me.teakivy.teakstweaks.packs.largerphantoms.LargerPhantoms;
import me.teakivy.teakstweaks.packs.lecternreset.LecternReset;
import me.teakivy.teakstweaks.packs.miniblocks.MiniBlocks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.musicdiscengraver.MusicDiscEngraver;
import me.teakivy.teakstweaks.packs.netherportalcoords.NetherPortalCoords;
import me.teakivy.teakstweaks.packs.paintingpicker.PaintingPicker;
import me.teakivy.teakstweaks.packs.playerheaddrops.PlayerHeadDrops;
import me.teakivy.teakstweaks.packs.quickcommands.QuickCommands;
import me.teakivy.teakstweaks.packs.realtimeclock.RealTimeClock;
import me.teakivy.teakstweaks.packs.rotationwrench.RotationWrench;
import me.teakivy.teakstweaks.packs.sawmill.Sawmill;
import me.teakivy.teakstweaks.packs.silencemobs.SilenceMobs;
import me.teakivy.teakstweaks.packs.slimecream.SlimeCream;
import me.teakivy.teakstweaks.packs.spawn.Spawn;
import me.teakivy.teakstweaks.packs.spawningspheres.SpawningSpheres;
import me.teakivy.teakstweaks.packs.spectatoralts.SpectatorAlts;
import me.teakivy.teakstweaks.packs.spectatorconduitpower.SpectatorConduitPower;
import me.teakivy.teakstweaks.packs.spectatornightvision.SpectatorNightVision;
import me.teakivy.teakstweaks.packs.stairchairs.StairChairs;
import me.teakivy.teakstweaks.packs.sudoku.Sudoku;
import me.teakivy.teakstweaks.packs.thundershrine.ThunderShrine;
import me.teakivy.teakstweaks.packs.tpa.TPA;
import me.teakivy.teakstweaks.packs.trackrawstatistics.TrackRawStatistics;
import me.teakivy.teakstweaks.packs.trackstatistics.TrackStatistics;
import me.teakivy.teakstweaks.packs.transferablepets.TransferablePets;
import me.teakivy.teakstweaks.packs.unlockallrecipes.UnlockAllRecipes;
import me.teakivy.teakstweaks.packs.unstickypistons.UnstickyPistons;
import me.teakivy.teakstweaks.packs.villagerdeathmessages.VillagerDeathMessages;
import me.teakivy.teakstweaks.packs.wanderingtraderannouncements.WanderingTraderAnnouncements;
import me.teakivy.teakstweaks.packs.wanderingtrades.WanderingTrades;
import me.teakivy.teakstweaks.packs.workstationhighlights.WorkstationHighlights;
import me.teakivy.teakstweaks.packs.xpmanagement.XPManagement;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public enum Pack {
    AFK_DISPLAY("afk-display", new AFKDisplay()),
    ALWAYS_ACTIVE_BEACONS("always-active-beacons", new AlwaysActiveBeacons()),
    ALWAYS_DROP("always-drop", new AlwaysDrop()),
    ANTI_CREEPER_GRIEF("anti-creeper-grief", new AntiCreeperGrief()),
    ANTI_ENDERMAN_GRIEF("anti-enderman-grief", new AntiEndermanGrief()),
    ANTI_GHAST_GRIEF("anti-ghast-grief", new AntiGhastGrief()),
    ARMORED_ELYTRA("armored-elytra", new ArmoredElytra()),
    BACK("back", new Back()),
    BAT_MEMBRANES("bat-membranes", new BatMembranes()),
    CAULDRON_CONCRETE("cauldron-concrete", new CauldronConcrete()),
    CAULDRON_MUD("cauldron-mud", new CauldronMud()),
    CHAT_COLORS("chat-colors", new ChatColors()),
    CHUNK_LOADERS("chunk-loaders", new ChunkLoaders()),
    CLASSIC_FISHING_LOOT("classic-fishing-loot", new ClassicFishingLoot()),
    COLLECTIBLE_BUDDING_AMETHYST("collectible-budding-amethyst", new CollectibleBuddingAmethyst()),
    CONFETTI_CREEPERS("confetti-creepers", new ConfettiCreepers()),
    COORDS_HUD("coords-hud", new CoordsHud()),
    COUNT_MOB_DEATHS("count-mob-deaths", new CountMobDeaths()),
    CUSTOM_NETHER_PORTALS("custom-nether-portals", new CustomNetherPortals()),
    DIRT_TO_GRASS("dirt-to-grass", new DirtToGrass()),
    DISPOSAL("disposal", new Disposal()),
    DOUBLE_SHULKER_SHELLS("double-shulker-shells", new DoubleShulkerShells()),
    DRAGON_DROPS("dragon-drops", new DragonDrops()),
    DURABILITY_PING("durability-ping", new DurabilityPing()),
    ELEVATORS("elevators", new Elevators()),
    FAST_LEAF_DECAY("fast-leaf-decay", new FastLeafDecay()),
    FIXED_ITEM_FRAMES("fixed-item-frames", new FixedItemFrames()),
    GRAVES("graves", new Graves()),
    HOMES("homes", new Homes()),
    HUSK_DROPS_SAND("husk-drops-sand", new HuskDropsSand()),
    INSTA_MINE("insta-mine", new InstaMine()),
    INVISIBLE_ITEM_FRAMES("invisible-item-frames", new InvisibleItemFrames()),
    ITEM_AVERAGES("item-averages", new ItemAverages()),
    KEEP_SMALL("keep-small", new KeepSmall()),
    KILL_BOATS("kill-boats", new KillBoats()),
    LARGER_PHANTOMS("larger-phantoms", new LargerPhantoms()),
    LECTERN_RESET("lectern-reset", new LecternReset()),
    MINI_BLOCKS("mini-blocks", new MiniBlocks()),
    MORE_MOB_HEADS("more-mob-heads", new MoreMobHeads()),
    MUSIC_DISC_ENGRAVER("music-disc-engraver", new MusicDiscEngraver()),
    NETHER_PORTAL_COORDS("nether-portal-coords", new NetherPortalCoords()),
    PAINTING_PICKER("painting-picker", new PaintingPicker()),
    PLAYER_HEAD_DROPS("player-head-drops", new PlayerHeadDrops()),
    QUICK_COMMANDS("quick-commands", new QuickCommands()),
    REAL_TIME_CLOCK("real-time-clock", new RealTimeClock()),
    ROTATION_WRENCH("rotation-wrench", new RotationWrench()),
    SAWMILL("sawmill", new Sawmill()),
    SILENCE_MOBS("silence-mobs", new SilenceMobs()),
    SLIME_CREAM("slime-cream", new SlimeCream()),
    SPAWN("spawn", new Spawn()),
    SPAWNING_SPHERES("spawning-spheres", new SpawningSpheres()),
    SPECTATOR_ALTS("spectator-alts", new SpectatorAlts()),
    SPECTATOR_CONDUIT_POWER("spectator-conduit-power", new SpectatorConduitPower()),
    SPECTATOR_NIGHT_VISION("spectator-night-vision", new SpectatorNightVision()),
    STAIR_CHAIRS("stair-chairs", new StairChairs()),
    SUDOKU("sudoku", new Sudoku()),
    THUNDER_SHRINE("thunder-shrine", new ThunderShrine()),
    TPA("tpa", new TPA()),
    TRACK_RAW_STATISTICS("track-raw-statistics", new TrackRawStatistics()),
    TRACK_STATISTICS("track-statistics", new TrackStatistics()),
    TRANSFERABLE_PETS("transferable-pets", new TransferablePets()),
    UNLOCK_ALL_RECIPES("unlock-all-recipes", new UnlockAllRecipes()),
    UNSTICKY_PISTONS("unsticky-pistons", new UnstickyPistons()),
    VILLAGER_DEATH_MESSAGES("villager-death-messages", new VillagerDeathMessages()),
    WANDERING_TRADER_ANNOUNCEMENTS("wandering-trader-announcements", new WanderingTraderAnnouncements()),
    WANDERING_TRADES("wandering-trades", new WanderingTrades()),
    WORKSTATION_HIGHLIGHTS("workstation-highlights", new WorkstationHighlights()),
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
