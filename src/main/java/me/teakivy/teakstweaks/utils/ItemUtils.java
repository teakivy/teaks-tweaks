package me.teakivy.teakstweaks.utils;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static ItemStack handleUse(ItemStack item) {
        if (item == null) return null;

        ItemMeta meta = item.getItemMeta();

        if (meta instanceof Damageable damageable && getAllDamageable().contains(item.getType())) {
            damageable.setDamage(damageable.getDamage() + 1);
            item.setItemMeta(damageable);
            return item;
        }

        item.setAmount(item.getAmount() - 1);
        return new ItemStack(item.getType(), item.getAmount());
    }

    public static List<Material> getAllDamageable() {
        List<Material> damageable = new ArrayList<>();

        damageable.addAll(getAllTools());
        damageable.addAll(getAllWeapons());
        damageable.addAll(getAllArmor());

        return damageable;
    }

    public static List<Material> getAllTools() {
        List<Material> tools = new ArrayList<>();

        tools.addAll(Tag.ITEMS_PICKAXES.getValues());
        tools.addAll(Tag.ITEMS_AXES.getValues());
        tools.addAll(Tag.ITEMS_SHOVELS.getValues());
        tools.addAll(Tag.ITEMS_HOES.getValues());
        tools.add(Material.SHEARS);
        tools.add(Material.BRUSH);
        tools.add(Material.FLINT_AND_STEEL);
        tools.add(Material.FISHING_ROD);
        tools.add(Material.CARROT_ON_A_STICK);
        tools.add(Material.WARPED_FUNGUS_ON_A_STICK);


        return tools;
    }

    public static List<Material> getAllWeapons() {
        List<Material> weapons = new ArrayList<>();

        weapons.addAll(Tag.ITEMS_SWORDS.getValues());
        weapons.add(Material.BOW);
        weapons.add(Material.CROSSBOW);
        weapons.add(Material.TRIDENT);
        weapons.add(Material.MACE);

        return weapons;
    }

    public static List<Material> getAllArmor() {
        List<Material> armor = new ArrayList<>();

        armor.addAll(Tag.ITEMS_HEAD_ARMOR.getValues());
        armor.addAll(Tag.ITEMS_CHEST_ARMOR.getValues());
        armor.add(Material.ELYTRA);
        armor.addAll(Tag.ITEMS_LEG_ARMOR.getValues());
        armor.addAll(Tag.ITEMS_FOOT_ARMOR.getValues());

        return armor;
    }
}
