package edu.hw5.Task4;

public final class PasswordValidator {
    private PasswordValidator() {}

    public static boolean isPasswordValid(String password) {
        if (password == null) {
            throw new IllegalArgumentException("String can't be null");
        }
        return password.matches("(.*[~!@#$%^&*|].*)+");
    }
}
