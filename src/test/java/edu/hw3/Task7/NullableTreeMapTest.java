package edu.hw3.Task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class NullableTreeMapTest {
    @Test
    @DisplayName("Null разрешён в качестве ключа")
    void bullKeyAllowed() {
        TreeMap<String, String> tree = new NullableTreeMap();
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
