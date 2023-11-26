package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.List;

public final class ParenthesisClusterizer {
    private ParenthesisClusterizer() {
    }

    public static List<String> clusterize(String parenthesisString) {
        if (parenthesisString == null) {
            throw new IllegalArgumentException("String shouldn't be null");
        }
        char[] charArray = parenthesisString.toCharArray();
        if (!isStringValid(charArray)) {
            throw new IllegalArgumentException("String not valid. You can use only parentheses. "
                + "Also parentheses should be balanced.");
        }
        int counter = 0;
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char a : charArray) {
            if (a == '(') {
                counter++;
                sb.append(a);
            } else if (a == ')') {
                counter--;
                sb.append(a);
            }
            if (counter == 0) {
                result.add(sb.toString());
                sb.setLength(0);
            }
        }
        return result;
    }

    private static boolean isStringValid(char[] charArray) {
        int counter = 0;
        for (char a : charArray) {
            if (counter < 0) {
                return false;
            }
            if (a == '(') {
                counter++;
            } else if (a == ')') {
                counter--;
            } else {
                return false;
            }
        }
        return counter == 0;
    }
}
