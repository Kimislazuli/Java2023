package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class TodayParser extends DateFormatParser {
    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (string.equalsIgnoreCase("today")) {
            return Optional.of(LocalDate.now());
        }
        if (nextParser != null) {
            return nextParser.parseDate(string);
        } else {
            return Optional.empty();
        }
    }
}
