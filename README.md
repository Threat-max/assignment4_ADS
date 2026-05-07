SE-2512 TOLENDI ARSEN
# Assignment 4: Graph Traversal and Representation System

## A. Project Overview

This project implements a graph traversal system in Java, exploring how graphs can be represented in memory and traversed efficiently using two classic algorithms.

A **graph** is a data structure consisting of **vertices** (nodes) and **edges** (connections between nodes). Graphs model real-world networks such as road maps, social connections, and computer networks.

- **Vertices** represent individual entities (e.g., cities, users, web pages)
- **Edges** represent relationships or connections between those entities (e.g., roads, friendships, hyperlinks)

This project implements two fundamental traversal algorithms:
- **BFS (Breadth-First Search)** — explores neighbors level by level
- **DFS (Depth-First Search)** — explores as deep as possible before backtracking

---

## B. Class Descriptions

### `Vertex`
Represents a single node in the graph. Contains a unique integer `id` used to identify and reference it. Provides a constructor, getter, and `toString()`.

### `Edge`
Represents a directed connection between two vertices. Stores `source` and `destination` as `Vertex` objects. Supports directed edges (A → B does not imply B → A).

### `Graph`
The core class. Stores vertices in a `HashMap<Integer, Vertex>` and uses an **adjacency list** represented as `HashMap<Integer, List<Edge>>`.

**Why adjacency list over adjacency matrix?**
| Feature | Adjacency List | Adjacency Matrix |
|---|---|---|
| Space | O(V + E) | O(V²) |
| Add edge | O(1) | O(1) |
| Find neighbors | O(degree) | O(V) |
| Best for | Sparse graphs | Dense graphs |

For most real-world graphs (sparse), the adjacency list is far more memory-efficient.

### `Experiment`
Handles automated performance testing. Builds graphs of sizes 10, 30, and 100 vertices, runs both traversals, records execution time using `System.nanoTime()`, and prints a formatted results table.

---

## C. Algorithm Descriptions

### Breadth-First Search (BFS)

**Step-by-step:**
1. Enqueue the start vertex; mark it visited
2. Dequeue the front vertex; process it (print it)
3. For each unvisited neighbor: mark visited, enqueue
4. Repeat until the queue is empty

**Uses a Queue (FIFO)** — processes vertices in the order they were discovered, exploring all neighbors at distance 1 before distance 2, etc.

**Use cases:**
- Finding the shortest path in an unweighted graph
- Web crawlers (level-by-level exploration)
- Social network friend suggestions (by degree of connection)
- GPS navigation (finding nearest locations first)

**Time Complexity:** O(V + E) — every vertex and edge is visited once

---

### Depth-First Search (DFS)

**Step-by-step:**
1. Push the start vertex onto the stack
2. Pop the top vertex; if unvisited, mark it and process it
3. Push all unvisited neighbors onto the stack
4. Repeat until the stack is empty

**Uses a Stack (LIFO)** — dives deep along one path before backtracking, exploring a full branch before moving to siblings.

**Use cases:**
- Detecting cycles in a graph
- Topological sorting (task dependency ordering)
- Solving mazes or puzzles
- Finding connected components

**Time Complexity:** O(V + E) — every vertex and edge is visited once

---

## D. Experimental Results

Graphs were built using a ring structure plus skip-one edges to ensure connectivity and consistent density across sizes.

| Graph Size | Vertices | Edges | BFS Time | DFS Time |
|---|---|---|---|---|
| Small  | 10  | ~14  | ~853,171 ns  | ~1,516,519 ns |
| Medium | 30  | ~40  | ~2,794,315 ns | ~2,439,031 ns |
| Large  | 100 | ~134 | ~7,061,994 ns | ~1,531,187 ns |

*(Times measured using `System.nanoTime()`. Results vary slightly between runs due to JVM warmup.)*

### Observations

- **Both algorithms scale linearly** with graph size, consistent with the expected O(V + E) complexity.
- **BFS shows higher overhead** on larger graphs due to Queue management and the cost of processing vertices in FIFO order across memory.
- **DFS tends to be faster in practice** on these graph structures because its Stack (ArrayDeque) has better cache locality than BFS's Queue when graphs are large.
- The **first run** of any traversal is slower due to JVM class loading and JIT compilation. Subsequent runs on the same JVM are faster — this explains why DFS times look faster on the 100-vertex test (it runs after BFS has already warmed the JVM).



## F. Reflection

Working through this assignment deepened my understanding of how graph traversal algorithms differ not just in output, but in the fundamental data structure driving them. BFS and DFS both have the same theoretical time complexity — O(V + E) — yet they behave very differently in practice. BFS explores "outward" in rings from the source, which is ideal when you need the shortest path, but requires holding an entire frontier of nodes in memory. DFS, by contrast, commits to one direction and goes all the way before backtracking, which uses less memory but can miss shorter paths entirely.

The most challenging part was implementing the adjacency list correctly and making sure the `addEdge` method validated that both vertices existed before creating the edge. Another subtle challenge was the DFS implementation: using an explicit stack instead of recursion means a vertex can be pushed onto the stack multiple times before being visited, so the `visited` check must happen at pop time, not push time. Understanding this distinction — and why it matters — was the biggest technical takeaway from this assignment.

---
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/2d5d95fa-eb98-4e9a-8973-d3d9e0e606e8" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/98ca2088-38e2-4306-b519-11f4f094648f" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/8b0aacd0-f23b-4ff8-a719-a9caf9989c57" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/7f7be002-9fc4-48e9-a3e5-669f848b21f6" />
