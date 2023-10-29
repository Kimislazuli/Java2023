package edu.hw3.Task4;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("checkstyle:MagicNumber") public final class RomanNumbers {
    private static final int LIMIT = 3999;
    private static final Map<Integer, String> ARABIC_TO_ROMAN;

    static {
        ARABIC_TO_ROMAN = new TreeMap<>(Collections.reverseOrder());
        ARABIC_TO_ROMAN.put(1, "I");
        ARABIC_TO_ROMAN.put(4, "IV");
        ARABIC_TO_ROMAN.put(5, "V");
        ARABIC_TO_ROMAN.put(9, "IX");
        ARABIC_TO_ROMAN.put(10, "X");
        ARABIC_TO_ROMAN.put(40, "XL");
        ARABIC_TO_ROMAN.put(50, "L");
        ARABIC_TO_ROMAN.put(90, "XC");
        ARABIC_TO_ROMAN.put(100, "C");
        ARABIC_TO_ROMAN.put(400, "CD");
        ARABIC_TO_ROMAN.put(500, "D");
        ARABIC_TO_ROMAN.put(900, "CM");
        ARABIC_TO_ROMAN.put(1000, "M");
    }

    private RomanNumbers() {
    }

    public static String convertToRoman(int arabicNumber) {
        if (arabicNumber > LIMIT) {
            throw new IllegalArgumentException("Number should be less than 3999.");
        }

        int number = arabicNumber;

        String roman = ARABIC_TO_ROMAN.get(number);

        if (roman != null) {
            return roman;
        }

        StringBuilder result = new StringBuilder();

        for (Map.Entry<Integer, String> entry : ARABIC_TO_ROMAN.entrySet()) {
            while (number >= entry.getKey() && number > 0) {
                result.append(entry.getValue());
                number -= entry.getKey();
            }
        }
        return result.toString();
    }
}
