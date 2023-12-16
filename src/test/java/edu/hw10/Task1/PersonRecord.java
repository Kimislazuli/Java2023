package edu.hw10.Task1;

public record PersonRecord(String name, int age) {
    public static PersonRecord create(String name) {
        return new PersonRecord(name, 0);
    }
}
