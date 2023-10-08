package edu.hw1;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String wrongString) {
        if (wrongString == null) {
            throw new NullPointerException("String can't be null");
        }
        StringBuilder fixedString = new StringBuilder();
        for (int i = 0; i < wrongString.length(); i += 2) {
            if (i != wrongString.length() - 1) {
                fixedString.append(new StringBuilder(wrongString.substring(i, i + 2)).reverse());
            } else {
                fixedString.append(wrongString.substring(i));
            }
        }
        return fixedString.toString();
    }
}
