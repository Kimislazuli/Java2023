package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task3.isNestable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Test
    @DisplayName("Вложен")
    void nestable() {
        int[] a = {2, 5, 2};
        int[] b = {-5, 10, 12};
        boolean actualResult = isNestable(a, b);

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Не вложен")
    void notNestable() {
        int[] a = {2, 5};
        int[] b = {10, 12};
        boolean actualResult = isNestable(a, b);

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Границы совпадают")
    void matchingBorders() {
        int[] a = {2, 5};
        int[] b = {5, 2, 5};
        boolean actualResult = isNestable(a, b);

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Вкладываемый пустой")
    void leftIsEmpty() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int[] a = new int[0];
                int[] b = {0, 10, 12};
                isNestable(a, b);
            }
        );
    }

    @Test
    @DisplayName("Целевой пустой")
    void rightIsEmpty() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int[] a = {1, 2};
                int[] b = new int[0];
                isNestable(a, b);
            }
        );
    }

    @Test
    @DisplayName("Оба пустые")
    void bothAreEmpty() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int[] a = new int[0];
                int[] b = new int[0];
                isNestable(a, b);
            }
        );
    }

    @Test
    @DisplayName("Вкладываемый null")
    void leftIsNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int[] a = null;
                int[] b = {1, 2};
                isNestable(a, b);
            }
        );
    }

    @Test
    @DisplayName("Целевой null")
    void rightIsNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int[] a = {100, 12};
                int[] b = null;
                isNestable(a, b);
            }
        );
    }

    @Test
    @DisplayName("Оба null")
    void bothAreNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                int[] a = null;
                int[] b = null;
                isNestable(a, b);
            }
        );
    }
}
