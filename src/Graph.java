import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
}
