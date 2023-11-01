package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.hermitcraft.gemvillagers.GemVllagers;
import me.teakivy.teakstweaks.packs.hermitcraft.treasuregems.Gems;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.command.TabCompleteEvent;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GemCommand extends AbstractCommand {

    public GemCommand() {
        super("treasure-gems", "gem", "<gem | villager> <type>", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (!event.hasArgs()) {
            sendError(ErrorType.MISSING_ACTION);
            return;
        }

        if (event.isArg(0, "villager")) {
            if (!checkPermission("villager")) return;
            if (!event.hasArgs(2)) {
                sendError("missing_villager_type");
                return;
            }

            GemVllagers gems = new GemVllagers();
            Location location = event.getPlayer().getLocation();
            switch (event.getArg(1)) {
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
                    sendError("missing_villager_type");
                    return;
            }

            sendMessage("summoned_villager", insert("type", getString("villagers." + event.getArg(1))));
            return;
        }

        if (event.isArg(0, "give")) {
            if (!checkPermission("give")) return;
            if (!event.hasArgs(2)) {
                sendError("missing_gem_type");
                return;
            }

            int amount = 1;
            if (event.getArgsLength() > 2) {
                try {
                    amount = Integer.parseInt(event.getArg(2));
                } catch (NumberFormatException ignored) {}
            }
            if (amount > 64) {
               sendError("amount_more_than_64");
            }
            if (amount < 1) {
                sendError("amount_less_than_1");
            }

            ItemStack item;
            switch (event.getArg(1)) {
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
                    sendError("missing_gem_type");
                    return;
            }

            item.setAmount(amount);
            event.getPlayer().getInventory().addItem(item);
            sendMessage("given_gem", insert("amount", amount), insert("type", getString("gems." + event.getArg(1))));
        }
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.isArgsSize(1)) return List.of("villager", "give");

        if (event.isArgsSize(2)) {
            if (event.isArg(0, "villager")) {
                return List.of("aquatic", "concrete", "gem_trader", "functional", "gem_collector", "more_blocks", "natural", "nether", "ores", "precious", "redstone", "stones", "wood");
            }
            if (event.isArg(0, "give")) {
                return List.of("aquamarine", "amethyst", "ruby", "topaz", "sapphire");
            }
        }

        return null;
    }
}
