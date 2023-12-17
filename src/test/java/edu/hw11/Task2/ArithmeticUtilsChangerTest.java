package edu.hw11.Task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static org.assertj.core.api.Assertions.assertThat;

public class ArithmeticUtilsChangerTest {
    public static class Interceptor {
        @RuntimeType
        public static int intercept(@AllArguments Object[] args) {
            return (int) args[0] * (int) args[1];
        }
    }

    @Test
    @DisplayName("Замена суммы на умножение")
    void sumToMultiplicationExchange() throws InstantiationException, IllegalAccessException {
        DynamicType.Unloaded unloadedType = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum")
                .and(isDeclaredBy(ArithmeticUtils.class))
                .and(returns(int.class)))
            .intercept(MethodDelegation.to(Interceptor.class))
            .make();

        Class<?> dynamicType = unloadedType.load(getClass().getClassLoader())
            .getLoaded();

        ArithmeticUtils instance = (ArithmeticUtils) dynamicType.newInstance();
        int actualResult = instance.sum(2, 3);
        assertThat(actualResult).isEqualTo(6);
    }
}
