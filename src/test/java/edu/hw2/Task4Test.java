package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task4.callingInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private static Task4.CallingInfo foo() {
        return callingInfo();
    }

    private static Task4.CallingInfo bar() {
        return callingInfo();
    }

    private static Task4.CallingInfo buzz() {
        return bar();
    }

    @Test
    @DisplayName("Прямой вызов из одной функции")
    void directCallFromAnotherMethod() {
        Task4.CallingInfo actualResult = foo();

        assertThat(actualResult).isEqualTo(new Task4.CallingInfo(foo().className(), foo().methodName()));
    }

    @Test
    @DisplayName("Последний вызов из цепочки функции")
    void callFromChainOfMethods() {
        Task4.CallingInfo actualResult = buzz();

        assertThat(actualResult).isEqualTo(new Task4.CallingInfo(bar().className(), bar().methodName()));
    }
}
