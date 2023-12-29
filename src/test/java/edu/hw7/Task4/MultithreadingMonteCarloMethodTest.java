package edu.hw7.Task4;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.hw7.Task4.multiple_thread_implementation.MultithreadingMonteCarloMethod.approximatePiValue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultithreadingMonteCarloMethodTest {
    @Test
    @DisplayName("Примерное вычисление числа пи")
    void approximatelyCountsPi() throws InterruptedException {
        float actualResult = approximatePiValue(100000);

        assertThat(actualResult).isCloseTo(3.1415F, Offset.offset(0.1F));
    }

    @Test
    @DisplayName("Некорректное количество итераций")
    void wrongIterationsAmount() {
        assertThrows(IllegalArgumentException.class, () -> approximatePiValue(-10));
    }
}
