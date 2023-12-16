package edu.hw10.Task1;

public class Person {
    private String name;

    private int age;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public static Person create() {
        return new Person("name", 0);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
