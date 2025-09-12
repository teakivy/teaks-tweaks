package me.teakivy.teakstweaks.packs.harderendprogression;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EnderSignal;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Random;

public class HarderEndProgression extends BasePack {

	public HarderEndProgression() {
        super(TTPack.HARDERENDPROGRESSION, Material.ENDER_EYE);
		newEyeOfEnderRecipe();
    }

	@EventHandler
	public void changeEyePosition(EntitySpawnEvent event) {
		if (event.getEntity() instanceof EnderSignal enderSignal) {
			int breakChance = getConfig().getInt("break-chance");
			enderSignal.setDropItem(breakChance <= new Random().nextInt(101));
		}
	}

	public void newEyeOfEnderRecipe() {
		int difficultyLevel = getConfig().getInt("difficulty-level");
        switch (difficultyLevel) {
            case 3 -> levelThreeRecipe();
            case 2 -> levelTwoRecipe();
			case 1 -> levelOneRecipe();
        }

	}

	public ShapedRecipe generateNewEmptyRecipe(){
		Material eyeOfEnder = Material.ENDER_EYE;
		Bukkit.removeRecipe(NamespacedKey.minecraft(eyeOfEnder.toString().toLowerCase()));
        return new ShapedRecipe(Key.get(eyeOfEnder.name().toLowerCase()), new ItemStack(eyeOfEnder, 1));
	}

	public void levelOneRecipe(){
		ShapedRecipe recipe = generateNewEmptyRecipe();
		recipe.shape("   ", "def", "   ");
		recipe.setIngredient('d', Material.WIND_CHARGE);
		recipe.setIngredient('e', Material.ENDER_PEARL);
		recipe.setIngredient('f', Material.BLAZE_POWDER);
		addRecipe(recipe);
	}

	public void levelTwoRecipe(){
		ShapedRecipe recipe = generateNewEmptyRecipe();
		recipe.shape(" b ", "def", " h ");
		recipe.setIngredient('b', Material.PRISMARINE_SHARD);
		recipe.setIngredient('d', Material.WIND_CHARGE);
		recipe.setIngredient('e', Material.ENDER_PEARL);
		recipe.setIngredient('f', Material.BLAZE_POWDER);
		recipe.setIngredient('h', Material.AMETHYST_SHARD);
		addRecipe(recipe);
	}

	public void levelThreeRecipe(){
		ShapedRecipe recipe = generateNewEmptyRecipe();
		recipe.shape("abc", "def", "ghi");
		recipe.setIngredient('a', Material.PRISMARINE_SHARD);
		recipe.setIngredient('b', Material.PHANTOM_MEMBRANE);
		recipe.setIngredient('c', Material.GHAST_TEAR);
		recipe.setIngredient('d', Material.WIND_CHARGE);
		recipe.setIngredient('e', Material.ENDER_PEARL);
		recipe.setIngredient('f', Material.BLAZE_POWDER);
		recipe.setIngredient('g', Material.RESIN_CLUMP);
		recipe.setIngredient('i', Material.AMETHYST_SHARD);
		recipe.setIngredient('i', Material.ECHO_SHARD);

		addRecipe(recipe);
	}
}
