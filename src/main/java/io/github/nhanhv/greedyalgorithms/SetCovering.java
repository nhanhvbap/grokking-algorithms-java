package io.github.nhanhv.greedyalgorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Demonstrates the Set Covering greedy algorithm for solving the <em>Set Cover Problem</em>.
 *
 * <p>The Set Cover Problem is an NP-hard optimization problem: given a universe of elements (states
 * to cover) and a collection of subsets (radio stations and the states they broadcast to), find the
 * smallest collection of subsets whose union equals the universe.
 *
 * <p>The greedy approximation works by repeatedly selecting the station that covers the most
 * <em>uncovered</em> states at each step, until all states are covered. This does not guarantee an
 * optimal solution but runs in polynomial time and produces a result within {@code O(log n)} of the
 * optimal solution.
 *
 * <p>Time complexity: O(n²) — where n is the number of stations, due to the nested loop that
 * evaluates each station at each iteration.
 *
 * <p>Space complexity: O(n) — for the sets of covered and remaining states.
 */
public class SetCovering {

  /**
   * Entry point that builds a sample radio station coverage map and runs the greedy set-covering
   * algorithm to find a near-optimal set of stations.
   *
   * <p>The universe of states to cover: {@code {mt, wa, or, id, nv, ut, ca, az}}.
   *
   * <p>Station coverage:
   *
   * <ul>
   *   <li>{@code kone} → id, nv, ut
   *   <li>{@code ktwo} → wa, id, mt
   *   <li>{@code kthree} → or, nv, ca
   *   <li>{@code kfour} → nv, ut
   *   <li>{@code kfive} → ca, az
   * </ul>
   *
   * Expected output: {@code [ktwo, kone, kthree, kfive]}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String... args) {
    var statesNeeded = new HashSet<>(Arrays.asList("mt", "wa", "or", "id", "nv", "ut", "ca", "az"));
    var stations = new LinkedHashMap<String, Set<String>>();

    stations.put("kone", new HashSet<>(Arrays.asList("id", "nv", "ut")));
    stations.put("ktwo", new HashSet<>(Arrays.asList("wa", "id", "mt")));
    stations.put("kthree", new HashSet<>(Arrays.asList("or", "nv", "ca")));
    stations.put("kfour", new HashSet<>(Arrays.asList("nv", "ut")));
    stations.put("kfive", new HashSet<>(Arrays.asList("ca", "az")));

    var finalStations = new HashSet<String>();

    // Keep picking stations until all states are covered
    while (!statesNeeded.isEmpty()) {
      String bestStation = null;
      var statesCovered = new HashSet<String>();

      // Find the station that covers the most uncovered states
      for (var station : stations.entrySet()) {
        var covered = new HashSet<>(statesNeeded);
        covered.retainAll(station.getValue());

        if (covered.size() > statesCovered.size()) {
          bestStation = station.getKey();
          statesCovered = covered;
        }
      }

      // Remove the newly covered states from the remaining set
      statesNeeded.removeIf(statesCovered::contains);

      if (bestStation != null) {
        finalStations.add(bestStation);
      }
    }
    System.out.println(finalStations); // [ktwo, kone, kthree, kfive]
  }
}
