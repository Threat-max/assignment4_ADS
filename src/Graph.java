import java.util.*;
public class Graph {

    // Maps each vertex ID to its Vertex object
    private Map<Integer, Vertex> vertices;

    // Maps each vertex ID to its list of outgoing edges
    private Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        Vertex source = vertices.get(from);
        Vertex destination = vertices.get(to);

        if (source == null || destination == null) {
            System.out.println("Error: vertex " + from + " or " + to + " not found.");
            return;
        }

        Edge edge = new Edge(source, destination);
        adjacencyList.get(from).add(edge);
    }

    public void printGraph() {
        System.out.println("--- Graph Adjacency List ---");
        for (int id : adjacencyList.keySet()) {
            System.out.print(vertices.get(id) + " : ");
            List<Edge> edges = adjacencyList.get(id);
            if (edges.isEmpty()) {
                System.out.print("(no edges)");
            } else {
                for (int i = 0; i < edges.size(); i++) {
                    System.out.print(edges.get(i).getDestination());
                    if (i < edges.size() - 1) System.out.print(", ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }

    public void bfs(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("BFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // Begin at the start vertex
        queue.add(start);
        visited.add(start);

        System.out.print("BFS from " + start + ": ");

        while (!queue.isEmpty()) {
            // Dequeue and process the front vertex
            int current = queue.poll();
            System.out.print(current + " ");

            // Enqueue each unvisited neighbor
            for (Edge edge : adjacencyList.get(current)) {
                int neighborId = edge.getDestination().getId();
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    queue.add(neighborId);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("DFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        // Begin at the start vertex
        stack.push(start);

        System.out.print("DFS from " + start + ": ");

        while (!stack.isEmpty()) {
            // Pop and process the top vertex
            int current = stack.pop();

            // Skip if already visited (can be re-added to stack before processing)
            if (visited.contains(current)) continue;

            visited.add(current);
            System.out.print(current + " ");

            // Push unvisited neighbors (in reverse order to preserve natural order)
            List<Edge> edges = adjacencyList.get(current);
            for (int i = edges.size() - 1; i >= 0; i--) {
                int neighborId = edges.get(i).getDestination().getId();
                if (!visited.contains(neighborId)) {
                    stack.push(neighborId);
                }
            }
        }
        System.out.println();
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Edge> edges : adjacencyList.values()) {
            count += edges.size();
        }
        return count;
    }
}
