package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public abstract class FromNumberDateParser extends DateFormatParser {
    protected final DateTimeFormatter formatter;

    protected FromNumberDateParser(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Optional<LocalDate> parseDate(String string) {
        try {
            return tryParse(string);
        } catch (DateTimeParseException e) {
            if (nextParser != null) {
                return nextParser.parseDate(string);
            }
            return Optional.empty();
        }
    }

    protected Optional<LocalDate> tryParse(String string) {
        LocalDate date = LocalDate.from(formatter.parse(string));
        return Optional.of(date);
    }
}
