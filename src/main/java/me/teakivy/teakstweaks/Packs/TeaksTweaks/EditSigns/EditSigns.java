package me.teakivy.teakstweaks.Packs.TeaksTweaks.EditSigns;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EditSigns implements Listener {

    Main main = Main.getPlugin(Main.class);

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
                        if (main.getConfig().getBoolean("packs.editable-signs.owner-only")) {
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

    public static void init(Main main) {

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
    public void unregister() {
        HandlerList.unregisterAll(this);
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
