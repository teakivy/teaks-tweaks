package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.oldcommand.AbstractCommand;
import me.teakivy.teakstweaks.utils.oldcommand.CommandEvent;
import me.teakivy.teakstweaks.utils.oldcommand.CommandType;
import me.teakivy.teakstweaks.utils.permission.Permission;

import java.util.ArrayList;
import java.util.List;

public class PackListCommand extends AbstractCommand {

    public PackListCommand() {
        super(CommandType.ALL, "packlist", Permission.COMMAND_PACKLIST, List.of("pkl"));
    }

    @Override
    public void command(CommandEvent event) {
        ArrayList<String> packs = TeaksTweaks.getInstance().getPacks();
        ArrayList<String> ctweaks = TeaksTweaks.getInstance().getCraftingTweaks();

        sendText(getString("packs") + arrayToString(packs), insert("count", packs.size()));
        sendText(getString("craftingtweaks") + arrayToString(ctweaks), insert("count", ctweaks.size()));
    }

    public String arrayToString(ArrayList<String> arr) {
        StringBuilder str = new StringBuilder();
        if (!arr.isEmpty()) {
            for (int i = 0; i < arr.size() - 1; i++) {
                str.append("<green>").append(arr.get(i));
                str.append("<white>, ");
            }
            str.append("<green>").append(arr.get(arr.size() - 1));
        }

        return str.toString();
    }
}
