import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class Graph {
    private final Map<Integer, List<Integer>> adjacencyList;
    private final Map<Integer, Vertex> vertices;
    private final boolean directed;

    public Graph() {
        this(false);
    }

    public Graph(boolean directed) {
        this.adjacencyList = new HashMap<>();
        this.vertices = new HashMap<>();
        this.directed = directed;
    }

    public void addVertex(Vertex v) {
        vertices.putIfAbsent(v.getId(), v);
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        if (!adjacencyList.containsKey(from)) {
            addVertex(new Vertex(from));
        }
        if (!adjacencyList.containsKey(to)) {
            addVertex(new Vertex(to));
        }
        adjacencyList.get(from).add(to);
        if (!directed) {
            adjacencyList.get(to).add(from);
        }
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        int total = 0;
        for (List<Integer> neighbors : adjacencyList.values()) {
            total += neighbors.size();
        }
        return directed ? total : total / 2;
    }

    public void printGraph() {
        Map<Integer, List<Integer>> sorted = new TreeMap<Integer, List<Integer>>(adjacencyList);
        for (Map.Entry<Integer, List<Integer>> entry : sorted.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.println(entry.getValue());
        }
    }

    // BFS uses a queue to visit vertices level by level starting from the source.
    public List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        if (!adjacencyList.containsKey(start)) return order;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return order;
    }

    // DFS uses an explicit stack to traverse as deep as possible before backtracking.
    public List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        if (!adjacencyList.containsKey(start)) return order;

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited.contains(current)) continue;
            visited.add(current);
            order.add(current);
            List<Integer> neighbors = adjacencyList.get(current);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
        return order;
    }
}
