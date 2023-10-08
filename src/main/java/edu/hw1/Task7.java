package edu.hw1;

public final class Task7 {
    private static final String EXCEPTION_MESSAGE = "This arguments are wrong";

    private Task7() {
    }

    public static int rotateLeft(int number, int shift) {
        if (isInputInvalid(number, shift)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        char[] numberArray = turnNumberIntoBinaryArray(number);
        int numberLength = numberArray.length;
        char[] rotatedNumber = new char[numberLength];
        for (int i = 0; i < numberLength; i++) {
            rotatedNumber[i] = numberArray[(i + shift) % numberLength];
        }

        return fromBaseTwoToBase10(rotatedNumber);
    }

    public static int rotateRight(int number, int shift) {
        if (isInputInvalid(number, shift)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        char[] numberArray = turnNumberIntoBinaryArray(number);
        int numberLength = numberArray.length;
        char[] rotatedNumber = new char[numberLength];
        for (int i = 0; i < numberLength; i++) {
            rotatedNumber[(i + shift) % numberLength] = numberArray[i];
        }

        return fromBaseTwoToBase10(rotatedNumber);
    }

    private static boolean isInputInvalid(int number, int offset) {
        return number < 0 || offset < 0;
    }

    private static char[] turnNumberIntoBinaryArray(int number) {
        String binary = Integer.toBinaryString(number);
        return binary.toCharArray();
    }

    private static int fromBaseTwoToBase10(char[] number) {
        int result = 0;
        int lastElementIndex = number.length - 1;
        for (int i = lastElementIndex; i >= 0; i--) {
            result += (int) (Math.pow(2, i) * Character.getNumericValue(number[lastElementIndex - i]));
        }
        return result;
    }
}
