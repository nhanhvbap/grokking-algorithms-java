package io.github.nhanhv.breadthfirstsearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Demonstrates the Breadth-First Search (BFS) algorithm on a social network graph.
 *
 * <p>BFS explores all neighbors of a node before moving to the next level of neighbors. This
 * guarantees that the first path found to a target is the shortest path (in terms of number of
 * edges).
 *
 * <p>In this example, BFS is used to find a "mango seller" — identified by a name ending with the
 * letter {@code 'm'} — starting from a given person in the graph.
 *
 * <p>Time complexity: O(V + E) — where V is the number of vertices (people) and E is the number of
 * edges (connections).
 *
 * <p>Space complexity: O(V) — for the search queue and the visited list.
 */
public class BreadthFirstSearch {

  /** The social network graph mapping each person to their list of direct connections. */
  private static Map<String, List<String>> graph = new HashMap<>();

  /**
   * Searches the graph starting from {@code name} to find a mango seller among their connections
   * using breadth-first traversal.
   *
   * <p>A person is considered a mango seller if their name ends with the letter {@code 'm'}.
   * Already-visited nodes are tracked to prevent cycles and redundant processing.
   *
   * @param name the starting node (person) from which to begin the search
   */
  private static void search(String name) {
    Queue<String> searchQueue = new ArrayDeque<>(graph.get(name));
    // This list is how you keep track of which people you've searched before.
    List<String> searched = new ArrayList<>();

    while (!searchQueue.isEmpty()) {
      String person = searchQueue.poll();
      // Only search this person if you haven't already searched them
      if (!searched.contains(person)) {
        if (person_is_seller(person)) {
          System.out.println(person + " is a mango seller!");
          return;
        } else {
          searchQueue.addAll(graph.get(person));
          // Marks this person as searched
          searched.add(person);
        }
      }
    }
  }

  /**
   * Determines whether a given person is a mango seller.
   *
   * <p>The heuristic used is that a mango seller's name ends with the letter {@code 'm'}.
   *
   * @param name the person's name to check
   * @return {@code true} if the name ends with {@code 'm'}; {@code false} otherwise
   */
  private static boolean person_is_seller(String name) {
    return name.endsWith("m");
  }

  /**
   * Entry point that builds a sample social network graph and runs a BFS starting from {@code
   * "you"} to find the nearest mango seller.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    graph.put("you", Arrays.asList("alice", "bob", "claire"));
    graph.put("bob", Arrays.asList("anuj", "peggy"));
    graph.put("alice", Arrays.asList("peggy"));
    graph.put("claire", Arrays.asList("thom", "jonny"));
    graph.put("anuj", Collections.emptyList());
    graph.put("peggy", Collections.emptyList());
    graph.put("thom", Collections.emptyList());
    graph.put("jonny", Collections.emptyList());

    search("you");
  }
}
