package edu.hw5.Task5;

public final class CarNumberValidator {
    private static final String AVAILABLE_LETTERS = "[А|В|Е|К|М|Н|О|Р|С|Т|У|Х]";

    private CarNumberValidator() {}

    public static boolean isNumberValid(String number) {
        if (number == null) {
            throw new IllegalArgumentException("String can't be null");
        }
        return number.matches(AVAILABLE_LETTERS + "\\d{3}" + AVAILABLE_LETTERS + "{2}\\d{3}");
    }
}
