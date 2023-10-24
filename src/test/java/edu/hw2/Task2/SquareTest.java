package edu.hw2.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareTest {
    @Test
    @DisplayName("Отрицательная сторона")
    void negativeSide() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Square(-10)
        );
    }

    @Test
    @DisplayName("Плозадь для нулевой стороны")
    void zeroSideSquareArea() {
        Square square = new Square();
        int actualResult = square.area();
        assertThat(actualResult).isEqualTo(0);
    }

    @Test
    @DisplayName("Площадь для положительной стороны")
    void positiveSideSquareArea() {
        Square square = new Square(10);
        int actualResult = square.area();
        assertThat(actualResult).isEqualTo(100);
    }
}
