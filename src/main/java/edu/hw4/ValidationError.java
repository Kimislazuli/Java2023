package edu.hw4;

public record ValidationError(Field field) {
    public enum Field {
        NAME,
        TYPE,
        SEX,
        AGE,
        HEIGHT,
        WEIGHT
    }
}
