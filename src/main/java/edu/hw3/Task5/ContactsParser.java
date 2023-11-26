package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ContactsParser {
    private ContactsParser() {
    }

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public static List<Contact> parseContacts(String[] names, SortOrder order) {
        if (names == null || names.length == 0) {
            return Collections.emptyList();
        }
        List<Contact> contacts = new ArrayList<>();
        for (String name : names) {
            Contact contact = new Contact(name);
            contacts.add(contact);
        }

        switch (order) {
            case ASC -> Collections.sort(contacts, new ContactComparator());
            case DESC -> Collections.sort(contacts, new ContactComparator().reversed());
        }

        return contacts;
    }
}
