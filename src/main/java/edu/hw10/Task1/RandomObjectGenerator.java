package edu.hw10.Task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class RandomObjectGenerator {
    private static final Random RANDOM = new SecureRandom();

    public <T> T nextObject(Class<T> clazz)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return createObject(clazz);
    }

    public <T> T  nextObject(Class<T> clazz, String name)
        throws InvocationTargetException, IllegalAccessException {
        return createObject(clazz, name);
    }

    private <T> T  createObject(Class<T> clazz, String factoryName)
        throws InvocationTargetException, IllegalAccessException {
        Method[] methods = Arrays.stream(clazz.getMethods())
            .filter(
                e -> e.getName().equals(factoryName)
                    && e.getReturnType().equals(clazz)
                    && Modifier.isStatic(e.getModifiers())
            ).toArray(Method[]::new);
        if (methods.length == 0) {
            throw new IllegalArgumentException("Такого фабричного метода не существует");
        }
        Method method;
        if (methods.length > 1) {
            method = methods[RANDOM.nextInt(0, methods.length)];
        } else {
            method = methods[0];
        }
        Parameter[] parameters = method.getParameters();
        Object[] definedParameters = ParamsGenerator.generateParams(parameters);
        return (T) method.invoke(clazz, definedParameters);
    }

    private <T> T  createObject(Class<T> clazz)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?> constructor;
        if (constructors.length > 1) {
            constructor = constructors[RANDOM.nextInt(0, constructors.length)];
        } else {
            constructor = constructors[0];
        }

        Parameter[] parameters = constructor.getParameters();

        Object[] definedParameters = ParamsGenerator.generateParams(parameters);
        return (T) constructor.newInstance(definedParameters);
    }
}
