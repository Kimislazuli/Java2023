package edu.hw2.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task4.Task4.callingInfo;
import static edu.hw2.Task4.Task4.CallingInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private static Task4.CallingInfo foo() {
        return callingInfo();
    }

    private static CallingInfo bar() {
        return callingInfo();
    }

    private static CallingInfo buzz() {
        return bar();
    }

    @Test
    @DisplayName("Прямой вызов из одной функции")
    void directCallFromAnotherMethod() {
        CallingInfo actualResult = foo();

        assertThat(actualResult).isEqualTo(new CallingInfo(foo().className(), foo().methodName()));
    }

    @Test
    @DisplayName("Последний вызов из цепочки функции")
    void callFromChainOfMethods() {
        CallingInfo actualResult = buzz();

        assertThat(actualResult).isEqualTo(new CallingInfo(bar().className(), bar().methodName()));
    }
}
