package edu.hw5.Task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SubsequenceChecker {
    private static final String ANY_SYMBOL_REGEX = ".*";

    private SubsequenceChecker() {}

    public static boolean isSubsequence(String potentialSubsequence, String targetString) {
        if (potentialSubsequence == null || targetString == null
            || potentialSubsequence.isEmpty() || targetString.isEmpty()) {
            throw new IllegalArgumentException("Strings shouldn't be empty or null");
        }
        char[] charArray = potentialSubsequence.toCharArray();
        StringBuilder regexBuilder = new StringBuilder(ANY_SYMBOL_REGEX);
        for (char ch : charArray) {
            regexBuilder.append(ch);
            regexBuilder.append(ANY_SYMBOL_REGEX);
        }
        Pattern pattern = Pattern.compile(regexBuilder.toString());
        Matcher matcher = pattern.matcher(targetString);
        return matcher.matches();
    }
}
