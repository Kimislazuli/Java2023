package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysAgoParser extends DateFormatParser {
    @Override
    public Optional<LocalDate> parseDate(String string) {
        Pattern pattern = Pattern.compile("(\\d+) days? ago");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(matcher.group(1))));
        }
        if (nextParser != null) {
            return nextParser.parseDate(string);
        }
        return Optional.empty();
    }
}
