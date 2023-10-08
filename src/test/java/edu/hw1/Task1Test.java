package edu.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task1.minutesToSeconds;

public class Task1Test {
    @Test
    @DisplayName("Стандартная строка mm:ss")
    void defaultInputByTemplate() {
        String timeStamp = "12:38";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(758);
    }

    @Test
    @DisplayName("Только минуты")
    void noSecondsOnlyMinutes() {
        String timeStamp = "01:00";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(60);
    }

    @Test
    @DisplayName("Больше 2 разрядов в минутах")
    void moreMinutes() {
        String timeStamp = "100:12";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(6012);
    }

    @Test
    @DisplayName("Секунды не соотетствуют шаблону")
    void nonCorrectSeconds() {
        String timeStamp = "20:1";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(-1);
    }

    @Test
    @DisplayName("Минуты не соотетствуют шаблону")
    void nonCorrectMinutes() {
        String timeStamp = "0:10";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(-1);
    }

    @Test
    @DisplayName("60 секунд")
    void sixtySeconds() {
        String timeStamp = "00:60";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(-1);
    }

    @Test
    @DisplayName("Более 60 секунд")
    void moreThanSixtySeconds() {
        String timeStamp = "00:99";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(-1);
    }

    @Test
    @DisplayName("Огромные минуты")
    void maxMinuteValue() {
        int minutes = Integer.MAX_VALUE;
        String timeStamp = Integer.toString(minutes) + ":20";

        int actualResult = minutesToSeconds(timeStamp);
        assertThat(actualResult).isEqualTo(-1);
    }

    @Test
    @DisplayName("Отрицательное время")
    void negativeTime() {
        String timeStamp = "-00:99";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(-1);
    }

    @Test
    @DisplayName("Другая строка")
    void wrongInputString() {
        String timeStamp = "hello";

        int actualResult = minutesToSeconds(timeStamp);

        assertThat(actualResult).isEqualTo(-1);
    }
}
