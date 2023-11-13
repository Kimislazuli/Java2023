package edu.hw5.Task3;

import java.time.format.DateTimeFormatter;

public class DefaultDateFormatParser extends FromNumberDateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DefaultDateFormatParser() {
        super(FORMATTER);
    }
}
