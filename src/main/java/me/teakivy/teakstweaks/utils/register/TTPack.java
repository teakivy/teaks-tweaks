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

import java.lang.reflect.Constructor;

public enum TTPack {
    AFK_DISPLAY("afk-display", AFKDisplay.class),
    ALWAYS_ACTIVE_BEACONS("always-active-beacons", AlwaysActiveBeacons.class),
    ALWAYS_DROP("always-drop", AlwaysDrop.class),
    ANTI_CREEPER_GRIEF("anti-creeper-grief", AntiCreeperGrief.class),
    ANTI_ENDERMAN_GRIEF("anti-enderman-grief", AntiEndermanGrief.class),
    ANTI_GHAST_GRIEF("anti-ghast-grief", AntiGhastGrief.class),
    ARMORED_ELYTRA("armored-elytra", ArmoredElytra.class),
    BACK("back", Back.class),
    BAT_MEMBRANES("bat-membranes", BatMembranes.class),
    CAULDRON_CONCRETE("cauldron-concrete", CauldronConcrete.class),
    CAULDRON_MUD("cauldron-mud", CauldronMud.class),
    CHAT_COLORS("chat-colors", ChatColors.class),
    CHUNK_LOADERS("chunk-loaders", ChunkLoaders.class),
    CLASSIC_FISHING_LOOT("classic-fishing-loot", ClassicFishingLoot.class),
    COLLECTIBLE_BUDDING_AMETHYST("collectible-budding-amethyst", CollectibleBuddingAmethyst.class),
    CONFETTI_CREEPERS("confetti-creepers", ConfettiCreepers.class),
    COORDS_HUD("coords-hud", CoordsHud.class),
    COUNT_MOB_DEATHS("count-mob-deaths", CountMobDeaths.class),
    CUSTOM_NETHER_PORTALS("custom-nether-portals", CustomNetherPortals.class),
    DIRT_TO_GRASS("dirt-to-grass", DirtToGrass.class),
    DISPOSAL("disposal", Disposal.class),
    DOUBLE_SHULKER_SHELLS("double-shulker-shells", DoubleShulkerShells.class),
    DRAGON_DROPS("dragon-drops", DragonDrops.class),
    DURABILITY_PING("durability-ping", DurabilityPing.class),
    ELEVATORS("elevators", Elevators.class),
    FAST_LEAF_DECAY("fast-leaf-decay", FastLeafDecay.class),
    FIXED_ITEM_FRAMES("fixed-item-frames", FixedItemFrames.class),
    GRAVES("graves", Graves.class),
    HOMES("homes", Homes.class),
    HUSK_DROPS_SAND("husk-drops-sand", HuskDropsSand.class),
    INSTA_MINE("insta-mine", InstaMine.class),
    INVISIBLE_ITEM_FRAMES("invisible-item-frames", InvisibleItemFrames.class),
    ITEM_AVERAGES("item-averages", ItemAverages.class),
    KEEP_SMALL("keep-small", KeepSmall.class),
    KILL_BOATS("kill-boats", KillBoats.class),
    LARGER_PHANTOMS("larger-phantoms", LargerPhantoms.class),
    LECTERN_RESET("lectern-reset", LecternReset.class),
    MINI_BLOCKS("mini-blocks", MiniBlocks.class),
    MORE_MOB_HEADS("more-mob-heads", MoreMobHeads.class),
    MUSIC_DISC_ENGRAVER("music-disc-engraver", MusicDiscEngraver.class),
    NETHER_PORTAL_COORDS("nether-portal-coords", NetherPortalCoords.class),
    PAINTING_PICKER("painting-picker", PaintingPicker.class),
    PLAYER_HEAD_DROPS("player-head-drops", PlayerHeadDrops.class),
    QUICK_COMMANDS("quick-commands", QuickCommands.class),
    REAL_TIME_CLOCK("real-time-clock", RealTimeClock.class),
    ROTATION_WRENCH("rotation-wrench", RotationWrench.class),
    SAWMILL("sawmill", Sawmill.class),
    SILENCE_MOBS("silence-mobs", SilenceMobs.class),
    SLIME_CREAM("slime-cream", SlimeCream.class),
    SPAWN("spawn", Spawn.class),
    SPAWNING_SPHERES("spawning-spheres", SpawningSpheres.class),
    SPECTATOR_ALTS("spectator-alts", SpectatorAlts.class),
    SPECTATOR_CONDUIT_POWER("spectator-conduit-power", SpectatorConduitPower.class),
    SPECTATOR_NIGHT_VISION("spectator-night-vision", SpectatorNightVision.class),
    STAIR_CHAIRS("stair-chairs", StairChairs.class),
    SUDOKU("sudoku", Sudoku.class),
    THUNDER_SHRINE("thunder-shrine", ThunderShrine.class),
    TPA("tpa", TPA.class),
    TRACK_RAW_STATISTICS("track-raw-statistics", TrackRawStatistics.class),
    TRACK_STATISTICS("track-statistics", TrackStatistics.class),
    TRANSFERABLE_PETS("transferable-pets", TransferablePets.class),
    UNLOCK_ALL_RECIPES("unlock-all-recipes", UnlockAllRecipes.class),
    UNSTICKY_PISTONS("unsticky-pistons", UnstickyPistons.class),
    VILLAGER_DEATH_MESSAGES("villager-death-messages", VillagerDeathMessages.class),
    WANDERING_TRADER_ANNOUNCEMENTS("wandering-trader-announcements", WanderingTraderAnnouncements.class),
    WANDERING_TRADES("wandering-trades", WanderingTrades.class),
    WORKSTATION_HIGHLIGHTS("workstation-highlights", WorkstationHighlights.class),
    XP_MANAGEMENT("xp-management", XPManagement.class);

    private final String key;
    private final Class<? extends BasePack> clazz;
    private BasePack pack;

    TTPack(String key, Class<? extends BasePack> clazz) {
        this.key = key;
        this.clazz = clazz;
    }

    public void instantiate() {
        try {
            Constructor<? extends BasePack> constructor = clazz.getConstructor();
            this.pack = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate pack: " + name(), e);
        }
    }

    public String getKey() {
        return key;
    }

    public BasePack getPack() {
        return pack;
    }

    public static TTPack fromKey(String key) {
        for (TTPack pack : values()) {
            if (pack.getKey().equalsIgnoreCase(key)) {
                return pack;
            }
        }
        return null;
    }

    public ConfigurationSection getConfig() {
        return getPack().getConfig();
    }

    public void register() {
        if (getPack().isRegistered()) return;
        if (!isEnabled() && !Config.isDevMode()) return;
        getPack().init();
    }

    public boolean isEnabled() {
        return getConfig().getBoolean("enabled", false);
    }

    public ItemStack getItem() {
        return getPack().getItem();
    }

    @Override
    public String toString() {
        return key;
    }
}

