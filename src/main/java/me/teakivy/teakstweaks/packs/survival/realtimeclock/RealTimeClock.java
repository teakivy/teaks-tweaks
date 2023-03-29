package me.teakivy.teakstweaks.packs.survival.realtimeclock;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;

public class RealTimeClock extends BasePack {

        public RealTimeClock() {
            super("Real Time Clock", "real-time-clock", PackType.SURVIVAL, Material.CLOCK, "Adds a command that allows you to view how long a world has been running in real time minutes and hours.", "Command: /rtc");
        }
}
