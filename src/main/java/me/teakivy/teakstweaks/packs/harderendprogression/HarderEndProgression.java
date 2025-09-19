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
import org.bukkit.inventory.ShapelessRecipe;

import java.util.Random;

public class HarderEndProgression extends BasePack {

	public HarderEndProgression() {
        super(TTPack.HARDER_END_PROGRESSION, Material.ENDER_EYE);
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
        ShapelessRecipe recipe = switch (difficultyLevel) {
            case 3 -> levelThreeRecipe();
            case 2 -> levelTwoRecipe();
			case 1 -> levelOneRecipe();
			default -> null;
        };
		if (recipe == null) return;
		addRecipe(recipe);

	}

	public ShapelessRecipe generateNewEmptyRecipe(){
		Material eyeOfEnder = Material.ENDER_EYE;
		Bukkit.removeRecipe(NamespacedKey.minecraft(eyeOfEnder.toString().toLowerCase()));
        return new ShapelessRecipe(Key.get(eyeOfEnder.name().toLowerCase()), new ItemStack(eyeOfEnder, 1));
	}

	public ShapelessRecipe levelOneRecipe(){
		ShapelessRecipe recipe = generateNewEmptyRecipe();
		recipe.addIngredient(Material.WIND_CHARGE);
		recipe.addIngredient(Material.ENDER_PEARL);
		recipe.addIngredient(Material.BLAZE_POWDER);

		return recipe;
	}

	public ShapelessRecipe levelTwoRecipe(){
		ShapelessRecipe recipe = generateNewEmptyRecipe();
		recipe.addIngredient(Material.PRISMARINE_SHARD);
		recipe.addIngredient(Material.WIND_CHARGE);
		recipe.addIngredient(Material.ENDER_PEARL);
		recipe.addIngredient(Material.BLAZE_POWDER);
		recipe.addIngredient(Material.AMETHYST_SHARD);

		return recipe;
	}

	public ShapelessRecipe levelThreeRecipe(){
		ShapelessRecipe recipe = generateNewEmptyRecipe();
		recipe.addIngredient(Material.PRISMARINE_SHARD);
		recipe.addIngredient(Material.PHANTOM_MEMBRANE);
		recipe.addIngredient(Material.GHAST_TEAR);
		recipe.addIngredient(Material.WIND_CHARGE);
		recipe.addIngredient(Material.ENDER_PEARL);
		recipe.addIngredient(Material.BLAZE_POWDER);
		recipe.addIngredient(Material.RESIN_CLUMP);
		recipe.addIngredient(Material.AMETHYST_SHARD);
		recipe.addIngredient(Material.ECHO_SHARD);

		return recipe;
	}
}
