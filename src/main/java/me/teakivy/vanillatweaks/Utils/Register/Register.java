package me.teakivy.vanillatweaks.Utils.Register;

import me.teakivy.vanillatweaks.Main;

public class Register {

    static Main main = Main.getPlugin(Main.class);

    public static void unregister(String pack) {

        if (pack.equalsIgnoreCase("tag")) {
            main.tagListener.uninstall();
        }

    }

}
