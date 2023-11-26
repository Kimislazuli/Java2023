package edu.hw7.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ReadWriteLockPersonDatabaseTest {
    int THREADS_AMOUNT = 6;

    @Test
    @DisplayName("Количество записей при состоянии гонки корректно")
    void afterAddUniqueElementsDatabaseHaveCorrectSize() throws InterruptedException {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        List<Thread> threads = new ArrayList<>();

        int threadLoad = 200;

        for (int i = 0; i < THREADS_AMOUNT; i++) {
            int threadNumber = i;
            Thread thread = new Thread(() -> {
                for (int j = threadNumber * threadLoad; j < (threadNumber + 1) * threadLoad; j++) {
                    database.add(new Person(
                        j + threadLoad * threadNumber,
                        "name" + j + threadLoad * threadNumber,
                        "address" + j + threadLoad * threadNumber,
                        "phone" + j + threadLoad * threadNumber
                    ));
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int actualResult = database.getDatabaseSize();

        assertThat(actualResult).isEqualTo(1200);
    }

    @Test
    @DisplayName("Нельзя найти, если одно из полей null")
    void cannotFindIfOneFieldIsNull() {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        database.add(new Person(1, "John Doe", null, null));

        List<Person> actualResult = database.findByName("John Doe");

        assertThat(actualResult).isEmpty();
    }

    @Test
    @DisplayName("Можно найти по имени")
    void canFindByName() {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        database.add(new Person(1, "John Doe", "address1", "number1"));
        database.add(new Person(2, "John Doe", "address2", "number2"));

        List<Person> actualResult = database.findByName("John Doe");

        assertThat(actualResult).containsExactlyInAnyOrderElementsOf(List.of(
            new Person(1, "John Doe", "address1", "number1"),
            new Person(2, "John Doe", "address2", "number2")));
    }

    @Test
    @DisplayName("Можно найти по адресу")
    void canFindByAddress() {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        database.add(new Person(1, "John Doe", "address", "number"));
        database.add(new Person(2, "Jane Doe", "address", "number"));

        List<Person> actualResult = database.findByAddress("address");

        assertThat(actualResult).containsExactlyInAnyOrderElementsOf(List.of(
            new Person(1, "John Doe", "address", "number"),
            new Person(2, "Jane Doe", "address", "number")
        ));
    }

    @Test
    @DisplayName("Можно найти по номеру телефона")
    void canFindByPhone() {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        database.add(new Person(1, "John Doe", "address", "number"));
        database.add(new Person(2, "Jane Doe", "address", "number"));

        List<Person> actualResult = database.findByPhone("number");

        assertThat(actualResult).containsExactlyInAnyOrderElementsOf(List.of(
            new Person(1, "John Doe", "address", "number"),
            new Person(2, "Jane Doe", "address", "number")
        ));
    }

    @Test
    @DisplayName("Поиск по имени, которое отсутствует в базе")
    void cannotFindByNameThatDoesntExist() {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        List<Person> actualResult = database.findByName("John Doe");

        assertThat(actualResult).isEmpty();
    }

    @Test
    @DisplayName("Поиск по телефону, которое отсутствует в базе")
    void cannotFindByPhoneThatDoesntExist() {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        List<Person> actualResult = database.findByPhone("555-555");

        assertThat(actualResult).isEmpty();
    }

    @Test
    @DisplayName("Поиск по адресу, которое отсутствует в базе")
    void cannotFindByAddressThatDoesntExist() {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        List<Person> actualResult = database.findByAddress("5th Avenue");

        assertThat(actualResult).isEmpty();
    }

    @Test
    @DisplayName("Замена данных о человеке при повторной записи по айди")
    void rewritePersonInfo() {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        database.add(new Person(1, "John Doe", "address", "number"));
        database.add(new Person(1, "Jane Doe", "address", "number"));

        List<Person> actualResult = database.findByPhone("number");

        assertThat(actualResult).containsExactlyInAnyOrderElementsOf(List.of(
            new Person(1, "Jane Doe", "address", "number")
        ));
    }


    @Test
    @DisplayName("Удаление + чтение в другом потоке")
    void delete() throws InterruptedException {
        PersonDatabase database = new ReadWriteLockPersonDatabase();

        database.add(new Person(1, "John Doe", "address", "number"));

        Thread threadDeleter = new Thread(() -> {
            database.delete(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadReader = new Thread(() -> {
            List<Person> actualResult = database.findByName("John Doe");
            assertThat(actualResult).isEmpty();
        });

        threadDeleter.start();
        threadReader.start();

        threadDeleter.join();
        threadReader.join();
    }
}
