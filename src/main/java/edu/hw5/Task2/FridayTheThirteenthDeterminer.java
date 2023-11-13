package edu.hw5.Task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("checkstyle:MagicNumber")
public final class FridayTheThirteenthDeterminer {
    private FridayTheThirteenthDeterminer() {
    }

    public static List<LocalDate> findAllFridayTheThirteenthInTheYear(int year) {
        List<LocalDate> fridays = new ArrayList<>();
        for (LocalDate date = LocalDate.ofYearDay(year, 13); date.getYear() == year; date = date.plusMonths(1)) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(date);
            }
        }
        return fridays;
    }

    public static LocalDate findNearestFridayTheThirteenth(LocalDate currentDate) {
        return currentDate.with(temporal -> {
            LocalDate dayToCheck =
                LocalDate.of(temporal.get(ChronoField.YEAR), temporal.get(ChronoField.MONTH_OF_YEAR), 13);
            if (currentDate.getDayOfMonth() == 13) {
                dayToCheck = dayToCheck.plusMonths(1);
            }
            while (true) {
                if (dayToCheck.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    return dayToCheck;
                }
                dayToCheck = dayToCheck.plusMonths(1);
            }
        });
    }
}
