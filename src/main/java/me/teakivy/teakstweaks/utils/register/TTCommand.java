package me.teakivy.teakstweaks.utils.register;

import me.teakivy.teakstweaks.commands.*;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;

import java.lang.reflect.Constructor;

public enum TTCommand {
    AFK("afk", AFKCommand.class, TTPack.AFK_DISPLAY),
    ALTS("alts", AltsCommand.class, TTPack.SPECTATOR_ALTS),
    BACK("back", BackCommand.class, TTPack.BACK),
    CONDUITPOWER("conduitpower", ConduitPowerCommand.class, TTPack.SPECTATOR_CONDUIT_POWER),
    COORDSHUD("coordshud", CoordsHudCommand.class, TTPack.COORDS_HUD),
    DELETEHOME("deletehome", DeleteHomeCommand.class, TTPack.HOMES),
    DISPOSAL("disposal", DisposalCommand.class, TTPack.DISPOSAL),
    DURABILITYPING("durabilityping", DurabilityPingCommand.class, TTPack.DURABILITY_PING),
    GRAVE("grave", GraveCommand.class, TTPack.GRAVES),
    HOME("home", HomeCommand.class, TTPack.HOMES),
    ITEMAVERAGES("itemaverages", ItemAveragesCommand.class, TTPack.ITEM_AVERAGES),
    KILLBOATS("killboats", KillBoatsCommand.class, TTPack.KILL_BOATS),
    NIGHTVISION("nightvision", NightVisionCommand.class, TTPack.SPECTATOR_NIGHT_VISION),
    PORTAL("portal", PortalCommand.class, TTPack.NETHER_PORTAL_COORDS),
    REALTIMECLOCK("realtimeclock", RealTimeClockCommand.class, TTPack.REAL_TIME_CLOCK),
    SETHOME("sethome", SetHomeCommand.class, TTPack.HOMES),
    SHRINE("shrine", ShrineCommand.class, TTPack.THUNDER_SHRINE),
    SPAWN("spawn", SpawnCommand.class, TTPack.SPAWN),
    SPAWNINGSPHERES("spawningspheres", SpawningSpheresCommand.class, TTPack.SPAWNING_SPHERES),
    SUDOKU("sudoku", SudokuCommand.class, TTPack.SUDOKU),
    TPA("tpa", TPACommand.class, TTPack.TPA),
    TPAHERE("tpahere", TPAHereCommand.class, TTPack.TPA),
    WORKSTATIONHIGHLIGHT("workstationhighlight", WorkstationHighlightCommand.class, TTPack.WORKSTATION_HIGHLIGHTS),

    MECHANICS("mechanics", MechanicsCommand.class),
    TEST("test", TestCommand.class),
    TEAKSTWEAKS("teakstweaks", TeaksTweaksCommand.class);

    private final String name;
    private final Class<? extends AbstractCommand> clazz;
    private final TTPack parentPack;
    private AbstractCommand command;

    TTCommand(String name, Class<? extends AbstractCommand> clazz, TTPack parentPack) {
        this.name = name;
        this.clazz = clazz;
        this.parentPack = parentPack;
    }

    TTCommand(String name, Class<? extends AbstractCommand> clazz) {
        this(name, clazz, null);
    }

    public String getName() {
        return name;
    }

    public TTPack getParentPack() {
        return parentPack;
    }

    public AbstractCommand getCommand() {
        if (command == null) instantiate();
        return command;
    }

    private void instantiate() {
        try {
            Constructor<? extends AbstractCommand> constructor = clazz.getConstructor();
            this.command = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate command: " + name, e);
        }
    }

    public static TTCommand fromName(String name) {
        for (TTCommand command : values()) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return null;
    }

    public void register() {
        if (parentPack != null && !parentPack.isEnabled()) return;
        getCommand().register();
    }

    public boolean isEnabled() {
        return getCommand().isEnabled();
    }

    @Override
    public String toString() {
        return name;
    }
}
