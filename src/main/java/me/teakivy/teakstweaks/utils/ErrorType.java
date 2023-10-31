package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public enum ErrorType {
    UNKNOWN_ERROR(Translatable.getError("unknown")),
    PACK_NOT_ENABLED(Translatable.getError("pack_not_enabled")),
    NOT_PLAYER(Translatable.getError("not_player")),
    NOT_OP(Translatable.getError("not_op")),
    MISSING_COMMAND_PERMISSION(Translatable.getError("missing_command_permission")),
    MISSING_PERMISSION(Translatable.getError("missing_permission")),
    CANT_GET_LATEST(Translatable.getError("cant_get_latest_version")),
    COMMAND_DISABLED(Translatable.getError("command_disabled")),
    MISSING_ACTION(Translatable.getError("missing_action")),
    PLAYER_DNE(Translatable.getError("player_dne"));


    final Component text;

    ErrorType(final Component error) {
        this.text = error;
    }

    /**
     * Gets the error message
     * @return The error message
     */
    public Component m() {
        return text;
    }

    /**
     * Converts the error message to a string
     * @return The error message
     */
    @Override
    public String toString() {
        return ((TextComponent) text).content();
    }
}
