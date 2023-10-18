package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.hermitcraft.gemvillagers.GemVllagers;
import me.teakivy.teakstweaks.packs.hermitcraft.treasuregems.Gems;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GemCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public GemCommand() {
        super("treasure-gems", "gem", "/gem", "Gems, Villagers, & Gem Villagers!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.treasure-gems.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ErrorType.MISSING_ACTION.m());
            return true;
        }

        if (args[0].equalsIgnoreCase("villager")) {
            if (args.length < 2) {
                player.sendMessage(getString("error.missing_villager_type"));
                return true;
            }
            if (!sender.hasPermission(permission+".villager")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            GemVllagers gems = new GemVllagers();

            if (args[1].equalsIgnoreCase("aquatic")) {
                gems.summonAquaticVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.aquatic")));
                return true;
            }

            if (args[1].equalsIgnoreCase("concrete")) {
                gems.summonConcreteVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.concrete")));
                return true;
            }

            if (args[1].equalsIgnoreCase("gem_trader")) {
                gems.summonGemTraderVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.gem_trader")));
                return true;
            }

            if (args[1].equalsIgnoreCase("functional")) {
                gems.summonFunctionalVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.functional")));
                return true;
            }

            if (args[1].equalsIgnoreCase("gem_collector")) {
                gems.summonGemCollectorVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.gem_collector")));
                return true;
            }

            if (args[1].equalsIgnoreCase("more_blocks")) {
                gems.summonMoreBlocksVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.more_blocks")));
                return true;
            }

            if (args[1].equalsIgnoreCase("natural")) {
                gems.summonNaturalVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.natural")));
                return true;
            }

            if (args[1].equalsIgnoreCase("nether")) {
                gems.summonNetherVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.nether")));
                return true;
            }

            if (args[1].equalsIgnoreCase("ores")) {
                gems.summonOresVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.ores")));
                return true;
            }

            if (args[1].equalsIgnoreCase("precious")) {
                gems.summonPreciousVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.precious")));
                return true;
            }

            if (args[1].equalsIgnoreCase("redstone")) {
                gems.summonRedstoneVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.redstone")));
                return true;
            }

            if (args[1].equalsIgnoreCase("stones")) {
                gems.summonStonesVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.stones")));
                return true;
            }

            if (args[1].equalsIgnoreCase("wood")) {
                gems.summonWoodVillager(player.getLocation());
                player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers.wood")));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (args.length < 2) {
                player.sendMessage(getString("error.missing_gem_type"));
            }
            if (!sender.hasPermission(permission+".give")) {
                sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return true;
            }
            int amount = 1;
            if (args.length > 2) {
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    amount = 1;
                }
            }
            if (amount > 64) {
                player.sendMessage(getString("error.amount_more_than_64"));
            }
            if (amount < 1) {
                player.sendMessage(getString("error.amount_less_than_1"));
            }

            if (args[1].equalsIgnoreCase("aquamarine")) {
                ItemStack itemStack = Gems.getAquamarineGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(getString("given_gem").replace("%amount%", amount + "").replace("%type%", getString("gems.aquamarine")));
            }

            if (args[1].equalsIgnoreCase("amethyst")) {
                ItemStack itemStack = Gems.getAmethystGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(getString("given_gem").replace("%amount%", amount + "").replace("%type%", getString("gems.amethyst")));
            }

            if (args[1].equalsIgnoreCase("ruby")) {
                ItemStack itemStack = Gems.getRubyGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(getString("given_gem").replace("%amount%", amount + "").replace("%type%", getString("gems.ruby")));
            }

            if (args[1].equalsIgnoreCase("topaz")) {
                ItemStack itemStack = Gems.getTopazGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(getString("given_gem").replace("%amount%", amount + "").replace("%type%", getString("gems.topaz")));
            }

            if (args[1].equalsIgnoreCase("sapphire")) {
                ItemStack itemStack = Gems.getSapphireGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(getString("given_gem").replace("%amount%", amount + "").replace("%type%", getString("gems.sapphire")));
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        List<String> arguments1 = new ArrayList<>();

        List<String> villagerArgs = new ArrayList<>();
        List<String> gemArgs = new ArrayList<>();

        arguments1.add("villager");
        arguments1.add("give");

        villagerArgs.add("aquatic");
        villagerArgs.add("concrete");
        villagerArgs.add("gem_trading");
        villagerArgs.add("functional");
        villagerArgs.add("gem_collector");
        villagerArgs.add("more_blocks");
        villagerArgs.add("natural");
        villagerArgs.add("nether");
        villagerArgs.add("ores");
        villagerArgs.add("precious");
        villagerArgs.add("redstone");
        villagerArgs.add("stones");
        villagerArgs.add("wood");

        gemArgs.add("aquamarine");
        gemArgs.add("amethyst");
        gemArgs.add("ruby");
        gemArgs.add("topaz");
        gemArgs.add("sapphire");

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("villager")) {
            for (String a : villagerArgs) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            for (String a : gemArgs) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}
