package me.teakivy.teakstweaks.utils;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;

public class ItemSerializer {

    public static byte[] toByteArray(ItemStack item){
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(1);

            // Save every element in the list

            dataOutput.writeObject(item);

            // Serialize that array
            dataOutput.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static ItemStack fromByteArray(byte[] bytes){
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            ItemStack item = (ItemStack) dataInput.readObject();

            dataInput.close();
            System.out.println(item);
            return item;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        CraftPlayer
        return null;
    }

}
