package me.teakivy.teakstweaks.Packs.Hermitcraft.GemVillagers;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.teakivy.teakstweaks.Packs.Hermitcraft.TreasureGems.Gems;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GemVllagers {

    public void summonFunctionalVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.AQUA + "Functional");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getAquamarineGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Ender Chest", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZjYzQ4NmMyYmUxY2I5ZGZjYjJlNTNkZDlhM2U5YTg4M2JmYWRiMjdjYjk1NmYxODk2ZDYwMmI0MDY3In19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Enchanting Table", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTU2OTYzNzNhMTg3ZTZkMmRkY2RmMWQ2Nzc3NGNiMTFmM2E1MmE5NzY3YTA4NDU4OWIyM2YxOWE3ZjIzYTcxYSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Furnace", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTI5NTc3YjhmNDBkNjE0ZDJhODA5NDYxNWRhMTA2OGNmMTJjYjhmNzgzNDU4MzliZDBmN2VhYTc3YjA2ZTI3ZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Blast Furnace", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBmNGQzNjAxYjI0ZDZiMzcxYmI5N2EzZjQyNzc5ODQwYTEyOTQ4N2EzMDRkYWI2MjM0NjlkY2EwMjg3Y2FmNSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Smoker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTgzMWNlODIyN2JiYzdiNzVjMzY4OTQ2NmNlYzRkYWY1ZGEyZDljNTNiYjgzZDExN2E5YmE4OTBkYWVhZjQwNiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Crafting Table", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU2YWIzZmRjMmJjYjA3YWU3NjkwODAxYWUwMGFjMmZmZjU0MmQ3OTMwODk2MWMyYjU3OTM3MGVjZjY1NmMyOSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Cartography Table", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWNiOTdjNjgyMzZjNjM1MjM2MmYzZWI5MTk0YWI1NDJmMTg3MjBhMzRlOGQwOWRhYTE2OGEwNWVmNjUwMTMwZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Fletching Table", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBiNzhlMDk2MTIzNmM4ZGE3N2QyOGZhOWExYzZmMDM5OGViYjI4ZWJmZDdhMTc4M2ViMmI2YzhjNDE2MDM0NiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Loom", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZjMDE0NDhkNjQ3N2UxZjdhM2QyMDdmMjM1MGEwNjZkZmE5NTA5MGQzNDBkNDUxNTUzY2UwNWU3MDBiYjczMSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Smithing Table", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWUyNzlmMDVkYjgzNjAyMTg1OTU5MGE1YjVmNDM0OWE2MGFkZjAyYmMxZDMwOWRhODQwZDllYmJlZjhmMGUyYiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Chest", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDVjNmRjMmJiZjUxYzM2Y2ZjNzcxNDU4NWE2YTU2ODNlZjJiMTRkNDdkOGZmNzE0NjU0YTg5M2Y1ZGE2MjIifX19")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Barrel", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGVjZDIyYzRiYjYxM2JkN2Y2OWNiMDIzMWExMzBiYjEwMzViNmIwZDQ2ZDY3MmMwN2U4ZTJhMDM1ZmUwMmU3MCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Shulker Box", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzlhYTg4YTA1ZmE1ZjMzYjYzMmU1NWE2NDU1YzE0ZmIwZmEyNzllNjMxNDdmOTc3OGQzOWRmOGY1OGE0NzkyMiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Jukebox", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThmNTY3MzM5ZjQ1NmNkNzk4NzVjNmRmMDM3NDI1MjAyMTIyYzhhNDE2YTRkNGU5ODcyMmNiMDFhYTVmODg5OCJ9fX0")));

        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.AQUA + "Functional", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVkNTNjYTUwYWQ1NjlmODU5NGM3YmE5MDc5YTc1MGRlMTZlOTRmYWJhZDFkNzJjOWJlNTRkMGNiZGZiN2Y3ZSJ9fX0"));
    }

    public void summonAquaticVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.AQUA + "Aquatic");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getAquamarineGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Dark Prismarine", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIxNTI0MWEyMGVkZWEwYTY0ZjY4MmYzOGE2OWQxZGNkZmFhOGQ5Y2M2NjhhNzhiM2I3MmMwODhmZDIyOTFkOSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Prismarine Brick", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWJkYTMyOTNlYzZhMDAzMzJlNjk4NjJjNzJhNGZmN2IxZDRhODBlZTY1YTRlMGU1MTViNTc0MzhiOTYxODcxYSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Prismarine", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTg2MjJmMzM3MTU1OGRjNGM3NDMxYzMyZTM1ZTc0YmVhZWE2NjA4MmMzZTRjY2NmNzAwNjIzMWY4ZjIzODNhZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Sea Lantern", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGY4NTAzZWY3OTEzNWUwZGI0NjE2N2MzMDRhZWM5Zjc4MTQwN2ZmODZiMDkzNDdkN2Y5OGZhMTQ4ZjkzOWIxMCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Tube Coral", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzlmYzNjZjU1MDhkNDBjMTQ2OTlkNWJmN2YyNTI3NTllMTk1NmFmOWE2NmQxNWE2YzM4NTQzNzhjNjFmNmQ5YSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Brain Coral", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU3ZGQ0M2U4ZjFjZDEzNzI2YzBmOTlhYzQwNDcxNTA0N2QxMmViNDJhMjhmZmM2YWU5YmZiM2I3MGQ3NjQwYSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Bubble Coral", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTNmMTgwNWVkY2QzMmI5N2FiMmYxOWEwM2JhYWIxZjhkNGRjNGRiOGVjN2EwMDRiMTRlYjY2NmQwOWZiODdmMiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Fire Coral", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZlOGRlZDNjNzRlYWNkNzg0MTJhOTAzYjkwNGY1NTc3ODUwZDFlMjBkMzQ4NzhmZDc3NTk3YWQxNjMzYmY3NCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Horn Coral", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ5MWI2ZTZhZTk0NTFjNDdlMDliZjFmZjIzZDUwZmZmODdiYTU5MjdhNTFmNDZmZmVkZjkyNmM1Y2JkZTc3ZiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Dried Kelp Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjgwNWNhZjNkMDJlMzVlNGFhZGMxOWFmMTVlODI3OTAxNzdmMWNkN2I3OWY0ZjViODhkOTQzYWM2YmUyMDNhMSJ9fX0")));

        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.AQUA + "Aquatic", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY3NjY5MmU2OTUwMmY5NTU1N2Y2ODdiY2VhMmI1NjUwZjNkOWM4ODQ0OGZkOWZhOGUwNDZkMWQzMTViNzJhYiJ9fX0"));
    }

    public void summonConcreteVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.AQUA + "Concrete");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getAquamarineGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "White Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDA5MjVjNDhiMDU2NjI4NDhlYzlmMDY4NWY4NThkODg5ZDNkYTExYjA3MTc4OGVhYTM2Y2NkOGYxZjMxZGUifX19")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Orange Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNmMTNlMjNlYzIzNDY3YWM1ZTZmNjVhODNmMjY4NmViZWNkOTk4NmRmNWY4Y2JjZDZmYWZjNDJlNjYyYjM4In19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Magenta Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjVlZjJkODdmN2MxZGVmNzk1MjNlOTU2NzY3YjgyODRjYTM4OWIyNDI5OWY1ZTQ2NWQ0NTc5ODlkNjJkZjgifX19")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Light Blue Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjQ3N2Y0NDM4OTM2MmM0Yzc2NGM4NDdhOTczOWJjNzhjMzI0NjdlYWI0ZTM4MzBhZTRjOGJlYWMzNDQyZWY5In19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Yellow Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNhNWJmZjMyNWVkNzFkOTdhMmRkZmM4M2FjZjA1ZmU3ZmQ5Y2I3Y2JkYjE1ZWJiNGYwNTYyMTkwN2U5ZjJiIn19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Lime Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI1OTljNjE4ZTkxNGMyNWEzN2Q2OWY1NDFhMjJiZWJiZjc1MTYxNTI2Mzc1NmYyNTYxZmFiNGNmYTM5ZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Pink Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI3NDlkMzdjM2Y5OGQ0NTdiZjU0MDIyYThiNjEzYTQzNTNlZDhkZDJlMTQ5NDI2ZmM0MmRiM2I3ZCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Gray Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzA2ZDdiZWZjODJmMjAxZjgzZTE5Mzc2N2U2M2Y4MTAzNzIxNWFmZDQ4M2EzOGQzNjk2NTk4MmNhNmQwIn19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Light Gray Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmJhMGM0YTBmZGNlOTIzYTkwNDgzMjhkNjY0MTQ3YzViOTI0NDkxZjRlZTVmZWE3MWYzZTllYzMxNCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Cyan Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMzYjUxZmVmMWQ3ZmRmMTkyNzRiYjc2ZmNlZGVjZWM3YTc3ZDAxMGNiMzRmZTAyOWZiNzk0Y2M1OWFiYSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Purple Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjBjMDVkNTYwZDhlMTNmMGNiMjVjMTVjODMxYmM1OTU0NTBjNWU1NGNlMzVmYTU0ZTE3ZTA0OTUyNjdjIn19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Blue Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdjN2EyOTcxMDNkYjA4NGFmNjI3M2I4Nzk4MDVhZmM4NTc3Y2M4MmM3NTJhYzI2NmNmOGQ3YTZlZWE2MCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Brown Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjUzODEyMGY2MThmMmNjZDNiYmRjMThjZDU3ODJlNjM4MmFlOWVlNzJkMDVmNWY4NjI3NmFkYTU0ZWY3ZWQifX19")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Green Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhYjdkYWViOGYzMzNjNzg4NmE3MGVmMzBjYWY0ZGVjNGE4Y2QxMDQ5M2YyMzgwMmYxNTE2YmRkMjNmY2QifX19")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Red Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjE4NTZjN2IzNzhkMzUwMjYyMTQzODQzZDFmOWZiYjIxOTExYTcxOTgzYmE3YjM5YTRkNGJhNWI2NmJlZGM2In19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Black Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZjMjM3MmI0NTc1NDJjNjU0ODNhZmExNDQyZTFjMzNhNWZmNzU4ZDM2MmVjZWM0MzQ4Nzk1MTcyODI0ZDg2OSJ9fX0")));

        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.AQUA + "Concrete", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJhMjUyZmQ1M2QxNjNiYTk2NTM2YWIxNDZiOGUyYTNiMDhmYmYyMGI5YzE0MmEzNjIwNGMzYmExMDZjZGZhZCJ9fX0"));
    }

    public void summonGemTraderVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.AQUA + "Gem Trader");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack diamond = new ItemStack(Material.DIAMOND);

        ItemStack aquamarineGem = Gems.getAquamarineGem();
        aquamarineGem.setAmount(1);
        ItemStack topazGem = Gems.getTopazGem();
        topazGem.setAmount(1);
        ItemStack rubyGem = Gems.getRubyGem();
        rubyGem.setAmount(1);
        ItemStack amethystGem = Gems.getAmethystGem();
        amethystGem.setAmount(1);
        ItemStack sapphireGem = Gems.getSapphireGem();
        sapphireGem.setAmount(1);

        recipes.add(getRecipe(diamond, aquamarineGem));
        recipes.add(getRecipe(diamond, topazGem));
        recipes.add(getRecipe(diamond, rubyGem));
        recipes.add(getRecipe(diamond, amethystGem));
        recipes.add(getRecipe(diamond, sapphireGem));


        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.AQUA + "Gem Trader", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWYwNTQzMTY1Njc4YzNhMWFmYTdlZmYwZGU3OGIzZGU4MzkyYWZiNWJkMGRmODczMzA2ZDk5N2ZjNTJhM2RhOCJ9fX0"));
    }

    public void summonGemCollectorVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.GOLD + "Gem Collector");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack heads = new ItemStack(Material.PLAYER_HEAD, 3);

        ItemStack aquamarineGem = Gems.getAquamarineGem();
        aquamarineGem.setAmount(1);
        ItemStack topazGem = Gems.getTopazGem();
        topazGem.setAmount(1);
        ItemStack rubyGem = Gems.getRubyGem();
        rubyGem.setAmount(1);
        ItemStack amethystGem = Gems.getAmethystGem();
        amethystGem.setAmount(1);
        ItemStack sapphireGem = Gems.getSapphireGem();
        sapphireGem.setAmount(1);

        recipes.add(getRecipe(aquamarineGem, heads));
        recipes.add(getRecipe(topazGem, heads));
        recipes.add(getRecipe(rubyGem, heads));
        recipes.add(getRecipe(amethystGem, heads));
        recipes.add(getRecipe(sapphireGem, heads));


        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.GOLD + "Gem Collector", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjUwNzcyYmZjYTBhZGEwMDIyNDQ5NjUzZDVmMGU3Yjc5ZTdlZmI2NjNhNzFiN2I3MWJiZDBiNTAyMDFlNmFlZCJ9fX0"));
    }

    public void summonMoreBlocksVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.BLUE + "More Blocks");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getSapphireGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Obsidian", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODRjMzA4NTVmODliNDkwYzZmZjIzMmRmM2QzZWM3NDMzYWI2MzYxMGE5YTk1N2M4OGE2Y2Q0MzI3YjA2YTQ5ZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Gilded Blackstone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJiZWQyZmM2MzIxZWM0MTA4MzE2ZjE4NjJjYmYyY2Q4MWYzODlmMjU4N2IxZjUzNjAyNTEzYjdhN2NhNTRhIn19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Bricks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ2YWJjY2RmZGI1MjMxZjc0NzEwZDc3OGMyMDg0ZjRjOGU0Y2Q2OTEzYTcwZThhNzIxM2FkYjYzOTE5MjUyNyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Polished Blackstone Bricks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk3NzUwYTI4NGZmNDAzOGNiMDM1ZjFkNmRiMDQ5M2ZmNTM5YzhhOTNhZTNjZGE2MTg2ZGYwYmU2MTYyYzMwNCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Sandstone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWJhZTQxMTk4NTdiZDgyYzdlZGVjMDM0ODIwYjc3ZDVhODM2MDBjOWRhZGNiYWI4NWE3MDQzMTM1MTU2MDFhYyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Quartz Bricks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTIxMDY0NTlkMjI0N2I0M2M2MjhkY2Y2YzY4Zjc0YmI3MDY2OTQ4YWRhMTFkMzNhODA0OTUzY2I1YzYwZjY3YyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Purpur Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTc0NWViNTM3YTA3YWNiYmE3ZmFiNTAwOTA1MWM0MmI4MmI3ZTg3N2ViODM2ODkxMzFkNDg3NjExOGYzOTMwMCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Polished Blackstone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWJiY2FhNDExN2UzNTA0NTI1OTNkMjA5MjcxMzM4NWMwYTQxM2NiNjJiYjljNDMyYTk3OWRiYTRlYjJkM2JjMiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Polished Bassalt", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVkNTYxMDVkMDY3OGFlNDBiNjZjNDQzMTE3ZTgwYTgxY2UyYzgyNTUzMzY2YWRjOGQ0NDc2ZmRjNWYxODFjOSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Snow Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNhOWUxNTM4MjhmNWZlMzJjMWM0ODVhYWUxNWMwYzFmNTE2ZWZlN2Y0NzBmYThjMGMzYjk0MDgxYjU2ZTBhNCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Terracotta", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODJkNWZlZmUyMGRhZjMxYzIzOGVlMjI3ZGQxNDE4MjdhZGE1ZWY4NDgyZDhkMzU3YmJlNWE3Y2Y0MGFmODUifX19")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "End Stone Bricks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjNzRlMDFiOGIzNTI1NjVjNzFiNGZiMjgxMjEwMDhmNjI4NDAxNzUzMmRjZDkxODAyMzM4ODIxZjdkYjQ4ZiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Red Mushroom Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2JhMWEzZDg3NmEwN2EzMDBkYWM1MTUwZWI3MGY0ZGE2NDE4NmM2NzcwZDQwOWMxODViYThjYjA5MDJlOGZhYiJ9fX0")));

        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.BLUE + "More Blocks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZhNDY0NjliMTBmNWY1MGM5MzE3ZjM1OTFkMzI5YmU0YTNjMGRiNjI5ZDE5NTU5NDlmZTEwOGFmM2MwNzI5ZSJ9fX0"));
    }

    public void summonNaturalVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.BLUE + "Natural");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getSapphireGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Sand", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE3OGQ4MWRhZjdlMGRmMjk3YmNiNWJiOTAwOWZiNjYzMjAzZjllMjA3MzYxOTRkZjgzYWFjOGVhOTQwODNiMSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Red Sand", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM5MzA5M2U4ZWU2NGVhZTBlNmNmYTUyZjI5ODhkMGQwNzZhNDI1YzQ0YmZhM2Q0MzQ0MGY3OTMxYzU0ZTU2YiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Gravel", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U0YThlNTFlMjg5OTA2OGU4MjNjODE4ZGIxYTBkYjk0NDdkMmYxNmY0YTE1NzhlNWQxNDYxZDcxNDc2NWU2MiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Grass Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjc4NDk5Nzc2ZmYyZGFiNzdhNTkzZGI2MDc3YTZmNzY1NDkzMWU1NWZmNTVlNWRhZDJkMjgwN2JiMGUzNzc2OCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Dirt", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFiNDNiOGMzZDM0ZjEyNWU1YTNmOGI5MmNkNDNkZmQxNGM2MjQwMmMzMzI5ODQ2MWQ0ZDRkN2NlMmQzYWVhIn19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Podzol Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTA0NmI4YzQzMDY0OTA4M2Y4NjRkNDBkZmFjODViZTBkNGRkYWRiZGRlOTE5ZTM2MjZjYzdmNDE3NGY1NGZlYiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Mycelium", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTNjMDc3ZWQ2ZDk5NjIyMzBkOWQ4NjcwYmFkODc2YzMzOTQxZmM5Y2ZiMTdlNmVkMGE4MDUyN2M2OTE4NTQ3OCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Clay", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkyNGVkYTcyYTA3ZjU2MzI4ZTAzNmYyMzBlNDg4ODE3ZGQ0ZDQ1NjgxOTEzZDJmYzliZjJkMmE1ZjE5NDFhZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Packed Ice Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE1NDIyODZkYTAzYWI3ZWVjYzRlNTYyYzNmOGI4YTFjZjc4MWRhMzA4YjA3OWUzYWMzMzE0NTYxYjljMWQ5OSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Melon", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjgzZjc4NWJmMGEwMDU0NDcwZDc0YWUyZDEyODUyNTI5NTZmZWUwYWJkMjg0YWZhMzcxNTQwNGVlYzY2ZWVlZiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Pumpkin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjliZTU1MDUzMTg4OGQ2MzI1NzE3Yjc1M2U2MjUyZTM4MDg2NzM2OWRlMDEyMjVmYTQwMmUxYWVlYzZmYWY3OSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Hay Bale", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGI1OTZiNzI4NmVlZjJkZjI3NWMwZmIzZjQ5MTY2NGM2ZWZkMzBjYTdkNDY5N2I5OTg0OWEwYTQ2YmRlM2QyNCJ9fX0")));


        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.BLUE + "Natural", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWM4YWE1MDI2OGUyMTUxOTM0MzE2OTFkMGQxZTI0NDUxM2YyMjY4NDQyMzc1ZDZmMTU2M2Q1MWQ0OGExZjE0NyJ9fX0"));
    }

    public void summonNetherVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.DARK_PURPLE + "Nether");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getAmethystGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Nether Wart Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAwNjIzOTk4YTI4NzA5ZmNmMDUzZmM1Njk2ODcxMTU4NjdlMGM3ZTU5ODlhOTRiNmU0YmY1MWQ4ZWQ3OWI0NiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Warped Wart Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U3ZTFlMGFkMWZmOGJlM2MxN2Q2MWUxNmQ5NGI2YjJiY2U3ODAyMmQ4OGRlMmFhNmM2NjliYmE1ZjVlYzA0NyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Magma", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVkNzEwMjZiODU4OTEyNWQ0Yjk3OWM5NzIxYzkwYjc3NTg0YmQ3YjIwOTQyODJkMGYyZmEzNTMwNjQ0MmFhNSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Soul Sand", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQyMTVjMDY5MDI2MDIxMGY5ODFjYTliYjgxZTY0MmIxODgyZWEzYzdiZjgxOTMwYmRhZmZmYjFkYWUxZmM5YSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Nether Bricks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NlNDZmZTNlNzU4MjYwZTYwMGE4OWMzM2QxM2UxZjYyN2ZkMDYxZDVlMGRkNzhiYWI4ODk5NTUxZTg0YWEwOCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Red Nether Bricks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc3Njk5ZWZkNGI5NzBhMGQxYzY1YWVmYmNiY2U4ZDNiZWJhODhmMWJlYzI5YmZhODY0NTA4OGY2YjI1YmM2MSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Netherrack", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZjNDRmN2Y3NmVkMTI0NzJmNzA1ZTk5YmI1ZDc5YTQ0NjUzNGU5ZGMwNmQyMjhiYjYxOTQxYzNmYjg0OTQ3YiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Crimson Nylium", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMThmMGI4ODQ2YmIyMmMyZGU2ODQ5NDgzYWU1MThmYWZiYmU0NDZhNzM1YTNlODgwNmUwMmYxYTQ3ZmMxNGQ3MCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Warped Nylium", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmUzZTFiZDJhMWJiMmRkNzczYmNmMTExMWMxNDZlMTAwNDMwNmFiOTk1ZDUxMDA5ZTY3ZDNhNWMyMTZmMjJlMSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Shrromlight", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQ2OTk3ZmQ5M2I1ZWJlNzFmYWUwMWQzZjNmOTc2MGMyMjM4N2FmNjBkN2VkNWRiZDE1YmI2Y2U0MDRjODA5YyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Glowstone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQ2ZDM0OTU4Zjg3MDQxYjVlNGFmNjQxMjM4NjAwYTI3N2YwNjRmZjIyZGMxM2Y2YWY5YjZiZTU1NDdkZDc5MCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Blackstone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkwNzQ1NzVkMDcwMTRlOTI4OTcyMTBmNTI3OTViYThhYzRhNTVhYjU5ZWYzNDhlMTFlOGRhMDMwMTJkNjc0NyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Bassalt", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzEwNDliZmJhNGY3ZWE5MzA1MWNhMTA5NWExMjNlMjNmYWRiNGFiYjJiZDU3YmVmOGI1Mzc4YTY2OTZiOGM5NCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Respawn Anchor", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFjZDQ3MzQ5NjQyNjQxM2VlYmI2NTIzZGVlNGEyZmMxN2MxOTJhYTMwMWQyMzQwNjcyY2FiYTI0OWMzZTRmNCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Lodestone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzEzOTUxZmQ4N2M2OGNmOGNhNDdkMThkYWVjYTVhZDNhZDgwNGIyNTE3NmYyYjRlZjQ4YmZjOTY4NmFiODA2NCJ9fX0")));


        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.DARK_PURPLE + "Nether", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDI5Mzc4NmU3ZTM2Yjc2NjQ4Y2MyOTdmYjliNjMyZTQ5MjEwNTIyMGUwZWRlMTc5NmQ4NTJjOWIxMjcxYWViMiJ9fX0"));
    }

    public void summonOresVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.DARK_PURPLE + "Ores");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getAmethystGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Coal Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmY0MDA1NzcyMTkzMzNlYjYxNGE0ZDkyYmVmMjM5ZDMwZTJjYjA5MmQyYWQ3ZGZlYTViZDlkMGYxYWM0MGJlYSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Iron Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWU4YWE3MWY1OTkxYWE3ZGNmNTc0ODM1MWU1ZjFhYzRmNTQ1OTY4NmM0ZTQ3NmY4NWRlYjU2OTI0NGYzNTk4NiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Lapis Lazuli Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmNkOTYyZTU5MDhlYWExYTIyYmNhNjY4Nzk4ZjlhZWQzM2IzN2I0OGUxNmEyZmM1OWViNGRhMGFiNDU3Y2RiZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Redstone Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgxMzM5MTc1ODljZjEwYzdmYzQ2ZWYzMzEyMDk2NjliZDdhYTUyNDQ4MDUxOTMyMzkyNDNiZDVhNTQ4ZmNjIn19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Gold Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGIwNGI5NGZkNDkxY2NjNDJjNzYyNmVlNWQ4NjdhNmExMTI2ZTkxODUzZjk0OTJhMTQ5YTYxZWI5ZGQ4MmU1OCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Diamond Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTk3N2JmNWMxZjFkZjQwYTk5YWU0NzhkMDRlOGMxZWE4NmI4ODFkZTBmZWZhZWZkN2JhNmQ1OTdjODcxZWM0NCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Emerald Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmU2MGY4OWMxNjVhMTllZjkxNDYwOWUyNDM0MzI0OTIxMmU1MWY2YmRlOTIxNGY2OWViMmUxMDRkMGY5NmVkYiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Nether Quartz Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGVhYzNhZmJhNjdhNWJhMTZhOTgzMTM3NDJhODJiMmJkODRkOTFhYzMyYTAyZWE4N2YxNWIxNDZjNzkwZTQ4YiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Nether Gold Ore", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjEzMjc5YTE5YjY1ODI5YWM3NDU5OGE3NjQ3OTgyNTQ5ZjdhMWUxMGIzNTRmMzk1ZTIzYjBlOGMzMGRmMjhlZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Ancient Debris", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTcxMTRmNWQzOTAxODhkZjA0NzdjZGY5YWVjZjViYzgxNDE2Y2U1ZTVjNTljZmNhYzU4MWE0M2YzOTAyYzFlIn19fQ")));


        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.DARK_PURPLE + "Ores", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDg2YzgwMjg4YzA1NmNhNjBlOGE1ZmU2MjhjYjUxYjkwYWFjNWJlMGMzYzgxZjk5MDA5Nzg1N2ZhMTJlY2I5ZCJ9fX0"));
    }

    public void summonPreciousVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.RED + "Precious");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getRubyGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Coal Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY4MjQ4NGNlNmMwYTgxMjE1MDMxZjk2YTcxNDliZWRlOThjOWQyMTVkMTZlYzhkZjAxZGFkZmYzZDA5NWRiNSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Iron Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmFhYjdkNjA4YmZkZGFjNWQ4ZTJjYWYzNzA0OWY2MmY4NDNmZmE5ZjA3NmMyNTJjMWRjMGE0NGVlNDZkMzIwNiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Lapis Lazuli Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBmOTcyYWU1ZDc4ZjVlMzA1ZGZiZWQ1NmM2NWI0YTNmYmFkZTZiM2E3NzkwYzdlYTUwZjI0NDM2MjZhYWI3OCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Redstone Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRiMjUwYzMwNDZmZGNhYWJlYjJmYzI4MjU2YTBmMjEzYTljYzYwNDhkN2ZjNzQwMDU4ZGYzMzgzMmJjZjE2YSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Gold Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmRjZjVmNzhiZjFkOWFkZTEyNmIyYzdmNmI0OTgwZGNmZTg5YjRlNjVjZDUzMmZjNjVhZDhiNzU3MjUzMGM1YiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Diamond Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2IxNWRiNDkzMzI1ZGNjZTI5MDhiZTkzMjMxNjY0MTA1YWRjYmZhOGNjMDM5NTc5NjYzMzgxNWVhMTU2ZmVmYSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Emerald Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFiOGZhNzY1NTU1MTVlMmRjYmUzODMzNmM2OTE4NDFhOWMyM2Q5OWM4OGY2NWQ5NmY0NDQ3Nzc1YmNjMTZlYiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Quartz Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTIxMDY0NTlkMjI0N2I0M2M2MjhkY2Y2YzY4Zjc0YmI3MDY2OTQ4YWRhMTFkMzNhODA0OTUzY2I1YzYwZjY3YyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Netherite Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzAxMWNkMmNmMWUzMmFlMTMxZDhlNTQyNDAzYmVhMDNjNDgyZmEzOTVhNzI3YTM3MDc2OTA0NzYyODQwMjkyZCJ9fX0")));


        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.RED + "Precious", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0MTJhYmQ4OTM3NzFiMzBhZmM4NzU1YTcwYWQ0MjY3YTYwOTVlNmQwZmQ3NGJjYmUzNDZkMjgzNDRkMDg3ZiJ9fX0"));
    }

    public void summonRedstoneVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.RED + "Redstone");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getRubyGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Redstone Block", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRiMjUwYzMwNDZmZGNhYWJlYjJmYzI4MjU2YTBmMjEzYTljYzYwNDhkN2ZjNzQwMDU4ZGYzMzgzMmJjZjE2YSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Dispenser", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDkzMzBkZGU0Zjk0MjRiNTBmMzZkNjJjODQzZWZlYWNiY2NmNWRhM2I5Y2UwNGFiZjE0ZTE3NWE5YzRmZGZmYSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Dropper", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjNmM2UwM2YwNGU2ZmRmYzAwZDkxZWM4OTQyMmU5NDgzY2FhNjdmYzAyMGZjYWNjOTEwMzQyZGJlOWNjODBiYiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Observer", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE5YzkzYTJhMzY0NDU2OTA4NDEwN2I3YjRkODAyNjE0NDk4ODRkOWZiYzM0YWRiZDg4YzYzNDIwNDUyMDJiMyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Unlit Redstone Lamp", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTAwZTg1ODU1YTY1OTVkODg5ZWYyNDYzOTZlMWQwNmIyYjg0MzFlMTAyZDcxYmViY2I1YzU5NTIzNzFiNzdiMyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Lit Redstone Lamp", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjcyZGM1ZDJjYzM0NTcxYWRhODA1ZjllZmFlOWY4YzVjZjA4MTU5ZjNhY2MwOGMwMDY1MjY5NDIxYjVjYjM4YyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Sticky Piston", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE2ZmVlNWI2MWVmYzYzYjliNDRmYzMyMjA2N2ZiNjIwZGQ1ZWE5YTdmYjJmODVhMGFhZGVjODAzOGJjMTM3NCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Piston", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjY2OTFkZTlmNTZiMzllNjhlODdmZGFhMTI4YzdjZTcxM2ZkMDA2NGM1Nzg2ODdiNjc5ZWU2YTg0Mzc1MDJlZiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "TNT", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA4OWY3OWUxZjc0ZTM3MGRjM2U2MWJhYWIyNmVlNzkzNWEyYTM4MTM4MGE0ZjJlOWRlMGY1YjBhNTI2ZTBhOCJ9fX0")));


        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.RED + "Redstone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFjNzk4NDk0ODE4YTNhMzc5ODcxNjZkODI3Y2RiYzk5NGI1YjdkMWFhZDE1NjMxMjE3YzkwYTYxOTZiOGZmNyJ9fX0"));
    }

    public void summonStonesVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.YELLOW + "Stones");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getTopazGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Stone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2MwOTMwYTFiZWMyNDg3ZjZhNmNiOTY0ZDczMTNmYjBhYmQwNDU5OWQ3NmZmNjUxOGQ5NzdlODNkNDYxMzg0OCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Polished Granite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDY4MTUxZjIxMzc2MGVlYWMwNWZiODYzZmU5ODVjMmMzNzM4OGM5MDVjODMxZDgwNWJjODA1ODkwY2Y4ZTllMyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Polished Diorite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWYyZWNhOTgyNzg2NmJhMTA1NWY1NjYzYmYyMDU0NzMxMDdjNzkwYjcyNGVkYTIxZjVkMTc0ODVmMGZjZWVlYiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Polished Andesite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGM3MzVlMjg5MzE5MWRlYzBmN2UxYzkwNDE3YmY4ZmRmODg5N2U4M2FkMDMzYmFiNGQzNDUzNWI3NTA2NzM2ZiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Granite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ4MzhiMmQwNDg0NzAwMTU1MmU0YmYyZTUzNzMzZWNlNzBmNTU1YzZmNGM2NmYxNzRjYzMxMWYzMDkxYzMyOSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Diorite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTVjMWVmMmQ0NDY1NzE1MDZiNTlmOTI5MTAzZDE5NTZmYzMxNTJmZDlkNDAwMjAyNmJjZTViMDI4YzkxN2ZlZiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Andesite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM1ZTE2Yzc2MWUwYWFhMmRkNTI4OWU0M2Y1MmNjNDcxNTY3Y2Q4ZjhjOGE0NzVhNGIyOTBhZWU4Y2ZhNDUzOCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Cobblestone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNkNTFmNGQwNjA5OGJiMWY0Y2VmZjYxOWM2ZGRjYTk3NmZjNzBlOWY4ODcxZWJlZTRlZWY2NTgwY2Y3NmIwZiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Mossy Cobblestone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTFiNzcyOTFlMTU3MWZkMjdkZWNhMWM3NzJlZjRmOTE3ZjU5YTlkNTllZjcwMjYxOTBmMTY2NzM1MDdmMmVlNyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Stone Bricks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlhMzEyNjIzZTFhOGFhNzVmZDczZmIyNWNhNjIwOTY0MmJjNWEyYzBlYTMwYjNiZTA2MmVjNGM4YzQzMjNmZSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Chisled Stone Bricks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBlYzk0MDZhYzA4NWNkOTU3OGRlYWIzMGNkNzQ2NzA0NmVmYzQyZjU4MjEyZmI4Mjg0MzllZTg4NWYxYmUxMyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Smooth Stone", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTU2MjQ2OTc1YmU4MTYxNmE1OWJjZTViNGU5MmJiNjE1ZDA3MjhjZmU0MWJlNmNmYjVmZmRjZGU1NzkyY2IwOCJ9fX0")));


        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.YELLOW + "Stones", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWVmMDk2ZWM5YmMzNTMxN2E5MmE5N2I2MjFiY2FkYjFmMTg0ZTRlOGVjOTc0ZTkwYWFhMjk2MGJkOTBhNWNlNyJ9fX0"));
    }

    public void summonWoodVillager(Location loc) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setVillagerLevel(5);
        villager.setProfession(Villager.Profession.MASON);
        villager.setSilent(true);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.YELLOW + "Wood");

        villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

        List<MerchantRecipe> recipes = new ArrayList<>();

        ItemStack gem = Gems.getTopazGem();
        gem.setAmount(1);

        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Oak Planks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzgxOTJhMWFkZDRhMTExMjRhZDFlOWI2M2ZhN2Q5NzViNTUwMGJmZjEyNzQyNGU3NWJmMjliMjlmNmFmYjI2NSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Spruce Planks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VhM2FhZjE5NDQ4MmEzOTc1YTE4ZDFlZWNlMjNjMGIzZWEwZjI1ODcwN2ZhZDc1YzVjYWE3NDUzMjA4OGRiIn19fQ")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Birch Planks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmNjODg4NzczZTlkMTFjYzhhYjE0ZmM4OGUwY2ZmOTRjY2IxNmM4OWE1OGZlYzE4MWUyMmYwZmIxOTRiYjlmZCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Jungle Planks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM3OTdmNWFhZTllZTkwNmViNTA2NmU3NDE2N2FjMGI2MGQ4NTc2ZjBkNGIyM2I0MTI0NDdmZDBmMjkwYmMwNyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Dark Oak Planks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODljYTc5OGZlM2U0MDIzOTcxYjc4NmE2NGE3MmJkOThhMTVmZjc1YTdmODExN2I1NjAxMDNlMjM0ZGUwNTJhYyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Acacia Planks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzAxNjk2NmY0ZjNkYWU2NTI0ODYxZTZjYzU2MzE3NDk5MDcwMWJlYWUyNjI3NzEyNzE4YzUxMGYzMzNjNmM4MyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Crimson Planks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlhYWIyZTE3NWE3ZTc3ZTM2NTVmNzY5MmQxYzY1MTczMTZiYTM3ODNiNWQ1ZmM1OWIzNDk1NGZmNmJhNjY1MyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Warped Planks", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQzYzNjZWJlMDZlMGRkZDljYzRhOTBlYzQ1Y2FjZGVjM2QxODU1ZGFmYzliODVhNTIzNDI3MGYwNmZmNzY0MyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Oak Log", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWEzY2ExNzdkMjE2OTMxM2YxNjU5NjQzOWRjZDYwZWJiNDgxM2IzNzhiMTdmZmIxNzUzNTQzNzBkNTEwZmIwZCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Spruce Log", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTczMTM3YzQ3ZWUxODllOWZkZDgxZWRhNDhjMTEyNjk0MGEwZTVkNmQ5Y2E0YzU5ZGQzYjgwY2NkOTI3YWRmOSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Birch Log", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmM0MDExOTliNzgzMzAwY2JkOWNiMWNlYWNmYTVlMjkzZmU3NTc1Nzc2MTg4Y2Q1YWE2Y2E4YmZmYjhlMmY1NyJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Jungle Log", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjAwMjlhNTJmOTQ1YjNlYWFjNzJlOWQzZDk5N2MxYTBiNTBlZmY5NTQ0NDE2YWIyYzNjNGU0YmIzOTc3ZjViZiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Dark Oak Log", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGIyZDUzNjA4NjM0OGZkZmMwODExZjljNmY2ZWMxYzBhN2Q2MDUwNzYxZjU5MjFiMmE3YTVkM2EwMDU5ZWMwMCJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Acacia Log", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY3ZTljNzRhYjc3YzAwOTE1NGE5YzczNzg0NmI1MjUxMDliOGMzMTdhNzE2Y2FlZGVjOTI3MDJhZmQwZGU2NSJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Crimson Stem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzRmOWE0ZDhhMDQ1NDRjYjIyOTdiZTE2MDM0MGFlZTlkMjE1MTk0NGY4OGE0NTQzZjdkYzhiZTlhN2IwN2Q1NiJ9fX0")));
        recipes.add(getRecipe(gem,
                getHead(ChatColor.YELLOW + "Warped Stem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTIyODE3ZWU1MmI3NWRlODEwM2Y1YWYyN2E3NWJiY2ZmODdhNDUzZWNlNTkzNTBmYjQxOTZmYWFiZmI2YjJiYyJ9fX0")));



        villager.setRecipes(recipes);
        villager.getEquipment().setHelmet(getHead(ChatColor.YELLOW + "Wood", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRjNTYwMGMzMWRiZDc5MDk2NGM1NTc0ZGNhMDRiNDg1Yjg0ZjQ1ZTQyOThiYTA2NmFhZGQzOWU5YTIwNzAwZCJ9fX0"));
    }

    public static MerchantRecipe getRecipe(ItemStack input, ItemStack output) {
        MerchantRecipe recipe = new MerchantRecipe(output, Integer.MAX_VALUE);
        recipe.addIngredient(input);
        recipe.setVillagerExperience(0);
        recipe.setExperienceReward(false);
        return recipe;
    }

    public static ItemStack getHead(String name, String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        GameProfile profile = new GameProfile(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), name);
        profile.getProperties().put("textures", new Property("textures", texture));
        Field field;
        try {
            field = headMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException x) {
            x.printStackTrace();
        }

        headMeta.setDisplayName(ChatColor.YELLOW + name);
        head.setItemMeta(headMeta);
        return head;
    }
}
