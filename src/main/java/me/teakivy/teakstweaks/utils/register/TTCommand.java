package me.teakivy.teakstweaks.utils.register;

import me.teakivy.teakstweaks.commands.*;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;

public enum TTCommand {
    AFK("afk", new AFKCommand(), TTPack.AFK_DISPLAY),
    ALTS("alts", new AltsCommand(), TTPack.SPECTATOR_ALTS),
    BACK("back", new BackCommand(), TTPack.BACK),
    CONDUITPOWER("conduitpower", new ConduitPowerCommand(), TTPack.SPECTATOR_CONDUIT_POWER),
    COORDSHUD("coordshud", new CoordsHudCommand(), TTPack.COORDS_HUD),
    DELETEHOME("deletehome", new DeleteHomeCommand(), TTPack.HOMES),
    DISPOSAL("disposal", new DisposalCommand(), TTPack.DISPOSAL),
    DURABILITYPING("durabilityping", new DurabilityPingCommand(), TTPack.DURABILITY_PING),
    GRAVE("grave", new GraveCommand(), TTPack.GRAVES),
    HOME("home", new HomeCommand(), TTPack.HOMES),
    ITEMAVERAGES("itemaverages", new ItemAveragesCommand(), TTPack.ITEM_AVERAGES),
    KILLBOATS("killboats", new KillBoatsCommand(), TTPack.KILL_BOATS),
    NIGHTVISION("nightvision", new NightVisionCommand(), TTPack.SPECTATOR_NIGHT_VISION),
    PORTAL("portal", new PortalCommand(), TTPack.NETHER_PORTAL_COORDS),
    REALTIMECLOCK("realtimeclock", new RealTimeClockCommand(), TTPack.REAL_TIME_CLOCK),
    SETHOME("sethome", new SetHomeCommand(), TTPack.HOMES),
    SHRINE("shrine", new ShrineCommand(), TTPack.THUNDER_SHRINE),
    SPAWN("spawn", new SpawnCommand(), TTPack.SPAWN),
    SPAWNINGSPHERES("spawningspheres", new SpawningSpheresCommand(), TTPack.SPAWNING_SPHERES),
    SUDOKU("sudoku", new SudokuCommand(), TTPack.SUDOKU),
    TPA("tpa", new TPACommand(), TTPack.TPA),
    TPAHERE("tpahere", new TPAHereCommand(), TTPack.TPA),
    WORKSTATIONHIGHLIGHT("workstationhighlight", new WorkstationHighlightCommand(), TTPack.WORKSTATION_HIGHLIGHTS),

    MECHANICS("mechanics", new MechanicsCommand()),
    TEST("test", new TestCommand()),
    TEAKSTWEAKS("teakstweaks", new TeaksTweaksCommand());


    private final String name;
    private final AbstractCommand command;
    private final TTPack parentPack;

    TTCommand(String name, AbstractCommand command, TTPack parentPack) {
        this.name = name;
        this.command = command;
        this.parentPack = parentPack;
    }

    TTCommand(String name, AbstractCommand command) {
        this(name, command, null);
    }

    public String getName() {
        return name;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public TTPack getParentPack() {
        return parentPack;
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
        command.register();
    }

    public boolean isEnabled() {
        return command.isEnabled();
    }

    @Override
    public String toString() {
        return name;
    }
}
