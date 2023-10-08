package edu.hw1;

import java.util.Arrays;

public final class Task6 {
    private static final int BOTTOM_BORDER = 1000;
    private static final int TOP_BORDER = 10000;
    private static final int KAPREKAR_NUMBER = 6174;
    private static final int NUMBER_LENGTH = 4;
    private static final int BASE = 10;

    public static final int[] RESTRICTED_NUMBERS = {
        1111, 2222, 3333,
        4444, 5555, 6666,
        7777, 8888, 9999
    };

    private Task6() {
    }

    public static int countK(int number) {
        if (isNumberInvalid(number)) {
            throw new IllegalArgumentException("You can't use this number");
        }
        if (number == KAPREKAR_NUMBER) {
            return 0;
        }
        int[] smallAndBigNumbers = findSmallAndBigNumber(number);
        return countK(smallAndBigNumbers[1] - smallAndBigNumbers[0]) + 1;
    }

    private static boolean isNumberInvalid(int number) {
        if (number <= BOTTOM_BORDER || number >= TOP_BORDER) {
            return true;
        }

        return Arrays.stream(RESTRICTED_NUMBERS)
            .anyMatch(i -> i == number);
    }

    private static int[] findSmallAndBigNumber(int number) {
        int numberToProcess = number;
        int[] digitsArray = new int[NUMBER_LENGTH];
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            digitsArray[i] = numberToProcess % BASE;
            numberToProcess /= BASE;
        }
        Arrays.sort(digitsArray);
        int biggerNumber = getNumberFromArrayOfDigits(digitsArray);
        reverseArray(digitsArray);
        int lessNumber = getNumberFromArrayOfDigits(digitsArray);

        return new int[] {lessNumber, biggerNumber};
    }

    private static void reverseArray(int[] intArray) {
        int size = intArray.length;
        for (int i = 0; i < size / 2; i++) {
            int temp = intArray[i];
            intArray[i] = intArray[size - i - 1];
            intArray[size - i - 1] = temp;
        }
    }

    private static int getNumberFromArrayOfDigits(int[] intArray) {
        int result = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            result += intArray[i] * (Math.pow(BASE, i));
        }
        return result;
    }
}
