package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateFormatParser {
    protected DateFormatParser nextParser;

    public DateFormatParser setNextParser(DateFormatParser nextParser) {
        this.nextParser = nextParser;
        return nextParser;
    }

    public abstract Optional<LocalDate> parseDate(String string);
}
