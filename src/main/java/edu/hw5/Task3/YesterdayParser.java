package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class YesterdayParser extends DateFormatParser {
    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (string.equalsIgnoreCase("yesterday")) {
            return Optional.of(LocalDate.now().minusDays(1));
        }
        if (nextParser != null) {
            return nextParser.parseDate(string);
        } else {
            return Optional.empty();
        }
    }
}
