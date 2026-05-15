import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiment {
    private final List<Result> results = new ArrayList<>();

    public static class Result {
        public final int size;
        public final int edges;
        public final long bfsTime;
        public final long dfsTime;

        public Result(int size, int edges, long bfsTime, long dfsTime) {
            this.size = size;
            this.edges = edges;
            this.bfsTime = bfsTime;
            this.dfsTime = dfsTime;
        }
    }

    public void runTraversals(Graph g) {
        long start = System.nanoTime();
        g.bfs(0);
        long bfsTime = System.nanoTime() - start;

        start = System.nanoTime();
        g.dfs(0);
        long dfsTime = System.nanoTime() - start;

        results.add(new Result(g.getVertexCount(), g.getEdgeCount(), bfsTime, dfsTime));
    }

    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        for (int size : sizes) {
            Graph g = buildRandomGraph(size, size * 2);
            runTraversals(g);
        }
    }

    public static Graph buildRandomGraph(int vertexCount, int edgeCount) {
        Graph g = new Graph(false);
        for (int i = 0; i < vertexCount; i++) {
            g.addVertex(new Vertex(i));
        }
        Random random = new Random(42);
        for (int i = 1; i < vertexCount; i++) {
            int parent = random.nextInt(i);
            g.addEdge(parent, i);
        }
        int extra = Math.max(0, edgeCount - (vertexCount - 1));
        for (int i = 0; i < extra; i++) {
            int from = random.nextInt(vertexCount);
            int to = random.nextInt(vertexCount);
            if (from != to) {
                g.addEdge(from, to);
            }
        }
        return g;
    }

    public void printResults() {
        System.out.println();
        System.out.println("=== Performance Results ===");
        System.out.printf("%-10s %-10s %-15s %-15s%n", "Vertices", "Edges", "BFS (ns)", "DFS (ns)");
        System.out.println("------------------------------------------------------");
        for (Result r : results) {
            System.out.printf("%-10d %-10d %-15d %-15d%n", r.size, r.edges, r.bfsTime, r.dfsTime);
        }
    }

    public List<Result> getResults() {
        return results;
    }
}
