package me.teakivy.teakstweaks.Packs.Hermitcraft.WanderingTrades;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.DataManager.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MiniBlocks {

    static Main main = Main.getPlugin(Main.class);
    static DataManager data = main.data;

    public static List<MerchantRecipe> getBlockTrades() {
        List<MerchantRecipe> recipes = new ArrayList<>();
        int amount = data.getConfig().getInt("wandering-trades.mini-blocks.amount-of-blocks");
        List<Integer> numbers = new ArrayList<>();

        if (main.getConfig().getBoolean("config.dev-mode")) {
            for (int i = 0; i < 175; i++) {
                recipes.add(getMiniBlockTradeByNumber(i));
            }
        } else {

            for (int i = 0; i < amount; i++) {
                Random rand = new Random();
                int num = rand.nextInt(175);
                if (!numbers.contains(num)) {
                    recipes.add(getMiniBlockTradeByNumber(num));
                    numbers.add(num);
                } else {
                    int num2 = rand.nextInt(175);
                    if (!numbers.contains(num2)) {
                        recipes.add(getMiniBlockTradeByNumber(num2));
                        numbers.add(num);
                    }
                }
            }
        }

        return recipes;
    }

    public static MerchantRecipe getMiniBlockTradeByNumber(int num) {
        if (num == 0) return newBlockTrade("Ender Chest", Material.ENDER_CHEST, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZjYzQ4NmMyYmUxY2I5ZGZjYjJlNTNkZDlhM2U5YTg4M2JmYWRiMjdjYjk1NmYxODk2ZDYwMmI0MDY3In19fQ");
        if (num == 1) return newBlockTrade("Enchanting Table", Material.ENCHANTING_TABLE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTU2OTYzNzNhMTg3ZTZkMmRkY2RmMWQ2Nzc3NGNiMTFmM2E1MmE5NzY3YTA4NDU4OWIyM2YxOWE3ZjIzYTcxYSJ9fX0");
        if (num == 2) return newBlockTrade("Furnace", Material.FURNACE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTI5NTc3YjhmNDBkNjE0ZDJhODA5NDYxNWRhMTA2OGNmMTJjYjhmNzgzNDU4MzliZDBmN2VhYTc3YjA2ZTI3ZSJ9fX0");
        if (num == 3) return newBlockTrade("Crafting Table", Material.CRAFTING_TABLE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU2YWIzZmRjMmJjYjA3YWU3NjkwODAxYWUwMGFjMmZmZjU0MmQ3OTMwODk2MWMyYjU3OTM3MGVjZjY1NmMyOSJ9fX0");
        if (num == 4) return newBlockTrade("Chest", Material.CHEST, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDVjNmRjMmJiZjUxYzM2Y2ZjNzcxNDU4NWE2YTU2ODNlZjJiMTRkNDdkOGZmNzE0NjU0YTg5M2Y1ZGE2MjIifX19");
        if (num == 5) return newBlockTrade("Shulker Box", Material.SHULKER_BOX, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzlhYTg4YTA1ZmE1ZjMzYjYzMmU1NWE2NDU1YzE0ZmIwZmEyNzllNjMxNDdmOTc3OGQzOWRmOGY1OGE0NzkyMiJ9fX0");
        if (num == 6) return newBlockTrade("Jukebox", Material.JUKEBOX, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThmNTY3MzM5ZjQ1NmNkNzk4NzVjNmRmMDM3NDI1MjAyMTIyYzhhNDE2YTRkNGU5ODcyMmNiMDFhYTVmODg5OCJ9fX0");

        if (num == 7) return newBlockTrade("White Concrete", Material.WHITE_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDA5MjVjNDhiMDU2NjI4NDhlYzlmMDY4NWY4NThkODg5ZDNkYTExYjA3MTc4OGVhYTM2Y2NkOGYxZjMxZGUifX19");
        if (num == 8) return newBlockTrade("Orange Concrete", Material.ORANGE_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNmMTNlMjNlYzIzNDY3YWM1ZTZmNjVhODNmMjY4NmViZWNkOTk4NmRmNWY4Y2JjZDZmYWZjNDJlNjYyYjM4In19fQ");
        if (num == 9) return newBlockTrade("Magenta Concrete", Material.MAGENTA_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjVlZjJkODdmN2MxZGVmNzk1MjNlOTU2NzY3YjgyODRjYTM4OWIyNDI5OWY1ZTQ2NWQ0NTc5ODlkNjJkZjgifX19");
        if (num == 10) return newBlockTrade("Light Blue Concrete", Material.LIGHT_BLUE_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjQ3N2Y0NDM4OTM2MmM0Yzc2NGM4NDdhOTczOWJjNzhjMzI0NjdlYWI0ZTM4MzBhZTRjOGJlYWMzNDQyZWY5In19fQ");
        if (num == 11) return newBlockTrade("Yellow Concrete", Material.YELLOW_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNhNWJmZjMyNWVkNzFkOTdhMmRkZmM4M2FjZjA1ZmU3ZmQ5Y2I3Y2JkYjE1ZWJiNGYwNTYyMTkwN2U5ZjJiIn19fQ");
        if (num == 12) return newBlockTrade("Lime Concrete", Material.LIME_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI1OTljNjE4ZTkxNGMyNWEzN2Q2OWY1NDFhMjJiZWJiZjc1MTYxNTI2Mzc1NmYyNTYxZmFiNGNmYTM5ZSJ9fX0");
        if (num == 13) return newBlockTrade("Pink Concrete", Material.PINK_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI3NDlkMzdjM2Y5OGQ0NTdiZjU0MDIyYThiNjEzYTQzNTNlZDhkZDJlMTQ5NDI2ZmM0MmRiM2I3ZCJ9fX0");
        if (num == 14) return newBlockTrade("Gray Concrete", Material.GRAY_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzA2ZDdiZWZjODJmMjAxZjgzZTE5Mzc2N2U2M2Y4MTAzNzIxNWFmZDQ4M2EzOGQzNjk2NTk4MmNhNmQwIn19fQ");
        if (num == 15) return newBlockTrade("Light Gray Concrete", Material.LIGHT_GRAY_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmJhMGM0YTBmZGNlOTIzYTkwNDgzMjhkNjY0MTQ3YzViOTI0NDkxZjRlZTVmZWE3MWYzZTllYzMxNCJ9fX0");
        if (num == 16) return newBlockTrade("Cyan Concrete", Material.CYAN_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMzYjUxZmVmMWQ3ZmRmMTkyNzRiYjc2ZmNlZGVjZWM3YTc3ZDAxMGNiMzRmZTAyOWZiNzk0Y2M1OWFiYSJ9fX0");
        if (num == 17) return newBlockTrade("Purple Concrete", Material.PURPLE_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjBjMDVkNTYwZDhlMTNmMGNiMjVjMTVjODMxYmM1OTU0NTBjNWU1NGNlMzVmYTU0ZTE3ZTA0OTUyNjdjIn19fQ");
        if (num == 18) return newBlockTrade("Blue Concrete", Material.BLUE_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdjN2EyOTcxMDNkYjA4NGFmNjI3M2I4Nzk4MDVhZmM4NTc3Y2M4MmM3NTJhYzI2NmNmOGQ3YTZlZWE2MCJ9fX0");
        if (num == 19) return newBlockTrade("Brown Concrete", Material.BROWN_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjUzODEyMGY2MThmMmNjZDNiYmRjMThjZDU3ODJlNjM4MmFlOWVlNzJkMDVmNWY4NjI3NmFkYTU0ZWY3ZWQifX19");
        if (num == 20) return newBlockTrade("Green Concrete", Material.GREEN_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhYjdkYWViOGYzMzNjNzg4NmE3MGVmMzBjYWY0ZGVjNGE4Y2QxMDQ5M2YyMzgwMmYxNTE2YmRkMjNmY2QifX19");
        if (num == 21) return newBlockTrade("Red Concrete", Material.RED_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjE4NTZjN2IzNzhkMzUwMjYyMTQzODQzZDFmOWZiYjIxOTExYTcxOTgzYmE3YjM5YTRkNGJhNWI2NmJlZGM2In19fQ");
        if (num == 22) return newBlockTrade("Black Concrete", Material.BLACK_CONCRETE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZjMjM3MmI0NTc1NDJjNjU0ODNhZmExNDQyZTFjMzNhNWZmNzU4ZDM2MmVjZWM0MzQ4Nzk1MTcyODI0ZDg2OSJ9fX0");

        if (num == 23) return newBlockTrade("Obsidian", Material.OBSIDIAN, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODRjMzA4NTVmODliNDkwYzZmZjIzMmRmM2QzZWM3NDMzYWI2MzYxMGE5YTk1N2M4OGE2Y2Q0MzI3YjA2YTQ5ZSJ9fX0");
        if (num == 24) return newBlockTrade("Bricks", Material.BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ2YWJjY2RmZGI1MjMxZjc0NzEwZDc3OGMyMDg0ZjRjOGU0Y2Q2OTEzYTcwZThhNzIxM2FkYjYzOTE5MjUyNyJ9fX0");
        if (num == 25) return newBlockTrade("Sandstone", Material.SANDSTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWJhZTQxMTk4NTdiZDgyYzdlZGVjMDM0ODIwYjc3ZDVhODM2MDBjOWRhZGNiYWI4NWE3MDQzMTM1MTU2MDFhYyJ9fX0");
        if (num == 26) return newBlockTrade("Quartz Block", Material.QUARTZ_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTIxMDY0NTlkMjI0N2I0M2M2MjhkY2Y2YzY4Zjc0YmI3MDY2OTQ4YWRhMTFkMzNhODA0OTUzY2I1YzYwZjY3YyJ9fX0");
        if (num == 27) return newBlockTrade("Purpur Block", Material.PURPUR_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTc0NWViNTM3YTA3YWNiYmE3ZmFiNTAwOTA1MWM0MmI4MmI3ZTg3N2ViODM2ODkxMzFkNDg3NjExOGYzOTMwMCJ9fX0");
        if (num == 28) return newBlockTrade("Snow Block", Material.SNOW_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNhOWUxNTM4MjhmNWZlMzJjMWM0ODVhYWUxNWMwYzFmNTE2ZWZlN2Y0NzBmYThjMGMzYjk0MDgxYjU2ZTBhNCJ9fX0");
        if (num == 29) return newBlockTrade("Terracotta", Material.TERRACOTTA, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmRkMWJkYjk0MWJkNjkyOGZhMDFlM2NkY2E2MzdhZjViNjFmYmNiYWZhZTk2MmQ0MTQzMDQ5MTUzYjA3NThhMyJ9fX0");
        if (num == 30) return newBlockTrade("End Stone Bricks", Material.END_STONE_BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjNzRlMDFiOGIzNTI1NjVjNzFiNGZiMjgxMjEwMDhmNjI4NDAxNzUzMmRjZDkxODAyMzM4ODIxZjdkYjQ4ZiJ9fX0");
        if (num == 31) return newBlockTrade("Red Mushroom Block", Material.RED_MUSHROOM_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2JhMWEzZDg3NmEwN2EzMDBkYWM1MTUwZWI3MGY0ZGE2NDE4NmM2NzcwZDQwOWMxODViYThjYjA5MDJlOGZhYiJ9fX0");

        if (num == 32) return newBlockTrade("Sand", Material.SAND, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE3OGQ4MWRhZjdlMGRmMjk3YmNiNWJiOTAwOWZiNjYzMjAzZjllMjA3MzYxOTRkZjgzYWFjOGVhOTQwODNiMSJ9fX0");
        if (num == 33) return newBlockTrade("Red Sand", Material.RED_SAND, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM5MzA5M2U4ZWU2NGVhZTBlNmNmYTUyZjI5ODhkMGQwNzZhNDI1YzQ0YmZhM2Q0MzQ0MGY3OTMxYzU0ZTU2YiJ9fX0");
        if (num == 34) return newBlockTrade("Gravel", Material.GRAVEL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U0YThlNTFlMjg5OTA2OGU4MjNjODE4ZGIxYTBkYjk0NDdkMmYxNmY0YTE1NzhlNWQxNDYxZDcxNDc2NWU2MiJ9fX0");
        if (num == 35) return newBlockTrade("Grass Block", Material.GRASS_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjc4NDk5Nzc2ZmYyZGFiNzdhNTkzZGI2MDc3YTZmNzY1NDkzMWU1NWZmNTVlNWRhZDJkMjgwN2JiMGUzNzc2OCJ9fX0");
        if (num == 36) return newBlockTrade("Dirt", Material.DIRT, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFiNDNiOGMzZDM0ZjEyNWU1YTNmOGI5MmNkNDNkZmQxNGM2MjQwMmMzMzI5ODQ2MWQ0ZDRkN2NlMmQzYWVhIn19fQ");
        if (num == 37) return newBlockTrade("Podzol", Material.PODZOL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTA0NmI4YzQzMDY0OTA4M2Y4NjRkNDBkZmFjODViZTBkNGRkYWRiZGRlOTE5ZTM2MjZjYzdmNDE3NGY1NGZlYiJ9fX0");
        if (num == 38) return newBlockTrade("Mycelium", Material.MYCELIUM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTNjMDc3ZWQ2ZDk5NjIyMzBkOWQ4NjcwYmFkODc2YzMzOTQxZmM5Y2ZiMTdlNmVkMGE4MDUyN2M2OTE4NTQ3OCJ9fX0");
        if (num == 39) return newBlockTrade("Clay", Material.CLAY, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkyNGVkYTcyYTA3ZjU2MzI4ZTAzNmYyMzBlNDg4ODE3ZGQ0ZDQ1NjgxOTEzZDJmYzliZjJkMmE1ZjE5NDFhZSJ9fX0");

        if (num == 40) return newBlockTrade("Packed Ice", Material.PACKED_ICE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE1NDIyODZkYTAzYWI3ZWVjYzRlNTYyYzNmOGI4YTFjZjc4MWRhMzA4YjA3OWUzYWMzMzE0NTYxYjljMWQ5OSJ9fX0");
        if (num == 41) return newBlockTrade("Melon", Material.MELON, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjgzZjc4NWJmMGEwMDU0NDcwZDc0YWUyZDEyODUyNTI5NTZmZWUwYWJkMjg0YWZhMzcxNTQwNGVlYzY2ZWVlZiJ9fX0");
        if (num == 42) return newBlockTrade("Pumpkin", Material.PUMPKIN, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjliZTU1MDUzMTg4OGQ2MzI1NzE3Yjc1M2U2MjUyZTM4MDg2NzM2OWRlMDEyMjVmYTQwMmUxYWVlYzZmYWY3OSJ9fX0");
        if (num == 43) return newBlockTrade("Hay Block", Material.HAY_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGI1OTZiNzI4NmVlZjJkZjI3NWMwZmIzZjQ5MTY2NGM2ZWZkMzBjYTdkNDY5N2I5OTg0OWEwYTQ2YmRlM2QyNCJ9fX0");

        if (num == 44) return newBlockTrade("Dark Prismarine", Material.DARK_PRISMARINE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIxNTI0MWEyMGVkZWEwYTY0ZjY4MmYzOGE2OWQxZGNkZmFhOGQ5Y2M2NjhhNzhiM2I3MmMwODhmZDIyOTFkOSJ9fX0");
        if (num == 45) return newBlockTrade("Prismarine Bricks", Material.PRISMARINE_BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWJkYTMyOTNlYzZhMDAzMzJlNjk4NjJjNzJhNGZmN2IxZDRhODBlZTY1YTRlMGU1MTViNTc0MzhiOTYxODcxYSJ9fX0");
        if (num == 46) return newBlockTrade("Prismarine", Material.PRISMARINE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTg2MjJmMzM3MTU1OGRjNGM3NDMxYzMyZTM1ZTc0YmVhZWE2NjA4MmMzZTRjY2NmNzAwNjIzMWY4ZjIzODNhZSJ9fX0");
        if (num == 47) return newBlockTrade("Sea Lantern", Material.SEA_LANTERN, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGY4NTAzZWY3OTEzNWUwZGI0NjE2N2MzMDRhZWM5Zjc4MTQwN2ZmODZiMDkzNDdkN2Y5OGZhMTQ4ZjkzOWIxMCJ9fX0");
        if (num == 48) return newBlockTrade("Tube Coral Block", Material.TUBE_CORAL_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzlmYzNjZjU1MDhkNDBjMTQ2OTlkNWJmN2YyNTI3NTllMTk1NmFmOWE2NmQxNWE2YzM4NTQzNzhjNjFmNmQ5YSJ9fX0");
        if (num == 49) return newBlockTrade("Brain Coral Block", Material.BRAIN_CORAL_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU3ZGQ0M2U4ZjFjZDEzNzI2YzBmOTlhYzQwNDcxNTA0N2QxMmViNDJhMjhmZmM2YWU5YmZiM2I3MGQ3NjQwYSJ9fX0");
        if (num == 50) return newBlockTrade("Bubble Coral Block", Material.BUBBLE_CORAL_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTNmMTgwNWVkY2QzMmI5N2FiMmYxOWEwM2JhYWIxZjhkNGRjNGRiOGVjN2EwMDRiMTRlYjY2NmQwOWZiODdmMiJ9fX0");
        if (num == 51) return newBlockTrade("Fire Coral Block", Material.FIRE_CORAL_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZlOGRlZDNjNzRlYWNkNzg0MTJhOTAzYjkwNGY1NTc3ODUwZDFlMjBkMzQ4NzhmZDc3NTk3YWQxNjMzYmY3NCJ9fX0");
        if (num == 52) return newBlockTrade("Horn Coral Block", Material.HORN_CORAL_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ5MWI2ZTZhZTk0NTFjNDdlMDliZjFmZjIzZDUwZmZmODdiYTU5MjdhNTFmNDZmZmVkZjkyNmM1Y2JkZTc3ZiJ9fX0");
        if (num == 53) return newBlockTrade("Dried Kelp Block", Material.DRIED_KELP_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjgwNWNhZjNkMDJlMzVlNGFhZGMxOWFmMTVlODI3OTAxNzdmMWNkN2I3OWY0ZjViODhkOTQzYWM2YmUyMDNhMSJ9fX0");

        if (num == 54) return newBlockTrade("Nether Wart Block", Material.NETHER_WART_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAwNjIzOTk4YTI4NzA5ZmNmMDUzZmM1Njk2ODcxMTU4NjdlMGM3ZTU5ODlhOTRiNmU0YmY1MWQ4ZWQ3OWI0NiJ9fX0");
        if (num == 55) return newBlockTrade("Magma Block", Material.MAGMA_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVkNzEwMjZiODU4OTEyNWQ0Yjk3OWM5NzIxYzkwYjc3NTg0YmQ3YjIwOTQyODJkMGYyZmEzNTMwNjQ0MmFhNSJ9fX0");
        if (num == 56) return newBlockTrade("Red Nether Bricks", Material.RED_NETHER_BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc3Njk5ZWZkNGI5NzBhMGQxYzY1YWVmYmNiY2U4ZDNiZWJhODhmMWJlYzI5YmZhODY0NTA4OGY2YjI1YmM2MSJ9fX0");
        if (num == 57) return newBlockTrade("Soul Sand", Material.SOUL_SAND, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQyMTVjMDY5MDI2MDIxMGY5ODFjYTliYjgxZTY0MmIxODgyZWEzYzdiZjgxOTMwYmRhZmZmYjFkYWUxZmM5YSJ9fX0");
        if (num == 58) return newBlockTrade("Nether Bricks", Material.NETHER_BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NlNDZmZTNlNzU4MjYwZTYwMGE4OWMzM2QxM2UxZjYyN2ZkMDYxZDVlMGRkNzhiYWI4ODk5NTUxZTg0YWEwOCJ9fX0");
        if (num == 59) return newBlockTrade("Netherrack", Material.NETHERRACK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZjNDRmN2Y3NmVkMTI0NzJmNzA1ZTk5YmI1ZDc5YTQ0NjUzNGU5ZGMwNmQyMjhiYjYxOTQxYzNmYjg0OTQ3YiJ9fX0");
        if (num == 60) return newBlockTrade("Glowstone", Material.GLOWSTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQ2ZDM0OTU4Zjg3MDQxYjVlNGFmNjQxMjM4NjAwYTI3N2YwNjRmZjIyZGMxM2Y2YWY5YjZiZTU1NDdkZDc5MCJ9fX0");

        if (num == 61) return newBlockTrade("Coal Ore", Material.COAL_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVkZTZkYTBjMzNkODAyNjFjNzhhZmJlMjY0Mjc5M2M2YTM4NTJkODdjZjY1ZDVhMjU5Njg1OGMwYjViY2EwNiJ9fX0");
        if (num == 62) return newBlockTrade("Iron Ore", Material.IRON_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWQwNDA5ZmZiMGZmMTBlNTliNzIwY2U0MThhNDQ5NWM5MjI3YzdlNzQ1YTFiNTVmMzZjNzYwODliMDNmZTA0NCJ9fX0");
        if (num == 63) return newBlockTrade("Gold Ore", Material.GOLD_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzY4OTY5NGM5NzlkMWM0YmM0NDMzZjdkMzM0ZGQzNzM3NjQ0NGQxZmM0MzY2MmFkODdjZDM5ODRlOWI5MTNiYyJ9fX0");
        if (num == 64) return newBlockTrade("Lapis Ore", Material.LAPIS_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4ZDkxMzE4NWU1YTM2NWI5NWE4NThkMmU2Yzk5NDE5ODU2NjM5ZDAzNWRjYmY3N2NkNDQwNDU2MGEyNTM4NCJ9fX0");
        if (num == 65) return newBlockTrade("Diamond Ore", Material.DIAMOND_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmZiZjBhYzMxNDFkNWI2MzJjYmNkMzY1OWViODE1MmIyOWQ1ZmEwZDA5ZTRiMDhkYmU4MTNhYzg0MWU0MzQ4YiJ9fX0");
        if (num == 66) return newBlockTrade("Redstone Ore", Material.REDSTONE_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk4MmI0MWNlMmViOWZlYjM1MTZlZWY3NTMzNjk0YWQ2YWE2YzE4NTA5MDFiYjU5NDkzOTkwOWQ3MzNiZjJkYiJ9fX0");
        if (num == 67) return newBlockTrade("Emerald Ore", Material.EMERALD_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmI0NTQ4MmE4Y2FlNjk0NmViZTUxMTUxNjc3MGQxMDE4MjA0ZmRjNDgyYmY3OTQyYjQxNzUyNjYxMWExYzBiNSJ9fX0");
        if (num == 68) return newBlockTrade("Nether Quartz Ore", Material.NETHER_QUARTZ_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGVhYzNhZmJhNjdhNWJhMTZhOTgzMTM3NDJhODJiMmJkODRkOTFhYzMyYTAyZWE4N2YxNWIxNDZjNzkwZTQ4YiJ9fX0");

        if (num == 69) return newBlockTrade("Lapis Lazuli Block", Material.LAPIS_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBmOTcyYWU1ZDc4ZjVlMzA1ZGZiZWQ1NmM2NWI0YTNmYmFkZTZiM2E3NzkwYzdlYTUwZjI0NDM2MjZhYWI3OCJ9fX0");
        if (num == 70) return newBlockTrade("Gold Block", Material.GOLD_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmRjZjVmNzhiZjFkOWFkZTEyNmIyYzdmNmI0OTgwZGNmZTg5YjRlNjVjZDUzMmZjNjVhZDhiNzU3MjUzMGM1YiJ9fX0");
        if (num == 71) return newBlockTrade("Iron Block", Material.IRON_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmFhYjdkNjA4YmZkZGFjNWQ4ZTJjYWYzNzA0OWY2MmY4NDNmZmE5ZjA3NmMyNTJjMWRjMGE0NGVlNDZkMzIwNiJ9fX0");
        if (num == 72) return newBlockTrade("Diamond Block", Material.DIAMOND_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2IxNWRiNDkzMzI1ZGNjZTI5MDhiZTkzMjMxNjY0MTA1YWRjYmZhOGNjMDM5NTc5NjYzMzgxNWVhMTU2ZmVmYSJ9fX0");
        if (num == 73) return newBlockTrade("Emerald Block", Material.EMERALD_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFiOGZhNzY1NTU1MTVlMmRjYmUzODMzNmM2OTE4NDFhOWMyM2Q5OWM4OGY2NWQ5NmY0NDQ3Nzc1YmNjMTZlYiJ9fX0");
        if (num == 74) return newBlockTrade("Coal Block", Material.COAL_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY4MjQ4NGNlNmMwYTgxMjE1MDMxZjk2YTcxNDliZWRlOThjOWQyMTVkMTZlYzhkZjAxZGFkZmYzZDA5NWRiNSJ9fX0");
        if (num == 75) return newBlockTrade("Redstone Block", Material.REDSTONE_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRiMjUwYzMwNDZmZGNhYWJlYjJmYzI4MjU2YTBmMjEzYTljYzYwNDhkN2ZjNzQwMDU4ZGYzMzgzMmJjZjE2YSJ9fX0");

        if (num == 76) return newBlockTrade("Dispenser", Material.DISPENSER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDkzMzBkZGU0Zjk0MjRiNTBmMzZkNjJjODQzZWZlYWNiY2NmNWRhM2I5Y2UwNGFiZjE0ZTE3NWE5YzRmZGZmYSJ9fX0");
        if (num == 77) return newBlockTrade("Dropper", Material.DROPPER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjNmM2UwM2YwNGU2ZmRmYzAwZDkxZWM4OTQyMmU5NDgzY2FhNjdmYzAyMGZjYWNjOTEwMzQyZGJlOWNjODBiYiJ9fX0");
        if (num == 78) return newBlockTrade("Observer", Material.OBSERVER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE5YzkzYTJhMzY0NDU2OTA4NDEwN2I3YjRkODAyNjE0NDk4ODRkOWZiYzM0YWRiZDg4YzYzNDIwNDUyMDJiMyJ9fX0");
        if (num == 79) return newBlockTrade("Unlit Redstone Lamp", Material.REDSTONE_LAMP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTAwZTg1ODU1YTY1OTVkODg5ZWYyNDYzOTZlMWQwNmIyYjg0MzFlMTAyZDcxYmViY2I1YzU5NTIzNzFiNzdiMyJ9fX0");
        if (num == 80) return newBlockTrade("Sticky Piston", Material.STICKY_PISTON, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE2ZmVlNWI2MWVmYzYzYjliNDRmYzMyMjA2N2ZiNjIwZGQ1ZWE5YTdmYjJmODVhMGFhZGVjODAzOGJjMTM3NCJ9fX0");
        if (num == 81) return newBlockTrade("Piston", Material.PISTON, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjY2OTFkZTlmNTZiMzllNjhlODdmZGFhMTI4YzdjZTcxM2ZkMDA2NGM1Nzg2ODdiNjc5ZWU2YTg0Mzc1MDJlZiJ9fX0");
        if (num == 82) return newBlockTrade("TNT", Material.TNT, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA4OWY3OWUxZjc0ZTM3MGRjM2U2MWJhYWIyNmVlNzkzNWEyYTM4MTM4MGE0ZjJlOWRlMGY1YjBhNTI2ZTBhOCJ9fX0");

        if (num == 83) return newBlockTrade("Stone", Material.STONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2MwOTMwYTFiZWMyNDg3ZjZhNmNiOTY0ZDczMTNmYjBhYmQwNDU5OWQ3NmZmNjUxOGQ5NzdlODNkNDYxMzg0OCJ9fX0");
        if (num == 84) return newBlockTrade("Polished Granite", Material.POLISHED_GRANITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDY4MTUxZjIxMzc2MGVlYWMwNWZiODYzZmU5ODVjMmMzNzM4OGM5MDVjODMxZDgwNWJjODA1ODkwY2Y4ZTllMyJ9fX0");
        if (num == 85) return newBlockTrade("Polished Diorite", Material.POLISHED_DIORITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWYyZWNhOTgyNzg2NmJhMTA1NWY1NjYzYmYyMDU0NzMxMDdjNzkwYjcyNGVkYTIxZjVkMTc0ODVmMGZjZWVlYiJ9fX0");
        if (num == 86) return newBlockTrade("Polished Andesite", Material.POLISHED_ANDESITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGM3MzVlMjg5MzE5MWRlYzBmN2UxYzkwNDE3YmY4ZmRmODg5N2U4M2FkMDMzYmFiNGQzNDUzNWI3NTA2NzM2ZiJ9fX0");
        if (num == 87) return newBlockTrade("Granite", Material.GRANITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ4MzhiMmQwNDg0NzAwMTU1MmU0YmYyZTUzNzMzZWNlNzBmNTU1YzZmNGM2NmYxNzRjYzMxMWYzMDkxYzMyOSJ9fX0");
        if (num == 88) return newBlockTrade("Diorite", Material.DIORITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTVjMWVmMmQ0NDY1NzE1MDZiNTlmOTI5MTAzZDE5NTZmYzMxNTJmZDlkNDAwMjAyNmJjZTViMDI4YzkxN2ZlZiJ9fX0");
        if (num == 89) return newBlockTrade("Andesite", Material.ANDESITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM1ZTE2Yzc2MWUwYWFhMmRkNTI4OWU0M2Y1MmNjNDcxNTY3Y2Q4ZjhjOGE0NzVhNGIyOTBhZWU4Y2ZhNDUzOCJ9fX0");
        if (num == 90) return newBlockTrade("Cobblestone", Material.COBBLESTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNkNTFmNGQwNjA5OGJiMWY0Y2VmZjYxOWM2ZGRjYTk3NmZjNzBlOWY4ODcxZWJlZTRlZWY2NTgwY2Y3NmIwZiJ9fX0");
        if (num == 91) return newBlockTrade("Mossy Cobblestone", Material.MOSSY_COBBLESTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTFiNzcyOTFlMTU3MWZkMjdkZWNhMWM3NzJlZjRmOTE3ZjU5YTlkNTllZjcwMjYxOTBmMTY2NzM1MDdmMmVlNyJ9fX0");
        if (num == 92) return newBlockTrade("Stone Bricks", Material.STONE_BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlhMzEyNjIzZTFhOGFhNzVmZDczZmIyNWNhNjIwOTY0MmJjNWEyYzBlYTMwYjNiZTA2MmVjNGM4YzQzMjNmZSJ9fX0");
        if (num == 93) return newBlockTrade("Chiseled Stone Bricks", Material.CHISELED_STONE_BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBlYzk0MDZhYzA4NWNkOTU3OGRlYWIzMGNkNzQ2NzA0NmVmYzQyZjU4MjEyZmI4Mjg0MzllZTg4NWYxYmUxMyJ9fX0");
        if (num == 94) return newBlockTrade("Smooth Stone", Material.SMOOTH_STONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTU2MjQ2OTc1YmU4MTYxNmE1OWJjZTViNGU5MmJiNjE1ZDA3MjhjZmU0MWJlNmNmYjVmZmRjZGU1NzkyY2IwOCJ9fX0");

        if (num == 95) return newBlockTrade("Oak Planks", Material.OAK_PLANKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzgxOTJhMWFkZDRhMTExMjRhZDFlOWI2M2ZhN2Q5NzViNTUwMGJmZjEyNzQyNGU3NWJmMjliMjlmNmFmYjI2NSJ9fX0");
        if (num == 96) return newBlockTrade("Spruce Planks", Material.SPRUCE_PLANKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VhM2FhZjE5NDQ4MmEzOTc1YTE4ZDFlZWNlMjNjMGIzZWEwZjI1ODcwN2ZhZDc1YzVjYWE3NDUzMjA4OGRiIn19fQ");
        if (num == 97) return newBlockTrade("Birch Planks", Material.BIRCH_PLANKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmNjODg4NzczZTlkMTFjYzhhYjE0ZmM4OGUwY2ZmOTRjY2IxNmM4OWE1OGZlYzE4MWUyMmYwZmIxOTRiYjlmZCJ9fX0");
        if (num == 98) return newBlockTrade("Jungle Planks", Material.JUNGLE_PLANKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM3OTdmNWFhZTllZTkwNmViNTA2NmU3NDE2N2FjMGI2MGQ4NTc2ZjBkNGIyM2I0MTI0NDdmZDBmMjkwYmMwNyJ9fX0");
        if (num == 99) return newBlockTrade("Dark Oak Planks", Material.DARK_OAK_PLANKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODljYTc5OGZlM2U0MDIzOTcxYjc4NmE2NGE3MmJkOThhMTVmZjc1YTdmODExN2I1NjAxMDNlMjM0ZGUwNTJhYyJ9fX0");
        if (num == 101) return newBlockTrade("Acacia Planks", Material.ACACIA_PLANKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzAxNjk2NmY0ZjNkYWU2NTI0ODYxZTZjYzU2MzE3NDk5MDcwMWJlYWUyNjI3NzEyNzE4YzUxMGYzMzNjNmM4MyJ9fX0");
        if (num == 102) return newBlockTrade("Oak Log", Material.OAK_LOG, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWEzY2ExNzdkMjE2OTMxM2YxNjU5NjQzOWRjZDYwZWJiNDgxM2IzNzhiMTdmZmIxNzUzNTQzNzBkNTEwZmIwZCJ9fX0");
        if (num == 103) return newBlockTrade("Spruce Log", Material.SPRUCE_LOG, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTczMTM3YzQ3ZWUxODllOWZkZDgxZWRhNDhjMTEyNjk0MGEwZTVkNmQ5Y2E0YzU5ZGQzYjgwY2NkOTI3YWRmOSJ9fX0");
        if (num == 104) return newBlockTrade("Birch Log", Material.BIRCH_LOG, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmM0MDExOTliNzgzMzAwY2JkOWNiMWNlYWNmYTVlMjkzZmU3NTc1Nzc2MTg4Y2Q1YWE2Y2E4YmZmYjhlMmY1NyJ9fX0");
        if (num == 105) return newBlockTrade("Jungle Log", Material.JUNGLE_LOG, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjAwMjlhNTJmOTQ1YjNlYWFjNzJlOWQzZDk5N2MxYTBiNTBlZmY5NTQ0NDE2YWIyYzNjNGU0YmIzOTc3ZjViZiJ9fX0");
        if (num == 106) return newBlockTrade("Dark Oak Log", Material.DARK_OAK_LOG, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGIyZDUzNjA4NjM0OGZkZmMwODExZjljNmY2ZWMxYzBhN2Q2MDUwNzYxZjU5MjFiMmE3YTVkM2EwMDU5ZWMwMCJ9fX0");
        if (num == 107) return newBlockTrade("Acacia Log", Material.ACACIA_LOG, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY3ZTljNzRhYjc3YzAwOTE1NGE5YzczNzg0NmI1MjUxMDliOGMzMTdhNzE2Y2FlZGVjOTI3MDJhZmQwZGU2NSJ9fX0");

        if (num == 108) return newBlockTrade("Crimson Nylium", Material.CRIMSON_NYLIUM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMThmMGI4ODQ2YmIyMmMyZGU2ODQ5NDgzYWU1MThmYWZiYmU0NDZhNzM1YTNlODgwNmUwMmYxYTQ3ZmMxNGQ3MCJ9fX0");
        if (num == 109) return newBlockTrade("Warped Nylium", Material.WARPED_NYLIUM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmUzZTFiZDJhMWJiMmRkNzczYmNmMTExMWMxNDZlMTAwNDMwNmFiOTk1ZDUxMDA5ZTY3ZDNhNWMyMTZmMjJlMSJ9fX0");
        if (num == 110) return newBlockTrade("Lit Redstone Lamp", Material.REDSTONE_LAMP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjcyZGM1ZDJjYzM0NTcxYWRhODA1ZjllZmFlOWY4YzVjZjA4MTU5ZjNhY2MwOGMwMDY1MjY5NDIxYjVjYjM4YyJ9fX0");
        if (num == 111) return newBlockTrade("Crimson Stem", Material.CRIMSON_STEM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzRmOWE0ZDhhMDQ1NDRjYjIyOTdiZTE2MDM0MGFlZTlkMjE1MTk0NGY4OGE0NTQzZjdkYzhiZTlhN2IwN2Q1NiJ9fX0");
        if (num == 112) return newBlockTrade("Warped Stem", Material.WARPED_STEM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTIyODE3ZWU1MmI3NWRlODEwM2Y1YWYyN2E3NWJiY2ZmODdhNDUzZWNlNTkzNTBmYjQxOTZmYWFiZmI2YjJiYyJ9fX0");
        if (num == 113) return newBlockTrade("Crimson Planks", Material.CRIMSON_PLANKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlhYWIyZTE3NWE3ZTc3ZTM2NTVmNzY5MmQxYzY1MTczMTZiYTM3ODNiNWQ1ZmM1OWIzNDk1NGZmNmJhNjY1MyJ9fX0");
        if (num == 114) return newBlockTrade("Warped Planks", Material.WARPED_PLANKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQzYzNjZWJlMDZlMGRkZDljYzRhOTBlYzQ1Y2FjZGVjM2QxODU1ZGFmYzliODVhNTIzNDI3MGYwNmZmNzY0MyJ9fX0");

        if (num == 115) return newBlockTrade("Netherite Block", Material.NETHERITE_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzAxMWNkMmNmMWUzMmFlMTMxZDhlNTQyNDAzYmVhMDNjNDgyZmEzOTVhNzI3YTM3MDc2OTA0NzYyODQwMjkyZCJ9fX0");
        if (num == 116) return newBlockTrade("Ancient Debris", Material.ANCIENT_DEBRIS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTcxMTRmNWQzOTAxODhkZjA0NzdjZGY5YWVjZjViYzgxNDE2Y2U1ZTVjNTljZmNhYzU4MWE0M2YzOTAyYzFlIn19fQ");
        if (num == 117) return newBlockTrade("Nether Gold Ore", Material.NETHER_GOLD_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjEzMjc5YTE5YjY1ODI5YWM3NDU5OGE3NjQ3OTgyNTQ5ZjdhMWUxMGIzNTRmMzk1ZTIzYjBlOGMzMGRmMjhlZSJ9fX0");

        if (num == 118) return newBlockTrade("Blackstone", Material.BLACKSTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkwNzQ1NzVkMDcwMTRlOTI4OTcyMTBmNTI3OTViYThhYzRhNTVhYjU5ZWYzNDhlMTFlOGRhMDMwMTJkNjc0NyJ9fX0");
        if (num == 119) return newBlockTrade("Polished Blackstone", Material.POLISHED_BLACKSTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWJiY2FhNDExN2UzNTA0NTI1OTNkMjA5MjcxMzM4NWMwYTQxM2NiNjJiYjljNDMyYTk3OWRiYTRlYjJkM2JjMiJ9fX0");
        if (num == 120) return newBlockTrade("Polished Blackstone Bricks", Material.POLISHED_BLACKSTONE_BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk3NzUwYTI4NGZmNDAzOGNiMDM1ZjFkNmRiMDQ5M2ZmNTM5YzhhOTNhZTNjZGE2MTg2ZGYwYmU2MTYyYzMwNCJ9fX0");
        if (num == 121) return newBlockTrade("Chiseled Polished Blackstone", Material.CHISELED_POLISHED_BLACKSTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJlODJmMGZlZDY5NTc4ZDI1ZjE1M2ZmMTVhYTBmNGRkNTdhZDY5ZGI2ZGNkNzdhYThkZmFmZTA1NmI2ZGNiNiJ9fX0");
        if (num == 122) return newBlockTrade("Gilded Blackstone", Material.GILDED_BLACKSTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJiZWQyZmM2MzIxZWM0MTA4MzE2ZjE4NjJjYmYyY2Q4MWYzODlmMjU4N2IxZjUzNjAyNTEzYjdhN2NhNTRhIn19fQ");
        if (num == 123) return newBlockTrade("Shroomlight", Material.SHROOMLIGHT, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQ2OTk3ZmQ5M2I1ZWJlNzFmYWUwMWQzZjNmOTc2MGMyMjM4N2FmNjBkN2VkNWRiZDE1YmI2Y2U0MDRjODA5YyJ9fX0");
        if (num == 124) return newBlockTrade("Chiseled Nether Bricks", Material.CHISELED_NETHER_BRICKS, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMxODBjYjA4MmQ5MTBjNjIwOGQ0M2FjNjE5ZmNiMGI2NTRhYjZmNzBmZDgxYzM1OTkxZmNhMjFhYWI5MDhjMSJ9fX0");
        if (num == 125) return newBlockTrade("Basalt", Material.BASALT, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzEwNDliZmJhNGY3ZWE5MzA1MWNhMTA5NWExMjNlMjNmYWRiNGFiYjJiZDU3YmVmOGI1Mzc4YTY2OTZiOGM5NCJ9fX0");
        if (num == 126) return newBlockTrade("Polished Basalt", Material.POLISHED_BASALT, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVkNTYxMDVkMDY3OGFlNDBiNjZjNDQzMTE3ZTgwYTgxY2UyYzgyNTUzMzY2YWRjOGQ0NDc2ZmRjNWYxODFjOSJ9fX0");
        if (num == 127) return newBlockTrade("Warped Wart Block", Material.WARPED_WART_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U3ZTFlMGFkMWZmOGJlM2MxN2Q2MWUxNmQ5NGI2YjJiY2U3ODAyMmQ4OGRlMmFhNmM2NjliYmE1ZjVlYzA0NyJ9fX0");

        if (num == 128) return newBlockTrade("Crying Obsidian", Material.CRYING_OBSIDIAN, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmRmODJiZGM4YTRmN2QwMjZiMGM0ZjE3ZDA2YjhiZjVkYzUyNmEyNTQzODk4NjYyOTFlN2I2YjQ0ZjkwYjBlIn19fQ");
        if (num == 129) return newBlockTrade("Target", Material.TARGET, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDIwZTE3ZWJjYjFiMTgzNzg2ZGRlMjUwNDViODMyYjIzOGY3NWEzNzRhYWNlMGM1YTRmNjllOTQyNWVkODllNyJ9fX0");
        if (num == 130) return newBlockTrade("Slime Block", Material.SLIME_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNiZmM0ZjAxYTgyMDYwZTgwODdhMzE0YTlmY2I1ZmZjMWVhYzlhYzFkMzA0ZGEzYzhiMGQ2NmFkNzE1YzViMCJ9fX0");
        if (num == 131) return newBlockTrade("Honey Block", Material.HONEY_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThkYTg2NWFhODNiNjAwNDM4MGMxZWRhOGNjZTNkZDEyZWRhMzZkNzFmZDhkNWZlYzliMThmMDE1OWRkY2M3NiJ9fX0");
        if (num == 132) return newBlockTrade("Sponge", Material.SPONGE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWNkMWNmMWZiNTVkNTA1NTgzMjQ1NWFkZThmYTdiMGE2OGY0NDUzNDIzOTlhMjdkZjE1MTllNjZmMmE3NGFiMCJ9fX0");

        if (num == 133) return newBlockTrade("White Wool", Material.WHITE_WOOL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODMxYTg3MDE0OTNjMDQ2MjdiMWYwYzQ5OWY0YWE3MGUyMjg2NzI5ODJlMTRmMTM1Y2NmMmU4ZjYzZDEyYTYxYyJ9fX0");
        if (num == 134) return newBlockTrade("End Stone", Material.END_STONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGI2YjZiMzJlOWZlOGY3Y2I3OTBkNDJlZGYzZGY2OWQ0NzFkYzZiOWI2MTZiMTU3MGVjOGJjMWJmZmExNDMyYyJ9fX0");
        if (num == 135) return newBlockTrade("Red Sandstone", Material.RED_SANDSTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFmZTdiNjg4ZmU0NTU4YzI0M2VlOTVlMDdjMmVlYzYwNjA2MTI5NzFhMzk1NDJmY2Y3N2ZlMzJhMmZkZWE4YyJ9fX0");
        if (num == 136) return newBlockTrade("Bone Block", Material.BONE_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Y0YjllZDFiMzRmYzQ3MzJmNjIyYmVkZWMwYzY0Y2UyZjJhNzkwZDdmNjNkNTkyZTU1N2RiNjAyZmY2OWIwYiJ9fX0");

        if (num == 137) return newBlockTrade("Barrel", Material.BARREL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGVjZDIyYzRiYjYxM2JkN2Y2OWNiMDIzMWExMzBiYjEwMzViNmIwZDQ2ZDY3MmMwN2U4ZTJhMDM1ZmUwMmU3MCJ9fX0");
        if (num == 138) return newBlockTrade("Loom", Material.LOOM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZjMDE0NDhkNjQ3N2UxZjdhM2QyMDdmMjM1MGEwNjZkZmE5NTA5MGQzNDBkNDUxNTUzY2UwNWU3MDBiYjczMSJ9fX0");
        if (num == 139) return newBlockTrade("Smoker", Material.SMOKER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTgzMWNlODIyN2JiYzdiNzVjMzY4OTQ2NmNlYzRkYWY1ZGEyZDljNTNiYjgzZDExN2E5YmE4OTBkYWVhZjQwNiJ9fX0");
        if (num == 140) return newBlockTrade("Blast Furnace", Material.BLAST_FURNACE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBmNGQzNjAxYjI0ZDZiMzcxYmI5N2EzZjQyNzc5ODQwYTEyOTQ4N2EzMDRkYWI2MjM0NjlkY2EwMjg3Y2FmNSJ9fX0");
        if (num == 141) return newBlockTrade("Fletching Table", Material.FLETCHING_TABLE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBiNzhlMDk2MTIzNmM4ZGE3N2QyOGZhOWExYzZmMDM5OGViYjI4ZWJmZDdhMTc4M2ViMmI2YzhjNDE2MDM0NiJ9fX0");
        if (num == 142) return newBlockTrade("Cartography Table", Material.CARTOGRAPHY_TABLE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWNiOTdjNjgyMzZjNjM1MjM2MmYzZWI5MTk0YWI1NDJmMTg3MjBhMzRlOGQwOWRhYTE2OGEwNWVmNjUwMTMwZSJ9fX0");
        if (num == 143) return newBlockTrade("Smithing Table", Material.SMITHING_TABLE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWUyNzlmMDVkYjgzNjAyMTg1OTU5MGE1YjVmNDM0OWE2MGFkZjAyYmMxZDMwOWRhODQwZDllYmJlZjhmMGUyYiJ9fX0");

        if (num == 144) return newBlockTrade("Bee Nest", Material.BEE_NEST, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDZmMjAwMDZlZWI0NTcyZWZlMTljM2YxNjcyMjY5MGY3MzVkYTlmZWE0Y2ZjOTQxZWI0YzNlZDU3NDNmMTkwNiJ9fX0");
        if (num == 145) return newBlockTrade("Beehive", Material.BEEHIVE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNlMGQzY2E1YzExMzY3MjRiNzg3ODdlMGE1NDc3NTNkYzhlOTk2OTY1MWU3YzZlZTNkZmU0MmI3OGZiMThjZiJ9fX0");
        if (num == 146) return newBlockTrade("Honeycomb Block", Material.HONEYCOMB_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNlZmNmMjMxNGFmZjdmZjVkNmRkOWFjNTRkNDI3N2RkNjA5ODcwNmY5NWYxZjM0ODBmNGVjMTYyZDg3MDU3ZCJ9fX0");
        if (num == 147) return newBlockTrade("Lodestone", Material.LODESTONE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzEzOTUxZmQ4N2M2OGNmOGNhNDdkMThkYWVjYTVhZDNhZDgwNGIyNTE3NmYyYjRlZjQ4YmZjOTY4NmFiODA2NCJ9fX0");
        if (num == 148) return newBlockTrade("Respawn Anchor", Material.RESPAWN_ANCHOR, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFjZDQ3MzQ5NjQyNjQxM2VlYmI2NTIzZGVlNGEyZmMxN2MxOTJhYTMwMWQyMzQwNjcyY2FiYTI0OWMzZTRmNCJ9fX0");

        if (num == 149) return newBlockTrade("Cut Copper", Material.CUT_COPPER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMxYzU3ODM3YjhjMzdlY2RiM2RkZDlmM2E4ZGMzOTVhNzEyYzI1N2ZhZjE3N2Q3ZmU4ZDNmNDRlZjQ3NTkzMSJ9fX0");
        if (num == 150) return newBlockTrade("Exposed Cut Copper", Material.EXPOSED_CUT_COPPER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY0YmQ2ZTc4MWFhMTU1NmNjZGZmMzIyYzE5NmM5Nzk5MWVhOTUyODI4NDhiZTkwOGYxYThkZGNhOTJjYmViMiJ9fX0");
        if (num == 151) return newBlockTrade("Weathered Cut Copper", Material.WEATHERED_CUT_COPPER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZhOGZjNDM3MThhZTgxZDU4M2M2NGIyOTA0ZmE1ZmMwMzBiMWJkYmVjMmFmNjYwNTA5OWRmMmZkMmYwMGE2MSJ9fX0");
        if (num == 152) return newBlockTrade("Oxidized Cut Copper", Material.OXIDIZED_CUT_COPPER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQ1YTk2ZTZlMGJkMGIxZjRkZDY0MjdiNTVjMmE2MGM5MDUzZmUwMGE2ODI5NTc1MWZkY2E3OThlMGVhZGU0MiJ9fX0");

        if (num == 153) return newBlockTrade("Deepslate", Material.DEEPSLATE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJkNzE0NGRjZjFlNzg0OTU2ZWUxMmZiMjljNjEzODJlZWEyNzJhNzYyMWI1MDY2MjM5N2JjMDc0NTE1ZDk4MiJ9fX0");
        if (num == 154) return newBlockTrade("Cobbled Deepslate", Material.COBBLED_DEEPSLATE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzAxMDFkMDEzNzk3MTJhYTZiMWE5ODg5ZmE2NmU0ZDA1OTE2MWUyZTM1ZDk1YThlYTQ4MzQ3ZDY4N2QwODYzOCJ9fX0");
        if (num == 155) return newBlockTrade("Calcite", Material.CALCITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDlhYjZlN2IwYzcyNjQ1Y2RiNGEwOWE0Mjk2MDAzZGYyNGM2Y2ZhMWQ4ZDAxMzNiYzhhZTIxMWU5NDg4MDdjMCJ9fX0");
        if (num == 156) return newBlockTrade("Deepslate Lapis Ore", Material.DEEPSLATE_LAPIS_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTU1ZWZjOWMzNzlhYzMzYWVjOTA1MWU4OTk5ZTdmNDgzNzE1NTEwYjM3NGYyYmUyY2QzNTc4MjFmZjg0MDYzMyJ9fX0");
        if (num == 157) return newBlockTrade("Dripstone Block", Material.DRIPSTONE_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjI1Yjk4NTRkMmU0OGVkZTdhMjY4ZjAwMWY2YTEzMjZjOTdlYTU2MmNjYzA1MmQyOWZkOTE4OTVlNThkZWUzMCJ9fX0");
        if (num == 158) return newBlockTrade("Tuff", Material.TUFF, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZkNzQ4M2Q5ZTY5Yjc5Nzg5NTg4NjllMjI2OGU1OTE5NDAxYzY3NTJlM2Y2MmYxYWNmYzNjYTlkYmY0MjllYiJ9fX0");

        if (num == 159) return newBlockTrade("Azalea Leaves", Material.AZALEA_LEAVES, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGRhNjI1ZGNmYmZiODc1NTZmZTk0NjI3MWQ4YWIwMWFhZjAyYmE2ODFmNTY3MzcyZDA1NjI4NmI3YTAyYmIxZiJ9fX0");
        if (num == 160) return newBlockTrade("Flowering Azalea Leaves", Material.FLOWERING_AZALEA_LEAVES, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc5ZDQ3ZjMxMDk0ZTYyNGU5YmQ3ZTIzZTY2ZDQ1ZjQ0ZDAzMzc5MWE1ZmFjNDJhODQ1ODQxYTM4NjI5NDYwMiJ9fX0");

        if (num == 161) return newBlockTrade("Raw Copper Block", Material.RAW_COPPER_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRmNWE3Y2NhMmY0MDk5MzhkMGRiOTJkYTQ3NDk2YjM2MjA0YmI4NTgyY2VkNjVkNWZiOGQzY2Q5MjFjMDhhZSJ9fX0");
        if (num == 162) return newBlockTrade("Raw Gold Block", Material.RAW_GOLD_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGRhYzc0YzVlOTVkMDdlYmM5MGYxYzE2Y2Q5NTk5ZWJiYzk0YWExMzIxZDljYjliYjE0MzRjMzg0YTdlMjc0ZCJ9fX0");
        if (num == 163) return newBlockTrade("Raw Iron Block", Material.RAW_IRON_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjVmYzA2YjBkY2M1ZGIwN2E4NTNhZmJlM2Q2OWNkNzcwM2UxMGQ3NTg0ZGE3OTlmZDI4MTlhZDU3YWJjNGRmZiJ9fX0");
        if (num == 164) return newBlockTrade("Amethyst Block", Material.AMETHYST_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzEzYjc3ZjVhNzQ2YzFmMDBkZDFjZTdmZTY2OTc2Yzc1Y2VjZjdkODliZGIwMmU4Y2Y5NDM2NjcyYWY1ODk2ZCJ9fX0");

        if (num == 165) return newBlockTrade("Polished Deepslate", Material.POLISHED_DEEPSLATE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFkMDIyNTY2OTNjYWYwODFlNGM1ZmMzMDQ5MTc5YmZkNTVjMWU3NmFmYWI5ZTExMjZiYmQ3NjQzZWRjZThkYSJ9fX0");


        if (num == 166) return newBlockTrade("Deepslate Coal Ore", Material.DEEPSLATE_COAL_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE5NjBkM2RlZGJjNmRlMTA5N2I3Y2MyZDgzNzcyOGJkOTQzZTdjZmI5MGQxMWM0OTQzNjc5NmZiZDlkZDBjYSJ9fX0");
        if (num == 167) return newBlockTrade("Deepslate Copper Ore", Material.DEEPSLATE_COPPER_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjQ2MWViZmYzZWZiMTk1NzIxYzRjNjQzZjEzNDFkMjg1NzMwMmYwZWYzY2U1MzdkZTA3MjI3ZWU5MzQ3NzYwNyJ9fX0");
        if (num == 168) return newBlockTrade("Deepslate Diamond Ore", Material.DEEPSLATE_DIAMOND_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmYxMjFlNTE4YTRmZmFkNzQ5NDAwNzQ2NDJkNmRiOWE0OGRjMmFhOWJlYzJiNjYyNTQ0NjgyZDhlODUxZDJhIn19fQ");
        if (num == 169) return newBlockTrade("Deepslate Emerald Ore", Material.DEEPSLATE_EMERALD_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTA1NjFjN2E3N2EyOTUyMjg2NTIwZGNhZTA3NjVkNDllZGEyNmRjOWQ0YzQwYmRjNmUwODQ3MzI3NWQ2MDI0MiJ9fX0");
        if (num == 170) return newBlockTrade("Deepslate Gold Ore", Material.DEEPSLATE_GOLD_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQwYzQwNzM0ZDU1YzIyNWRhMjIwYTMwYzA3NjhjNzAwYmRmYTU2YzE3ZDNhNWViNWZkY2I3NTg2MTliNWUxNyJ9fX0");
        if (num == 171) return newBlockTrade("Deepslate Iron Ore", Material.DEEPSLATE_IRON_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY2NWZmZGZiMjA5YjU4OGYzYTVlYTJkYTZmYzIxZjg0MzQ1ZjQ3ZjMxODQxODk5NjZjZGQwODBhN2RhOWEyOCJ9fX0");
        if (num == 172) return newBlockTrade("Deepslate Redstone Ore", Material.DEEPSLATE_REDSTONE_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBjZGZiZTUzODk5ODk4MWFiMzlkM2Q4OTg0MzBkNTg4NjExZjQ3NmM4ODIwOTA5Njc4YTI5YTA3ZmRmNDAyZCJ9fX0");

        if (num == 173) return newBlockTrade("Copper Ore", Material.COPPER_ORE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjUwMTliMmMxOTRkNDBkYmFkOWYzOTUwMTU2MGMxZTc5ZjhkNTZiOTg4MzAxNDU0OGFlOTk3NmJiNmQ5ODEwIn19fQ");
        if (num == 174) return newBlockTrade("Moss Block", Material.MOSS_BLOCK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NhMWFlYWRhZDkyZmM4YTE4ZGVmMTQ4MmNlZjMwODhmZDdlMzlhMjAyMjA3ODFiNmU2ODUzZmNkMGQ5YjhjYSJ9fX0");



        return newBlockTrade("Error", Material.BARRIER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTNkYTM5ZTU1NmM5Mjc5OTAzODRmYWExZmViM2I4MjUyNTJkYWM3OGNkMjg4Nzc5Y2RlMTExN2MzN2E4In19fQ");
    }


    private static MerchantRecipe newBlockTrade(String name, Material block, String texture) {
        MerchantRecipe recipe = new MerchantRecipe(newMiniBlock(name, texture), data.getConfig().getInt("wandering-trades.mini-blocks.max-per-trade"));

        recipe.addIngredient(new ItemStack(Material.valueOf(data.getConfig().getString("wandering-trades.mini-blocks.trade-item")), data.getConfig().getInt("wandering-trades.mini-blocks.trade-amount")));
        recipe.addIngredient(new ItemStack(block));

        return recipe;
    }



    private static ItemStack newMiniBlock(String name, String texture) {
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
