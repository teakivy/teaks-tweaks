package me.teakivy.teakstweaks.packs.paintingpicker;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Art;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecuttingRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PaintingPicker extends BasePack {
    private final String resourcePackUrl = "https://drive.google.com/uc?export=download&id=14BCDxbOyxDSdSVcHo5l2oZlhFBIpcTYb";
    private final byte[] hash = hexStringToByteArray("bfafd1e28c44e761029e710c9d54e020c09844b6");

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public PaintingPicker() {
        super("painting-picker", Material.PAINTING);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (getConfig().getBoolean("suggest-pack")) {
            event.getPlayer().addResourcePack(UUID.randomUUID(), resourcePackUrl, hash, "Would You like to install the Unique Painting Items resource pack?", false);
        }
    }

    @Override
    public void init() {
        super.init();

        Registry<@NotNull Art> registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.PAINTING_VARIANT);

        StonecuttingRecipe recipe = new StonecuttingRecipe(Key.get("painting_any"), new ItemStack(Material.PAINTING), Material.PAINTING);
        addRecipe(recipe);

        for (Art art : registry) {
            System.out.println(art);
            String name = art.toString();
            ItemStack item = ItemStack.of(Material.PAINTING);
            item.setData(DataComponentTypes.PAINTING_VARIANT, art);

            recipe = new StonecuttingRecipe(Key.get("painting_" + name.toLowerCase().replaceAll(" ", "_")), item, Material.PAINTING);
            addRecipe(recipe);
        }
    }
}
