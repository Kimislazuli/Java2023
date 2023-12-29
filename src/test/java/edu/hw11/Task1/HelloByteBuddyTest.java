package edu.hw11.Task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloByteBuddyTest {
    @Test
    @DisplayName("Замена метода toString()")
    void helloByteBuddy() throws InstantiationException, IllegalAccessException {
        DynamicType.Unloaded unloadedType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make();

        Class<?> dynamicType = unloadedType.load(getClass()
                .getClassLoader())
            .getLoaded();

        String actualResult = dynamicType.newInstance().toString();

        assertThat(
            actualResult).isEqualTo("Hello, ByteBuddy!");
    }
}
