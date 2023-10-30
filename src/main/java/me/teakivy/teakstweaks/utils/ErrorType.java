package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.utils.lang.Translatable;

public enum ErrorType {
    UNKNOWN_ERROR(Translatable.getLegacyError("unknown")),
    PACK_NOT_ENABLED(Translatable.getLegacyError("pack_not_enabled")),
    NOT_PLAYER(Translatable.getLegacyError("not_player")),
    NOT_OP(Translatable.getLegacyError("not_op")),
    MISSING_COMMAND_PERMISSION(Translatable.getLegacyError("missing_command_permission")),
    MISSING_PERMISSION(Translatable.getLegacyError("missing_permission")),
    CANT_GET_LATEST(Translatable.getLegacyError("cant_get_latest_version")),
    COMMAND_DISABLED(Translatable.getLegacyError("command_disabled")),
    MISSING_ACTION(Translatable.getLegacyError("missing_action")),
    PLAYER_DNE(Translatable.getLegacyError("player_dne"));


    final String text;

    ErrorType(final String error) {
        this.text = error;
    }

    /**
     * Gets the error message
     * @return The error message
     */
    public String m() {
        return text;
    }

    /**
     * Converts the error message to a string
     * @return The error message
     */
    @Override
    public String toString() {
        return text;
    }
}
