package me.teakivy.teakstweaks.utils.register;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.craftingtweaks.recipes.*;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.inventory.ItemStack;

public enum TTCraftingTweak {
    BACK_TO_BLOCKS("back-to-blocks", new BackToBlocks()),
    BLACKSTONE_COBBLESTONE("blackstone-cobblestone", new BlackstoneCobblestone()),
    CHARCOAL_TO_BLACK_DYE("charcoal-to-black-dye", new CharcoalToBlackDye()),
    CHEAPER_ARMOR_TRIM_TEMPLATES("cheaper-armor-trim-templates", new CheaperArmorTrimTemplates()),
    CHEAPER_RESIN_BLOCKS("cheaper-resin-blocks", new CheaperResinBlocks()),
    COAL_TO_BLACK_DYE("coal-to-black-dye", new CoalToBlackDye()),
    COPPER_POWERED_RAILS("copper-powered-rails", new CopperPoweredRails()),
    CRAFTABLE_BLACKSTONE("craftable-blackstone", new CraftableBlackstone()),
    CRAFTABLE_CHAINMAIL("craftable-chainmail", new CraftableChainmail()),
    CRAFTABLE_CORAL_BLOCKS_2X2("craftable-coral-blocks-2x2", new CraftableCoralBlocks2x2()),
    CRAFTABLE_CORAL_BLOCKS_3X3("craftable-coral-blocks-3x3", new CraftableCoralBlocks3x3()),
    CRAFTABLE_ENCHANTED_GOLDEN_APPLES("craftable-enchanted-golden-apples", new CraftableEnchantedGoldenApples()),
    CRAFTABLE_GILDED_BLACKSTONE("craftable-gilded-blackstone", new CraftableGildedBlackstone()),
    CRAFTABLE_GRAVEL("craftable-gravel", new CraftableGravel()),
    CRAFTABLE_HORSE_ARMOR("craftable-horse-armor", new CraftableHorseArmor()),
    CRAFTABLE_NAME_TAGS("craftable-name-tags", new CraftableNameTags()),
    CRAFTABLE_SADDLES("craftable-saddles", new CraftableSaddles()),
    CRAFTABLE_SMALL_DRIPLEAF("craftable-small-dripleaf", new CraftableSmallDripleaf()),
    CRAFTABLE_SPORE_BLOSSOMS("craftable-spore-blossoms", new CraftableSporeBlossoms()),
    DOUBLE_SLABS("double-slabs", new DoubleSlabs()),
    DROPPER_TO_DISPENSER("dropper-to-dispenser", new DropperToDispenser()),
    LOG_CHESTS("log-chests", new LogChests()),
    MORE_BARK("more-bark", new MoreBark()),
    MORE_BRICKS("more-bricks", new MoreBricks()),
    MORE_COPPER_TRAPDOORS("more-copper-trapdoors", new MoreCopperTrapdoors()),
    MORE_IRON_TRAPDOORS("more-iron-trapdoors", new MoreIronTrapdoors()),
    MORE_PACKED_MUD("more-packed-mud", new MorePackedMud()),
    MORE_SHERDS("more-sherds", new MoreSherds()),
    MORE_STAIRS("more-stairs", new MoreStairs()),
    MORE_TRAPDOORS("more-trapdoors", new MoreTrapdoors()),
    POWDER_TO_GLASS("powder-to-glass", new PowderToGlass()),
    ROTTEN_FLESH_TO_LEATHER("rotten-flesh-to-leather", new RottenFleshToLeather()),
    SANDSTONE_DYEING("sandstone-dyeing", new SandstoneDyeing()),
    SMELTABLE_RAW_ORE_BLOCKS("smeltable-raw-ore-blocks", new SmeltableRawOreBlocks()),
    STRAIGHT_TO_SHAPELESS("straight-to-shapeless", new StraightToShapeless()),
    UNPACKABLE_ICE("unpackable-ice", new UnpackableIce()),
    UNPACKABLE_NETHER_WART("unpackable-nether-wart", new UnpackableNetherWart()),
    UNPACKABLE_QUARTZ_BLOCKS("unpackable-quartz-blocks", new UnpackableQuartzBlocks()),
    UNPACKABLE_WOOL("unpackable-wool", new UnpackableWool()),
    UNIVERSAL_DYEING("universal-dyeing", new UniversalDyeing());

    private final String key;
    private final AbstractCraftingTweak craftingTweak;

    TTCraftingTweak(String key, AbstractCraftingTweak craftingTweak) {
        this.key = key;
        this.craftingTweak = craftingTweak;
    }

    public String getKey() {
        return key;
    }

    public AbstractCraftingTweak getCraftingTweak() {
        return craftingTweak;
    }

    public static TTCraftingTweak fromKey(String key) {
        for (TTCraftingTweak craftingTweak : values()) {
            if (craftingTweak.getKey().equalsIgnoreCase(key)) {
                return craftingTweak;
            }
        }
        return null;
    }

    public void register() {
        craftingTweak.register();
    }

    public boolean isEnabled() {
        return Config.isCraftingTweakEnabled(key) || Config.isDevMode();
    }

    public ItemStack getItem() {
        return craftingTweak.getItem();
    }

    @Override
    public String toString() {
        return key;
    }
}
