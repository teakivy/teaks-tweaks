package me.teakivy.teakstweaks.craftingtweaks;

import me.teakivy.teakstweaks.craftingtweaks.recipes.*;

public class CraftingRegister {

    public static void registerAll() {
        try {
            new BackToBlocks().register();
            new DoubleSlabs().register();
            new DropperToDispenser().register();
            new RottenFleshToLeather().register();
            new CharcoalToBlackDye().register();
            new CoalToBlackDye().register();
            new SandstoneDyeing().register();
            new UniversalDyeing().register();
            new StraightToShapeless().register();
            new BlackstoneCobblestone().register();
            new PowderToGlass().register();
            new MoreTrapdoors().register();
            new MoreBark().register();
            new MoreStairs().register();
            new MoreBricks().register();
            new CraftableGravel().register();
            new CraftableHorseArmor().register();
            new CraftableCoral2x2().register();
            new CraftableCoral3x3().register();
            new CraftableEnchantedGoldenApples().register();
            new CraftableNameTags().register();
            new CraftableBundlesRabbitHide().register();
            new CraftableBundlesLeather().register();
            new CraftableBlackstone().register();
            new CraftableGildedBlackstone().register();
            new CraftableSporeBlossoms().register();
            new CraftableSmallDripleaves().register();
            new CraftableSculkSensors().register();
            new UnpackableIce().register();
            new UnpackableNetherWart().register();
            new UnpackableWool().register();
            new UnpackableQuartz().register();
            new LogChests().register();
            new SmeltableRawOreBlocks().register();
            new MorePackedMud().register();
        } catch (Exception e) {
        }
    }

}
