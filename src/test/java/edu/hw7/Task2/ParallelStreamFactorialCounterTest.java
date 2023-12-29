package edu.hw7.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw7.Task2.ParallelStreamFactorialCounter.factorial;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParallelStreamFactorialCounterTest {
    @Test
    @DisplayName("Отрицательное число")
    void negativeNumber() {
        assertThrows(IllegalArgumentException.class,
            () -> factorial(-8));
    }

    @Test
    @DisplayName("Ноль")
    void zero() {
        long actualResult = factorial(0);

        assertThat(actualResult).isEqualTo(1);
    }

    @Test
    @DisplayName("Корректный ответ для положительного числа")
    void positiveNumberCorrectAnswer() {
        long actualResult = factorial(15);

        assertThat(actualResult).isEqualTo(1307674368000L);
    }
}
