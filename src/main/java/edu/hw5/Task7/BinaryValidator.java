package edu.hw5.Task7;

public final class BinaryValidator {
    private BinaryValidator() {
    }

    public static boolean atLeastThreeSymbolsAndThirdIsZero(String string) {
        validateString(string);
        return string.matches("[0|1]{2}0[0|1]*");
    }

    public static boolean startsAndEndsWithTheSameLetter(String string) {
        validateString(string);
        if (string.length() == 1) {
            return true;
        }
        return string.matches("^([1|0])[0|1]*\\1$");
    }

    public static boolean lengthFromOneToThree(String string) {
        validateString(string);
        return string.matches("[0|1]{1,3}");
    }

    public static void validateString(String string) {
        if (string == null || !string.matches("[0|1]+")) {
            throw new IllegalArgumentException();
        }
    }
}
