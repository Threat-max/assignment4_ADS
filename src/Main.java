public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    Assignment 4: Graph Traversal System      ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // ─────────────────────────────────────────────
        // SECTION 1: Small graph demo (10 vertices)
        // Show structure + traversal output visually
        // ─────────────────────────────────────────────
        System.out.println("\n>>> SMALL GRAPH DEMO (10 vertices)");

        Graph smallGraph = new Graph();
        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }

        // Manually add edges to create an interesting structure
        int[][] edges = {
                {0, 1}, {0, 2},
                {1, 3}, {1, 4},
                {2, 5}, {2, 6},
                {3, 7}, {4, 7},
                {5, 8}, {6, 9},
                {7, 9}, {8, 9}
        };

        for (int[] e : edges) {
            smallGraph.addEdge(e[0], e[1]);
        }

        smallGraph.printGraph();

        System.out.println("\n--- Traversal Output ---");

        // BFS traversal with timing
        long bfsStart = System.nanoTime();
        smallGraph.bfs(0);
        long bfsEnd = System.nanoTime();
        System.out.println("  BFS time: " + (bfsEnd - bfsStart) + " ns");

        // DFS traversal with timing
        long dfsStart = System.nanoTime();
        smallGraph.dfs(0);
        long dfsEnd = System.nanoTime();
        System.out.println("  DFS time: " + (dfsEnd - dfsStart) + " ns");

        // ─────────────────────────────────────────────
        // SECTION 2: Performance experiments
        // Runs BFS + DFS on sizes 10, 30, 100
        // ─────────────────────────────────────────────
        System.out.println("\n>>> PERFORMANCE EXPERIMENTS");

        Experiment experiment = new Experiment();
        experiment.runMultipleTests();
        experiment.printResults();

        System.out.println("\nDone.");
    }
}
