package edu.hw2.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("принцип Лисков")
    void rectangleArea(Rectangle rect) {
        Rectangle newRectangle = rect.setWidth(20);
        newRectangle = newRectangle.setHeight(10);

        assertThat(newRectangle.area()).isEqualTo(200);
    }

}
