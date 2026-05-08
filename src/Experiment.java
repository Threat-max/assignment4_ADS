public class Experiment {

    // Stores timing results: [graphSize][0=BFS, 1=DFS]
    private long[][] results;
    private int[] graphSizes = {10, 30, 100};

    public Experiment() {
        results = new long[graphSizes.length][2];
    }


    public void runTraversals(Graph g, int index, boolean print) {
        if (!print) {
            // Suppress output during timing runs — redirect to null
            System.out.print("");
        }

        // --- BFS Timing ---
        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();

        // --- DFS Timing ---
        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();

        results[index][0] = bfsEnd - bfsStart;
        results[index][1] = dfsEnd - dfsStart;
    }

    public void runMultipleTests() {
        for (int i = 0; i < graphSizes.length; i++) {
            int size = graphSizes[i];
            Graph g = buildGraph(size);

            boolean isSmall = (size == 10);

            if (isSmall) {
                System.out.println("\n=== Small Graph (" + size + " vertices) ===");
                g.printGraph();
            } else {
                System.out.println("\n=== Graph with " + size + " vertices ===");
                System.out.println("(Vertices: " + g.getVertexCount() + ", Edges: " + g.getEdgeCount() + ")");
            }

            runTraversals(g, i, isSmall);
        }
    }

    private Graph buildGraph(int size) {
        Graph g = new Graph();

        // Add all vertices
        for (int i = 0; i < size; i++) {
            g.addVertex(new Vertex(i));
        }

        // Ring: 0->1->2->...->n-1->0
        for (int i = 0; i < size; i++) {
            g.addEdge(i, (i + 1) % size);
        }

        // Extra "skip" edges to increase edge density
        for (int i = 0; i < size; i += 3) {
            g.addEdge(i, (i + 2) % size);
        }

        return g;
    }

    public void printResults() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║            PERFORMANCE RESULTS (nanoTime)            ║");
        System.out.println("╠══════════════╦═══════════════════╦═══════════════════╣");
        System.out.println("║  Graph Size  ║   BFS Time        ║   DFS Time        ║");
        System.out.println("╠══════════════╬═══════════════════╬═══════════════════╣");

        for (int i = 0; i < graphSizes.length; i++) {
            long bfsNs = results[i][0];
            long dfsNs = results[i][1];
            System.out.printf("║  %-10d  ║  %-15s  ║  %-15s  ║%n",
                    graphSizes[i],
                    bfsNs + " ns",
                    dfsNs + " ns");
            System.out.printf("║              ║  %-15s  ║  %-15s  ║%n",
                    "(" + (bfsNs / 1000) + " µs)",
                    "(" + (dfsNs / 1000) + " µs)");
            if (i < graphSizes.length - 1)
                System.out.println("╠══════════════╬═══════════════════╬═══════════════════╣");
        }

        System.out.println("╚══════════════╩═══════════════════╩═══════════════════╝");
    }
}
