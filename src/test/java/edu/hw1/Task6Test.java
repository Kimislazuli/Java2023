package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task6.countK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    @Test
    @DisplayName("Правильное число")
    void correctNumberInput() {
        int number = 6621;

        int actualResult = countK(number);

        assertThat(actualResult).isEqualTo(5);
    }

    @Test
    @DisplayName("Число меньше 1000")
    void lessThen1000() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int number = 31;

                countK(number);
            }
        );
    }

    @Test
    @DisplayName("Число больше 9999")
    void greaterThen9999() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int number = 12345;

                countK(number);
            }
        );
    }

    @Test
    @DisplayName("Число, состоящее из одинаковых цифр")
    void matchingDigits() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int number = 3333;

                countK(number);
            }
        );
    }
}
