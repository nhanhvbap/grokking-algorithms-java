package io.github.nhanhv.hashtables;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates the use of a hash table to prevent duplicate voting.
 *
 * <p>A hash table provides O(1) average-time lookups, making it ideal for tracking whether a person
 * has already voted. On each check, the name is looked up in the table: if found, the person has
 * already voted; if not, they are recorded as a new voter.
 *
 * <p>Time complexity: O(1) average — for both lookup and insertion in the hash table.
 *
 * <p>Space complexity: O(n) — where n is the number of unique voters recorded.
 */
public class CheckVoter {

  /** Hash table that maps a voter's name to {@code true} if they have already voted. */
  private static Map<String, Boolean> voted = new HashMap<>();

  /**
   * Checks whether a person has already voted and processes them accordingly.
   *
   * <p>If the person's name is already in the {@code voted} table, they are flagged as a duplicate
   * voter. Otherwise, they are added to the table and allowed to vote.
   *
   * @param name the name of the person attempting to vote
   */
  private static void checkVoter(String name) {
    if (voted.containsKey(name)) {
      System.out.println(name + ": kick them out!");
    } else {
      voted.put(name, true);
      System.out.println(name + ": let them vote!");
    }
  }

  /**
   * Entry point demonstrating the duplicate-voter check with sample names.
   *
   * <p>Expected output:
   *
   * <pre>
   *   let them vote!
   *   let them vote!
   *   kick them out!
   * </pre>
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    checkVoter("tom"); // let them vote!
    checkVoter("mike"); // let them vote!
    checkVoter("mike"); // kick them out!
  }
}
