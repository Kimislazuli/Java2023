package edu.hw10.Task2;

import edu.hw10.Task2.caches.AbstractCache;
import edu.hw10.Task2.caches.DiskCache;
import edu.hw10.Task2.caches.InMemoryCache;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;

public final class CacheProxy implements InvocationHandler {
    private final DiskCache diskCache;
    private final InMemoryCache inMemoryCache = new InMemoryCache();
    private final Object targetObject;

    private CacheProxy(Object targetObject) throws IOException {
        diskCache = new DiskCache(Path.of("src/main/resources", targetObject.getClass().getName()));
        this.targetObject = targetObject;
    }

    public static <T> T  create(Object targetObject, Class<?> clazz) throws IOException {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new CacheProxy(targetObject));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache annotation = method.getAnnotation(Cache.class);
        if (annotation != null) {
            return getValue(annotation.persist(), method, args);
        }

        return method.invoke(targetObject, args);
    }

    private Object getValue(boolean persist, Method method, Object[] args) {
        AbstractCache cache;

        if (persist) {
            cache = diskCache;
        } else {
            cache = inMemoryCache;
        }

        String key = method.getName() + Arrays.toString(args);

        Object value = cache.get(key);
        if (value != null) {
            return value;
        }

        Object result;
        try {
            result = method.invoke(targetObject, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        cache.put(key, result);

        return result;
    }
}
