package me.teakivy.teakstweaks.packs.musicdiscengraver;

import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.registry.tag.Tag;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import me.teakivy.teakstweaks.utils.customitems.ItemHandler;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class MusicDiscEngraver extends BasePack {

    public MusicDiscEngraver() {
        super(TTPack.MUSIC_DISC_ENGRAVER, Material.MUSIC_DISC_STAL);
    }

    @Override
    public void init() {
        super.init();
        List<Material> discTypes = new java.util.ArrayList<>();

        for (Material material : Material.values()) {
            if (material.toString().startsWith("MUSIC_DISC_") && !discTypes.contains(material)) {
                discTypes.add(material);
            }
        }

        ItemStack emptyDisc = getEmptyDisc();

        ShapedRecipe recipe = new ShapedRecipe(Key.get("empty_music_disc"), emptyDisc);
        recipe.shape("III", "IDI", "III");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('D', Material.DIAMOND);
        addRecipe(recipe);

        for (Material output : discTypes) {
            NamespacedKey key = Key.get("empty_craft_" + output.toString().toLowerCase());
            StonecuttingRecipe r = new StonecuttingRecipe(key, ItemStack.of(output), new RecipeChoice.ExactChoice(emptyDisc));
            addRecipe(r);
        }

        StonecuttingRecipe r = new StonecuttingRecipe(Key.get("empty_craft_empty"), getEmptyDisc(), new RecipeChoice.ExactChoice(emptyDisc));
        addRecipe(r);

        for (Material input : discTypes) {
            registerDiscs(input, discTypes);
        }


        ItemHandler.addCustomItem(new CustomItem("empty_music_disc", emptyDisc));
    }

    private void registerDiscs(Material input, List<Material> outputs) {
        for (Material output : outputs) {
            NamespacedKey key = Key.get(input.toString().toLowerCase() + "_craft_" + output.toString().toLowerCase());
            StonecuttingRecipe recipe = new StonecuttingRecipe(key, ItemStack.of(output), input);
            addRecipe(recipe);
        }

        StonecuttingRecipe recipe = new StonecuttingRecipe(Key.get(input.toString().toLowerCase() + "_craft_empty"), getEmptyDisc(), input);
        addRecipe(recipe);
    }

    private ItemStack getEmptyDisc() {
        ItemStack emptyDisc = ItemStack.of(Material.POISONOUS_POTATO);
        emptyDisc.unsetData(DataComponentTypes.CONSUMABLE);
        ItemMeta meta = emptyDisc.getItemMeta();
        meta.setItemModel(Material.MUSIC_DISC_STRAD.getKey());
        meta.displayName(Component.text("Empty Music Disc").decoration(TextDecoration.ITALIC, false));
        meta.setRarity(ItemRarity.COMMON);
        meta.setMaxStackSize(16);
        emptyDisc.setItemMeta(meta);
        return emptyDisc;
    }
}
