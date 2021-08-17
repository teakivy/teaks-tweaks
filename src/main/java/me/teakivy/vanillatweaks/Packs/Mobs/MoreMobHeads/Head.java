package me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.DataManager.DataManager;

public class Head {
    static Main main = Main.getPlugin(Main.class);
    static DataManager data = main.data;

    public static double[] getChance(String path) {
        double[] chances = {data.getConfig().getDouble("mob-heads." + path + ".chance"), data.getConfig().getDouble("mob-heads." + path + ".looting-bonus")};

        return chances;
    }

}
