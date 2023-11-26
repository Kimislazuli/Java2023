package edu.hw3.Task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BackwardIteratorTest {
    @Test
    @DisplayName("Итератор списка целых чисел")
    void integerListBackwardIterator() {
        Iterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3));
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    @DisplayName("Итератор списка целых строк")
    void stringListBackwardIterator() {
        Iterator<String> iterator = new BackwardIterator<>(List.of("Hello", "world"));
        assertThat(iterator.next()).isEqualTo("world");
        assertThat(iterator.next()).isEqualTo("Hello");
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListBackwardIterator() {
        assertThrows(
            NoSuchElementException.class,
            () -> {
                Iterator<Integer> iterator = new BackwardIterator<>(List.of());
                iterator.next();
            }
        );
    }

    @Test
    @DisplayName("Null")
    void nullListBackwardIterator() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Iterator<Integer> iterator = new BackwardIterator<>(null);
            }
        );
    }
}
