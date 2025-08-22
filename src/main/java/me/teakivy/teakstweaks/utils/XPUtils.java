package me.teakivy.teakstweaks.utils;

import org.bukkit.entity.Player;

public class XPUtils {
    /**
     * Calculate the amount of experience needed to level up
     * @param level The level to calculate from
     * @return The amount of experience needed to level up
     */
    public static int getExpToLevelUp(int level){
        if(level <= 15){
            return 2*level+7;
        } else if(level <= 30){
            return 5*level-38;
        } else {
            return 9*level-158;
        }
    }

    /**
     * Calculate the total experience needed to get to a level
     * @param level The level to calculate to
     * @return The total amount of experience needed
     */
    public static int getExpAtLevel(int level){
        if(level <= 16){
            return (int) (level*level + 6*level);
        } else if(level <= 31){
            return (int) (2.5*level*level - 40.5*level + 360.0);
        } else {
            return (int) (4.5*level*level - 162.5*level + 2220.0);
        }
    }

    /**
     * Calculate the amount of xp a player has
     * @param player The player
     * @return The amount of experience the player has
     */
    public static int getPlayerExp(Player player){
        int exp = 0;
        int level = player.getLevel();

        // Get the amount of XP in past levels
        exp += getExpAtLevel(level);

        // Get amount of XP towards next level
        exp += Math.round(getExpToLevelUp(level) * player.getExp());

        return exp;
    }

}
