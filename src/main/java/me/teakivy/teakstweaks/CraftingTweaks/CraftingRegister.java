package me.teakivy.teakstweaks.CraftingTweaks;

import me.teakivy.teakstweaks.CraftingTweaks.Recipes.*;
import me.teakivy.teakstweaks.Main;

public class CraftingRegister {

    static Main main = Main.getPlugin(Main.class);

    public static void registerAll() {
        for (String craftingTweak : main.getConfig().getConfigurationSection("crafting-tweaks").getKeys(false)) {
            if (main.getConfig().getBoolean("packs." + craftingTweak + ".enabled")) {
                registerTweak(craftingTweak);
            }
        }
    }

    public static void registerTweak(String tweak) {
        switch (tweak) {
            case "back-to-blocks" -> new BackToBlocks().init();
            case "double-slabs" -> new DoubleSlabs().init();
            case "dropper-to-dispenser" -> new DropperToDispenser().init();
            case "rotten-flesh-to-leather" -> new RottenFleshToLeather().init();
            case "charcoal-to-black-dye" -> new CharcoalToBlackDye().init();
            case "coal-to-black-dye" -> new CoalToBlackDye().init();
            case "sandstone-dyeing" -> new SandstoneDyeing().init();
            case "universal-dyeing" -> new UniversalDyeing().init();
            case "straight-to-shapeless" -> new StraightToShapeless().init();
            case "blackstone-cobblestone" -> new BlackstoneCobblestone().init();
            case "powder-to-glass" -> new PowderToGlass().init();
            case "more-trapdoors" -> new MoreTrapdoors().init();
            case "more-bark" -> new MoreBark().init();
            case "more-stairs" -> new MoreStairs().init();
            case "more-bricks" -> new MoreBricks().init();
            case "craftable-gravel" -> new CraftableGravel().init();
            case "craftable-horse-armor" -> new CraftableHorseArmor().init();
            case "craftable-coral-blocks-2x2" -> new CraftableCoral2x2().init();
            case "craftable-coral-blocks-3x3" -> new CraftableCoral3x3().init();
            case "craftable-enchanted-golden-apples" -> new CraftableEnchantedGoldenApples().init();
            case "craftable-name-tags" -> new CraftableNameTags().init();
            case "craftable-bundles-rabbit-hide" -> new CraftableBundlesRabbitHide().init();
            case "craftable-bundles-leather" -> new CraftableBundlesLeather().init();
            case "craftable-blackstone" -> new CraftableBlackstone().init();
            case "craftable-gilded-blackstone" -> new CraftableGildedBlackstone().init();
            case "craftable-spore-blossoms" -> new CraftableSporeBlossoms().init();
            case "craftable-small-dripleaf" -> new CraftableSmallDripleaves().init();
            case "craftable-sculk-sensors" -> new CraftableSculkSensors().init();
            case "unpackable-ice" -> new UnpackableIce().init();
            case "unpackable-nether-wart" -> new UnpackableNetherWart().init();
            case "unpackable-wool" -> new UnpackableWool().init();
            case "unpackable-quartz-blocks" -> new UnpackableQuartz().init();
            case "log-chests" -> new LogChests().init();
            case "smeltable-raw-ore-blocks" -> new SmeltableRawOreBlocks().init();
        }
    }

}
