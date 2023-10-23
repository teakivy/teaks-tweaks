package me.teakivy.teakstweaks.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {
    /**
     * Gets a field value from an object
     * @param object The object
     * @param fieldName The field name
     * @return The field value
     */
    public static Object getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a field from a class
     * @param clazz The class
     * @param fieldName The field name
     * @return The field
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}