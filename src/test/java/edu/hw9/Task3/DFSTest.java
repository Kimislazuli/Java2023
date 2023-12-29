package edu.hw9.Task3;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DFSTest {
    Graph graph = new Graph(9);

    DFSTest() {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(7, 8);
    }

    @Test
    void pathNotExists() {
        List<Integer> actualResult = graph.findPath(0, 7);

        assertThat(actualResult).isEmpty();
    }

    @Test
    void pathExists() {
        List<Integer> actualResult = graph.findPath(0, 6);

        assertThat(actualResult).containsExactly(0, 2, 6);
    }
}
