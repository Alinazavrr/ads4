public class Main {
    public static void main(String[] args) {
        System.out.println("=== Small Graph (10 vertices) ===");
        Graph small = Experiment.buildRandomGraph(10, 15);
        small.printGraph();

        System.out.println();
        System.out.println("BFS order: " + small.bfs(0));
        System.out.println("DFS order: " + small.dfs(0));

        Experiment experiment = new Experiment();

        long start = System.nanoTime();
        small.bfs(0);
        long end = System.nanoTime();
        System.out.println();
        System.out.println("Small graph BFS time: " + (end - start) + " ns");

        start = System.nanoTime();
        small.dfs(0);
        end = System.nanoTime();
        System.out.println("Small graph DFS time: " + (end - start) + " ns");

        experiment.runMultipleTests();
        experiment.printResults();
    }
}
