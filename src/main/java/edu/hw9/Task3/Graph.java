package edu.hw9.Task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class Graph {
    private final int vertices;
    private final Map<Integer, ArrayList<Integer>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int v, int w) {
        adjacencyList.get(v).add(w);
        adjacencyList.get(w).add(v);
    }

    public List<Integer> findPath(int start, int end) {
        boolean[] visited = new boolean[vertices];
        visited[start] = true;

        DFSRecursiveTask task = new DFSRecursiveTask(start, end, visited.clone());
        List<Integer> path = new ArrayList<>(task.invoke());

        if (path.isEmpty() || !path.contains(end)) {
            return Collections.emptyList();
        }

        return path;
    }

    private class DFSRecursiveTask extends RecursiveTask<List<Integer>> {
        private final int current;
        private final int end;
        private final boolean[] visited;

        DFSRecursiveTask(int current, int end, boolean[] visited) {
            this.current = current;
            this.end = end;
            this.visited = visited;
        }

        protected List<Integer> compute() {
            List<Integer> path = new ArrayList<>();
            path.add(current);

            if (current == end) {
                return path;
            }

            List<DFSRecursiveTask> tasks = new ArrayList<>();

            for (int neighbor : adjacencyList.get(current)) {
                if (!visited[neighbor]) {
                    boolean[] newVisited = visited.clone();
                    newVisited[neighbor] = true;
                    DFSRecursiveTask task = new DFSRecursiveTask(neighbor, end, newVisited);
                    task.fork();
                    tasks.add(task);
                }
            }

            for (DFSRecursiveTask task : tasks) {
                List<Integer> subPath = task.join();
                if (!subPath.isEmpty() && subPath.get(subPath.size() - 1) == end) {
                    path.addAll(subPath);
                    return path;
                }
            }

            return Collections.emptyList();
        }
    }
}
