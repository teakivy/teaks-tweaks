package me.teakivy.teakstweaks.packs.easiernametags;

import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.dialog.DialogResponseView;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.chatcolors.ChatColors;
import me.teakivy.teakstweaks.utils.StringUtils;
import me.teakivy.teakstweaks.utils.dialog.DialogUtils;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EasierNametags extends BasePack {

    public EasierNametags() {
        super(TTPack.EASIER_NAMETAGS, Material.NAME_TAG);
    }

    @EventHandler
    public void onNameTagUse(PlayerInteractEvent event) {
        if (!event.getPlayer().isSneaking()) return;
        ItemStack item = event.getItem();
        if (item == null) return;
        if (item.getType() != Material.NAME_TAG) return;
        event.setCancelled(true);

        DialogUtils.openNameTagDialog(event.getPlayer(), item);
    }

    @EventHandler
    public void handleDialog(PlayerCustomClickEvent event) {
        if (!event.getIdentifier().equals(Key.key("teakstweaks:easier_name_tags/confirm"))) return;

        DialogResponseView view = event.getDialogResponseView();
        if (view == null) return;

        var ref = new Object() {
            Component input = Component.text(view.getText("input"));
        };

        if (event.getCommonConnection() instanceof PlayerGameConnection conn) {
            Player player = conn.getPlayer();

            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType() != Material.NAME_TAG) item = player.getInventory().getItemInOffHand();
            if (item.getType() != Material.NAME_TAG) return;

            if (TTPack.CHAT_COLORS.isEnabled() && Permission.CHAT_COLORS_ANVIL.check(player)) {
                MiniMessage mm = MiniMessage.miniMessage();
                ref.input = mm.deserialize(mm.serialize(StringUtils.parseLegacyChatColors(ref.input)).replaceAll("\\\\<", "<"));
            }

            item.editMeta(meta -> meta.displayName(ref.input));
        }
    }

}
