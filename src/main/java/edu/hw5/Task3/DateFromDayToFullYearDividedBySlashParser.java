package edu.hw5.Task3;

import java.time.format.DateTimeFormatter;

public class DateFromDayToFullYearDividedBySlashParser extends FromNumberDateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    public DateFromDayToFullYearDividedBySlashParser() {
        super(FORMATTER);
    }
}
