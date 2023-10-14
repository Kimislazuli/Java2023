package edu.hw1;

public final class Task2 {
    private static final int BASE = 10;

    private Task2() {
    }

    public static int countDigits(int number) {
        int numberToProcess = number;
        if (numberToProcess == 0) {
            return 1;
        }
        int counter = 0;

        while (numberToProcess > 0) {
            counter += 1;
            numberToProcess /= BASE;
        }

        return counter;
    }
}
