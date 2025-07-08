package me.teakivy.teakstweaks.utils.permission;

import org.bukkit.command.CommandSender;

public enum Permission {
    COMMAND_AFK("command.afk"),
    COMMAND_AFK_UNINSTALL("command.afk.uninstall", PermissionType.OP),
    COMMAND_ALTS("command.alts"),
    COMMAND_ALTS_MANAGE("command.alts.manage", PermissionType.OP),
    COMMAND_BACK("command.back"),
    COMMAND_CONDUITPOWER("command.conduitpower"),
    COMMAND_COORDSHUD("command.coordshud"),
    COMMAND_HOME("command.home"),
    COMMAND_HOME_SET("command.home.set"),
    COMMAND_HOME_DELETE("command.home.delete"),
    COMMAND_DURABILITYPING("command.durabilityping"),
    COMMAND_DURABILITYPING_PREVIEW("command.durabilityping.preview"),
    COMMAND_DURABILITYPING_SET("command.durabilityping.set"),
    COMMAND_GRAVE("command.grave"),
    COMMAND_GRAVE_LOCATE("command.grave.locate"),
    COMMAND_GRAVE_KEY("command.grave.key", PermissionType.OP),
    COMMAND_GRAVE_UNINSTALL("command.grave.uninstall", PermissionType.OP),
    COMMAND_ITEMAVERAGES("command.itemaverages"),
    COMMAND_ITEMAVERAGES_UNINSTALL("command.itemaverages.uninstall", PermissionType.OP),
    COMMAND_KILLBOATS("command.killboats", PermissionType.OP),
    COMMAND_MECHANICS("command.mechanics"),
    COMMAND_NIGHTVISION("command.nightvision"),
    COMMAND_PACKLIST("command.packlist"),
    COMMAND_PORTAL("command.portal"),
    COMMAND_REALTIMECLOCK("command.realtimeclock"),
    COMMAND_SHRINE("command.shrine", PermissionType.OP),
    COMMAND_SHRINE_CREATE("command.shrine.create", PermissionType.OP),
    COMMAND_SHRINE_REMOVE("command.shrine.remove", PermissionType.OP),
    COMMAND_SHRINE_UNINSTALL("command.shrine.uninstall", PermissionType.OP),
    COMMAND_SPAWN("command.spawn"),
    COMMAND_SPAWNINGSPHERES("command.spawningspheres"),
    COMMAND_SPAWNINGSPHERES_CREATE("command.spawningspheres.create"),
    COMMAND_SPAWNINGSPHERES_REMOVE("command.spawningspheres.remove"),
    COMMAND_SPAWNINGSPHERES_TELEPORT("command.spawningspheres.teleport"),
    COMMAND_SUDOKU("command.sudoku"),
    COMMAND_DISPOSAL("command.disposal"),
    COMMAND_TEAKSTWEAKS("command.teakstweaks"),
    COMMAND_TEAKSTWEAKS_PASTE("command.teakstweaks.paste", PermissionType.OP),
    COMMAND_TEAKSTWEAKS_GIVE("command.teakstweaks.give", PermissionType.OP),
    COMMAND_TPA("command.tpa"),
    COMMAND_TPAHERE("command.tpahere"),
    COMMAND_WORKSTATIONHIGHLIGHT("command.workstationhighlight"),
    COMMAND_GMC("command.gmc", PermissionType.OP),
    COMMAND_GMS("command.gms", PermissionType.OP),
    COMMAND_GMA("command.gma", PermissionType.OP),
    COMMAND_GMSP("command.gmsp", PermissionType.OP),
    COMMAND_REPLY("command.reply"),
    COMMAND_FEED("command.feed", PermissionType.OP),
    COMMAND_HEAL("command.heal", PermissionType.OP),
    COMMAND_FLY("command.fly", PermissionType.OP),
    COMMAND_ENDERCHEST("command.enderchest"),
    COMMAND_CRAFTINGTABLE("command.craftingtable"),
    COMMAND_ANVIL("command.anvil"),
    COMMAND_CARTOGRAPHYTABLE("command.cartographytable"),
    COMMAND_GRINDSTONE("command.grindstone"),
    COMMAND_LOOM("command.loom"),
    COMMAND_SMITHINGTABLE("command.smithingtable"),

    ARMORED_ELYTRA_CREATE("armored-elytra.create"),
    ARMORED_ELYTRA_SEPARATE("armored-elytra.separate"),
    BACK_DEATH("back.death"),
    BACK_TELEPORT("back.teleport"),
    CAULDRON_CONCRETE("cauldron-concrete"),
    CAULDRON_MUD("cauldron-mud"),
    CHAT_COLORS_CHAT("chat-colors.chat"),
    CHAT_COLORS_ANVIL("chat-colors.anvil"),
    CHAT_COLORS_SIGNS("chat-colors.signs"),
    CHUNK_LOADERS("chunk-loaders"),
    CLASSIC_FISHING_LOOT("classic-fishing-loot"),
    DIRT_TO_GRASS("dirt-to-grass"),
    DURABILITY_PING("durability-ping"),
    ELEVATOR_CREATE("elevator.create"),
    ELEVATOR_USE("elevator.use"),
    FIXED_ITEM_FRAMES("fixed-item-frames"),
    INSTA_MINE("insta-mine"),
    INVISIBLE_ITEM_FRAMES("invisible-item-frames"),
    KEEP_SMALL("keep-small"),
    ROTATION_WRENCH_REDSTONE("rotation-wrench.redstone"),
    ROTATION_WRENCH_TERRACOTTA("rotation-wrench.terracotta"),
    SILENCE_MOBS("silence-mobs"),
    SLEEPY_SPIDER_EGGS("sleepy-spider-eggs"),
    SLIME_CREAM("slime-cream"),
    STAIR_CHAIRS_CREATE("stair-chairs.create"),
    STAIR_CHAIRS_SIT("stair-chairs.sit"),
    THUNDER_SHRINE_USE("thunder-shrine.use"),
    UNLOCK_ALL_RECIPES("unlock-all-recipes"),
    UNSTICKY_PISTONS("unsticky-pistons"),
    XP_MANAGEMENT_BOTTLE("xp-management.bottle"),

    MANAGE("manage", PermissionType.OP),

    TEST("test", PermissionType.NONE);

    private final String permission;
    private final PermissionType type;

    Permission(String permission, PermissionType type) {
        this.permission = "teakstweaks." + permission;
        this.type = type;
    }
    Permission(String permission) {
        this.permission = "teakstweaks." + permission;
        this.type = PermissionType.ALL;
    }

    public String getPermission() {
        return permission;
    }

    public PermissionType getType() {
        return type;
    }

    public boolean check(CommandSender sender) {
        return sender.hasPermission(this.getPermission());
    }

    public static Permission get(String permission) {
        permission = PermissionManager.signPermission(permission);
        for (Permission p : values()) {
            if (p.getPermission().equals(permission)) return p;
        }
        return null;
    }
}

enum PermissionType {
    OP,
    NON_OP,
    ALL,
    NONE
}