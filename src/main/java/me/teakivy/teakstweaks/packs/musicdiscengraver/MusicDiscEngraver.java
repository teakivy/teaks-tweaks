package me.teakivy.teakstweaks.packs.musicdiscengraver;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MusicDiscEngraver extends BasePack {

    public MusicDiscEngraver() {
        super("music-disc-engraver", Material.MUSIC_DISC_STAL);
    }

    @Override
    public void init() {
        super.init();
        List<Material> discTypes = List.of(
                Material.MUSIC_DISC_11,
                Material.MUSIC_DISC_13,
                Material.MUSIC_DISC_5,
                Material.MUSIC_DISC_BLOCKS,
                Material.MUSIC_DISC_CAT,
                Material.MUSIC_DISC_CHIRP,
                Material.MUSIC_DISC_FAR,
                Material.MUSIC_DISC_MALL,
                Material.MUSIC_DISC_MELLOHI,
                Material.MUSIC_DISC_OTHERSIDE,
                Material.MUSIC_DISC_PIGSTEP,
                Material.MUSIC_DISC_RELIC,
                Material.MUSIC_DISC_STAL,
                Material.MUSIC_DISC_STRAD,
                Material.MUSIC_DISC_WAIT,
                Material.MUSIC_DISC_WARD,
                Material.MUSIC_DISC_RELIC,
                Material.MUSIC_DISC_CREATOR,
                Material.MUSIC_DISC_CREATOR_MUSIC_BOX,
                Material.MUSIC_DISC_PRECIPICE,
                Material.MUSIC_DISC_LAVA_CHICKEN,
                Material.MUSIC_DISC_TEARS
        );

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
        ItemStack emptyDisc = ItemStack.of(Material.KNOWLEDGE_BOOK);
        ItemMeta meta = emptyDisc.getItemMeta();
        meta.setItemModel(Material.MUSIC_DISC_STRAD.getKey());
        meta.displayName(Component.text("Empty Music Disc").decoration(TextDecoration.ITALIC, false));
        meta.setRarity(ItemRarity.COMMON);
        meta.setMaxStackSize(16);
        emptyDisc.setItemMeta(meta);
        return emptyDisc;
    }
}
