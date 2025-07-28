package me.teakivy.teakstweaks.utils.register;

import java.util.ArrayList;
import java.util.List;

public class Register {

    public static void registerAll() {
        registerPacks();
        registerCraftingTweaks();
    }

    public static void registerPacks() {
        for (Pack pack : Pack.values()) {
            pack.register();
        }
    }

    public static List<Pack> getEnabledPacks() {
        List<Pack> enabled = new ArrayList<>();
        for (Pack pack : Pack.values()) {
            if (pack.isEnabled()) {
                enabled.add(pack);
            }
        }
        return enabled;
    }

    public static void registerCraftingTweaks() {
        for (CraftingTweak craftingTweak : CraftingTweak.values()) {
            craftingTweak.register();
        }
    }

    public static List<CraftingTweak> getEnabledCraftingTweaks() {
        List<CraftingTweak> enabled = new ArrayList<>();
        for (CraftingTweak craftingTweak : CraftingTweak.values()) {
            if (craftingTweak.isEnabled()) {
                enabled.add(craftingTweak);
            }
        }
        return enabled;
    }
}
