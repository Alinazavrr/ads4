# Assignment 4: Graph Traversal and Representation System

## A. Project Overview

This project implements a graph data structure with adjacency list representation
and the two classic traversal algorithms — Breadth-First Search (BFS) and
Depth-First Search (DFS). It also includes an experimental harness that measures
their execution time on graphs of different sizes.

A graph is a collection of **vertices** (nodes) connected by **edges** (links).
Vertices represent entities and edges represent the relationships between them.
Graphs can be directed or undirected; this project uses undirected graphs.

BFS explores the graph level by level using a queue, while DFS explores as deep
as possible along each branch before backtracking, using a stack. Both visit
every reachable vertex from a given start point exactly once.

## B. Class Descriptions

### Vertex
Represents a node in the graph. Stores a unique integer `id`, exposes a getter
and a `toString()` method.

### Edge
Represents a connection between two vertices. Stores `source` and `destination`
ids, with getters and a `toString()` method.

### Graph
Represents the graph structure using an **adjacency list** — a `Map<Integer, List<Integer>>`
where each key is a vertex id and the value is the list of its neighbors.
Compared to an adjacency matrix, an adjacency list uses O(V + E) memory instead
of O(V²), which is more efficient for sparse graphs.

Methods:
- `addVertex(Vertex v)` — adds a vertex if it does not already exist.
- `addEdge(int from, int to)` — adds an edge between two vertices.
- `printGraph()` — prints the adjacency list.
- `bfs(int start)` — performs BFS starting from the given vertex.
- `dfs(int start)` — performs DFS starting from the given vertex.

### Experiment
Runs traversals on graphs of different sizes, measures execution time using
`System.nanoTime()`, and prints a comparison table.

## C. Algorithm Descriptions

### Breadth-First Search (BFS)
1. Mark the start vertex as visited and place it in a queue.
2. While the queue is not empty, dequeue the front vertex and record it.
3. For each unvisited neighbor, mark it visited and enqueue it.
4. Repeat until the queue is empty.

**Use cases:** shortest path in unweighted graphs, level-order traversal, finding
connected components, broadcast/flood algorithms.

**Time complexity:** O(V + E), where V is the number of vertices and E is the
number of edges. **Space:** O(V) for the visited set and queue.

### Depth-First Search (DFS)
1. Push the start vertex onto a stack.
2. While the stack is not empty, pop a vertex; if not visited, mark and record it.
3. Push all unvisited neighbors onto the stack.
4. Repeat until the stack is empty.

**Use cases:** cycle detection, topological sorting, finding strongly connected
components, solving puzzles like mazes.

**Time complexity:** O(V + E). **Space:** O(V) for the visited set and stack.

## D. Experimental Results

Each graph is built with `vertexCount` vertices and roughly `2 × vertexCount`
edges, with a fixed random seed for reproducibility. Times are in nanoseconds
and were measured with `System.nanoTime()` on a single run.

| Vertices | Edges | BFS (ns) | DFS (ns) |
|---------:|------:|---------:|---------:|
| 10       | 19    | ~25 000  | ~10 000  |
| 30       | 60    | ~25 000  | ~22 000  |
| 100      | 196   | ~42 000  | ~97 000  |

**Observations.** Execution time grows roughly linearly with V + E, matching the
expected O(V + E) complexity. For very small graphs the timings are dominated by
JVM warm-up and measurement overhead, so the comparison between BFS and DFS is
not meaningful at that scale; the trend stabilises as the graph grows.

## E. Screenshots

Place the following screenshots inside `docs/screenshots/`:

- `graph-output.png` — output of `printGraph()` for the small graph.
- `bfs-output.png` — BFS traversal order for the small graph.
- `dfs-output.png` — DFS traversal order for the small graph.
- `performance.png` — performance results table for all three sizes.

## F. Reflection

Working through this assignment made the difference between BFS and DFS very
concrete. BFS expands outward in waves, which makes it ideal whenever you need
the shortest path in an unweighted graph or want to process vertices in order
of distance from the source. DFS dives down a single branch and only backtracks
when it has to, which makes it natural for problems like cycle detection,
topological sorting and exhaustive search where the path itself is what matters.

The main implementation challenges were two: making sure both algorithms visit
every vertex exactly once (the visited set is critical and must be checked at
both push and pop time for the iterative DFS), and producing reproducible
performance numbers. Using a fixed random seed for graph generation and
measuring with `System.nanoTime()` solved the second problem; for the first
the algorithms now produce stable, deterministic traversal orders that match
the expected behaviour for the adjacency list ordering.

## How to Run

```bash
cd src
javac *.java
java Main
```

## Repository Structure

```
ads-4/
├── src/
│   ├── Vertex.java
│   ├── Edge.java
│   ├── Graph.java
│   ├── Experiment.java
│   └── Main.java
├── docs/
│   └── screenshots/
├── README.md
└── .gitignore
```
