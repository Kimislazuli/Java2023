package edu.hw5.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task1.AverageTimeChecker.countAverageTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AverageTimeCheckerTest {
    @Test
    @DisplayName("Пустой список")
    void emptyArgumentList() {
        assertThrows(
            IllegalArgumentException.class,
            AverageTimeChecker::countAverageTime
        );
    }

    @Test
    @DisplayName("Среднее для двух строк")
    void twoStringsAverage() {
        String actualResult = countAverageTime("2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20");

        assertThat(actualResult).isEqualTo("3ч 40м");
    }

    @Test
    @DisplayName("Среднее для одной строки")
    void oneStringAverage() {
        String actualResult = countAverageTime("2022-03-12, 20:20 - 2022-03-12, 23:50");

        assertThat(actualResult).isEqualTo("3ч 30м");
    }

    @Test
    @DisplayName("Неправильная строка")
    void wrongFormat() {
        assertThrows(
            IllegalArgumentException.class,
            () -> countAverageTime("2022-03-12, 20:20")
        );
    }

    @Test
    @DisplayName("Неправильное время")
    void wrongTimeFormat() {
        assertThrows(
            IllegalArgumentException.class,
            () -> countAverageTime("2022-3-12, 2:20- 2022-03-12, 23:50")
        );
    }
}
