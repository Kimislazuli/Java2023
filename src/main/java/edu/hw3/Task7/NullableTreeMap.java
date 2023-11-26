package edu.hw3.Task7;

import java.util.Comparator;
import java.util.TreeMap;

public class NullableTreeMap extends TreeMap<String, String> {
    public NullableTreeMap() {
        super(Comparator.nullsFirst(Comparator.naturalOrder()));
    }
}
