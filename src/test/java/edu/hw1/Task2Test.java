package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task2.countDigits;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Обычный ввод")
    void defaultInput() {
        int number = 1232141;

        int actualResult = countDigits(number);

        assertThat(actualResult).isEqualTo(7);
    }

    @Test
    @DisplayName("Ноль")
    void countZero() {
        int number = 0;

        int actualResult = countDigits(number);

        assertThat(actualResult).isEqualTo(1);
    }
}
