package io.github.nhanhv.hashtables;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates the use of a hash table to store and look up grocery prices.
 *
 * <p>A hash table maps item names (String keys) to their prices (Double values), providing O(1)
 * average-time lookup, insertion, and deletion. This is a common use case for hash tables —
 * building a simple in-memory key/value store (like a price catalogue).
 *
 * <p>Time complexity: O(1) average — for each {@code put} and {@code get} operation.
 *
 * <p>Space complexity: O(n) — where n is the number of items in the map.
 */
public class PriceOfGroceries {

  /**
   * Entry point that creates a grocery price book and prints all entries.
   *
   * <p>Expected output (order may vary due to HashMap's lack of ordering):
   *
   * <pre>
   *   {apple=0.67, avocado=1.49, milk=1.49}
   * </pre>
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Map<String, Double> book = new HashMap<>();

    // an apple costs 67 cents
    book.put("apple", 0.67);
    // milk costs $1.49
    book.put("milk", 1.49);
    book.put("avocado", 1.49);

    System.out.println(book); // {apple=0.67, avocado=1.49, milk=1.49}
  }
}
