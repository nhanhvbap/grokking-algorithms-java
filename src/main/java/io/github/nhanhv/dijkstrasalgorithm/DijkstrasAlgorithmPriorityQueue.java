package io.github.nhanhv.dijkstrasalgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Demonstrates Dijkstra's Algorithm for finding the shortest path in a weighted, directed graph
 * using a {@link PriorityQueue} for efficient minimum-cost node selection.
 *
 * <p>This implementation improves upon the naive O(V²) linear-scan approach by using a min-heap
 * priority queue, which reduces the node-selection step to O(log V) per iteration.
 *
 * <p><strong>Constraints:</strong> All edge weights must be non-negative.
 *
 * <p>Time complexity: O((V + E) log V) — where V is the number of vertices and E is the number of
 * edges.
 *
 * <p>Space complexity: O(V + E) — for the graph, cost table, and priority queue.
 *
 * <p>Requires Java 17+ (uses {@code record} for the {@link Tuple} type).
 *
 * @see DijkstrasAlgorithm for a simpler O(V²) variant without a priority queue.
 */
// Suggested to be run with Java 17+
public class DijkstrasAlgorithmPriorityQueue {

  /**
   * The weighted directed graph, represented as an adjacency map. Each key is a vertex name; its
   * value is a set of {@link Tuple} entries representing neighboring vertices and their edge costs.
   */
  private static final Map<String, Set<Tuple>> GRAPH = new HashMap<>(4);

  static {
    Set<Tuple> start = new HashSet<>(2);
    start.add(new Tuple("a", 6));
    start.add(new Tuple("b", 2));
    GRAPH.put("start", start);

    GRAPH.put("a", new HashSet<>(1));
    GRAPH.get("a").add(new Tuple("fin", 1));

    Set<Tuple> b = new HashSet<>(2);
    b.add(new Tuple("a", 3));
    b.add(new Tuple("fin", 5));
    GRAPH.put("b", b);
    GRAPH.put("fin", new HashSet<>(1));
  }

  /**
   * Entry point that runs Dijkstra's algorithm on the pre-built sample graph starting from {@code
   * "start"} and prints the shortest cost to each node.
   *
   * <p>Expected output: {@code {start=0.0, a=5.0, b=2.0, fin=6.0}}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println("Cost from the start to each node:");
    System.out.println(calculateDistances("start")); // { a: 5, b: 2, fin: 6 }
  }

  /**
   * Computes the shortest distance from {@code startVertex} to all other reachable vertices in
   * {@link #GRAPH} using Dijkstra's algorithm with a min-heap priority queue.
   *
   * <p>The algorithm maintains a cost map initialized to infinity for all nodes except the start.
   * It repeatedly polls the lowest-cost entry from the queue, relaxes outgoing edges, and pushes
   * updated costs back into the queue. Stale (outdated) queue entries are skipped via a cost
   * comparison guard.
   *
   * @param startVertex the name of the vertex from which to compute distances
   * @return a map of vertex names to their minimum cost from {@code startVertex}; vertices that are
   *     unreachable are not included in the result
   */
  private static Map<String, Double> calculateDistances(String startVertex) {
    // The costs table
    Map<String, Double> costs = new HashMap<>();
    costs.put(startVertex, 0.0);

    PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparing(Tuple::cost));
    pq.offer(new Tuple(startVertex, 0));

    while (!pq.isEmpty()) {
      Tuple node = pq.poll();

      // Skip this entry if a shorter path to node.vertex has already been found
      if (node.cost > costs.getOrDefault(node.vertex, Double.POSITIVE_INFINITY)) {
        continue;
      }
      // Go through all the neighbors of this node
      Set<Tuple> neighbors = GRAPH.getOrDefault(node.vertex, Collections.emptySet());
      for (Tuple n : neighbors) {
        double newCost = node.cost + n.cost;
        // If it's cheaper to get to this neighbor by going through this node
        if (newCost < costs.getOrDefault(n.vertex, Double.POSITIVE_INFINITY)) {
          // ... update the cost for this node
          costs.put(n.vertex, newCost);
          pq.offer(new Tuple(n.vertex, newCost));
        }
      }
    }
    return costs;
  }

  /**
   * An immutable pair representing a graph vertex and the cost to reach it.
   *
   * <p>Used as entries in the priority queue and in the adjacency sets of {@link #GRAPH}.
   *
   * @param vertex the name of the graph vertex
   * @param cost the edge weight or accumulated cost associated with this vertex
   */
  private record Tuple(String vertex, double cost) {}
}
