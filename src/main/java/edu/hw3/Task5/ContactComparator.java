package edu.hw3.Task5;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact contact1, Contact contact2) {
        int result = 0;

        if (contact1.getSurname() == null && contact2.getSurname() == null) {
            result = contact1.getName().compareTo(contact2.getName());
        } else if (contact1.getSurname() == null) {
            result = contact1.getName().compareTo(contact2.getSurname());
        } else if (contact2.getSurname() == null) {
            result = contact1.getSurname().compareTo(contact2.getName());
        } else {
            result = contact1.getSurname().compareTo(contact2.getSurname());
        }

        if (result == 0) {
            result = contact1.getName().compareTo(contact2.getName());
        }
        return result;
    }
}
