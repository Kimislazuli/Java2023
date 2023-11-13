package edu.hw5.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static edu.hw5.Task2.FridayTheThirteenthDeterminer.findAllFridayTheThirteenthInTheYear;
import static edu.hw5.Task2.FridayTheThirteenthDeterminer.findNearestFridayTheThirteenth;
import static org.assertj.core.api.Assertions.assertThat;

public class FridayTheThirteenthDeterminerTest {
    @Test
    @DisplayName("Найти все пятницы 13 в году")
    void findAllFridaysTheThirteen() {
        List<LocalDate> actualResult = findAllFridayTheThirteenthInTheYear(1925);
        assertThat(actualResult).containsExactly(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );
    }

    @Test
    @DisplayName("Найти ближайшую пятницу 13")
    void findNearestFridayTheThirteen() {
        LocalDate actualResult = findNearestFridayTheThirteenth(LocalDate.of(2023, 11, 12));
        assertThat(actualResult).isEqualTo(LocalDate.of(2024, 9, 13));
    }

    @Test
    @DisplayName("Найти ближайшую пятницу 13 к пятнице 13")
    void findNearestFridayTheThirteenForFridayTheThirteen() {
        LocalDate actualResult = findNearestFridayTheThirteenth(LocalDate.of(2024, 9, 13));
        assertThat(actualResult).isEqualTo(LocalDate.of(2024, 12, 13));
    }
}
