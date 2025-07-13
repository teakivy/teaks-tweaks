package me.teakivy.teakstweaks.utils.dialog;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.action.DialogActionCallback;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import org.bukkit.entity.Player;

import java.util.List;

public class DialogUtils {

    public static void showConfirmation(Player player, Component message, DialogActionCallback onConfirm, DialogActionCallback onCancel) {
        ActionButton yesButton = ActionButton.builder(Component.text("Confirm")).action(DialogAction.customClick(onConfirm, ClickCallback.Options.builder().build())).build();
        ActionButton noButton = ActionButton.builder(Component.text("Cancel")).action(DialogAction.customClick(onCancel, ClickCallback.Options.builder().build())).build();

        Dialog dialog = Dialog.create(b -> b.empty()
                .base(DialogBase.builder(Component.text("Teak's Tweaks")).body(List.of(DialogBody.plainMessage(message))).build())
                .type(DialogType.confirmation(yesButton, noButton))
        );

        player.showDialog(dialog);
    }
}
