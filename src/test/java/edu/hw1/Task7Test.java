package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task7.rotateLeft;
import static edu.hw1.Task7.rotateRight;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {
    @Test
    @DisplayName("Сдвиг влево")
    void leftShift() {
        int number = 123;
        int shift = 3;

        int actualResult = rotateLeft(number, shift);

        assertThat(actualResult).isEqualTo(95);
    }

    @Test
    @DisplayName("Сдвиг вправо")
    void rightShift() {
        int number = 123;
        int shift = 3;

        int actualResult = rotateRight(number, shift);

        assertThat(actualResult).isEqualTo(63);
    }

    @Test
    @DisplayName("Сдвиг длиннее числа")
    void longShift() {
        int number = 123;
        int shift = 10;

        int actualResult = rotateRight(number, shift);

        assertThat(actualResult).isEqualTo(63);
    }

    @Test
    @DisplayName("Нулевой сдвиг")
    void noShift() {
        int number = 123;
        int shift = 0;

        int actualResult = rotateRight(number, shift);

        assertThat(actualResult).isEqualTo(number);
    }

    @Test
    @DisplayName("Некорректный ввод для правого сдвига")
    void wrongInputForRightShift() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int number = -344242123;
                int shift = 12;

                rotateRight(number, shift);
            }
        );
    }

    @Test
    @DisplayName("Некорректный ввод для левого сдвига")
    void wrongInputForLeftShift() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int number = -344242123;
                int shift = 12;

                rotateLeft(number, shift);
            }
        );
    }
}
