package edu.hw3.Task1;

public final class AtbashCipher {
    private static final int UPPER_A_ASCII_CODE = 65;
    private static final int UPPER_Z_ASCII_CODE = 90;
    private static final int LOWER_A_ASCII_CODE = 97;
    private static final int LOWER_Z_ASCII_CODE = 122;

    private AtbashCipher() {
    }

    public static String atbash(String inputString) {
        StringBuilder result = new StringBuilder();
        char[] inputStringCharArray = inputString.toCharArray();
        for (char a : inputStringCharArray) {
            if (Character.isUpperCase(a)) {
                result.append(Character.toString(UPPER_Z_ASCII_CODE - a + UPPER_A_ASCII_CODE));
            } else if (Character.isLowerCase(a)) {
                result.append(Character.toString(LOWER_Z_ASCII_CODE - a + LOWER_A_ASCII_CODE));
            } else {
                result.append(a);
            }
        }
        return result.toString();
    }
}
