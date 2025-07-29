package me.teakivy.teakstweaks.utils.register;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.craftingtweaks.recipes.*;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;

public enum TTCraftingTweak {
    BACK_TO_BLOCKS("back-to-blocks", BackToBlocks.class),
    BLACKSTONE_COBBLESTONE("blackstone-cobblestone", BlackstoneCobblestone.class),
    CHARCOAL_TO_BLACK_DYE("charcoal-to-black-dye", CharcoalToBlackDye.class),
    CHEAPER_ARMOR_TRIM_TEMPLATES("cheaper-armor-trim-templates", CheaperArmorTrimTemplates.class),
    CHEAPER_RESIN_BLOCKS("cheaper-resin-blocks", CheaperResinBlocks.class),
    COAL_TO_BLACK_DYE("coal-to-black-dye", CoalToBlackDye.class),
    COPPER_POWERED_RAILS("copper-powered-rails", CopperPoweredRails.class),
    CRAFTABLE_BLACKSTONE("craftable-blackstone", CraftableBlackstone.class),
    CRAFTABLE_CHAINMAIL("craftable-chainmail", CraftableChainmail.class),
    CRAFTABLE_CORAL_BLOCKS_2X2("craftable-coral-blocks-2x2", CraftableCoralBlocks2x2.class),
    CRAFTABLE_CORAL_BLOCKS_3X3("craftable-coral-blocks-3x3", CraftableCoralBlocks3x3.class),
    CRAFTABLE_ENCHANTED_GOLDEN_APPLES("craftable-enchanted-golden-apples", CraftableEnchantedGoldenApples.class),
    CRAFTABLE_GILDED_BLACKSTONE("craftable-gilded-blackstone", CraftableGildedBlackstone.class),
    CRAFTABLE_GRAVEL("craftable-gravel", CraftableGravel.class),
    CRAFTABLE_HORSE_ARMOR("craftable-horse-armor", CraftableHorseArmor.class),
    CRAFTABLE_NAME_TAGS("craftable-name-tags", CraftableNameTags.class),
    CRAFTABLE_SADDLES("craftable-saddles", CraftableSaddles.class),
    CRAFTABLE_SMALL_DRIPLEAF("craftable-small-dripleaf", CraftableSmallDripleaf.class),
    CRAFTABLE_SPORE_BLOSSOMS("craftable-spore-blossoms", CraftableSporeBlossoms.class),
    DOUBLE_SLABS("double-slabs", DoubleSlabs.class),
    DROPPER_TO_DISPENSER("dropper-to-dispenser", DropperToDispenser.class),
    LOG_CHESTS("log-chests", LogChests.class),
    MORE_BARK("more-bark", MoreBark.class),
    MORE_BRICKS("more-bricks", MoreBricks.class),
    MORE_COPPER_TRAPDOORS("more-copper-trapdoors", MoreCopperTrapdoors.class),
    MORE_IRON_TRAPDOORS("more-iron-trapdoors", MoreIronTrapdoors.class),
    MORE_PACKED_MUD("more-packed-mud", MorePackedMud.class),
    MORE_SHERDS("more-sherds", MoreSherds.class),
    MORE_STAIRS("more-stairs", MoreStairs.class),
    MORE_TRAPDOORS("more-trapdoors", MoreTrapdoors.class),
    POWDER_TO_GLASS("powder-to-glass", PowderToGlass.class),
    ROTTEN_FLESH_TO_LEATHER("rotten-flesh-to-leather", RottenFleshToLeather.class),
    SANDSTONE_DYEING("sandstone-dyeing", SandstoneDyeing.class),
    SMELTABLE_RAW_ORE_BLOCKS("smeltable-raw-ore-blocks", SmeltableRawOreBlocks.class),
    STRAIGHT_TO_SHAPELESS("straight-to-shapeless", StraightToShapeless.class),
    UNPACKABLE_ICE("unpackable-ice", UnpackableIce.class),
    UNPACKABLE_NETHER_WART("unpackable-nether-wart", UnpackableNetherWart.class),
    UNPACKABLE_QUARTZ_BLOCKS("unpackable-quartz-blocks", UnpackableQuartzBlocks.class),
    UNPACKABLE_WOOL("unpackable-wool", UnpackableWool.class),
    UNIVERSAL_DYEING("universal-dyeing", UniversalDyeing.class);

    private final String key;
    private final Class<? extends AbstractCraftingTweak> clazz;
    private AbstractCraftingTweak instance;

    TTCraftingTweak(String key, Class<? extends AbstractCraftingTweak> clazz) {
        this.key = key;
        this.clazz = clazz;
    }

    public String getKey() {
        return key;
    }

    public AbstractCraftingTweak getCraftingTweak() {
        if (instance == null) instantiate();
        return instance;
    }

    private void instantiate() {
        try {
            Constructor<? extends AbstractCraftingTweak> constructor = clazz.getConstructor();
            this.instance = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate crafting tweak: " + name(), e);
        }
    }

    public static TTCraftingTweak fromKey(String key) {
        for (TTCraftingTweak tweak : values()) {
            if (tweak.getKey().equalsIgnoreCase(key)) {
                return tweak;
            }
        }
        return null;
    }

    public void register() {
        getCraftingTweak().register();
    }

    public boolean isEnabled() {
        return Config.isCraftingTweakEnabled(key) || Config.isDevMode();
    }

    public ItemStack getItem() {
        return getCraftingTweak().getItem();
    }

    @Override
    public String toString() {
        return key;
    }
}
