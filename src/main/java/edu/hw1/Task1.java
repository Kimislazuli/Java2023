package edu.hw1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task1 {
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MAX_VALUE_FOR_MINUTES = 10000;

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        if (isInputStringValid(time)) {
            String[] dividedTime = time.split(":");
            int seconds = Integer.parseInt(dividedTime[1]);
            int minutes = Integer.parseInt(dividedTime[0]);

            if (isTimeValid(seconds, minutes)) {
                return minutes * SECONDS_IN_MINUTE + seconds;
            }
        }
        return -1;
    }

    private static boolean isInputStringValid(String time) {
        Pattern pattern = Pattern.compile("\\d{2,}:\\d{2}");
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    private static boolean isTimeValid(int seconds, int minutes) {
        return seconds < SECONDS_IN_MINUTE && seconds >= 0 && minutes >= 0 && minutes <= MAX_VALUE_FOR_MINUTES;
    }
}
