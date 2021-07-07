package me.teakivy.vanillatweaks.Packs.ArmorStatues.Book;

import me.teakivy.vanillatweaks.Main;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookItem {

    static Main main = Main.getPlugin(Main.class);

    public static void giveBook(Player player) {
        player.getInventory().addItem(getBook());
    }

    public static ItemStack getBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        assert meta != null;
        meta.setAuthor("Stick God");
        meta.setTitle("Statues V2.8");
        meta.setGeneration(BookMeta.Generation.ORIGINAL);


        meta.spigot().addPage(getPageOne());
        meta.spigot().addPage(getPageTwo());
        meta.spigot().addPage(getPageThree());
        meta.spigot().addPage(getPageFour());
        meta.addPage("5");
        meta.addPage("6");
        meta.addPage("7");
        meta.addPage("8");
        meta.addPage("9");
        meta.addPage("10");
        book.setItemMeta(meta);

        return book;
    }

    private static BaseComponent[] newInfoPopup(String message) {
        BaseComponent[] infoPopup = new ComponentBuilder(ChatColor.DARK_PURPLE + "\u24be")
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.YELLOW + message)))
                .create();

        return infoPopup;
    }

    private static BaseComponent[] getPageOne() {

        TextComponent mainPageLink = newPageLink(ChatColor.LIGHT_PURPLE + "\u00ab", "1");
        TextComponent title = new TextComponent(ChatColor.WHITE + "      " + ChatColor.DARK_BLUE + "Statues V2.8" + ChatColor.WHITE + "    ");
        TextComponent infoPopup = new TextComponent(newInfoPopup("This book allows you to apply basic settings to an armor stand. Clicking the coloured links will adjust the closest armor stand within three blocks."));
        TextComponent newLine = new TextComponent("\n");
        TextComponent subTitle = new TextComponent("  Table of Contents");
        TextComponent styles = newPageLink(ChatColor.DARK_AQUA + "Styles", "2");
        TextComponent autoAlign = newPageLink(ChatColor.DARK_AQUA + "Auto Align", "7");
        TextComponent nudge = newPageLink(ChatColor.DARK_AQUA + "Nudge", "3");
        TextComponent swapSlots = newPageLink(ChatColor.DARK_AQUA + "Swap Slots", "7");
        TextComponent rotation = newPageLink(ChatColor.DARK_AQUA + "Rotation", "4");
        TextComponent mirrorFlip = newPageLink(ChatColor.DARK_AQUA + "Mirror/Flip", "8");
        TextComponent pointing = newPageLink(ChatColor.DARK_AQUA + "Pointing", "4");
        TextComponent utilities = newPageLink(ChatColor.DARK_AQUA + "Utilities", "8");
        TextComponent presets = newPageLink(ChatColor.DARK_AQUA + "Presets", "5");
        TextComponent itemFrames = newPageLink(ChatColor.DARK_AQUA + "Item Frames", "9");
        TextComponent poseAdjust = newPageLink(ChatColor.DARK_AQUA + "Pose Adjust", "6");
        TextComponent credits = newPageLink(ChatColor.DARK_AQUA + "Credits", "10");
        TextComponent targetSpace = newPageLink("      ", "1");
        TextComponent checkTarget = new TextComponent(ChatColor.DARK_GREEN + "Check Target");

        checkTarget.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/armorstand check_target"));


        BaseComponent[] page = new ComponentBuilder(title)
                .append(infoPopup)
                .append("\n")
                .append(newLine)
                .append(subTitle)
                .append("\n\n")
                .append(styles).append(newPageLink("     ", "1")).append(autoAlign).append(newLine)
                .append(nudge).append(newPageLink("     ", "1")).append(swapSlots).append(newLine)
                .append(rotation).append(newPageLink("  " + ChatColor.WHITE + ".", "1")).append(mirrorFlip).append(newLine)
                .append(pointing).append(newPageLink("   ", "1")).append(utilities).append(newLine)
                .append(presets).append(newPageLink("  " + ChatColor.WHITE + ".", "1")).append(itemFrames).append(newLine)
                .append(poseAdjust).append(newPageLink("   ", "1")).append(credits).append(newLine)
                .append("\n\n")
                .append(targetSpace)
                .append(checkTarget)
                .create();
        return page;
    }

    private static BaseComponent[] getPageTwo() {

        TextComponent mainPageLink = newPageLink(ChatColor.LIGHT_PURPLE + "\u00ab", "1");
        TextComponent title = newPageLink(ChatColor.WHITE + "   " + ChatColor.DARK_BLUE + "Style Settings" + ChatColor.WHITE + "  ", "2");
        TextComponent infoPopup = new TextComponent(newInfoPopup("Change the Armorstand's styles."));
        TextComponent newLine = new TextComponent("\n");

        TextComponent basePlate = newPageLink("Show Base Plate:" + "\n", "2");
        TextComponent showArms = newPageLink("Show Arms:" + "\n", "2");
        TextComponent smallStand = newPageLink("Small Stand:" + "\n", "2");
        TextComponent gravity = newPageLink("Apply Gravity:" + "\n", "2");
        TextComponent standVisible = newPageLink("Stand Visible:" + "\n", "2");
        TextComponent displayName = newPageLink("Display Name:" + "\n", "2");


        BaseComponent[] basePlateOptions = newStyleOption("style_show_base_plate");
        BaseComponent[] showArmsOptions = newStyleOption("style_show_arms");
        BaseComponent[] smallStandOptions = newStyleOption("style_small_stand");
        BaseComponent[] gravityOptions = newStyleOption("style_apply_gravity");
        BaseComponent[] standVisibleOptions = newStyleOption("style_stand_visible");
        BaseComponent[] displayNameOptions = newStyleOption("style_display_name");

        BaseComponent[] page = new ComponentBuilder(mainPageLink)
                .append(title)
                .append(infoPopup)
                .append("\n\n")
                .append(basePlate)
                .append(basePlateOptions).append("\n")
                .append(showArms)
                .append(showArmsOptions).append("\n")
                .append(smallStand)
                .append(smallStandOptions).append("\n")
                .append(gravity)
                .append(gravityOptions).append("\n")
                .append(standVisible)
                .append(standVisibleOptions).append("\n")
                .append(displayName)
                .append(displayNameOptions)
                .append("\n")
                .create();
        return page;
    }

    private static BaseComponent[] getPageThree() {

        TextComponent mainPageLink = newPageLink(ChatColor.LIGHT_PURPLE + "\u00ab", "1");
        TextComponent title = newPageLink(ChatColor.WHITE + "   ." + ChatColor.DARK_BLUE + "Nudge Position" + ChatColor.WHITE + "  ", "3");
        TextComponent infoPopup = new TextComponent(newInfoPopup("Turn gravity off before nudging the Y-position.\n\nRelative Facing nudging moves the armor stand relative to which way the player is facing.  (X=Left/Right, Z=Away/Towards)"));
        TextComponent newLine = new TextComponent("\n");

        TextComponent github = new TextComponent(ChatColor.DARK_GRAY + "</>");
        github.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/teakivy/vanilla-tweaks"));
        github.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GOLD + "Relative Aligned and Relative Exact Nudging is not yet avaliable.\n\nIf you would like these features sooner, Please contribute\nto the project's GitHub (click here)")));

        BaseComponent[] page = new ComponentBuilder(mainPageLink)
                .append(title)
                .append(infoPopup)
                .append("\n\n")
                .append(newNudgePosition("X", "nudge_stand_x")).append("\n")
                .append(newNudgePosition("Y", "nudge_stand_y")).append("\n")
                .append(newNudgePosition("Z", "nudge_stand_z")).append("\n")
                .append(newPageLink("\n     Relative Facing\n", "3"))
                .append(newNudgePosition("X", "nudge_facing_x")).append("\n")
                .append(newNudgePosition("Z", "nudge_facing_z")).append("\n")
                .append("\n\n\n\n")
                .append(newPageLink("                       ", "3"))
                .append(github)
                .create();
        return page;
    }

    private static BaseComponent[] getPageFour() {

        TextComponent mainPageLink = newPageLink(ChatColor.LIGHT_PURPLE + "\u00ab", "1");
        TextComponent title = newPageLink(ChatColor.WHITE + "   " + ChatColor.DARK_BLUE + "Adjust Rotation" + ChatColor.WHITE + " ", "4");
        TextComponent infoPopup = new TextComponent(newInfoPopup("The angle step (default 15\u00b0) is used for both the stand rotation and the pose adjustments.\n\nToward and Away turn the armor stand to face toward or away from you."));
        TextComponent newLine = new TextComponent("\n");


        BaseComponent[] page = new ComponentBuilder(mainPageLink)
                .append(title)
                .append(infoPopup)
                .append("\n\n")
                .append(newPageLink("  ", "4"))
                .append(newCommandLink(ChatColor.DARK_GREEN + "<<", "/armorstand rotate counterclockwise"))
                .append(newPageLink("  ", "4"))
                .append(newCommandLink(ChatColor.DARK_GREEN + "45\u00b0", "/armorstand set_rotation_number 45"))
                .append(newPageLink(" ", "4"))
                .append(newCommandLink(ChatColor.DARK_GREEN + "15\u00b0", "/armorstand set_rotation_number 15"))
                .append(newPageLink(" ", "4"))
                .append(newCommandLink(ChatColor.DARK_GREEN + "5\u00b0", "/armorstand set_rotation_number 5"))
                .append(newPageLink(" ", "4"))
                .append(newCommandLink(ChatColor.DARK_GREEN + "1\u00b0", "/armorstand set_rotation_number 1"))
                .append(newPageLink("  ", "4"))
                .append(newCommandLink(ChatColor.DARK_GREEN + ">>", "/armorstand rotate clockwise"))
                .append(newPageLink("\n\n     ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Toward", "4"))
                .append(newPageLink("   ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Away", "4"))
                .append(newPageLink("\n\n         ", "4"))
                .append(newPageLink(ChatColor.DARK_BLUE + "Pointing", "4"))
                .append(newPageLink("       ", "4"))
                .append(newInfoPopup("Points selected appendage at head or feet."))
                .append(newPageLink("\n\nHead:", "4"))
                .append(newPageLink(ChatColor.WHITE + "    .", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Head", "4"))
                .append(newPageLink("   ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Feet", "4"))
                .append(newPageLink("\nBody:", "4"))
                .append(newPageLink(ChatColor.WHITE + "    .", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Head", "4"))
                .append(newPageLink("   ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Feet", "4"))
                .append(newPageLink("\nR.Arm:", "1"))
                .append(newPageLink(ChatColor.WHITE + "    ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Head", "4"))
                .append(newPageLink("   ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Feet", "4"))
                .append(newPageLink("\nL.Arm:", "4"))
                .append(newPageLink(ChatColor.WHITE + "    ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Head", "4"))
                .append(newPageLink("   ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Feet", "4"))
                .append(newPageLink("\nR.Leg:", "4"))
                .append(newPageLink(ChatColor.WHITE + "    ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Head", "4"))
                .append(newPageLink("   ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Feet", "4"))
                .append(newPageLink("\nL.Leg:", "4"))
                .append(newPageLink(ChatColor.WHITE + "    ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Head", "4"))
                .append(newPageLink("   ", "4"))
                .append(newPageLink(ChatColor.DARK_GREEN + "Feet", "4"))
                .create();
        return page;
    }

    private static TextComponent newPageLink(String text, String page) {
        TextComponent component = new TextComponent(text);
        component.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, page));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("")));

        return component;
    }

    private static TextComponent newCommandLink(String text, String command) {
        TextComponent component = new TextComponent(text);
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));

        return component;
    }

    private static BaseComponent[] newStyleOption(String commandName) {
        TextComponent yes = newCommandLink(ChatColor.DARK_GREEN + "Yes", "/armorstand " + commandName + " true");
        TextComponent no = newCommandLink(ChatColor.RED + "No", "/armorstand " + commandName + " false");

        BaseComponent[] options = new ComponentBuilder("        ")
                .append(yes)
                .append(" / ")
                .append(no)
                .create();

        return options;
    }

    private static BaseComponent[] newNudgePosition(String type, String commandName) {
        TextComponent n8 = newCommandLink(ChatColor.DARK_GREEN + "-8", "/armorstand " + commandName + " -8");
        TextComponent n3 = newCommandLink(ChatColor.DARK_GREEN + "-3", "/armorstand " + commandName + " -3");
        TextComponent n1 = newCommandLink(ChatColor.DARK_GREEN + "-1", "/armorstand " + commandName + " -1");
        TextComponent p1 = newCommandLink(ChatColor.DARK_GREEN + "+1", "/armorstand " + commandName + " +1");
        TextComponent p3 = newCommandLink(ChatColor.DARK_GREEN + "+3", "/armorstand " + commandName + " +3");
        TextComponent p8 = newCommandLink(ChatColor.DARK_GREEN + "+8", "/armorstand " + commandName + " +8");


        BaseComponent[] options = new ComponentBuilder(ChatColor.BLACK + type + ":  ")
                .append(n8).append(" ")
                .append(n3).append(" ")
                .append(n1).append(" ")
                .append(p1).append(" ")
                .append(p3).append(" ")
                .append(p8).append(" ")
                .create();

        return options;
    }



}
