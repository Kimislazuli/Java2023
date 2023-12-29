package edu.hw11.Task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneratedFibonacciTest {
    @Test
    @DisplayName("Первые члены последовательности")
    void correctlyFindFirstElements() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> dynamicType =  new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciCalculator")
            .defineMethod("fib", long.class, Modifier.PUBLIC | Modifier.STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciGenerator.FibonacciByteCodeAppender()))
            .make()
            .load(FibonacciGenerator.class.getClassLoader())
            .getLoaded();

        Object instance = dynamicType.newInstance();

        long actualResult = (long) dynamicType.getDeclaredMethods()[0].invoke(instance, 1);

        assertThat(actualResult).isEqualTo(1L);
    }

    @Test
    @DisplayName("Члены последовательности после 2")
    void correctlyFindOtherElements() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> dynamicType =  new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciCalculator")
            .defineMethod("fib", long.class, Modifier.PUBLIC | Modifier.STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciGenerator.FibonacciByteCodeAppender()))
            .make()
            .load(FibonacciGenerator.class.getClassLoader())
            .getLoaded();

        Object instance = dynamicType.newInstance();

        long actualResult = (long) dynamicType.getDeclaredMethods()[0].invoke(instance, 14);

        assertThat(actualResult).isEqualTo(610L);
    }
}
