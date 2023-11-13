package edu.hw5.Task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AverageTimeChecker {
    private static final Pattern TIME_FORMAT_TO_EXTRACT = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");
    private static final String TIME_PATTERN = "yyyy-MM-dd, HH:mm";

    private AverageTimeChecker() {
    }

    public static String countAverageTime(String... timestamps) {
        if (timestamps.length == 0) {
            throw new IllegalArgumentException("Can't find average time from zero intervals");
        }

        OptionalDouble seconds = Arrays.stream(timestamps)
            .map(s -> {
                if (!stringIsValid(s)) {
                    throw new IllegalArgumentException("String in invalid format.");
                }
                Matcher matcher = TIME_FORMAT_TO_EXTRACT.matcher(s);
                String start = getMatchingResult(matcher);
                String end = getMatchingResult(matcher);
                return Duration.between(
                    LocalDateTime.parse(start, DateTimeFormatter.ofPattern(TIME_PATTERN)),
                    LocalDateTime.parse(end, DateTimeFormatter.ofPattern(TIME_PATTERN))
                );
            })
            .mapToLong(Duration::getSeconds)
            .average();
        if (seconds.isPresent()) {
            Duration averageTime = Duration.ofSeconds(Double.valueOf(seconds.getAsDouble()).longValue());
            return averageTime
                .toString()
                .replace("PT", "")
                .replace("H", "ч ")
                .replace("M", "м");
        } else {
            return null;
        }
    }

    private static boolean stringIsValid(String timestamp) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - \\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}");
        return pattern.matcher(timestamp).matches();
    }

    private static String getMatchingResult(Matcher matcher) {
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("Can't match timestamp in this string");
        }
    }
}
