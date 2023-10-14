package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static Boolean findChild(String stringNumber) {
        StringBuilder child = new StringBuilder();
        int len = stringNumber.length();
        for (int i = 0; i < len; i += 2) {
            int firstDigit = Character.getNumericValue(stringNumber.charAt(i));
            if (i == len - 1) {
                child.append(firstDigit);
                break;
            }
            int secondDigit = Character.getNumericValue(stringNumber.charAt(i + 1));
            child.append(firstDigit + secondDigit);
        }
        if (child.length() == 1) {
            return false;
        }
        return isPalindromeDescendant(Integer.valueOf(child.toString()));
    }

    public static boolean isPalindromeDescendant(int number) {
        String stringNumber = Integer.toString(number);
        int stringLength = stringNumber.length();
        for (int i = 0; i < stringLength / 2; i++) {
            if (stringNumber.charAt(i) != stringNumber.charAt(stringLength - i - 1)) {
                return findChild(stringNumber);
            }
        }

        return true;
    }
}
