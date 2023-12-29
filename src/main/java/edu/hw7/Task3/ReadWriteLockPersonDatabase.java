package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> database = new HashMap<>();
    private final Map<String, List<Person>> names = new HashMap<>();
    private final Map<String, List<Person>> phoneNumbers = new HashMap<>();
    private final Map<String, List<Person>> addresses = new HashMap<>();
    private final ReadWriteLock locker = new ReentrantReadWriteLock();

    public void add(Person person) {
        locker.writeLock().lock();

        try {
            if (database.containsKey(person.id())) {
                delete(person.id());
            }

            database.put(person.id(), person);

            if (names.containsKey(person.name())) {
                names.get(person.name()).add(person);
            } else {
                List<Person> people = new ArrayList<>();
                people.add(person);
                names.put(person.name(), people);
            }

            if (phoneNumbers.containsKey(person.phoneNumber())) {
                phoneNumbers.get(person.phoneNumber()).add(person);
            } else {
                List<Person> people = new ArrayList<>();
                people.add(person);
                phoneNumbers.put(person.phoneNumber(), people);
            }

            if (addresses.containsKey(person.address())) {
                addresses.get(person.address()).add(person);
            } else {
                List<Person> people = new ArrayList<>();
                people.add(person);
                addresses.put(person.address(), people);
            }
        } finally {
            locker.writeLock().unlock();
        }

    }

    public void delete(int id) {
        locker.writeLock().lock();

        try {
            if (database.get(id) != null) {
                Person personToDelete = database.remove(id);

                List<Person> nameMatch = names.get(personToDelete.name());
                nameMatch.remove(personToDelete);
                if (nameMatch.isEmpty()) {
                    names.remove(personToDelete.name());
                }

                List<Person> addressMatch = addresses.get(personToDelete.address());
                addressMatch.remove(personToDelete);
                if (addressMatch.isEmpty()) {
                    addresses.remove(personToDelete.address());
                }

                List<Person> phoneNumberMatch = phoneNumbers.get(personToDelete.phoneNumber());
                phoneNumberMatch.remove(personToDelete);
                if (phoneNumberMatch.isEmpty()) {
                    phoneNumbers.remove(personToDelete.phoneNumber());
                }
            }
        } finally {
            locker.writeLock().unlock();
        }
    }

    public List<Person> findByName(String name) {
        List<Person> people;
        locker.readLock().lock();

        try {
            people = names.get(name);
        } finally {
            locker.readLock().unlock();
        }
        if (people != null && !people.isEmpty()) {
            return people.stream().filter(this::personIsValid).toList();
        }
        return Collections.emptyList();
    }

    public synchronized List<Person> findByAddress(String address) {
        List<Person> people;
        locker.readLock().lock();

        try {
            people = addresses.get(address);
        } finally {
            locker.readLock().unlock();
        }

        if (people != null && !people.isEmpty()) {
            return people.stream().filter(this::personIsValid).toList();
        }
        return Collections.emptyList();
    }

    public synchronized List<Person> findByPhone(String phone) {
        List<Person> people;
        locker.readLock().lock();

        try {
            people = phoneNumbers.get(phone);
        } finally {
            locker.readLock().unlock();
        }

        if (people != null && !people.isEmpty()) {
            return people.stream().filter(this::personIsValid).toList();
        }
        return Collections.emptyList();
    }

    private boolean personIsValid(Person person) {
        return person.name() != null && person.address() != null && person.phoneNumber() != null;
    }

    public int getDatabaseSize() {
        return database.size();
    }
}
