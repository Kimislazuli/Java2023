package edu.hw5.Task3;

import java.time.format.DateTimeFormatter;

public class DateFromDayToReducedYearDividedBySlashParser extends FromNumberDateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yy");

    public DateFromDayToReducedYearDividedBySlashParser() {
        super(FORMATTER);
    }
}
