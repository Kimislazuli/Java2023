package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> collection;
    private int cursor;

    public BackwardIterator(List<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection can't be null.");
        }
        this.collection = collection;
        cursor = collection.size();
    }

    @Override
    public boolean hasNext() {
        return cursor - 1 >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Iterator doesn't hava next element.");
        }
        cursor--;
        return collection.get(cursor);
    }
}
