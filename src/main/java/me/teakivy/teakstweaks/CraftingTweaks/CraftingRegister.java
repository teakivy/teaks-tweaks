package me.teakivy.teakstweaks.CraftingTweaks;

import me.teakivy.teakstweaks.CraftingTweaks.Recipes.*;
import me.teakivy.teakstweaks.Main;

public class CraftingRegister {

    static Main main = Main.getPlugin(Main.class);

    public static void register() {
        if (main.getConfig().getBoolean("crafting-tweaks.back-to-blocks.enabled"))
            BackToBlocks.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.double-slabs.enabled"))
            DoubleSlabs.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.dropper-to-dispenser.enabled"))
            DropperToDispenser.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.rotten-flesh-to-leather.enabled"))
            RottenFleshToLeather.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.charcoal-to-black-dye.enabled"))
            CharcoalToBlackDye.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.coal-to-black-dye.enabled"))
            CoalToBlackDye.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.sandstone-dyeing.enabled"))
            SandstoneDyeing.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.universal-dyeing.enabled"))
            UniversalDyeing.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.straight-to-shapeless.enabled"))
            StraightToShapeless.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.blackstone-cobblestone.enabled"))
            BlackstoneCobblestone.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.powder-to-glass.enabled"))
            PowderToGlass.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.more-trapdoors.enabled"))
            MoreTrapdoors.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.more-bark.enabled"))
            MoreBark.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.more-stairs.enabled"))
            MoreStairs.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.more-bricks.enabled"))
            MoreBricks.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-gravel.enabled"))
            CraftableGravel.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-horse-armor.enabled"))
            CraftableHorseArmor.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-coral-blocks-2x2.enabled"))
            CraftableCoral2x2.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-coral-blocks-3x3.enabled"))
            CraftableCoral3x3.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-enchanted-golden-apples.enabled"))
            CraftableEnchantedGoldenApples.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-name-tags.enabled"))
            CraftableNameTags.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-bundles-rabbit-hide.enabled"))
            CraftableBundlesRabbitHide.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-bundles-leather.enabled"))
            CraftableBundlesLeather.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.craftable-blackstone.enabled"))
            CraftableBlackstone.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.unpackable-ice.enabled"))
            UnpackableIce.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.unpackable-nether-wart.enabled"))
            UnpackableNetherWart.registerRecipes();
        if (main.getConfig().getBoolean("crafting-tweaks.unpackable-wool.enabled"))
            UnpackableWool.registerRecipes();

    }

}
