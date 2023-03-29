package me.teakivy.teakstweaks.packs.teakstweaks.editsigns;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EditSigns extends BasePack {

    public EditSigns() {
        super("Editable Signs", "editable-signs", PackType.TEAKSTWEAKS, Material.OAK_SIGN, "Allow players to edit signs by shift-right clicking them");
    }

    @Override
    public void init() {
        String ver = Bukkit.getServer().getVersion().toLowerCase();
        if (ver.contains("paper") || config.getBoolean("bypass-paper-check")) {
            super.init();
        } else {
            Logger.log(Logger.LogLevel.ERROR, "Editable signs are only supported on Paper at the moment.");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().isSneaking()) return;

        Block block = event.getClickedBlock();

        if (block != null && block.getType().toString().contains("SIGN") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && isInteractingWithAir(event.getPlayer())) {

            if (block.getState() instanceof TileState) {
                TileState tileState = (TileState) block.getState();
                PersistentDataContainer container = tileState.getPersistentDataContainer();
                if (container.has(new NamespacedKey(main, "tweaks_sign_owner"), PersistentDataType.STRING)) {
                    String owner = container.get(new NamespacedKey(main, "tweaks_sign_owner"), PersistentDataType.STRING);
                    if (owner != null) {
                        if (getConfig().getBoolean("owner-only")) {
                            if (!owner.equals(event.getPlayer().getUniqueId().toString())) return;
                        }
                    }
                }
            }
        }

        HumanEntity player = event.getPlayer();
        if (event.getClickedBlock() == null) return;
        if (!event.getClickedBlock().getType().toString().contains("SIGN")) return;
        player.openSign((Sign) event.getClickedBlock().getState());

    }

    private boolean isInteractingWithAir(Player player) {
        try {
            ItemStack mainHand = player.getEquipment().getItemInMainHand();
            ItemStack offHand = player.getEquipment().getItemInOffHand();
            return (mainHand == null || mainHand.getType().equals(Material.AIR)) && (offHand == null || !offHand.getType().toString().contains("DYE"));
        } catch (Throwable e) {
            @SuppressWarnings("deprecation")
            ItemStack item = player.getItemInHand();
            return item == null || item.getType().equals(Material.AIR);
        }
    }

    @EventHandler
    public void onChange(SignChangeEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        TileState state = (TileState) block.getState();
        PersistentDataContainer container = state.getPersistentDataContainer();
        if (!container.has(new NamespacedKey(main, "tweaks_sign_owner"), PersistentDataType.STRING)) {
            container.set(new NamespacedKey(main, "tweaks_sign_owner"), PersistentDataType.STRING, player.getUniqueId().toString());
            state.update();
        }

    }

}
