package me.teakivy.teakstweaks.utils.register;

import java.util.ArrayList;
import java.util.List;

public class Register {

    public static void registerAll() {
        registerCraftingTweaks();
        registerPacks();
        registerCommands();
    }

    public static void registerPacks() {
        for (TTPack pack : TTPack.values()) {
            pack.instantiate();
            pack.register();
        }
    }

    public static List<TTPack> getEnabledPacks() {
        List<TTPack> enabled = new ArrayList<>();
        for (TTPack pack : TTPack.values()) {
            if (pack.isEnabled()) {
                enabled.add(pack);
            }
        }
        return enabled;
    }

    public static void registerCraftingTweaks() {
        for (TTCraftingTweak craftingTweak : TTCraftingTweak.values()) {
            craftingTweak.register();
        }
    }

    public static List<TTCraftingTweak> getEnabledCraftingTweaks() {
        List<TTCraftingTweak> enabled = new ArrayList<>();
        for (TTCraftingTweak craftingTweak : TTCraftingTweak.values()) {
            if (craftingTweak.isEnabled()) {
                enabled.add(craftingTweak);
            }
        }
        return enabled;
    }

    public static void registerCommands() {
        for (TTCommand command : TTCommand.values()) {
            command.register();
        }
    }

    public static List<TTCommand> getEnabledCommands() {
        List<TTCommand> enabled = new ArrayList<>();
        for (TTCommand command : TTCommand.values()) {
            if (command.isEnabled()) {
                enabled.add(command);
            }
        }
        return enabled;
    }
}
