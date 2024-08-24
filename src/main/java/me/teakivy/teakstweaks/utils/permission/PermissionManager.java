package me.teakivy.teakstweaks.utils.permission;

import me.teakivy.teakstweaks.TeaksTweaks;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class PermissionManager {

    public static void init() {
        for (me.teakivy.teakstweaks.utils.permission.Permission permission : me.teakivy.teakstweaks.utils.permission.Permission.values()) {
            PermissionDefault permissionDefault = switch (permission.getType()) {
                case ALL -> PermissionDefault.TRUE;
                case OP -> PermissionDefault.OP;
                case NON_OP -> PermissionDefault.NOT_OP;
                case NONE -> PermissionDefault.FALSE;
            };
            TeaksTweaks.getInstance().getServer().getPluginManager().addPermission(new Permission(permission.getPermission(), permissionDefault));
            System.out.println("Registered permission: " + permission.getPermission());
        }
    }

    public static String signPermission(String permission) {
        if (permission.startsWith("teakstweaks.")) {
            return permission;
        } else {
            return "teakstweaks." + permission;
        }
    }
}


