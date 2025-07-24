package me.teakivy.teakstweaks.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public enum ErrorType {
    UNKNOWN_ERROR(Component.translatable("error.unknown")),
    PACK_NOT_ENABLED(Component.translatable("error.pack_not_enabled")),
    NOT_PLAYER(Component.translatable("error.not_player")),
    NOT_OP(Component.translatable("error.not_op")),
    MISSING_COMMAND_PERMISSION(Component.translatable("error.missing_command_permission")),
    MISSING_PERMISSION(Component.translatable("error.missing_permission")),
    CANT_GET_LATEST(Component.translatable("error.cant_get_latest_version")),
    COMMAND_DISABLED(Component.translatable("error.command_disabled")),
    MISSING_ACTION(Component.translatable("error.missing_action")),
    PLAYER_DNE(Component.translatable("error.player_dne"));


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
