package edu.hw10.Task2.caches;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCache extends AbstractCache {
    private final Map<String, Object> cache = new HashMap<>();

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, Object value) {
        cache.put(key, value);
    }
}
