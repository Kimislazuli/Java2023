package edu.hw5.Task3;

import java.time.format.DateTimeFormatter;

public class ReducedZeroDateFormatParser extends FromNumberDateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d");

    public ReducedZeroDateFormatParser() {
        super(FORMATTER);
    }
}
