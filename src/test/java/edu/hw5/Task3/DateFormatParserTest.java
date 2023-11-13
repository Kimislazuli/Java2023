package edu.hw5.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class DateFormatParserTest {
    private static DateFormatParser defaultDateFormatParser = new DefaultDateFormatParser();
    private static DateFormatParser reducedZeroDateFormatParser = defaultDateFormatParser.setNextParser(new ReducedZeroDateFormatParser());
    private static DateFormatParser dateFromDayToFullYearDividedBySlashParser = reducedZeroDateFormatParser.setNextParser(new DateFromDayToFullYearDividedBySlashParser());
    private static DateFormatParser dateFromDayToReducedYearDividedBySlashParser = dateFromDayToFullYearDividedBySlashParser.setNextParser(new DateFromDayToReducedYearDividedBySlashParser());
    private static DateFormatParser tomorrowParser = dateFromDayToReducedYearDividedBySlashParser.setNextParser(new TomorrowParser());
    private static DateFormatParser todayParser = tomorrowParser.setNextParser(new TodayParser());
    private static DateFormatParser yesterdayParser = todayParser.setNextParser(new YesterdayParser());
    private static DateFormatParser daysAgoParser = yesterdayParser.setNextParser(new DaysAgoParser());

    @Test
    @DisplayName("2020-10-10")
    void defaultDateFormatParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("2020-10-10");

        assertThat(actualResult).contains(LocalDate.of(2020, 10, 10));
    }

    @Test
    @DisplayName("2020-12-2")
    void reducedZeroDateFormatParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("2020-12-2");

        assertThat(actualResult).contains(LocalDate.of(2020, 12, 2));
    }

    @Test
    @DisplayName("2020-10-10")
    void dateFromDayToFullYearDividedBySlashParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("1/3/1976");

        assertThat(actualResult).contains(LocalDate.of(1976, 3, 1));
    }

    @Test
    @DisplayName("2020-10-10")
    void dateFromDayToReducedYearDividedBySlashParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("1/3/20");

        assertThat(actualResult).contains(LocalDate.of(2020, 3, 1));
    }

    @Test
    @DisplayName("tomorrow")
    void tomorrowParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("tomorrow");

        assertThat(actualResult).contains(LocalDate.now().plusDays(1));
    }

    @Test
    @DisplayName("today")
    void todayParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("today");

        assertThat(actualResult).contains(LocalDate.now());
    }

    @Test
    @DisplayName("yesterday")
    void yesterdayParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("yesterday");

        assertThat(actualResult).contains(LocalDate.now().minusDays(1));
    }

    @Test
    @DisplayName("1")
    void dayAgoParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("1 day ago");

        assertThat(actualResult).contains(LocalDate.now().minusDays(1));
    }

    @Test
    @DisplayName("2234 days ago")
    void daysAgoParserTest() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("2234 days ago");

        assertThat(actualResult).contains(LocalDate.now().minusDays(2234));
    }

    @Test
    @DisplayName("invalid input")
    void invalidInput() {
        Optional<LocalDate> actualResult = defaultDateFormatParser.parseDate("2234 days before");

        assertThat(actualResult).isEmpty();
    }
}
