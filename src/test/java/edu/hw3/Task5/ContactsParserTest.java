package edu.hw3.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static edu.hw3.Task5.ContactsParser.parseContacts;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactsParserTest {
    @Test
    @DisplayName("Все контакты с полным именем в порядке возрастания")
    void fullNameContactsAsc() {
        List<Contact> actualResult = parseContacts(
            new String[] {
                "John Locke",
                "Thomas Aquinas",
                "David Hume",
                "Rene Descartes"
            },
            SortOrder.ASC
        );

        assertThat(actualResult).isEqualTo(
            Arrays.asList(
                new Contact("Thomas Aquinas"),
                new Contact("Rene Descartes"),
                new Contact("David Hume"),
                new Contact("John Locke")
            ));
    }

    @Test
    @DisplayName("Не все контакты с полным именем в порядке возрастания")
    void notAllContactsWithFullNameAsc() {
        List<Contact> actualResult = parseContacts(
            new String[] {
                "April",
                "Thomas Aquinas",
                "David Hume",
                "Rene Descartes"
            },
            SortOrder.ASC
        );

        assertThat(actualResult).isEqualTo(
            Arrays.asList(
                new Contact("April"),
                new Contact("Thomas Aquinas"),
                new Contact("Rene Descartes"),
                new Contact("David Hume")
            ));
    }

    @Test
    @DisplayName("Не все контакты с полным именем в порядке убывания")
    void notAllContactsWithFullNameDesc() {
        List<Contact> actualResult = parseContacts(
            new String[] {
                "April",
                "Thomas"
            },
            SortOrder.DESC
        );

        assertThat(actualResult).isEqualTo(
            Arrays.asList(
                new Contact("Thomas"),
                new Contact("April")
            ));
    }

    @Test
    @DisplayName("Все контакты с полным именем в порядке убывания")
    void fullNameContactsDesc() {
        List<Contact> actualResult = parseContacts(
            new String[] {
                "Paul Erdos",
                "Leonhard Euler",
                "Carl Gauss"
            },
            SortOrder.DESC
        );

        assertThat(actualResult).isEqualTo(
            Arrays.asList(
                new Contact("Carl Gauss"),
                new Contact("Leonhard Euler"),
                new Contact("Paul Erdos")
            ));
    }

    @Test
    @DisplayName("Контакты с одинаковой фамилией")
    void matchingSurnameContactsAsc() {
        List<Contact> actualResult = parseContacts(
            new String[] {
                "Mark Black",
                "Andrew Black"
            },
            SortOrder.ASC
        );

        assertThat(actualResult).isEqualTo(
            Arrays.asList(
                new Contact("Andrew Black"),
                new Contact("Mark Black")
            ));
    }
}
