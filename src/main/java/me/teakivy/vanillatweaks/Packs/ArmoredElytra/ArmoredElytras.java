package me.teakivy.vanillatweaks.Packs.ArmoredElytra;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.ItemStackSerializer;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArmoredElytras implements Listener {

    Main main = Main.getPlugin(Main.class);

    static List<UUID> broken = new ArrayList<>();

    private final List<String> combinable = Stream.of(
            "NETHERITE_CHESTPLATE",
            "DIAMOND_CHESTPLATE",
            "GOLDEN_CHESTPLATE",
            "IRON_CHESTPLATE",
            "CHAINMAIL_CHESTPLATE",
            "LEATHER_CHESTPLATE"
            )
            .collect(Collectors.toList());

    private final List<String> chestplates = Stream.of(
            "NETHERITE_CHESTPLATE",
            "DIAMOND_CHESTPLATE",
            "GOLDEN_CHESTPLATE",
            "IRON_CHESTPLATE",
            "CHAINMAIL_CHESTPLATE",
            "LEATHER_CHESTPLATE"
    )
            .collect(Collectors.toList());

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (!combinable.contains(item.getType().toString())) return;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (event.getItemDrop().getLocation().add(0, -1, 0).getBlock().getType().equals(Material.ANVIL)) {
                    for (Entity entity : event.getItemDrop().getNearbyEntities(1, 1, 1)) {
                        if (entity.getType() == EntityType.DROPPED_ITEM) {
                            if (chestplates.contains(item.getType().toString())) {
                                Item item = (Item) entity;
                                if (item.getItemStack().getType() == Material.ELYTRA) {
                                    if (item.getItemStack().hasItemMeta()) {
                                        if (!item.getItemStack().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(main, "armored_elytra"), PersistentDataType.STRING)) {
                                            if (!event.getItemDrop().isDead() && !item.isDead()) {
                                                item.remove();
                                                event.getItemDrop().remove();
                                                item.getWorld().spawnParticle(Particle.FLAME, item.getLocation(), 100, 0, 0, 0, .5);
                                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);

                                                ItemStack newElytra = createArmoredElytra(item.getItemStack(), event.getItemDrop().getItemStack());

                                                item.getLocation().getWorld().dropItem(item.getLocation(), newElytra).setVelocity(new Vector(0, 0, 0));
                                            }
                                        }
                                    } else {
                                        if (!event.getItemDrop().isDead() && !item.isDead()) {
                                            item.remove();
                                            event.getItemDrop().remove();
                                            item.getWorld().spawnParticle(Particle.FLAME, item.getLocation(), 100, 0, 0, 0, .5);
                                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);

                                            ItemStack newElytra = createArmoredElytra(item.getItemStack(), event.getItemDrop().getItemStack());

                                            item.getLocation().getWorld().dropItem(item.getLocation(), newElytra).setVelocity(new Vector(0, 0, 0));
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
                if (event.getItemDrop().isDead()) this.cancel();
            }
        }.runTaskTimer(main, 0, 20L);
    }

    @EventHandler
    public void onElytraDrop(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (!itemStack.hasItemMeta()) return;
        if (!itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(main, "armored_elytra"), PersistentDataType.STRING)) return;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (event.getItemDrop().getLocation().add(0, -1, 0).getBlock().getType().equals(Material.GRINDSTONE)) {
                    Item item = event.getItemDrop();
                    item.remove();
                    item.getWorld().spawnParticle(Particle.FLAME, item.getLocation(), 100, 0, 0, 0, .5);
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GRINDSTONE_USE, 1, 1);
                    item.getWorld().dropItem(event.getItemDrop().getLocation(), getChestplateFromArmoredElytra(itemStack)).setVelocity(new Vector(0, 0, 0));
                    item.getWorld().dropItem(event.getItemDrop().getLocation(), getElytraFromArmoredElytra(itemStack)).setVelocity(new Vector(0, 0, 0));
                }
                if (event.getItemDrop().isDead()) this.cancel();
            }
        }.runTaskTimer(main, 0, 20L);
    }

    @EventHandler
    public void onBurn(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Item)) return;
        Item item = (Item) event.getEntity();
        ItemStack itemStack = item.getItemStack();
        if (itemStack.getType() != Material.ELYTRA) return;
        if (!itemStack.hasItemMeta()) return;
        if (itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(main, "armored_elytra"), PersistentDataType.STRING)) {
            item.getWorld().dropItem(item.getLocation(), getChestplateFromArmoredElytra(itemStack)).setVelocity(new Vector(0, 0, 0));
            item.getWorld().dropItem(item.getLocation(), getElytraFromArmoredElytra(itemStack)).setVelocity(new Vector(0, 0, 0));
            item.remove();
        }
    }

    private ItemStack createArmoredElytra(ItemStack elytra, ItemStack chestplate) {
        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();

        HashMap<Enchantment, Integer> enchantmentStorage = new HashMap<>();

        String name = ChatColor.GOLD + "Armored Elytra";
        if (chestplate.hasItemMeta()) {
            if (chestplate.getItemMeta().hasDisplayName()) {
                name = chestplate.getItemMeta().getDisplayName();
            }
        }
        if (elytra.hasItemMeta()) {
            if (elytra.getItemMeta().hasDisplayName()) {
                name = elytra.getItemMeta().getDisplayName();
            }
        }
        meta.setDisplayName(name);

        NamespacedKey key = new NamespacedKey(main, "armored_elytra");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");

        NamespacedKey chestplate_storage_key = new NamespacedKey(main, "chestplate_storage");
        meta.getPersistentDataContainer().set(chestplate_storage_key, PersistentDataType.STRING, ItemStackSerializer.serialize(chestplate));

        NamespacedKey elytra_storage_key = new NamespacedKey(main, "elytra_storage");
        meta.getPersistentDataContainer().set(elytra_storage_key, PersistentDataType.STRING, ItemStackSerializer.serialize(elytra));


        chestplate.getEnchantments().forEach((enchantment, integer) -> {
            if (!enchantmentStorage.containsKey(enchantment)) {
                enchantmentStorage.put(enchantment, integer);
            } else {
                if (enchantmentStorage.get(enchantment) < integer) {
                    enchantmentStorage.put(enchantment, integer);
                }
            }
        });

        elytra.getEnchantments().forEach((enchantment, integer) -> {
            if (!enchantmentStorage.containsKey(enchantment)) {
                enchantmentStorage.put(enchantment, integer);
            } else {
                if (enchantmentStorage.get(enchantment) < integer) {
                    enchantmentStorage.put(enchantment, integer);
                }
            }
        });

        enchantmentStorage.forEach((enchantment, integer) -> {
            meta.addEnchant(enchantment, integer, true);
        });
        int armor = 0;
        int toughness = 0;
        double knockbackResistance = 0;

        if (chestplate.getType() == Material.LEATHER_CHESTPLATE) armor = 3;
        if (chestplate.getType() == Material.CHAINMAIL_CHESTPLATE) armor = 5;
        if (chestplate.getType() == Material.IRON_CHESTPLATE) armor = 6;
        if (chestplate.getType() == Material.GOLDEN_CHESTPLATE) armor = 5;
        if (chestplate.getType() == Material.DIAMOND_CHESTPLATE) {
            armor = 8;
            toughness = 2;
        }
        if (chestplate.getType() == Material.NETHERITE_CHESTPLATE) {
            armor = 8;
            toughness = 3;
            knockbackResistance = .1;
        }
        if (armor != 0) {
            AttributeModifier modifierArmor = new AttributeModifier(UUID.randomUUID(), "generic.armor", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifierArmor);
        }
        if (toughness != 0) {
            AttributeModifier modifierToughness = new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", toughness, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifierToughness);
        }
        if (knockbackResistance != 0) {
            AttributeModifier modifierKnockback = new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", knockbackResistance, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifierKnockback);
        }

        item.setItemMeta(meta);

        return item;
    }

    private ItemStack getChestplateFromArmoredElytra(ItemStack elytra) {
        if (!elytra.hasItemMeta()) return null;
        if (!elytra.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(main, "armored_elytra"), PersistentDataType.STRING)) return null;
        String chestplate = elytra.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(main, "chestplate_storage"), PersistentDataType.STRING);
        return ItemStackSerializer.deserialize(chestplate);
    }

    private ItemStack getElytraFromArmoredElytra(ItemStack elytra) {
        if (!elytra.hasItemMeta()) return null;
        if (!elytra.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(main, "armored_elytra"), PersistentDataType.STRING)) return null;
        String oldElytra = elytra.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(main, "elytra_storage"), PersistentDataType.STRING);
        return ItemStackSerializer.deserialize(oldElytra);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
