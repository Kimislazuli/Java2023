package edu.hw2.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {
    @ParameterizedTest
    @CsvSource({
        "-5, 2",
        "2, -1",
        "0, -2",
        "-12, 0",
        "-3, -2"
    })
    @DisplayName("Отрицательная сторона")
    void negativeSide(int height, int width) {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Rectangle(height, width));
    }

    @Test
    @DisplayName("Площадь для нулевых сторон")
    void zeroSidesRectangleArea() {
        Rectangle rectangle = new Rectangle();
        int actualResult = rectangle.area();
        assertThat(actualResult).isEqualTo(0);
    }

    @Test
    @DisplayName("Площадь для положительных сторон")
    void positiveSidesRectangleArea() {
        Rectangle rectangle = new Rectangle(10, 20);
        int actualResult = rectangle.area();
        assertThat(actualResult).isEqualTo(200);
    }
}
