package edu.hw1;

import java.util.Arrays;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a, int[] b) {
        if (isInputInvalid(a, b)) {
            throw new IllegalArgumentException("Arrays shouldn't be null or empty");
        }
        int aMax = Arrays.stream(a).max().getAsInt();
        int aMin = Arrays.stream(a).min().getAsInt();
        int bMax = Arrays.stream(b).max().getAsInt();
        int bMin = Arrays.stream(b).min().getAsInt();

        return aMax < bMax && aMin > bMin;
    }

    private static boolean isInputInvalid(int[] a, int[] b) {
        return a == null || b == null || a.length == 0 || b.length == 0;
    }
}
