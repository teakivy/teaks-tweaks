package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.hermitcraft.gemvillagers.GemVllagers;
import me.teakivy.teakstweaks.packs.hermitcraft.treasuregems.Gems;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GemCommand extends AbstractCommand {

    public GemCommand() {
        super("treasure-gems", "gem", "/gem <gem|villager> <type>", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage(ErrorType.MISSING_ACTION.m());
            return;
        }

        if (args[0].equals("villager")) {
            if (!checkPermission(player, "villager")) return;
            if (args.length < 2) {
                player.sendMessage(getError("missing_villager_type"));
                return;
            }

            GemVllagers gems = new GemVllagers();
            Location location = player.getLocation();
            switch (args[1]) {
                case "aquatic":
                    gems.summonAquaticVillager(location);
                    break;
                case "concrete":
                    gems.summonConcreteVillager(location);
                    break;
                case "gem_trader":
                    gems.summonGemTraderVillager(location);
                    break;
                case "functional":
                    gems.summonFunctionalVillager(location);
                    break;
                case "gem_collector":
                    gems.summonGemCollectorVillager(location);
                    break;
                case "more_blocks":
                    gems.summonMoreBlocksVillager(location);
                    break;
                case "natural":
                    gems.summonNaturalVillager(location);
                    break;
                case "nether":
                    gems.summonNetherVillager(location);
                    break;
                case "ores":
                    gems.summonOresVillager(location);
                    break;
                case "precious":
                    gems.summonPreciousVillager(location);
                    break;
                case "redstone":
                    gems.summonRedstoneVillager(location);
                    break;
                case "stones":
                    gems.summonStonesVillager(location);
                    break;
                case "wood":
                    gems.summonWoodVillager(location);
                    break;
                default:
                    player.sendMessage(getError("missing_villager_type"));
                    return;
            }

            player.sendMessage(getString("summoned_villager").replace("%type%", getString("villagers." + args[1])));
            return;
        }

        if (args[0].equals("give")) {
            if (!checkPermission(player, "give")) return;
            if (args.length < 2) {
                player.sendMessage(getError("missing_gem_type"));
                return;
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

            ItemStack item = null;
            switch (args[1]) {
                case "aquamarine":
                    item = Gems.getAquamarineGem();
                    break;
                case "amethyst":
                    item = Gems.getAmethystGem();
                    break;
                case "ruby":
                    item = Gems.getRubyGem();
                    break;
                case "topaz":
                    item = Gems.getTopazGem();
                    break;
                case "sapphire":
                    item = Gems.getSapphireGem();
                    break;
                default:
                    player.sendMessage(getError("missing_gem_type"));
                    return;
            }

            item.setAmount(amount);
            player.getInventory().addItem(item);
            player.sendMessage(getString("given_gem").replace("%amount%", amount + "").replace("%type%", getString("gems." + args[1])));
        }
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return List.of("villager", "give");

        if (args.length == 2) {
            if (args[0].equals("villager")) {
                return List.of("aquatic", "concrete", "gem_trader", "functional", "gem_collector", "more_blocks", "natural", "nether", "ores", "precious", "redstone", "stones", "wood");
            }
            if (args[0].equals("give")) {
                return List.of("aquamarine", "amethyst", "ruby", "topaz", "sapphire");
            }
        }

        return null;
    }
}
