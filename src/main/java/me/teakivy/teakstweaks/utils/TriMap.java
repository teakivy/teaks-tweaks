package me.teakivy.teakstweaks.utils;

import java.util.HashMap;

public class TriMap<K, V, T> {
    private HashMap<K, V> leftMap = new HashMap<>();
    private HashMap<K, T> rightMap = new HashMap<>();

    /**
     * Puts a value into the map
     * @param key The key
     * @param left The left value
     * @param right The right value
     */
    public void put(K key, V left, T right) {
        leftMap.put(key, left);
        rightMap.put(key, right);
    }

    /**
     * Gets the left value
     * @param key The key
     * @return The left value
     */
    public V getLeft(K key) {
        return leftMap.get(key);
    }

    /**
     * Gets the right value
     * @param key The key
     * @return The right value
     */
    public T getRight(K key) {
        return rightMap.get(key);
    }

    /**
     * Checks if the map contains the key
     * @param key The key
     * @return If the map contains the key
     */
    public boolean containsKey(K key) {
        return leftMap.containsKey(key);
    }

    /**
     * Sets the left value
     * @param key The key
     * @param value The value
     */
    public void setLeft(K key, V value) {
        if (!leftMap.containsKey(key)) rightMap.put(key, null);
        leftMap.put(key, value);
    }

    /**
     * Sets the right value
     * @param key The key
     * @param value The value
     */
    public void setRight(K key, T value) {
        if (!rightMap.containsKey(key)) leftMap.put(key, null);
        rightMap.put(key, value);
    }

    /**
     * Removes the key
     * @param key The key
     */
    public void remove(K key) {
        leftMap.remove(key);
        rightMap.remove(key);
    }

    /**
     * Clears the map
     */
    public void clear() {
        leftMap.clear();
        rightMap.clear();
    }

    /**
     * Gets the size of the map
     * @return The size of the map
     */
    public int size() {
        return leftMap.size();
    }

    /**
     * Checks if the map is empty
     * @return If the map is empty
     */
    public boolean isEmpty() {
        return leftMap.isEmpty();
    }
}
