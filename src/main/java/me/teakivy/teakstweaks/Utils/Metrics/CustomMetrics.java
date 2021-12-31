package me.teakivy.teakstweaks.Utils.Metrics;

import me.teakivy.teakstweaks.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CustomMetrics {

    static Main main = Main.getPlugin(Main.class);

    public static void registerCustomMetrics(Metrics metrics) {
        registerPackMetrics(metrics);
        registerCraftingTweaksMetrics(metrics);
        registerCommandMetrics(metrics);
    }

    public static void registerPackMetrics(Metrics metrics) {
        metrics.addCustomChart(new Metrics.AdvancedPie("packs", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                Map<String, Integer> valueMap = new HashMap<>();
                valueMap.put("Keep Small", getPack("keep-small"));
                valueMap.put("Chat Colors", getPack("chat-colors"));
                valueMap.put("Editable Signs", getPack("editable-signs"));
                valueMap.put("Lectern Reset", getPack("lectern-reset"));
                valueMap.put("Sudoku", getPack("sudoku"));
                valueMap.put("Stair Chairs", getPack("stair-chairs"));
                valueMap.put("Unsticky Pistons", getPack("unsticky-pistons"));
                valueMap.put("Slime Cream", getPack("slime-cream"));
                valueMap.put("Invisible Item Frames", getPack("invisible-item-frames"));
                valueMap.put("Better Foliage", getPack("better-foliage"));

                valueMap.put("Unlock All Recipes", getPack("unlock-all-recipes"));
                valueMap.put("Fast Leaf Decay", getPack("fast-leaf-decay"));
                valueMap.put("AFK Display", getPack("afk-display"));
                valueMap.put("Multiplayer Sleep", getPack("multiplayer-sleep"));
                valueMap.put("Graves", getPack("graves"));
                valueMap.put("Custom Nether Portals", getPack("custom-nether-portals"));
                valueMap.put("Durability Ping", getPack("durability-ping"));
                valueMap.put("Cauldron Concrete", getPack("cauldron-concrete"));
                valueMap.put("Track Raw Statistics", getPack("track-raw-statistics"));
                valueMap.put("Track Statistics", getPack("track-statistics"));
                valueMap.put("Nether Portal Coords", getPack("nether-portal-coords"));
                valueMap.put("Coords HUD", getPack("coords-hud"));
                valueMap.put("Classic Fishing Loop", getPack("classic-fishing-loop"));
                valueMap.put("Real Time Clock", getPack("real-time-clock"));
                valueMap.put("Workstation Highlights", getPack("workstation-highlights"));
                valueMap.put("Pillager Tools", getPack("pillager-tools"));

                valueMap.put("Rotation Wrench", getPack("rotation-wrench"));
                valueMap.put("Armored Elytra", getPack("armored-elytra"));
                valueMap.put("Player Head Drops", getPack("player-head-drops"));

                valueMap.put("Anti Creeper Grief", getPack("anti-creeper-grief"));
                valueMap.put("Anti Enderman Grief", getPack("anti-enderman-grief"));
                valueMap.put("Anti Ghast Grief", getPack("anti-ghast-grief"));
                valueMap.put("Double Shulker Shells", getPack("double-shulker-shells"));
                valueMap.put("Dragon Drops", getPack("dragon-drops"));
                valueMap.put("Larger Phantoms", getPack("larger-phantoms"));
                valueMap.put("More Mob Heads", getPack("more-mob-heads"));
                valueMap.put("Silence Mobs", getPack("silence-mobs"));
                valueMap.put("Count Mob Deaths", getPack("count-mob-deaths"));
                valueMap.put("Villager Death Messages", getPack("villager-death-messages"));

                valueMap.put("Spawn", getPack("spawn"));
                valueMap.put("Homes", getPack("homes"));
                valueMap.put("TPA", getPack("tpa"));
                valueMap.put("Back", getPack("back"));

                valueMap.put("Custom Villager Shops", getPack("custom-villager-shops"));
                valueMap.put("Spawning Spheres", getPack("spawning-spheres"));
                valueMap.put("Spectator Night Vision", getPack("spectator-night-vision"));
                valueMap.put("Spectator Conduit Power", getPack("spectator-conduit-power"));
                valueMap.put("Item Averages", getPack("item-averages"));
                valueMap.put("Kill Boats", getPack("kill-boats"));

                valueMap.put("Treasure Gems", getPack("treasure-gems"));
                valueMap.put("Wandering Trades", getPack("wandering-trades"));
                valueMap.put("Tag", getPack("tag"));
                valueMap.put("Thunder Shrine", getPack("thunder-shrine"));

                valueMap.put("XP Management", getPack("xp-management"));
                valueMap.put("Chunk Loaders", getPack("chunk-loaders"));
                valueMap.put("Confetti Creepers", getPack("confetti-creepers"));
                valueMap.put("Elevators", getPack("elevators"));
                return valueMap;
            }
        }));
    }

    public static int getPack(String pack) {
        if (main.getConfig().getBoolean("packs." + pack + ".enabled")) return 1;
        return 0;
    }

    public static void registerCraftingTweaksMetrics(Metrics metrics) {
        metrics.addCustomChart(new Metrics.AdvancedPie("crafting_tweaks", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                Map<String, Integer> valueMap = new HashMap<>();
                valueMap.put("Back To Blocks", getCraftingTweak("back-to-blocks"));
                valueMap.put("Double Slabs", getCraftingTweak("double-slabs"));
                valueMap.put("Dropper To Dispenser", getCraftingTweak("dropper-to-dispenser"));
                valueMap.put("Rotten Flesh To Leather", getCraftingTweak("rotten-flesh-to-leather"));
                valueMap.put("Charcoal To Black Dye", getCraftingTweak("charcoal-to-black-dye"));
                valueMap.put("Coal To Black Dye", getCraftingTweak("coal-to-black-dye"));
                valueMap.put("Sandstone Dyeing", getCraftingTweak("sandstone-dyeing"));
                valueMap.put("Universal Dyeing", getCraftingTweak("universal-dyeing"));
                valueMap.put("Straight To Shapeless", getCraftingTweak("straight-to-shapeless"));
                valueMap.put("Blackstone Cobblestone", getCraftingTweak("blackstone-cobblestone"));
                valueMap.put("Powder To Glass", getCraftingTweak("powder-to-glass"));
                valueMap.put("More Trapdoors", getCraftingTweak("more-trapdoors"));
                valueMap.put("More Bark", getCraftingTweak("more-bark"));
                valueMap.put("More Stairs", getCraftingTweak("more-stairs"));
                valueMap.put("More Bricks", getCraftingTweak("more-bricks"));
                valueMap.put("Craftable Gravel", getCraftingTweak("craftable-gravel"));
                valueMap.put("Craftable Horse Armor", getCraftingTweak("craftable-horse-armor"));
                valueMap.put("Craftable Coral Blocks 2x2", getCraftingTweak("craftable-coral-blocks-2x2"));
                valueMap.put("Craftable Coral Blocks 3x3", getCraftingTweak("craftable-coral-blocks-3x3"));
                valueMap.put("Craftable Enchanted Golden Apples", getCraftingTweak("craftable-enchanted-golden-apples"));
                valueMap.put("Craftable Name Tags", getCraftingTweak("craftable-name-tags"));
                valueMap.put("Craftable Bundles Rabbit Hide", getCraftingTweak("craftable-bundles-rabbit-hide"));
                valueMap.put("Craftable Bundles Leather", getCraftingTweak("craftable-bundles-leather"));
                valueMap.put("Craftable Blackstone", getCraftingTweak("craftable-blackstone"));
                valueMap.put("Craftable Gilded Blackstone", getCraftingTweak("craftable-gilded-blackstone"));
                valueMap.put("Craftable Spore Blossoms", getCraftingTweak("craftable-spore-blossoms"));
                valueMap.put("Craftable Small Dripleaf", getCraftingTweak("craftable-small-dripleaf"));
                valueMap.put("Craftable Sculk Sensors", getCraftingTweak("craftable-sculk-sensors"));
                valueMap.put("Unpackable Ice", getCraftingTweak("unpackable-ice"));
                valueMap.put("Unpackable Nether Wart", getCraftingTweak("unpackable-nether-wart"));
                valueMap.put("Unpackable Quartz Blocks", getCraftingTweak("unpackable-quartz-blocks"));
                valueMap.put("Unpackable Wool", getCraftingTweak("unpackable-wool"));
                valueMap.put("Log Chests", getCraftingTweak("log-chests"));
                valueMap.put("Smeltable Raw Ore Blocks", getCraftingTweak("smeltable-raw-ore-blocks"));
                return valueMap;
            }
        }));
    }

    public static int getCraftingTweak(String tweak) {
        if (main.getConfig().getBoolean("crafting-tweaks." + tweak + ".enabled")) return 1;
        return 0;
    }

    public static void registerCommandMetrics(Metrics metrics) {
        metrics.addCustomChart(new Metrics.AdvancedPie("commands", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                Map<String, Integer> valueMap = new HashMap<>();
                valueMap.put("Test", getCommand("test"));
                valueMap.put("Portal", getCommand("portal"));
                valueMap.put("Coords HUD", getCommand("coordshud"));
                valueMap.put("Night Vision", getCommand("nightvision"));
                valueMap.put("Conduit Power", getCommand("conduitpower"));
                valueMap.put("Kill Boats", getCommand("killboats"));
                valueMap.put("Real Time", getCommand("realtimeclock"));
                valueMap.put("Spawn", getCommand("spawn"));
                valueMap.put("TPA", getCommand("tpa"));
                valueMap.put("Home", getCommand("home"));
                valueMap.put("DuraPing", getCommand("durabilityping"));
                valueMap.put("Tag", getCommand("taggame"));
                valueMap.put("Back", getCommand("back"));
                valueMap.put("AFK", getCommand("afk"));
                valueMap.put("Shrine", getCommand("shrine"));
                valueMap.put("Workstation", getCommand("workstationhighlights"));
                valueMap.put("Set Home", getCommand("sethome"));
                valueMap.put("Item Averages", getCommand("itemaverages"));
                valueMap.put("Grave", getCommand("grave"));
                valueMap.put("Spawning Spheres", getCommand("spawningspheres"));
                valueMap.put("Gem", getCommand("gem"));
                valueMap.put("Sudoku", getCommand("sudoku"));
                return valueMap;
            }
        }));
    }

    public static int getCommand(String cmd) {
        if (main.getConfig().getBoolean("commands." + cmd + ".enabled")) return 1;
        return 0;
    }

}
