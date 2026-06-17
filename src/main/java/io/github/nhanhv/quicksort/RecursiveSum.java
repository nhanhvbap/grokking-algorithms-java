package io.github.nhanhv.quicksort;

import java.util.Arrays;

/**
 * Demonstrates computing the sum of an integer array using recursion.
 *
 * <p>This is a foundational divide-and-conquer example: the sum of an array equals the first
 * element plus the sum of the rest of the array. Compare with {@link LoopSum} which solves the same
 * problem iteratively.
 *
 * <p>Time complexity: O(n) — one recursive call per element.
 *
 * <p>Space complexity: O(n) — call stack depth equals the number of elements. Additionally, {@link
 * Arrays#copyOfRange} allocates a new array at each level, making the total memory usage O(n²) for
 * all intermediate arrays.
 */
public class RecursiveSum {

  /**
   * Recursively computes the sum of all elements in the given array.
   *
   * <p><strong>Base case:</strong> An empty array has a sum of {@code 0}.
   *
   * <p><strong>Recursive case:</strong> The sum equals the first element plus the sum of the
   * remaining sub-array.
   *
   * @param arr the integer array to sum
   * @return the total sum of all elements in {@code arr}, or {@code 0} if the array is empty
   */
  private static int sum(int[] arr) {
    if (arr.length == 0) {
      return 0;
    } else {
      return arr[0] + sum(Arrays.copyOfRange(arr, 1, arr.length));
    }
  }

  /**
   * Entry point demonstrating the recursive sum on a sample array.
   *
   * <p>Expected output: {@code 10}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println(sum(new int[] {1, 2, 3, 4})); // 10
  }
}
