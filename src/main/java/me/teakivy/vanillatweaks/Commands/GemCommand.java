package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Hermitcraft.GemVillagers.GemVllagers;
import me.teakivy.vanillatweaks.Packs.Hermitcraft.TreasureGems.Gems;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GemCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public GemCommand() {
        super(MessageHandler.getCmdName("gem"), MessageHandler.getCmdUsage("gem"), MessageHandler.getCmdDescription("gem"), MessageHandler.getCmdAliases("gem"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.treasure-gems.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("[VT] You must be a player to run this command!");
            return true;
        }
        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(vt + ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }
        if (args.length < 1) {
            player.sendMessage(vt + ChatColor.RED + "Please specify an action!");
            return true;
        }

        if (args[0].equalsIgnoreCase("villager")) {
            if (args.length < 2) {
                player.sendMessage(vt + ChatColor.RED + "Plase specify a villager type!");
                return true;
            }
            GemVllagers gems = new GemVllagers();

            if (args[1].equalsIgnoreCase("aquatic")) {
                gems.summonAquaticVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.AQUA + "Aquatic" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("concrete")) {
                gems.summonConcreteVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.AQUA + "Concrete" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("gem_trading")) {
                gems.summonGemTraderVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.AQUA + "Gem Trader" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("functional")) {
                gems.summonFunctionalVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.AQUA + "Functional" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("gem_collector")) {
                gems.summonGemCollectorVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.GOLD + "Gem Collector" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("more_blocks")) {
                gems.summonMoreBlocksVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.BLUE + "More Blocks" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("natural")) {
                gems.summonNaturalVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.BLUE + "Natural" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("nether")) {
                gems.summonNetherVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.DARK_PURPLE + "Nether" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("ores")) {
                gems.summonOresVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.DARK_PURPLE + "Ores" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("precious")) {
                gems.summonPreciousVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.RED + "Precious" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("redstone")) {
                gems.summonRedstoneVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.RED + "Redstone" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("stones")) {
                gems.summonStonesVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.YELLOW + "Stones" + ChatColor.GREEN + " villager!");
                return true;
            }

            if (args[1].equalsIgnoreCase("wood")) {
                gems.summonWoodVillager(player.getLocation());
                player.sendMessage(vt + ChatColor.GREEN + "Summoned " + ChatColor.YELLOW + "Wood" + ChatColor.GREEN + " villager!");
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (args.length < 2) {
                player.sendMessage(vt + ChatColor.RED + "Please choose a gem to give!");
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
                player.sendMessage(vt + ChatColor.RED + "You cannot give yourself more than 64 gems!");
            }
            if (amount < 1) {
                player.sendMessage(vt + ChatColor.RED + "You must give yourself atleast 1 gem!");
            }

            if (args[1].equalsIgnoreCase("aquamarine")) {
                ItemStack itemStack = Gems.getAquamarineGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(vt + ChatColor.GREEN + "You have been given " + amount + " " + ChatColor.AQUA + "Aquamarine" + ChatColor.GREEN + " Gems!");
            }

            if (args[1].equalsIgnoreCase("ruby")) {
                ItemStack itemStack = Gems.getRubyGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(vt + ChatColor.GREEN + "You have been given " + amount + " " + ChatColor.RED + "Ruby" + ChatColor.GREEN + " Gems!");
            }

            if (args[1].equalsIgnoreCase("amethyst")) {
                ItemStack itemStack = Gems.getAmethystGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(vt + ChatColor.GREEN + "You have been given " + amount + " " + ChatColor.DARK_PURPLE + "Amethyst" + ChatColor.GREEN + " Gems!");
            }

            if (args[1].equalsIgnoreCase("topaz")) {
                ItemStack itemStack = Gems.getTopazGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(vt + ChatColor.GREEN + "You have been given " + amount + " " + ChatColor.YELLOW + "Topaz" + ChatColor.GREEN + " Gems!");
            }

            if (args[1].equalsIgnoreCase("sapphire")) {
                ItemStack itemStack = Gems.getSapphireGem();
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
                player.sendMessage(vt + ChatColor.GREEN + "You have been given " + amount + " " + ChatColor.BLUE + "Sapphire" + ChatColor.GREEN + " Gems!");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        List<String> arguments1 = new ArrayList<String>();

        List<String> villagerArgs = new ArrayList<String>();
        List<String> gemArgs = new ArrayList<String>();

        Player player = (Player) sender;

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

        List<String> result = new ArrayList<String>();
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
