package edu.hw3.Task5;

import java.util.Objects;

public class Contact {
    private final String name;
    private final String surname;

    public Contact(String name) {
        String[] contactName = name.split(" ");
        this.name = contactName[0];
        if (contactName.length == 2) {
            this.surname = contactName[1];
        } else if (contactName.length > 2) {
            throw new IllegalArgumentException("Contact's name max length is 2 words.");
        } else {
            this.surname = null;
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(surname, contact.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
