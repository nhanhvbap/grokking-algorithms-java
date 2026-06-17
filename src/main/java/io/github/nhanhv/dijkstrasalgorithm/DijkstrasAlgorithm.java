package io.github.nhanhv.dijkstrasalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demonstrates Dijkstra's Algorithm for finding the shortest path in a weighted, directed graph
 * using a simple linear scan for the lowest-cost node.
 *
 * <p>Dijkstra's algorithm works by greedily selecting the unvisited node with the lowest known
 * cost, then relaxing (updating) the costs of its neighbors. This process repeats until all
 * reachable nodes have been processed.
 *
 * <p><strong>Constraints:</strong> This implementation requires all edge weights to be
 * non-negative. Negative weights will produce incorrect results.
 *
 * <p>Time complexity: O(V²) — due to the linear scan for the minimum-cost node at each step, where
 * V is the number of vertices.
 *
 * <p>Space complexity: O(V + E) — for the graph, cost table, parent table, and processed list.
 *
 * @see DijkstrasAlgorithmPriorityQueue for an optimized version using a priority queue.
 */
public class DijkstrasAlgorithm {

  /**
   * The weighted directed graph, represented as an adjacency map. Each key is a node name; its
   * value is a map of neighboring node names to their corresponding edge weights.
   */
  private static Map<String, Map<String, Double>> graph = new HashMap<>();

  /**
   * Tracks nodes that have already been processed (finalized) so they are not revisited during the
   * algorithm.
   */
  private static List<String> processed = new ArrayList<>();

  /**
   * Scans the cost table and returns the name of the unprocessed node with the lowest known cost.
   *
   * <p>This is the O(V) linear-scan variant. Nodes already in the {@code processed} list are
   * skipped.
   *
   * @param costs a map of node names to their current known costs from the start node
   * @return the name of the lowest-cost unprocessed node, or {@code null} if all nodes have been
   *     processed
   */
  private static String findLowestCostNode(Map<String, Double> costs) {
    Double lowestCost = Double.POSITIVE_INFINITY;
    String lowestCostNode = null;

    // Go through each node
    for (Map.Entry<String, Double> node : costs.entrySet()) {
      Double cost = node.getValue();
      // If it's the lowest cost so far and hasn't been processed yet...
      if (cost < lowestCost && !processed.contains(node.getKey())) {
        // ... set it as the new lowest-cost node.
        lowestCost = cost;
        lowestCostNode = node.getKey();
      }
    }

    return lowestCostNode;
  }

  /**
   * Entry point that builds a sample weighted graph and runs Dijkstra's algorithm to compute the
   * shortest path cost from {@code "start"} to all other nodes.
   *
   * <p>The sample graph has the following edges:
   *
   * <pre>
   *   start → a  (cost 6)
   *   start → b  (cost 2)
   *   b     → a  (cost 3)
   *   b     → fin (cost 5)
   *   a     → fin (cost 1)
   * </pre>
   *
   * Expected output: {@code {a=5.0, b=2.0, fin=6.0}}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    graph.put("start", new HashMap<>());
    graph.get("start").put("a", 6.0);
    graph.get("start").put("b", 2.0);

    graph.put("a", new HashMap<>());
    graph.get("a").put("fin", 1.0);

    graph.put("b", new HashMap<>());
    graph.get("b").put("a", 3.0);
    graph.get("b").put("fin", 5.0);

    graph.put("fin", new HashMap<>());

    // The costs table
    Map<String, Double> costs = new HashMap<>();
    costs.put("a", 6.0);
    costs.put("b", 2.0);
    costs.put("fin", Double.POSITIVE_INFINITY);

    // the parents table
    Map<String, String> parents = new HashMap<>();
    parents.put("a", "start");
    parents.put("b", "start");
    parents.put("fin", null);

    String node = findLowestCostNode(costs);
    while (node != null) {
      Double cost = costs.get(node);
      // Go through all the neighbors of this node

      Map<String, Double> neighbors = graph.get(node);

      for (String n : neighbors.keySet()) {
        double newCost = cost + neighbors.get(n);
        // If it's cheaper to get to this neighbor by going through this node
        if (costs.get(n) > newCost) {
          // ... update the cost for this node
          costs.put(n, newCost);
          // This node becomes the new parent for this neighbor.
          parents.put(n, node);
        }
      }
      // Mark the node as processed
      processed.add(node);

      // Find the next node to process, and loop
      node = findLowestCostNode(costs);
    }

    System.out.println("Cost from the start to each node:");
    System.out.println(costs); // { a: 5, b: 2, fin: 6 }
  }
}
