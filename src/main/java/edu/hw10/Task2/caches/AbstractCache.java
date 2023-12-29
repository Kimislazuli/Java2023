package edu.hw10.Task2.caches;

public abstract class AbstractCache {
    public abstract Object get(String key);

    public abstract void put(String key, Object value);
}
