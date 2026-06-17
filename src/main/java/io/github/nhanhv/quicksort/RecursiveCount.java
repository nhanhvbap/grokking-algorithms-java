package io.github.nhanhv.quicksort;

import java.util.Arrays;

/**
 * Demonstrates counting the number of elements in an array using recursion.
 *
 * <p>This is a simple example of applying divide-and-conquer thinking to a trivial problem —
 * counting — in order to illustrate how recursive algorithms work before tackling more complex ones
 * like quicksort.
 *
 * <p>Time complexity: O(n) — one recursive call per element.
 *
 * <p>Space complexity: O(n) — call stack depth equals the number of elements. Additionally, {@link
 * Arrays#copyOfRange} allocates a new array at each level, making the total memory usage O(n²) for
 * all intermediate arrays.
 */
public class RecursiveCount {

  /**
   * Recursively counts the number of elements in the given array.
   *
   * <p><strong>Base case:</strong> An empty array has zero elements.
   *
   * <p><strong>Recursive case:</strong> The count equals 1 (for the first element) plus the count
   * of the remaining sub-array.
   *
   * @param list the integer array to count
   * @return the total number of elements in {@code list}
   */
  private static int count(int[] list) {
    if (list.length == 0) {
      return 0;
    }

    return 1 + count(Arrays.copyOfRange(list, 1, list.length));
  }

  /**
   * Entry point demonstrating the recursive count on a sample array.
   *
   * <p>Expected output: {@code 6}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println(count(new int[] {0, 1, 2, 3, 4, 5})); // 6
  }
}
