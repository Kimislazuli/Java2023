package edu.hw5.Task8;

import edu.hw5.Task7.BinaryValidator;

public final class ExtendedBinaryValidator {
    private ExtendedBinaryValidator() {}

    public static boolean oddLength(String string) {
        BinaryValidator.validateString(string);
        return string.matches("[0|1]([0|1]{2})*");
    }

    public static boolean oddLengthIfStartsWIthZeroEvenIfFromOne(String string) {
        BinaryValidator.validateString(string);
        return string.matches("(0([0|1]{2})*)|(1[0|1]([0|1]{2})*)");
    }

    public static boolean threeNZeros(String string) {
        BinaryValidator.validateString(string);
        return string.matches("(1*01*01*01*)+");
    }

    public static boolean everyEvenIsOne(String string) {
        BinaryValidator.validateString(string);
        return string.matches("([0|1]1)+0?");
    }

    public static boolean noNeighbourOnes(String string) {
        BinaryValidator.validateString(string);
        return string.matches("(0*(?<!1)10*)*");
    }
}
