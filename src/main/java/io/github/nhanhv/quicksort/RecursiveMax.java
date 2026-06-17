package io.github.nhanhv.quicksort;

import java.util.Arrays;

/**
 * Demonstrates finding the maximum element in an array using recursion.
 *
 * <p>This illustrates divide-and-conquer thinking: the maximum of an array is the larger of the
 * first element and the maximum of the rest of the array.
 *
 * <p>Time complexity: O(n) — one recursive call per element after the first.
 *
 * <p>Space complexity: O(n) — call stack depth equals the number of recursive calls. Additionally,
 * {@link Arrays#copyOfRange} allocates a new array at each level, making the total memory usage
 * O(n²) for all intermediate arrays.
 */
public class RecursiveMax {

  /**
   * Recursively finds the maximum value in the given array.
   *
   * <p><strong>Base case:</strong> A two-element array returns the larger of the two values.
   *
   * <p><strong>Recursive case:</strong> The maximum is the larger of the first element and the
   * maximum of the remaining sub-array.
   *
   * @param list the integer array to search; must contain at least two elements
   * @return the maximum integer value found in {@code list}
   */
  private static int max(int[] list) {
    if (list.length == 2) {
      return list[0] > list[1] ? list[0] : list[1];
    }

    int subMax = max(Arrays.copyOfRange(list, 1, list.length));
    return list[0] > subMax ? list[0] : subMax;
  }

  /**
   * Entry point demonstrating the recursive maximum search on a sample array.
   *
   * <p>Expected output: {@code 25}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println(max(new int[] {1, 5, 10, 25, 16, 1})); // 25
  }
}
